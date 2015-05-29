package com.spring15.sprinter.technion.technionsprinter.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;
import com.spring15.sprinter.technion.technionsprinter.Activities.MainActivity;
import com.spring15.sprinter.technion.technionsprinter.Adapters.GroupsAdapter;
import com.spring15.sprinter.technion.technionsprinter.Models.Category;
import com.spring15.sprinter.technion.technionsprinter.Models.Group;
import com.spring15.sprinter.technion.technionsprinter.Models.UserCategory;
import com.spring15.sprinter.technion.technionsprinter.R;
import com.spring15.sprinter.technion.technionsprinter.Repositories.CategoryRepository;
import com.spring15.sprinter.technion.technionsprinter.Repositories.GroupRepository;

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
        final View rootView = inflater.inflate(R.layout.fragment_categories, container,
                false);
        int i = getArguments().getInt(ARG_MENU_NUMBER);

        String categoryObjectId = getArguments().getString("categoryObjectId");
        if(categoryObjectId == null) {
            String menuTitle = getResources().getStringArray(R.array.menu_list)[i];
            getActivity().setTitle(menuTitle);
        }
        else {
            ParseQuery<Category> query = ParseQuery.getQuery(Category.class);
            query.getInBackground(categoryObjectId, new GetCallback<Category>() {
                public void done(final Category item, ParseException e) {
                    if (e == null) {
                        // item was found
                        ParseQueryAdapter.QueryFactory<Group> factory = GroupRepository.getCategoryGroups(item);
                        GroupsAdapter adapter = new GroupsAdapter(
                                getActivity().getApplicationContext(), factory, R.layout.groups_list_item);

                        // Attach it to your ListView, as in the example above
                        ListView listView = (ListView) rootView.findViewById(R.id.listview);
                        listView.setAdapter(adapter);
                        // On Item Click
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                            @Override
                            public void onItemClick(AdapterView<?> parent, final View view,
                                                    int position, long id) {
                                Group group = (Group) parent.getItemAtPosition(position);
                                ((MainActivity) getActivity())
                                        .goToGroupMessages(group.getTitle(), group.getObjectId());
                            }
                        });

                        // On Item Click
                        final Button addActivityDialog = (Button) rootView.findViewById(R.id.addActivity);
                        addActivityDialog.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                GroupRepository.addGroup(new Group("New Group", "Haifa", 3, 0, item));
                            }
                        });
                    }
                }
            });
        }

        // Prepare Data for the View




        return rootView;
    }
}