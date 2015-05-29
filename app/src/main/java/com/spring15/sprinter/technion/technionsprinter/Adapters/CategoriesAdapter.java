package com.spring15.sprinter.technion.technionsprinter.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;
import com.spring15.sprinter.technion.technionsprinter.Models.UserCategory;
import com.spring15.sprinter.technion.technionsprinter.R;
import com.squareup.picasso.Picasso;

public class CategoriesAdapter extends ParseQueryAdapter<UserCategory> {


    public CategoriesAdapter(Context context, ParseQueryAdapter.QueryFactory<UserCategory> queryFactory, Integer itemViewResource) {
        super(context, queryFactory, itemViewResource);
    }

    @Override
    public View getItemView(UserCategory userCategory, View v, ViewGroup parent) {

        if (v == null) {
            v = View.inflate(getContext(), R.layout.categories_list_item, null);
        }

        super.getItemView(userCategory, v, parent);

        ParseImageView categoryImage = (ParseImageView) v.findViewById(R.id.icon);
        ParseFile photoFile = userCategory.getCategory().getImage();
        if (photoFile != null) {
            String chatImageFile = photoFile.getUrl();
            Picasso.with(getContext()).load(chatImageFile).into(categoryImage);
        }

        TextView titleTextView = (TextView) v.findViewById(R.id.title);
        titleTextView.setText(userCategory.getCategory().getName());
        return v;
    }

}
