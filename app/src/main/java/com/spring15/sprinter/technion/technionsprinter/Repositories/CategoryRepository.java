package com.spring15.sprinter.technion.technionsprinter.Repositories;

import android.widget.Adapter;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;
import com.spring15.sprinter.technion.technionsprinter.Models.Category;
import com.spring15.sprinter.technion.technionsprinter.Models.UserCategory;
import com.spring15.sprinter.technion.technionsprinter.Models.UserDetails;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {

    public static ParseQuery<Category> getAllCategories()
    {
        ParseQuery<Category> query = ParseQuery.getQuery("Categories");
        query.findInBackground(new FindCallback<Category>() {
            public void done(List<Category> objects, ParseException e) {
                if (e == null) {

                }
            }
        });
        return query;
    }
}
