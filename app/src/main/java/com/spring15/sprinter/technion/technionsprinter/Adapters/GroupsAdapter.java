package com.spring15.sprinter.technion.technionsprinter.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;
import com.spring15.sprinter.technion.technionsprinter.Models.Group;
import com.spring15.sprinter.technion.technionsprinter.Models.UserCategory;
import com.spring15.sprinter.technion.technionsprinter.Models.UserDetails;
import com.spring15.sprinter.technion.technionsprinter.R;
import com.squareup.picasso.Picasso;

public class GroupsAdapter extends ParseQueryAdapter<Group> {


    public GroupsAdapter(Context context, QueryFactory<Group> queryFactory, Integer itemViewResource) {
        super(context, queryFactory, itemViewResource);
    }

    @Override
    public View getItemView(Group group, View v, ViewGroup parent) {

        if (v == null) {
            v = View.inflate(getContext(), R.layout.groups_list_item, null);
        }

        super.getItemView(group, v, parent);

        final ImageView groupImage = (ImageView) v.findViewById(R.id.icon);
        ParseQuery<UserDetails> query = ParseQuery.getQuery("UserDetails");
        query.whereEqualTo("user", group.getCreator());
        query.getFirstInBackground(new GetCallback<UserDetails>() {
            public void done(UserDetails object, ParseException e) {
                if (e == null) {
                    Picasso.with(getContext())
                            .load("http://graph.facebook.com/" + object.getFacebookId() + "/picture?type=normal")
                            .into(groupImage);
                }
            }
        });


        TextView titleTextView = (TextView) v.findViewById(R.id.title);
        titleTextView.setText(group.getTitle());

        TextView timeAndLocationTextView = (TextView) v.findViewById(R.id.timeAndLocation);
        timeAndLocationTextView.setText(group.getTime().toString().substring(0, 19) + ", " + group.getLocation());

        TextView sizeTextView = (TextView) v.findViewById(R.id.size);
        sizeTextView.setText(group.getSize());

        TextView levelTextView = (TextView) v.findViewById(R.id.level);
        levelTextView.setText(group.getLevel());

        // http://graph.facebook.com/10205760114496396/picture?type=small
        return v;
    }

}
