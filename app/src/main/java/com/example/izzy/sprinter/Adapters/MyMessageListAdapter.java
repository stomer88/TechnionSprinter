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
import com.example.izzy.sprinter.data.MessageContent;

import java.util.List;

/**
 * Created by izzy on 22/05/15.
 */
public class MyMessageListAdapter extends ArrayAdapter<MessageContent> {

    final private Context context;
    private List<MessageContent> messageContentList;

    public MyMessageListAdapter(Context context1, int resource1, List<MessageContent> obj){
        super(context1,resource1,obj);
        this.context = context1;
        this.messageContentList = obj;

    }

    public static int getImageId(Context context, String imageName) {
        //return R.drawable.profile1;
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }

    @Override
    public int getCount() {
        return messageContentList.size();
    }

    @Override
    public MessageContent getItem(int position) {
        return messageContentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        MessageContent messageContent = messageContentList.get(position);

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.group_message_raw_list,null);

        TextView tv1 = (TextView)view.findViewById(R.id.tv_title_messages);
        tv1.setText(messageContent.title);

        TextView tv2 = (TextView)view.findViewById(R.id.tv_time_messages);
        tv2.setText("Time: " + messageContent.time);

        TextView tv3 = (TextView)view.findViewById(R.id.tv_location_messages);
        tv3.setText("Location: " + messageContent.location);

        TextView tv4 = (TextView)view.findViewById(R.id.tv_group_size_messages);
        tv4.setText("Group: " + Integer.toString(messageContent.groupSize));

        TextView tv5 = (TextView)view.findViewById(R.id.tv_level_messages);
        tv5.setText("Level: " + messageContent.level);

        ImageView imageView = (ImageView)view.findViewById(R.id.image_messages);
        String pic = messageContent.myPicture;
        imageView.setImageResource(getImageId(context, pic)); //TODO: change





//        ImageView imageView = (ImageView)view.findViewById(R.id.img_raw_home);
//        String pic = messageContent.get();



//        imageView.setImageResource(getImageId(context,pic));




        return view;
    }
}
