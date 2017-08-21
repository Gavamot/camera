package OpenCV_cam;

import SqlLite.Config;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChannelWriter extends Thread{

    
    
    private class NativeObject{
        
        public NativeObject(){}
        public NativeObject(List<ChannelInfo> info, List<ChannelValue> values, Position position, Device device){   
            this.info = info;
            this.values = values;
            this.position = position;
            this.device = device;
        }
        
        public List<ChannelInfo> info;
        public List<ChannelValue> values;
        public Position position;
        public Device device;
    }
    
    private class Position {
        public String Cluster;
        public String Field;
        public int Rig;
        public String Well;
        public int Work; 
    }
    
    private class Device {
        public int Model;
        public int Number;
        public int Release;
        public int Serial;
    }
    
    private class ChannelInfo{
        public int ID;
        public int Num;
        public int Point;
        public String Name;
        public int LimL;
        public int LimH;
        public int AvarL; 
        public int AvarH;   
    }
    
    private class ChannelValue
    {
        public ChannelValue(){ }
        
        public ChannelValue(String data, long lastReq, long now){
            date = now;
            if(checkDisconect(now, lastReq)){
                this.data = new String[0];
            }else{
                data = data.replace("[", "");
                data = data.replace("]", "");
                this.data = data.split(",");
            }
        }
        
        public long date;
        public String[] data;
    }
    
    public ChannelWriter(){
        super("Поток чтения каналов");
        channelsValues = new ArrayList<>();
        values = new ArrayList<>();
        Config dev = new Config();
        redis = new RedisHealper(dev.redis_ip, dev.redis_port);
    }
    
    private boolean checkDisconect(long now, long lastReq){
        return now - lastReq > DISCONNECT_TIME_MILISECOND;
    }
    
    private ArrayList<ChannelValue> channelsValues = new ArrayList<>();
    private Date startDate = null;
    private HashMap<String, Object> jsonObject;
    private int DISCONNECT_TIME_MILISECOND = 5000;
    private int WRITE_TIME_MILISECOND = 10000;
    private RedisHealper redis;
    
    public ArrayList<RedisChannel> getRedisChannels(){    
        String jsonStr = redis.getRedisDataByKey(redis.GET_CHANNELS_INFO);
        JsonParser parser = new JsonParser();
        JsonArray mainObject = parser.parse(jsonStr).getAsJsonArray();
        Type listType = new TypeToken<ArrayList<RedisChannel>>(){}.getType();
        ArrayList<RedisChannel> redisChannels = new Gson().fromJson(mainObject, listType);
        
        return redisChannels;
    }
    
    
    public ArrayList<Channel> getChannels(){

        ArrayList<Channel> channels = new ArrayList<>();

        try{
            ArrayList<RedisChannel> redisChannels = getRedisChannels();
            
            int nn = 0;
            
            for(RedisChannel rc : redisChannels){
                if(rc == null) continue;
                
                Channel c = new Channel();
                c.id = rc.ID;
                String[] strArr = rc.Name.trim().split(",");
                if(strArr.length > 0)
                    c.name =  strArr[0];
                
                if(strArr.length > 1){
                    c.axis_label = strArr[1];
//                    for(int i = 2; i < strArr.length; i++){
//                        c.axis_label += ", " + strArr[i];
//                    }
                }
                
                c.nn = nn++;
                
                double value = getValue(c.nn);   
                c.minValue = value < rc.LimL ? value : rc.LimL;
                c.maxValue = value > rc.LimH ? value : rc.LimH; 
                channels.add(c);
            }

         }catch(Exception e){ 
             
             //logger.error("Не удалось получить значения каналов из редиса(3). (" + e.getMessage()  + ")" );
            
         }
          
        return channels;     
    }
    
    private ArrayList<Double> values = new ArrayList<>();
    
    public double getValue(int nn){
        if(values.size() > nn && 0 <= nn){
            return values.get(nn);
        }
        return Double.NaN;
    }
    
    @Override
    public void run()
    {
        //logger.info(this.getName() + " запущен.");
        JsonParser parser = new JsonParser();
        Gson gson = new Gson();
        while(!this.isInterrupted()){
            try{
                // Получаем дату последней записи в REDIS 
                String jsonDate = redis.getRedisDataByKey(redis.GET_LAST_WRITE_TIME);
                Date lastReq = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jsonDate);
                
                // Проверяем актуальность данных полученных из REDIS
                long nowTime = new Date().getTime();
                long lastReqTime = lastReq.getTime();
                boolean isDisconect = checkDisconect(nowTime, lastReqTime);
                
                String jsonStr = redis.getRedisDataByKey(redis.GET_CHANNELS_VALUES);
                JsonArray mainObject = parser.parse(jsonStr).getAsJsonArray();
              
                // Запись данных полученных из редиса
                ArrayList<Double> list = new ArrayList<>();
                for(JsonElement val : mainObject){
                   try{
                       if(isDisconect){
                           list.add(Double.NaN);
                        }else{
                           if("NaN".equals(val.getAsString())) {
                                list.add(Double.NaN);
                            } else {
                               list.add(val.getAsDouble());
                            }
                        }
                   }catch(Exception e)
                   {
                       e.printStackTrace();
                       list.add(Double.NaN);
                   } 
                }
                values.clear();
                values.addAll(list);
                

                String valStr  = redis.getRedisDataByKey(redis.GET_CHANNELS_VALUES_NATIVE);
                ChannelValue channelValue = new ChannelValue(valStr, lastReqTime, nowTime);
                
                if(startDate == null){
                    startDate = new Date();
                    jsonObject = new HashMap<>();
                    jsonObject.put("info",  getRedisChannels());//getRedisDataByKey(REDIS_GET_CHANNELS_INFO));
                    channelsValues = new ArrayList<>();
                    channelsValues.add(channelValue);
                }else{
                    channelsValues.add(channelValue);
                    if(startDate.getTime() + WRITE_TIME_MILISECOND <= new Date().getTime())
                    {
                        startDate = null;
                        FileName f = new FileName("./" , "channels", FileName.EXT_JSON);
     
                        String infoJson = redis.getRedisDataByKey(RedisHealper.GET_CHANNELS_INFO_NATIVE);
                        List<ChannelInfo> info = RedisHealper.<ChannelInfo>parceListFromJson(infoJson);
                        
                        String positionJson = redis.getRedisDataByKey(RedisHealper.GET_BRIGADE_INFO);
                        //Position position = RedisHealper.<Position>parceFromJson(positionJson);
                        
                        JsonObject o = parser.parse(positionJson).getAsJsonObject();
                        Type type = new TypeToken<Position>(){}.getType();
                        Position position = gson.fromJson(o, type);
                        
                        
                        String deviceJson = redis.getRedisDataByKey(RedisHealper.GET_DEVISE_INFO);
                        o = parser.parse(deviceJson).getAsJsonObject();
                        type = new TypeToken<Device>(){}.getType();
                        Device device = gson.fromJson(o, type);

                        Object obj = new NativeObject(info, channelsValues, position, device);
                        String writeObj = gson.toJson(obj);
                        
                        try(FileWriter writer = new FileWriter(f.getFileName()))
                        {
                            writer.write(writeObj);
                            writer.flush();
                        }
                        catch(IOException e){
                            e.printStackTrace();
                            //logger.error("Не удалось получить значения каналов из редиса(1). (" + e.getMessage()  + ")" );
                        } 
                    }
                }

            }catch(Exception e){ 
                //logger.error("Не удалось получить значения каналов из редиса(2). (" + e.getMessage()  + ")" );
                values.clear();
            }
            
            try { this.sleep(990); } catch (InterruptedException ex) { }
        }
        //logger.info(this.getName() + " завершен.");
       
    }
    
}
