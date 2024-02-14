package com.example;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Config implements Configurable{

    private Themes theme;
    private int difficulty = 0;
    private boolean sound;


    public Config(Themes theme, int difficulty, boolean sound) {
        this.theme = theme;
        this.difficulty = difficulty;
        this.sound = sound;
    }

    public Config(){
        //attemt to load the config from file
        loadConfig();

        //if the config is still null, set the default values
        if(this.theme == null){
            this.theme = Themes.LIGHT;
            this.difficulty = 1;
            this.sound = true;
        }
        
    }


    @Override
    public void changeConfig(SettingField setting, Serializable value) {
        switch (setting) {
            case THEME:
                // change the theme
                this.theme = (Themes) value;
                break;
            case DIFFICULTY:
                // change the difficulty
                this.difficulty = (int) value;
                break;
            case SOUND:
                // toggle the sound
                this.sound = (boolean) value;
                break;
            default:
                break;
        }
    }

    @Override
    // return the config as a list
    public Map<SettingField, Serializable> getConfig() {
        Map<SettingField, Serializable> config = new HashMap<>();
        config.put(SettingField.THEME, this.theme);
        config.put(SettingField.DIFFICULTY, this.difficulty);
        config.put(SettingField.SOUND, this.sound);

        return config;
    }

    @Override
    public void setConfig(Map<SettingField, Serializable> config) {
        for(SettingField setting : config.keySet()){
            changeConfig(setting, config.get(setting));
        }
    }

    public Themes getTheme() {
        return theme;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public boolean isSound() {
        return sound;
    }

}
