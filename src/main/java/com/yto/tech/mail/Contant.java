package com.yto.tech.mail;

public class Contant {

    public static final String CAT_SERVER_KEY = "catip";
    public static final String CAT_ENV = "catenv";

    public static final String CAT_SERVER_KEY_PROVIOUS = "cat-web-server";


    public static String alertContentAddCatEnv(String content){
        String serverIp = System.getProperty(Contant.CAT_SERVER_KEY);
        if(serverIp != null && !"".equals(serverIp.trim()) && !"null".equals(serverIp.trim())) {
            content = content.replaceFirst(Contant.CAT_SERVER_KEY_PROVIOUS, serverIp);
        }else{
            serverIp ="";
        }
        String catEnv = System.getProperty(Contant.CAT_ENV);
        if(catEnv == null){
            catEnv = "";
        }
        content = content + "<br>" +catEnv +"["+serverIp+"]";
        return content;
    }

    public static void main(String[] args) {
//        System.setProperty("catenv","DEV");
//        System.setProperty("catip","192.168.1.2");
        System.out.println(alertContentAddCatEnv("dsa"));
    }
}
