package com.example.izzy.sprinter.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.izzy.sprinter.Adapters.MySportsListAdapter;
import com.example.izzy.sprinter.R;
import com.example.izzy.sprinter.data.Sport;
import com.example.izzy.sprinter.data.SportTypesData;

import java.util.List;


public class HomeActivity extends Activity {

    private List<Sport> mySportTypesList;
    public   static  String PACKAGE_NAME;
    public static int mySelection;

    public  static Context CONTEXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_list);
        PACKAGE_NAME = getApplicationContext().getPackageName();

        CONTEXT = getApplicationContext();
        mySportTypesList = new SportTypesData().getSportsList();
        final ListView listView = (ListView)findViewById(R.id.lv_home) ;

        MySportsListAdapter adapter = new MySportsListAdapter(this,R.layout.home_raw_list,mySportTypesList);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                setResult(position);
                mySelection = position;

                Intent intent = new Intent(HomeActivity.this,GroupMessageActivity.class);
                intent.putExtra("categoryChosen",position);

                startActivity(intent);
               // finish();

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
