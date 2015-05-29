package com.spring15.sprinter.technion.technionsprinter.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.spring15.sprinter.technion.technionsprinter.Activities.MainActivity;
import com.spring15.sprinter.technion.technionsprinter.Adapters.GroupsAdapter;
import com.spring15.sprinter.technion.technionsprinter.Models.Category;
import com.spring15.sprinter.technion.technionsprinter.Models.Group;
import com.spring15.sprinter.technion.technionsprinter.R;
import com.spring15.sprinter.technion.technionsprinter.Repositories.GroupRepository;

public class Messages extends Fragment {
    public static final String ARG_MENU_NUMBER = "menu_number";

    public Messages() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Fragment Preparation
        final View rootView = inflater.inflate(R.layout.fragment_categories, container,
                false);
        int i = getArguments().getInt(ARG_MENU_NUMBER);

        String groupObjectId = getArguments().getString("groupObjectId");
        if(groupObjectId == null) {
            String menuTitle = getResources().getStringArray(R.array.menu_list)[i];
            getActivity().setTitle(menuTitle);
        }


        // Prepare Data for the View




        return rootView;
    }
}