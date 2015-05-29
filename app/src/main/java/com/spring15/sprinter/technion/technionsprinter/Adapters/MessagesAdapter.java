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
import com.spring15.sprinter.technion.technionsprinter.Models.Message;
import com.spring15.sprinter.technion.technionsprinter.R;

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

        TextView titleTextView = (TextView) v.findViewById(R.id.title);
        titleTextView.setText(message.getBody());
        return v;
    }

}
