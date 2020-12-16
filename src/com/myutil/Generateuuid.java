package com.myutil;

import java.util.UUID;

public class Generateuuid {
    public static String getuuid(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
