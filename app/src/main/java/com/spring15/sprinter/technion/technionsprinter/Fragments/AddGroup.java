package com.spring15.sprinter.technion.technionsprinter.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

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

import java.util.Date;

public class AddGroup extends DialogFragment {

    public static AddGroup newInstance(String categoryObjectId) {
        AddGroup frag = new AddGroup();
        Bundle args = new Bundle();
        args.putString("categoryObjectId", categoryObjectId);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater layoutInflater = (LayoutInflater) getActivity()
                .getBaseContext().getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
        final View view = layoutInflater.inflate(
                R.layout.fragment_add_group, null);

        final String categoryObjectId = getArguments().getString("categoryObjectId");

        TextView mTitle = (TextView) view.findViewById(R.id.header);
        mTitle.setText("Add Activity");

        final EditText title = (EditText) view.findViewById(R.id.title);
        final EditText time = (EditText) view.findViewById(R.id.time);
        final EditText location = (EditText) view.findViewById(R.id.location);
        final Spinner level = (Spinner) view.findViewById(R.id.level);
        final EditText maxSize = (EditText) view.findViewById(R.id.maxSize);

        AlertDialog.Builder builder = new AlertDialog.Builder(
                new ContextThemeWrapper(getActivity(),
                        R.style.AlertDialogCustom));

        builder.setView(view)
                .setCancelable(false)
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, int id) {

                                //TODO: add validation

                                ParseQuery<Category> query = ParseQuery.getQuery(Category.class);
                                query.getInBackground(categoryObjectId, new GetCallback<Category>() {
                                    public void done(final Category item, ParseException e) {
                                        if (e == null) {
                                            GroupRepository.addGroup(new Group(
                                                    title.getText().toString(),
                                                    location.getText().toString(),
                                                    Integer.valueOf(maxSize.getText().toString()),
                                                    new Date(), // TODO: create date
                                                    level.getSelectedItemPosition(),
                                                    item));
                                            dialog.dismiss();
                                        }
                                    }
                                });
                            }
                        });

        AlertDialog dialog = builder.create();
        dialog.show();
        return dialog;
    }
}