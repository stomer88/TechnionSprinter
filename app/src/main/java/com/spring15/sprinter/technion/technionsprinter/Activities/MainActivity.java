package com.spring15.sprinter.technion.technionsprinter.Activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.spring15.sprinter.technion.technionsprinter.Fragments.Categories;
import com.spring15.sprinter.technion.technionsprinter.Fragments.Groups;
import com.spring15.sprinter.technion.technionsprinter.Fragments.Messages;
import com.spring15.sprinter.technion.technionsprinter.Fragments.PersonalDetails;
import com.spring15.sprinter.technion.technionsprinter.Fragments.Settings;
import com.spring15.sprinter.technion.technionsprinter.R;


public class MainActivity extends ActionBarActivity {
        public static final String ARG_MENU_NUMBER = "menu_number";

        private DrawerLayout mDrawerLayout;
        private ListView mDrawerList;
        private ActionBarDrawerToggle mDrawerToggle;

        private CharSequence mDrawerTitle;
        private CharSequence mTitle;
        private String[] menuTitles;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_main);
            mTitle = mDrawerTitle = getTitle();
            menuTitles = getResources().getStringArray(R.array.menu_list);
            mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            mDrawerList = (ListView) findViewById(R.id.left_drawer);

            // set a custom shadow that overlays the main content when the drawer
            // opens
            mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
                    GravityCompat.START);
            // set up the drawer's list view with items and click listener
            mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                    R.layout.drawer_list_item, menuTitles));
            mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

            // enable ActionBar app icon to behave as action to toggle nav drawer
            getSupportActionBar().setBackgroundDrawable(
                    new ColorDrawable(Color.parseColor("#4A8CFF")));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);

            // ActionBarDrawerToggle ties together the the proper interactions
            // between the sliding drawer and the action bar app icon
            mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
                    mDrawerLayout, /* DrawerLayout object */
                    R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
                    R.string.drawer_open, /* "open drawer" description for accessibility */
                    R.string.drawer_close /* "close drawer" description for accessibility */
            ) {
                public void onDrawerClosed(View view) {
                    getSupportActionBar().setTitle(" " + mTitle);
                    invalidateOptionsMenu(); // creates call to
                    // onPrepareOptionsMenu()
                }

                public void onDrawerOpened(View drawerView) {
                    getSupportActionBar().setTitle(" " + mDrawerTitle);
                    invalidateOptionsMenu(); // creates call to
                    // onPrepareOptionsMenu()
                }
            };
            mDrawerLayout.setDrawerListener(mDrawerToggle);

            if (savedInstanceState == null) {
                selectItem(0);
            }
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.main, menu);
            return super.onCreateOptionsMenu(menu);
        }

	/* Called whenever we call invalidateOptionsMenu() */
        @Override
        public boolean onPrepareOptionsMenu(Menu menu) {
            // If the nav drawer is open, hide action items related to the content
            // view
            // boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
            return super.onPrepareOptionsMenu(menu);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // The action bar home/up action should open or close the drawer.
            // ActionBarDrawerToggle will take care of this.
            if (mDrawerToggle.onOptionsItemSelected(item)) {
                return true;
            }
            // Handle action buttons
            switch (item.getItemId()) {
                default:
                    return super.onOptionsItemSelected(item);
            }
        }

    /* The click listner for ListView in the navigation drawer */
        private class DrawerItemClickListener implements
                ListView.OnItemClickListener {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                selectItem(position);
            }
        }

    private void selectItem(int position) {
        if(getIntent().getStringExtra("firstLogin") != null){
            position = 2;
        }

        // update the main content by replacing fragments
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new Categories();
                break;
            case 1:
                fragment = new Groups();
                break;
            case 2:
                fragment = new PersonalDetails();
                break;
            case 3:
                fragment = new Settings();
                break;
            case 4:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                return;
            default:
                fragment = new Categories();
        }

        Bundle args = new Bundle();
        args.putInt(MainActivity.ARG_MENU_NUMBER, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment).commit();

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(" " + menuTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(" " + mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void goToCategoryGroup(String categoryName, String categoryObjectId) {
        Fragment fragment = new Groups();
        Bundle args = new Bundle();
        args.putInt(MainActivity.ARG_MENU_NUMBER, 0);
        args.putString("categoryObjectId", categoryObjectId);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment).commit();

        // update selected item and title, then close the drawer
        setTitle(categoryName + " activities");
    }

    public void goToGroupMessages(String title, String groupObjectId) {
        Fragment fragment = new Messages();
        Bundle args = new Bundle();
        args.putInt(MainActivity.ARG_MENU_NUMBER, 0);
        args.putString("groupObjectId", groupObjectId);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment).commit();
        setTitle(title);
    }


}
