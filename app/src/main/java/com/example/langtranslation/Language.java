package com.example.langtranslation;

import androidx.annotation.NonNull;
import java.util.Locale;

public class Language {
    private String code;

    public Language(String code){
        this.code = code;
    }
    String getDisplayName(){
        return new Locale(code).getDisplayName();
    }
    public String getCode(){
        return this.code;
    }

    @NonNull

    public String toString(){
        return this.code + " - " + getDisplayName();

    }

    @Override
    public int hashCode() {
        return this.code.hashCode();
    }
}