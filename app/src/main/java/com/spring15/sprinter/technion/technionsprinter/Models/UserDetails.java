package com.spring15.sprinter.technion.technionsprinter.Models;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;
import com.parse.FindCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.spring15.sprinter.technion.technionsprinter.Repositories.UserRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
@ParseClassName("UserDetails")
public class UserDetails  extends ParseObject{

    public UserDetails(){
        super();
    }
    public UserDetails(JSONObject jsonObject)
    {
        super();
        try {
            put("facebookId", jsonObject.getLong("id"));
            put("firstName", jsonObject.getString("first_name"));
            put("lastName", jsonObject.getString("last_name"));
            put("email", jsonObject.getString("email") != null ? jsonObject.getString("email") : "");
            put("user", ParseUser.getCurrentUser());
        } catch (JSONException e) {
            Log.d("MyApp", "Error parsing returned user data. " + e);
        }
    }
    public long getFacebookId(){ return getLong("facebookId"); }
    public String getFirstName(){ return getString("firstName"); }
    public String getLastName(){ return getString("lastName"); }
    public String getEmail(){ return getString("email"); }
    public ParseUser getUser(){ return getParseUser("user"); }
}
