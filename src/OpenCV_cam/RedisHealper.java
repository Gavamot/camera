package OpenCV_cam;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
//import javafx.scene.input.KeyCode.T;
import redis.clients.jedis.Jedis;

public class RedisHealper {
    
    public final static String GET_CHANNELS_INFO = "IVE0D";
    public final static String GET_CHANNELS_VALUES = "IVE07";
    public final static String GET_BRIGADE_INFO = "IVE08";
    public final static String GET_LAST_WRITE_TIME = "IVEwrite";
    public final static String GET_CHANNELS_VALUES_NATIVE = "IVE07_NATIVE";
    public final static String GET_CHANNELS_INFO_NATIVE = "IVE0D_NATIVE";
    public final static String GET_DEVISE_INFO = "IVEserial";

    private final Jedis redis;

    public RedisHealper(String redis_ip, int redis_port){
        this.redisIp = redis_ip;
        this.redisPort = redis_port;
        redis = new Jedis(redisIp, redisPort);
    }

    public String getRedisDataByKey(String key){
        return redis.get(key);
    }
    
    public static <T> ArrayList<T> parceListFromJson(String json)
    {
        JsonParser parser = new JsonParser();
        JsonArray obj = parser.parse(json).getAsJsonArray();
        Type type = new TypeToken<ArrayList<T>>(){}.getType();
        return new Gson().fromJson(obj, type);
    }

    private String redisIp;
    private int redisPort;
}
