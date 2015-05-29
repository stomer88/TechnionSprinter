package com.spring15.sprinter.technion.technionsprinter.Repositories;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.spring15.sprinter.technion.technionsprinter.Models.UserDetails;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class UserRepository {

    public static void addUserDetails(UserDetails newUserDetails)
    {
        newUserDetails.saveInBackground();
    }
}
