package OpenCV_cam;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Keybord extends Thread 
{
    //Logger logger = LogManager.getLogger(getClass().getName());
    public Keybord(){
        super("Keybord");
        init();
    }

    public SerialPort serialPort;
    public String portName = "ttyUSBKPD";
    public int clickLag = 290;
    public int polling = 24;
    public int responceCounter = 0;
    
    //private volatile byte statusCode = 0X00;

    public void init(){
        SqlLite.Keybord s;
        try {
            s = SqlLite.Keybord.get();
            portName = s.port;
            polling = 24;
            clickLag = 300;
        } catch (Exception ex) { } 
        
    }

    private PortReader portReader;
    
    @Override
    public void run()
    {
        while(!this.isInterrupted())
        {
            try 
            {
                init();
                responceCounter = 0;
                serialPort = new SerialPort(portName);

                serialPort.openPort();
                serialPort.setParams(SerialPort.BAUDRATE_9600,
                        SerialPort.DATABITS_8,
                        SerialPort.STOPBITS_1,
                        SerialPort.PARITY_NONE);
                
                portReader = new PortReader(this);
             
                serialPort.addEventListener(portReader, SerialPort.MASK_RXCHAR);

                //logger.info("Клавиатура запущенна на порте " + serialPort.getPortName());
                while(!this.isInterrupted())
                {    
                    
                    // bSend[1] - 00 - не горит
                    // bSend[1] - 01 - зеленый
                    // bSend[1] - 10 - красный
                    // bSend[1] - 11 - оранжевый
                    
                    byte[] bSend = new byte[4];
                    bSend[0] = (byte) 0xC0;
                    bSend[1] = (byte) 0X00; //statusCode;
                    bSend[2] = (byte) 0X00; //((byte) 0x00 - statusCode);
                    bSend[3] = (byte) 0xC1; 
                    
                    serialPort.purgePort(SerialPort.PURGE_RXCLEAR);
                    serialPort.purgePort(SerialPort.PURGE_TXCLEAR);
                    
                    serialPort.writeBytes(bSend);

                    if(responceCounter++ > 40)
                        throw new Exception(portName + " не отвечает.");
                    Keybord.sleep(polling);
                }
            } 
            catch (Exception ex)
            {
                if(serialPort != null){
                    close();
                }

                try{ this.sleep(2000);}catch(Exception e){}
            }
        }
        
       close();
        
    }

    public void close(){
        if(serialPort != null){
            try{
                serialPort.purgePort(SerialPort.PURGE_RXCLEAR);
                serialPort.purgePort(SerialPort.PURGE_TXCLEAR);
                serialPort.removeEventListener();
                serialPort.closePort();
            }catch(Exception e){
                
            }
        }
    }
        
    
    private class PortReader implements SerialPortEventListener
    {
        private Keybord keyBord;

        private final byte SF     = (byte)0xC0; 
        private final byte EF     = (byte)0xC1;    
        private final byte T      = (byte)0xDB;    
        private final byte T_0xC0 = (byte)0xDC;
        private final byte T_0xDB = (byte)0xDD;
        private final byte T_0xC1 = (byte)0xDE;

        private volatile int exitCount = 0; 
        private final int COUNT_FOR_EXIT = 20;
        
        public PortReader(Keybord keyBord)
        {
            this.keyBord = keyBord;
        }
        
        private boolean checkFrame(byte[] out)
        {
            boolean isEmpty = true;
            if(out[7] != EF)
                return false;
            
            byte crc = (byte) (0 - out[1] - out[2] - out[3] - out[4] - out[5]);
            
            if(crc != out[6])
                return false;
            
            return isEmpty;     
        }
        
        private int byteToUint(byte val)
        {
            int res = val & 0xFF;
            return res;         
        }

        private byte[] parseFrame() throws SerialPortException
        {
            byte[] bRes = new byte[8];
      
            while(bRes[0] != SF)
            {
               byte b1 = serialPort.readBytes(1)[0];
               if(b1 == SF) 
               {
                    bRes[0] = b1;
                    break;
               }
            }

            int i = 0;
            while(++i < 8)
            {
               byte b1 = serialPort.readBytes(1)[0];
               if(b1 == T)
               {
                   b1 = serialPort.readBytes(1)[0];
                   if(b1 == T_0xC0) bRes[i] = (byte)0xC0;
                   if(b1 == T_0xDB) bRes[i] = (byte)0xDB;
                   if(b1 == T_0xC1) bRes[i] = (byte)0xC1;
               }
               else
               {    
                   bRes[i] = b1;
               }
            }
            return bRes; 
        }
        
        @Override
        public void serialEvent(SerialPortEvent event)
        {
            if (event.isRXCHAR() && event.getEventValue() > 0) 
            {
                try 
                {
                    byte[] bRes = parseFrame();
                  
                    if(checkFrame(bRes)){
                        
                        int b0 = byteToUint(bRes[2]);
                        int b1 = byteToUint(bRes[3]) << 8;
                        int b2 = byteToUint(bRes[4]) << 16;
                        
                        int cmd = b0+ b1 + b2;

                        // Прибор выключен
                        if( (bRes[5]&1) == 0){ 
                            exitCount++;
                            if(exitCount >= COUNT_FOR_EXIT){
                                cmd = -1;
                                exitCount = 0;
                            }
                        }else{
                            exitCount = 0;
                        }
                        keyBord.responceCounter = 0;
                         
                        if(cmd == 0) 
                            return;
                        
                        System.err.println(cmd);
                        Manager.Signal(cmd);
                  
                        if(cmd == -1){
                            keyBord.interrupt();
                        }
                        
                        try{ keyBord.sleep(keyBord.clickLag); } catch(InterruptedException e){}
                    }
                } 
                catch (SerialPortException ex) 
                {
                    //System.out.println(ex);
                } 
            }
        }
    }
}
