package com.spring15.sprinter.technion.technionsprinter.Activities;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;
import com.spring15.sprinter.technion.technionsprinter.Models.Category;
import com.spring15.sprinter.technion.technionsprinter.Models.UserCategory;
import com.spring15.sprinter.technion.technionsprinter.Models.UserDetails;

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        // Register your parse models
        ParseObject.registerSubclass(UserDetails.class);
        ParseObject.registerSubclass(Category.class);
        ParseObject.registerSubclass(UserCategory.class);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "A9SvXc9q00No0mkJyFFIJDBAexvxqi24m8h6ZLge", "x8yxKJkpaQioDbL5wq7w0YV5Q9Om1WunnuNB59H3");
        FacebookSdk.sdkInitialize(getApplicationContext());
        ParseFacebookUtils.initialize(getApplicationContext());
    }
}
