package com.spring15.sprinter.technion.technionsprinter.Models;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Date;

@ParseClassName("Messages")
public class Message extends ParseObject {

    public Message(){
        super();
    }
    public Message(String body, Group group){
        super();
        put("body", body);
        put("group", group);
        put("sender", ParseUser.getCurrentUser());
    }

    public String getBody(){ return getString("body"); }
    public ParseUser getSender(){ return getParseUser("sender"); }
    public Group getGroup()  {
        return (Group) getParseObject("group");
    }

}
