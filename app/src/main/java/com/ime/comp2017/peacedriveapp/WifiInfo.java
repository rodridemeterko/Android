package com.ime.comp2017.peacedriveapp;

/**
 * Created by demeterko on 16/05/2016.
 */
public class WifiInfo {
    private String SSID;
    private int level;
    private int x;
    private int y;
    public WifiInfo(String SSID,int level,int x,int y){
        this.SSID = SSID;
        this.level = level;
        this.x = x;
        this.y = y;
    }

    public void setSSID(String SSID){
        this.SSID = SSID;
    }
    public String getSSID(){
        return SSID;
    }
    public void setLevel(int level){
        this.level = level;
    }
    public int getLevel(){
        return level;
    }
    public void setX(int x){
        this.x = x;
    }
    public int getX(){
        return x;
    }
    public void setY(int y){
        this.y = y;
    }
    public int getY(){
        return y;
    }
}
