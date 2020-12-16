package com.myutil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Generatetime {
    public static String gettime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date t=new Date();
        return df.format(t);
    }
}
