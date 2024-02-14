package com.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;

public interface Configurable extends Serializable{

    public static final String CONFIG_FILE = "settings.bin";

    public void changeConfig(SettingField setting, Serializable value);
    public Map<SettingField, Serializable> getConfig();
    public void setConfig(Map<SettingField, Serializable> config);

    default void saveConfig() {
        // save the settings to a file
        //collect the settings into a map
        Map<SettingField, Serializable> config = getConfig();
        //save the map to a file
        
        try(FileOutputStream fileOut = new FileOutputStream(CONFIG_FILE)){
            //write the map to the file
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    default void loadConfig() {
        // load the settings from a file
        //read the map from the file
        try {
            //read the map from the file
            Map<SettingField, Serializable> config;
            try(FileInputStream fileIn = new FileInputStream(CONFIG_FILE)){
                ObjectInputStream in = new ObjectInputStream(fileIn);
                config = (Map<SettingField, Serializable>) in.readObject();
            }catch(Exception e){
                config = null;
            }
            if(config == null){
                return;
            }
            //set the current config
            setConfig(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
