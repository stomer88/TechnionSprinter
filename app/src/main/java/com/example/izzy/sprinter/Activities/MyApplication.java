package com.example.izzy.sprinter.Activities;

import android.app.Application;

import com.example.izzy.sprinter.Utilities.Message;
import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by izzy on 23/05/15.
 */
public class MyApplication extends Application {
//    public UUID myUniqueID;

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        // Register your parse models here
        ParseObject.registerSubclass(Message.class);

        Parse.initialize(this, "s6P6Tr0mV0yOYHWPk5i717woxmuuJ41HOF61Xfit", "EnJ45cDbrG7TEWBQbhGv5eeUTYFpsyHoZdR8JvMC");
//        GenerateUniqueID uniqueID = new GenerateUniqueID();
//        myUniqueID = uniqueID.getUniqueID();
//        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context)


    }
}
