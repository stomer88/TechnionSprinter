package com.example.izzy.sprinter.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.izzy.sprinter.R;
import com.example.izzy.sprinter.data.Sport;

import java.util.List;

/**
 * Created by izzy on 22/05/15.
 */
public class MySportsListAdapter extends ArrayAdapter<Sport> {

    private Context context;
    private List<Sport> sportList;

    public MySportsListAdapter(Context context1, int resource1, List<Sport> obj){
        super(context1,resource1,obj);
        this.context = context1;
        this.sportList = obj;

    }

    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }

    @Override
    public int getCount() {
        return sportList.size();
    }

    @Override
    public Sport getItem(int position) {
        return sportList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        Sport sport = sportList.get(position);

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.home_raw_list,null);

        TextView tv1 = (TextView)view.findViewById(R.id.tv_raw_name);
        tv1.setText(sport.toString());




        ImageView imageView = (ImageView)view.findViewById(R.id.img_raw_home);
        String pic = sport.getPictureName();


//        Drawable image = getResources().getDrawable(getImageId(context,pic));
        imageView.setImageResource(getImageId(context,pic));

//        imageView.setImageResource(R.drawable.paella);
//        Drawable imgDrawable=getResources().getDrawable(R.drawable.paella));
//        imgView.setImageDrawable(imgDrawable);


        return view;
    }
}
