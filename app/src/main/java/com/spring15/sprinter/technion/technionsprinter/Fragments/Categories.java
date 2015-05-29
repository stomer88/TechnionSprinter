package com.spring15.sprinter.technion.technionsprinter.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;
import com.spring15.sprinter.technion.technionsprinter.Activities.MainActivity;
import com.spring15.sprinter.technion.technionsprinter.Adapters.CategoriesAdapter;
import com.spring15.sprinter.technion.technionsprinter.Models.Category;
import com.spring15.sprinter.technion.technionsprinter.Models.UserCategory;
import com.spring15.sprinter.technion.technionsprinter.R;
import com.spring15.sprinter.technion.technionsprinter.Repositories.CategoryRepository;
import com.spring15.sprinter.technion.technionsprinter.Repositories.UserCategoryRepository;
import com.spring15.sprinter.technion.technionsprinter.Repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class Categories extends Fragment {

    public Categories() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Fragment Preparation
        View rootView = inflater.inflate(R.layout.fragment_categories, container,
                false);
        int i = getArguments().getInt(MainActivity.ARG_MENU_NUMBER);
        String menuTitle = getResources().getStringArray(R.array.menu_list)[i];
        getActivity().setTitle(menuTitle);

        // Prepare Data for the View
        ParseQueryAdapter.QueryFactory<UserCategory> factory = UserCategoryRepository.getUserCategories();
        CategoriesAdapter adapter = new CategoriesAdapter(
                getActivity().getApplicationContext(), factory, R.layout.categories_list_item);


        // Attach it to your ListView, as in the example above
        ListView listView = (ListView) rootView.findViewById(R.id.listview);
        listView.setAdapter(adapter);

        // On Item Click
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                UserCategory userCategory = (UserCategory)parent.getItemAtPosition(position);
                ((MainActivity) getActivity())
                        .goToCategoryGroup(userCategory.getCategory().getName(), userCategory.getCategory().getObjectId());
            }
        });

        return rootView;
    }
}