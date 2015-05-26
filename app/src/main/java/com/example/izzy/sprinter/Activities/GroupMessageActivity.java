package com.example.izzy.sprinter.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.izzy.sprinter.Adapters.MyMessageListAdapter;
import com.example.izzy.sprinter.R;
import com.example.izzy.sprinter.data.Constants;
import com.example.izzy.sprinter.data.MessageContent;
import com.example.izzy.sprinter.data.MessagesData;

import java.util.List;


public class GroupMessageActivity extends Activity {


    private List<MessageContent> myMessageContentList;
    public static String PACKAGE_NAME;
    public static int mySelection;
    public int categoryChosenIndex;
    private Button bt_add_activity;
    private TextView tv_title;
    public MyMessageListAdapter adapter;
    public ListView listView;

    public  static Context CONTEXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_message_list_details);

        Bundle extra = getIntent().getExtras();
        int value = extra.getInt("categoryChosen");
        categoryChosenIndex = value;
        tv_title = (TextView)findViewById(R.id.tv_group_title);
        tv_title.setText(Constants.STRING_TYPES_ARRAY[value]);

        PACKAGE_NAME = getApplicationContext().getPackageName();

        CONTEXT = getApplicationContext();
        myMessageContentList = new MessagesData(categoryChosenIndex).getMyMessagesList();
        listView = (ListView) findViewById(R.id.lv_massages);
        adapter = new MyMessageListAdapter(CONTEXT, R.layout.group_message_raw_list,
                myMessageContentList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                setResult(position);
                mySelection = position;

                Intent intent = new Intent(GroupMessageActivity.this,ChatActivity.class);
                intent.putExtra("categoryTitleForConversation",categoryChosenIndex);
                startActivity(intent);
                // finish();

            }
        });


        bt_add_activity = (Button) findViewById(R.id.bt_add_activity);
        bt_add_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //adding new temp message
                MessageContent temp_message = new MessageContent("Temp List Item: hi, who wants " +
                        Constants.STRING_ACTIONS_ARRAY[categoryChosenIndex],
                        "17:14","Tel-Aviv",3,"easy","profile4");
                myMessageContentList.add(temp_message);
                adapter.notifyDataSetChanged();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_group_message_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
