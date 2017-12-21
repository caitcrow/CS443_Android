package com.example.hihi_.soundboard_cs443;

public class SoundObject{
    private String itemName;
    private Integer itemID;

    public SoundObject(String itemName, Integer itemID){
        this.itemName = itemName;
        this.itemID = itemID;

    }
    public String getItemName(){
        return itemName;
    }
    public Integer getItemID(){
        return itemID;
    }
}