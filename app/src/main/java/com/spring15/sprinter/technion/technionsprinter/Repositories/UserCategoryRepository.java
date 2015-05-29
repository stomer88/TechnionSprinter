package com.spring15.sprinter.technion.technionsprinter.Repositories;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;
import com.spring15.sprinter.technion.technionsprinter.Models.Category;
import com.spring15.sprinter.technion.technionsprinter.Models.UserCategory;

import java.util.ArrayList;
import java.util.List;

public class UserCategoryRepository {

    public static void addUsersCategories(final CharSequence[] categories, final ArrayList<Integer> selectedCategories) {
        for (int j = 0; j < selectedCategories.size(); j++) {
            ParseQuery<Category> query = ParseQuery.getQuery("Categories");
            query.whereEqualTo("name", categories[selectedCategories.get(j)]);
            query.getFirstInBackground(new GetCallback<Category>() {
                public void done(Category object, ParseException e) {
                    if (e == null) {
                        UserCategory userCategory = new UserCategory(object);
                        userCategory.saveInBackground();
                    }
                }
            });
        }
    }

    public static ParseQueryAdapter.QueryFactory<UserCategory> getUserCategories(){
        ParseQueryAdapter.QueryFactory<UserCategory> factory =
                new ParseQueryAdapter.QueryFactory<UserCategory>() {
                    public ParseQuery create() {
                        ParseQuery<UserCategory> query = new ParseQuery<UserCategory>("UserCategories");
                        query.include("category");
                        query.whereEqualTo("user", ParseUser.getCurrentUser());
                        return query;
                    }
                };
        return factory;
    }

    public static void removeUnselectedUserCategories(final CharSequence[] categories, final ArrayList<Integer> selectedCategories){
        ParseQuery<UserCategory> query = new ParseQuery<UserCategory>("UserCategories");
        query.include("category");
        query.whereEqualTo("user", ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<UserCategory>() {
            public void done(List<UserCategory> objects, ParseException e) {
                if (e == null) {
                    for(UserCategory userCategory : objects){
                        boolean isFound = false;
                        for (int j = 0; j < selectedCategories.size(); j++) {
                            if(userCategory.getCategory().getName() == categories[selectedCategories.get(j)]){
                                isFound = true;
                            }
                        }
                        if(isFound) continue;
                        userCategory.deleteEventually();
                    }
                }
            }
        });

    }
}
