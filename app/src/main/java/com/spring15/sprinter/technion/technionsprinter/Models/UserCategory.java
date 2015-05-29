package com.spring15.sprinter.technion.technionsprinter.Models;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("UserCategories")
public class UserCategory extends ParseObject{

    public UserCategory(){
        super();
    }

    public UserCategory(Category category){
        super();
        setUser(ParseUser.getCurrentUser());
        setCategory(category);
    }

    public ParseUser getUser()  {
        return getParseUser("user");
    }

    public void setUser(ParseUser user) {
        put("user", user);
    }

    public Category getCategory()  {
        return (Category) getParseObject("category");
    }

    public void setCategory(Category category) {
        put("category", category);
    }
}
