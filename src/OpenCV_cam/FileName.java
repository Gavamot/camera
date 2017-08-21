package OpenCV_cam;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Locale;


public class FileName {
    
    public final static String EXT_MP4 = ".mp4";
    public final static String EXT_JSON = ".json";
    
    public String folder;
    public String subFolder;
    public String ext;

    public FileName(String folder, String subFolder, String ext){
        this.folder = folder;
        this.subFolder = subFolder;
        this.ext = ext;
    }

    
    private boolean isNullOrEmpty(String str){
        return str == null || str.equals("");
    }
    private File generateSubFolder(){
          ZonedDateTime date = ZonedDateTime.now();
          String sub = isNullOrEmpty(subFolder) ? "" : subFolder + "/";
          return new File(this.folder + "/" + sub + (date.getYear()) +
                "/" + date.getMonthValue() + "/" + date.getDayOfMonth()+ "/" + date.getHour());
    }
    public String getFileName(){
 
        File subFolder = generateSubFolder();
        if (!subFolder.exists())
        {
            subFolder.mkdirs();
        }
              
        Date now = new Date(); // java.util.Date, NOT java.sql.Date or java.sql.Timestamp!
        String format1 = new SimpleDateFormat("yyyy.MM.dd'T'HH.mm.ss", Locale.ENGLISH).format(now);
        return subFolder + "/" + format1 + ext;
    }
    
    public static Date parse(String str, String ext) throws ParseException{
        //str = str.replaceAll(ext, "");
        String[] arr = str.split("_", 1);
        return new SimpleDateFormat("yyyy.MM.dd'T'HH.mm.ss", Locale.ENGLISH).parse(arr[0]);
    }
}
