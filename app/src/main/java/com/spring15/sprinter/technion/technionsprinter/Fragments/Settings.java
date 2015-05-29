package com.spring15.sprinter.technion.technionsprinter.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spring15.sprinter.technion.technionsprinter.Activities.MainActivity;
import com.spring15.sprinter.technion.technionsprinter.R;

public class Settings extends Fragment {

    public Settings() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Fragment Preparation
        final View rootView = inflater.inflate(R.layout.fragment_categories, container,
                false);
        int i = getArguments().getInt(MainActivity.ARG_MENU_NUMBER);

        String groupObjectId = getArguments().getString("groupObjectId");
        String menuTitle = getResources().getStringArray(R.array.menu_list)[i];
        getActivity().setTitle(menuTitle);


        // Prepare Data for the View


        return rootView;
    }
}