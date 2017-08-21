package OpenCV_cam;

import java.util.ArrayList;

public class Channel {

    public int id;
    public int num;
    public int res;
    
    public int point;
    
    public String name;
    public String axis_label;
    
    public int LimL, LimH;
    public int AvarL, AvarH;
    
    
    public transient double minValue; 
    public transient double maxValue;
    
    public transient int nn;
    
    public Channel()
    {
        
    }
    
    public static ArrayList<Channel> getAll() {
        return Manager.getChannelWriter().getChannels();
    }

    public static Channel getById(int id)  {
        ChannelWriter channelWriter = Manager.getChannelWriter();
        ArrayList<Channel> channels =  channelWriter.getChannels();
        for(Channel c :  channels)
            if(c.id == id)
                return c;
        return null;
    }
    
    public double getValue(){
        return Manager.getChannelWriter().getValue(nn);
    }

    @Override
    public String toString(){
        return this.name + "(" + this.id + ")";
    }


}
