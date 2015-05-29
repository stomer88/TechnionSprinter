package com.spring15.sprinter.technion.technionsprinter.Models;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Date;

@ParseClassName("Groups")
public class Group extends ParseObject {

    public Group(){
        super();
    }
    public Group(String title, String location, int maxSize, Date date, int level, Category category){
        super();
        put("title", title);
        put("location", location);
        put("size", 1);
        put("maxSize", maxSize);
        put("time", date);
        put("level", level);
        put("creator", ParseUser.getCurrentUser());
        put("category", category);
    }

    public String getTitle(){
        return getString("title");
    }
    public String getLocation(){return getString("location");}
    public int getMaxSize(){return getInt("maxSize");}
    public int getSize(){return getInt("size");}
    public Date getTime(){ return getDate("time"); }
    public int getLevel(){
        return getInt("level");
    }
    public ParseUser getUser(){ return getParseUser("creator"); }
    public Category getCategory()  {
        return (Category) getParseObject("category");
    }

}
