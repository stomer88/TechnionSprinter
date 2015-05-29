package com.spring15.sprinter.technion.technionsprinter.Models;

import android.util.Log;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

import org.json.JSONException;
import org.json.JSONObject;
@ParseClassName("Categories")
public class Category extends ParseObject {

    public Category(){
        super();
    }

    public String getName(){
        return getString("name");
    }

    public ParseFile getImage() {
        return getParseFile("img");
    }
}
