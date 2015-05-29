package com.spring15.sprinter.technion.technionsprinter.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.spring15.sprinter.technion.technionsprinter.Activities.MainActivity;
import com.spring15.sprinter.technion.technionsprinter.Models.Category;
import com.spring15.sprinter.technion.technionsprinter.Models.UserDetails;
import com.spring15.sprinter.technion.technionsprinter.R;
import com.spring15.sprinter.technion.technionsprinter.Repositories.UserCategoryRepository;

import java.util.ArrayList;
import java.util.List;

public class PersonalDetails extends Fragment {
    public Dialog dialog;
    public CharSequence[] categories;
    final ArrayList<Integer> selectedCategories = new ArrayList<Integer>();

    public PersonalDetails() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Fragment Preparation
        View rootView = inflater.inflate(R.layout.fragment_personal_details, container, false);
        int i = getArguments().getInt(MainActivity.ARG_MENU_NUMBER);
        String menuTitle = getResources().getStringArray(R.array.menu_list)[i];
        getActivity().setTitle(menuTitle);

        final Button save = (Button) rootView.findViewById(R.id.save);
        final Button categoriesDialog = (Button) rootView.findViewById(R.id.categoriesDialog);
        final EditText email = (EditText) rootView.findViewById(R.id.email);
        final EditText userName = (EditText) rootView.findViewById(R.id.userName);
        final EditText firstName = (EditText) rootView.findViewById(R.id.firstName);
        final EditText lastName = (EditText) rootView.findViewById(R.id.lastName);

        // Prepare Data for the View
        ParseQuery<UserDetails> query = ParseQuery.getQuery("UserDetails");
        query.whereEqualTo("user", ParseUser.getCurrentUser());
        query.getFirstInBackground(new GetCallback<UserDetails>() {
            public void done(UserDetails object, ParseException e) {
                if (e == null) {
                    userName.setText(object.getUserName());
                    firstName.setText(object.getFirstName());
                    lastName.setText(object.getLastName());
                    email.setText(object.getEmail());
                }
            }
        });

        // On Item Click
        categoriesDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<Category> query = ParseQuery.getQuery("Categories");
                query.findInBackground(new FindCallback<Category>() {
                    public void done(List<Category> objects, ParseException e) {
                        if (e == null) {

                            categories = new CharSequence[objects.size()];
                            for(int j = 0; j < objects.size(); j++){
                                categories[j] = objects.get(j).getName();
                            }

                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                            builder.setTitle("Select your favorite sports");
                            builder.setMultiChoiceItems(categories, null,
                                    new DialogInterface.OnMultiChoiceClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int indexSelected,
                                                            boolean isChecked) {
                                            if (isChecked) {
                                                selectedCategories.add(indexSelected);
                                            } else if (selectedCategories.contains(indexSelected)) {
                                                selectedCategories.remove(Integer.valueOf(indexSelected));
                                            }
                                        }
                                    })
                                    // Set the action buttons
                                    .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int id) {

                                        }
                                    });
                            dialog = builder.create();
                            dialog.show();
                        }
                    }
                });
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO: validate fields

                // save user details
                ParseQuery<UserDetails> query = ParseQuery.getQuery("UserDetails");
                query.whereEqualTo("user", ParseUser.getCurrentUser());
                query.getFirstInBackground(new GetCallback<UserDetails>() {
                    public void done(UserDetails object, ParseException e) {
                        if (e == null) {
                            object.setEmail(email.getText().toString());
                            object.setFirstName(firstName.getText().toString());
                            object.setUserName(userName.getText().toString());
                            object.setLastName(lastName.getText().toString());
                            object.saveEventually();
                        }
                    }
                });

                // remove user categories
                UserCategoryRepository.removeUnselectedUserCategories(categories, selectedCategories);
                // add user categories
                UserCategoryRepository.addUsersCategories(categories, selectedCategories);

                Toast.makeText(getActivity(), "Your personal info has been saved!", Toast.LENGTH_LONG).show();
            }
        });

        return rootView;
    }
}