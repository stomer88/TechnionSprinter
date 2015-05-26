package com.example.izzy.sprinter.data;

import android.content.Context;
import android.content.res.Resources;

import com.example.izzy.sprinter.Activities.HomeActivity;
import com.example.izzy.sprinter.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by izzy on 22/05/15.
 */
public class SportTypesData {




    private List<Sport> sportsList = new ArrayList<>();

    public List<Sport> getSportsList() {
        return sportsList;
    }

    private void addSport( Sport sport){
        sportsList.add(sport);
    }

    private String getStringResourceByName(String aString) {
//        Activity activity = new Activity();
//        activity = selectFoodActivity;
        String packageName = HomeActivity.PACKAGE_NAME;
        int resId = HomeActivity.CONTEXT.getResources().getIdentifier(aString, "string", packageName);
        return HomeActivity.CONTEXT.getResources().getString(resId);
    }


    private  String  getStringResourceById(int id){
        Context context = HomeActivity.CONTEXT;
        Resources res =  context.getResources();
        String tempString = res.getString(id);
        return tempString;
    }
    public SportTypesData() {
//        addSport(new Sport("Bicycle","paella"));
//        addSport(new Sport("Swimming","paella"));
//        addSport(new Sport("a1","paella"));
//        addSport(new Sport("a1","paella"));



//        Integer intName = new Integer(R.string.food_type1);
        String myResourceString1 = getStringResourceById(R.string.sport_type1);
        addSport(new Sport(myResourceString1, "bicycle3"));

        String myResourceString2 = getStringResourceById(R.string.sport_type2);
        addSport(new Sport(myResourceString2, "squash"));

        String myResourceString3 = getStringResourceById(R.string.sport_type3);
        addSport(new Sport(myResourceString3, "swimming"));

        String myResourceString4 = getStringResourceById(R.string.sport_type4);
        addSport(new Sport(myResourceString4, "jogging"));

        String myResourceString5 = getStringResourceById(R.string.sport_type5);
        addSport(new Sport(myResourceString5, "lifting"));

        String myResourceString6 = getStringResourceById(R.string.sport_type6);
        addSport(new Sport(myResourceString6, "zumba"));





    }

}
