package com.spring15.sprinter.technion.technionsprinter.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseQueryAdapter;
import com.spring15.sprinter.technion.technionsprinter.Models.Group;
import com.spring15.sprinter.technion.technionsprinter.Models.UserCategory;
import com.spring15.sprinter.technion.technionsprinter.R;

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

        ParseImageView groupImage = (ParseImageView) v.findViewById(R.id.icon);
        ParseFile photoFile = group.getCategory().getImage();
        if (photoFile != null) {
            groupImage.setParseFile(photoFile);
            groupImage.loadInBackground(new GetDataCallback() {
                @Override
                public void done(byte[] data, ParseException e) {
                    // nothing to do
                }
            });
        }

        TextView titleTextView = (TextView) v.findViewById(R.id.title);
        titleTextView.setText(group.getTitle());

        TextView timeTextView = (TextView) v.findViewById(R.id.time);
        titleTextView.setText(group.getTime().toString());

        TextView locationTextView = (TextView) v.findViewById(R.id.location);
        titleTextView.setText(group.getLocation());

        TextView sizeTextView = (TextView) v.findViewById(R.id.size);
        titleTextView.setText(group.getSize() + "/" + group.getMaxSize());

        TextView levelTextView = (TextView) v.findViewById(R.id.level);
        String level;
        switch(group.getLevel()){
            case 0:level = "Easy"; break;
            case 1:level = "Medium"; break;
            case 2:level = "Hard"; break;
            case 3:level = "Pro"; break;
            default:level = "Easy"; break;
        }
        titleTextView.setText(level);

        return v;
    }

}
