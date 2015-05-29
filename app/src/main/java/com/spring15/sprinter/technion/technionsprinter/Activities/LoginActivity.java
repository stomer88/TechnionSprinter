package com.spring15.sprinter.technion.technionsprinter.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.spring15.sprinter.technion.technionsprinter.Models.Category;
import com.spring15.sprinter.technion.technionsprinter.Models.UserDetails;
import com.spring15.sprinter.technion.technionsprinter.R;
import com.spring15.sprinter.technion.technionsprinter.Repositories.CategoryRepository;
import com.spring15.sprinter.technion.technionsprinter.Repositories.UserCategoryRepository;
import com.spring15.sprinter.technion.technionsprinter.Repositories.UserRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LoginActivity extends Activity {
        public CharSequence[] categories;
        public AlertDialog dialog;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            // User is logged in with facebook
            if(AccessToken.getCurrentAccessToken() != null){
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            Button login_facebook_button = (Button) findViewById(R.id.bt_facebook_login);

            login_facebook_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final List<String> permissions = Arrays.asList("public_profile", "email");
                    ParseFacebookUtils.logInWithReadPermissionsInBackground(LoginActivity.this, permissions, new LogInCallback() {
                        @Override
                        public void done(final ParseUser user, ParseException err) {
                            if (user == null) {
                                Log.d("MyApp", "Uh oh. The user cancelled the Facebook login.");
                            } else if (user.isNew()) {
                                Log.d("MyApp", "User signed up and logged in through Facebook!");
                                // Set up choose sports button click handler
                                createDialog();
                            } else {
                                Log.d("MyApp", "User logged in through Facebook!");
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
								finish();
                            }
                        }
                    });
                }
            });
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

    private void createDialog() {
        ParseQuery<Category> query = ParseQuery.getQuery("Categories");
        query.findInBackground(new FindCallback<Category>() {
            public void done(List<Category> objects, ParseException e) {
                if (e == null) {
                    final ArrayList<Integer> selectedCategories = new ArrayList<Integer>();
                    categories = new CharSequence[objects.size()];
                    for(int j = 0; j < objects.size(); j++){
                        categories[j] = objects.get(j).getName();
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setTitle("Select sports that you like");
                    builder.setMultiChoiceItems(categories, null,
                            new DialogInterface.OnMultiChoiceClickListener() {
                                // sportSelected contains the index of item (of which checkbox checked)
                                @Override
                                public void onClick(DialogInterface dialog, int indexSelected,
                                                    boolean isChecked) {
                                    if (isChecked) {
                                        selectedCategories.add(indexSelected);
                                    } else if (selectedCategories.contains(indexSelected)) {
                                        selectedCategories.remove(Integer.valueOf(indexSelected));
                                    }
                                }
                            })
                            // Set the action buttons
                            .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    newUserRequest(categories, selectedCategories);
                                }
                            });
                    dialog = builder.create();
                    dialog.show();
                }
            }
        });
    }

    private void newUserRequest(final CharSequence[] categories, final ArrayList selectedCategories) {
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse) {
                        if (jsonObject != null) {
                            // add userDetails to parse
                            UserRepository.addUserDetails(new UserDetails(jsonObject));
                            // add user categories
                            UserCategoryRepository.addUsersCategories(categories, selectedCategories);

                            // go to main activity
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();

                        } else if (graphResponse.getError() != null) {
                            switch (graphResponse.getError().getCategory()) {
                                case LOGIN_RECOVERABLE:
                                    Log.d("MyApp","Authentication error: " + graphResponse.getError());
                                    break;

                                case TRANSIENT:
                                    Log.d("MyApp", "Transient error. Try again. " + graphResponse.getError());
                                    break;

                                case OTHER:
                                    Log.d("MyApp", "Some other error: " + graphResponse.getError());
                                    break;
                            }
                        }
                    }
                });
        request.executeAsync();
    }
}
