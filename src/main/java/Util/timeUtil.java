package Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class timeUtil {

    public static String getTime(){
        Date date =new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

}
