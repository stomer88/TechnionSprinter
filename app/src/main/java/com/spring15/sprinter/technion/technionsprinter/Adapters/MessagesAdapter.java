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
import com.spring15.sprinter.technion.technionsprinter.Models.Message;
import com.spring15.sprinter.technion.technionsprinter.Models.UserDetails;
import com.spring15.sprinter.technion.technionsprinter.R;
import com.squareup.picasso.Picasso;

public class MessagesAdapter extends ParseQueryAdapter<Message> {


    public MessagesAdapter(Context context, QueryFactory<Message> queryFactory, Integer itemViewResource) {
        super(context, queryFactory, itemViewResource);
    }

    @Override
    public View getItemView(Message message, View v, ViewGroup parent) {

        if (v == null) {
            v = View.inflate(getContext(), R.layout.messages_list_item, null);
        }

        super.getItemView(message, v, parent);

        final ImageView senderIsMe = (ImageView) v.findViewById(R.id.iconMe);
        final ImageView senderIsOther = (ImageView) v.findViewById(R.id.iconOther);
        ParseQuery<UserDetails> query = ParseQuery.getQuery("UserDetails");
        query.whereEqualTo("user", message.getSender());
        query.getFirstInBackground(new GetCallback<UserDetails>() {
            public void done(UserDetails object, ParseException e) {
                if (e == null) {
                    if(object.getUser().getObjectId() == ParseUser.getCurrentUser().getObjectId()) {
                        Picasso.with(getContext())
                                .load("http://graph.facebook.com/" + object.getFacebookId() + "/picture?type=normal")
                                .into(senderIsMe);
                    }
                    else{
                        Picasso.with(getContext())
                                .load("http://graph.facebook.com/" + object.getFacebookId() + "/picture?type=normal")
                                .into(senderIsOther);
                    }
                }
            }
        });

        TextView titleTextView = (TextView) v.findViewById(R.id.title);
        titleTextView.setText(message.getBody());
        return v;
    }

}
