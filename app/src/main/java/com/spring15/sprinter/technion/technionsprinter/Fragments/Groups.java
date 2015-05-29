package com.spring15.sprinter.technion.technionsprinter.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.ParseUser;
import com.spring15.sprinter.technion.technionsprinter.Models.UserCategory;
import com.spring15.sprinter.technion.technionsprinter.R;
import com.spring15.sprinter.technion.technionsprinter.Repositories.CategoryRepository;

import java.util.ArrayList;

public class Groups extends Fragment {
    public static final String ARG_MENU_NUMBER = "menu_number";

    public Groups() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Fragment Preparation
        View rootView = inflater.inflate(R.layout.fragment_categories, container,
                false);
        int i = getArguments().getInt(ARG_MENU_NUMBER);

        String categoryObjectId = getArguments().getString("categoryObjectId");
        if(categoryObjectId == null) {
            String menuTitle = getResources().getStringArray(R.array.menu_list)[i];
            getActivity().setTitle(menuTitle);
        }

        // Prepare Data for the View

        // On Item Click

        return rootView;
    }
}