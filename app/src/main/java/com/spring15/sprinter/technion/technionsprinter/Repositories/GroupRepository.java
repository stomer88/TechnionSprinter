package com.spring15.sprinter.technion.technionsprinter.Repositories;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;
import com.spring15.sprinter.technion.technionsprinter.Models.Category;
import com.spring15.sprinter.technion.technionsprinter.Models.Group;
import com.spring15.sprinter.technion.technionsprinter.Models.UserCategory;

import java.util.ArrayList;
import java.util.List;

public class GroupRepository {

    public static void addGroup(Group group) {
        group.saveInBackground();
    }

    public static ParseQueryAdapter.QueryFactory<Group> getCategoryGroups(final Category category){
        ParseQueryAdapter.QueryFactory<Group> factory =
                new ParseQueryAdapter.QueryFactory<Group>() {
                    public ParseQuery create() {
                        ParseQuery<Group> query = new ParseQuery<Group>("Groups");
                        query.include("category");
                        query.include("creator");
                        query.whereEqualTo("category", category);
                        return query;
                    }
                };
        return factory;
    }
}
