package com.example.izzy.sprinter.Utilities;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.example.izzy.sprinter.Activities.MainActivity;
import com.example.izzy.sprinter.data.Constants;
import com.example.izzy.sprinter.data.PrivateDetails;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by izzy on 22/05/15.
 */
public class ParserUtilities {

    public ParserUtilities(){}

    public void addNewUser(Long facebookId, String firstName, String lastName, String email, String link, Boolean[] sportsArray, String userKey, String facebookObjectId)
    {
        // TODO: remove this and make sure sportsArray is good -----------
        Boolean[] isCheckedArray = new Boolean[Constants.SPORTS_NUMBER];
        isCheckedArray[0] = true;
        isCheckedArray[1] = true;
        isCheckedArray[2] = false;
        isCheckedArray[3] = true;
        isCheckedArray[4] = true;
        isCheckedArray[5] = true;
        sportsArray = isCheckedArray;
        // TODO: ---------------------------------------------------------

        ParseObject newUser = new ParseObject("AllUsers");

        newUser.put("facebookId", facebookId);
        newUser.put("firstName", firstName);
        newUser.put("lastName", lastName);
        newUser.put("email", email);
        newUser.put("link", link);
        newUser.put("userId", userKey);
        newUser.put("facebookObjectId", facebookObjectId);

        for (int i = 0; i < Constants.SPORTS_NUMBER; i++)
        {
            newUser.put(Constants.STRING_TYPES__TABLES_ARRAY[i], sportsArray[i] == true);
        }
        newUser.saveInBackground();
    }

    public Boolean isUserIdExistInAllUsers(String userId)
    {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("AllUsers");
        query.whereEqualTo("userID", userId);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> categoryAttributes, ParseException e) {
                if (e == null) {


                    }

                else {




//                    Log.d("DATA", "Error: " + e.getMessage());
                    // Alert.alertOneBtn(getActivity(),"Something went wrong!");
                }
            }
        });
        return false;
    }




    public void show(PrivateDetails privateDetails)
    {
        String id =  privateDetails.getUniqueKey();
//        final String nameToPut = "ddddd";
        final PrivateDetails privateDetails1 = new PrivateDetails(privateDetails.getUsername(),
                privateDetails.getPassword(),
                privateDetails.getEmail(),
                privateDetails.getFirstName(),
                privateDetails.getLastName()
                ,privateDetails.getIsSportChosenArray(),
                privateDetails.getUniqueKey());
        ParseQuery<ParseObject> query = ParseQuery.getQuery("AllUsers");
        query.whereEqualTo("userID", id);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> categoryAttributes, ParseException e) {
                if (e == null) {
                    for (ParseObject ob: categoryAttributes){
                        Log.d("DATA", "ObjectID: " + ob.getObjectId());
                        Log.d("DATA", "Column1: " + ob.getString("username"));

                        ob.put("username",privateDetails1.getUsername());
                        ob.put("password", privateDetails1.getPassword());
                        ob.put("email", privateDetails1.getEmail());
                        ob.put("firstName", privateDetails1.getFirstName());
                        ob.put("lastName", privateDetails1.getLastName());
//                        ob.put("userID",privateDetails1.getUniqueKey());
                        ob.saveInBackground();

                    }
                }
                else {

                    ParseObject testObject1 = new ParseObject("AllUsers");


                    testObject1.put("username", privateDetails1.getUsername());
                    testObject1.put("password", privateDetails1.getPassword());
                    testObject1.put("email", privateDetails1.getEmail());
                    testObject1.put("firstName", privateDetails1.getFirstName());
                    testObject1.put("lastName", privateDetails1.getLastName());
                    testObject1.put("userID",privateDetails1.getUniqueKey());

                    for (int i = 0; i < Constants.SPORTS_NUMBER;i++)
                    {
                        testObject1.put(Constants.STRING_TYPES__TABLES_ARRAY[i],privateDetails1.getIsSportChosenArray()[i] == true);
                    }
                    testObject1.saveInBackground();
//                    Log.d("DATA", "Error: " + e.getMessage());
                    // Alert.alertOneBtn(getActivity(),"Something went wrong!");
                }
            }
        });
    }
//
//    public Boolean isPrivateDetailsExists(PrivateDetails privateDetails)
//    {
//
//
//
//        return false;
//    }

    public void uploadObjectToParseAllUserTable(PrivateDetails privateDetails)
    {
        Boolean isExitsInTable = false;

         ParseObject testObject1 = new ParseObject("AllUsers");


        testObject1.put("username", privateDetails.getUsername());
        testObject1.put("password", privateDetails.getPassword());
        testObject1.put("email", privateDetails.getEmail());
        testObject1.put("firstName", privateDetails.getFirstName());
        testObject1.put("lastName", privateDetails.getLastName());
        testObject1.put("userID",privateDetails.getUniqueKey());

        for (int i = 0; i < Constants.SPORTS_NUMBER;i++)
        {
            testObject1.put(Constants.STRING_TYPES__TABLES_ARRAY[i],privateDetails.getIsSportChosenArray()[i] == true);
        }
        testObject1.saveInBackground();

    }
}

//
//TODO backup for later
//
//    public void show(PrivateDetails privateDetails)
//    {
//        String id =  privateDetails.getUniqueKey();
//        final String nameToPut = "ddddd";
//        ParseQuery<ParseObject> query = ParseQuery.getQuery("AllUsers");
//        query.whereEqualTo("userID", id);
//        query.findInBackground(new FindCallback<ParseObject>() {
//            @Override
//            public void done(List<ParseObject> categoryAttributes, ParseException e) {
//                if (e == null) {
//                    for (ParseObject ob: categoryAttributes){
//                        Log.d("DATA", "ObjectID: " + ob.getObjectId());
//                        Log.d("DATA", "Column1: " + ob.getString("username"));
//                        ob.put("username","aaaaaaaa");
//                        ob.saveInBackground();
//
//                    }
//                }