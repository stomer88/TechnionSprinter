package com.example.izzy.sprinter.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.izzy.sprinter.R;
import com.example.izzy.sprinter.Utilities.ParserUtilities;
import com.example.izzy.sprinter.Utilities.UIUtilities;
import com.example.izzy.sprinter.data.Constants;
import com.example.izzy.sprinter.data.PrivateDetails;

public class ProfileDetailsActivity extends Activity {
    Button submit_button;
    EditText et_username;
    EditText et_password;
    EditText et_email;
    EditText et_firstname;
    EditText et_lastname;

    CheckBox cbx_bicycle, cbx_squash, cbx_swimming, cbx_jogging, cbx_bodybuilding, cbx_zumba;
    String userKey;
    Boolean isDataChanged;
    Boolean[] isCheckedArray;


//    MyApplication myApplicationContext = (MyApplication)getApplicationContext();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_details);
        addListenerOnSubmitButton();


        et_username = (EditText) findViewById(R.id.et_username);
        et_email = (EditText) findViewById(R.id.et_email);
        et_firstname = (EditText) findViewById(R.id.et_first_name);
        et_lastname = (EditText) findViewById(R.id.et_last_name);
        et_password = (EditText) findViewById(R.id.et_password);

        cbx_bicycle = (CheckBox) findViewById(R.id.cb_bicycle);
        cbx_squash = (CheckBox) findViewById(R.id.cb_squash);
        cbx_swimming = (CheckBox) findViewById(R.id.cb_swimming);
        cbx_jogging = (CheckBox) findViewById(R.id.cb_jogging);
        cbx_bodybuilding = (CheckBox) findViewById(R.id.cb_body_building);
        cbx_zumba = (CheckBox) findViewById(R.id.cb_zumba);

        isCheckedArray = new Boolean[Constants.SPORTS_NUMBER];


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        SharedPreferences.Editor editor=prefs.edit();
        String keyString = prefs.getString("idKey", "");
        userKey = keyString;


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile_details, menu);
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

    public boolean isAllOfCheckboxIsNotChosen()
    {
//        isCheckedArray = new Boolean[Constants.SPORTS_NUMBER];
        isCheckedArray[0] = cbx_bicycle.isChecked() == true;
        isCheckedArray[1] = cbx_squash.isChecked() == true;
        isCheckedArray[2] = cbx_swimming.isChecked() == true;
        isCheckedArray[3] = cbx_jogging.isChecked() == true;
        isCheckedArray[4] = cbx_bodybuilding.isChecked() == true;
        isCheckedArray[5] = cbx_zumba.isChecked() == true;

        boolean res = true;
        for (int i = 0; i< Constants.SPORTS_NUMBER;i++)
        {
            if (isCheckedArray[i] == true)
            {
                res = false;
                break;
            }
        }
        return  res;

    }


    public PrivateDetails getCurrentProfileData() {

//        Boolean[] isCheckedArray = new Boolean[Constants.SPORTS_NUMBER];

        //fills the array with the requierd boolean values if check Box is checked
//        for (int i = 0; i < Constants.SPORTS_NUMBER; i++)
//        {

        isCheckedArray[0] = cbx_bicycle.isChecked() == true;
        isCheckedArray[1] = cbx_squash.isChecked() == true;
        isCheckedArray[2] = cbx_swimming.isChecked() == true;
        isCheckedArray[3] = cbx_jogging.isChecked() == true;
        isCheckedArray[4] = cbx_bodybuilding.isChecked() == true;
        isCheckedArray[5] = cbx_zumba.isChecked() == true;


//        }
        PrivateDetails privateDetails = new PrivateDetails(et_username.getText().toString(), et_password.getText().toString()
                , et_email.getText().toString(), et_firstname.getText().toString(),
                et_lastname.getText().toString(), isCheckedArray, userKey);

        return privateDetails;


    }

    public void addListenerOnSubmitButton() {
        submit_button = (Button) findViewById(R.id.bt_submit);

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean validationError = false;
                StringBuilder validationErrorMessage =
                        new StringBuilder(getResources().getString(R.string.error_intro));
                if (UIUtilities.isEmpty(et_username)) {
                    validationError = true;
                    validationErrorMessage.append(getResources().getString(R.string.error_blank_username));
                }

                if (UIUtilities.isEmpty(et_password)) {
                    if (validationError) {
                        validationErrorMessage.append(getResources().getString(R.string.error_join));
                    }
                    validationError = true;
                    validationErrorMessage.append(getResources().getString(R.string.error_blank_password));
                }


                if (UIUtilities.isEmpty(et_email)) {
                    if (validationError) {
                        validationErrorMessage.append(getResources().getString(R.string.error_join));
                    }
                    validationError = true;
                    validationErrorMessage.append(getResources().getString(R.string.error_blank_email));
                }



                if (UIUtilities.isEmpty(et_firstname)) {
                    if (validationError) {
                        validationErrorMessage.append(getResources().getString(R.string.error_join));
                    }
                    validationError = true;
                    validationErrorMessage.append(getResources().getString(R.string.error_blank_firstname));
                }

                if (UIUtilities.isEmpty(et_lastname)) {
                    if (validationError) {
                        validationErrorMessage.append(getResources().getString(R.string.error_join));
                    }
                    validationError = true;
                    validationErrorMessage.append(getResources().getString(R.string.error_blank_lastname));
                }

                if (isAllOfCheckboxIsNotChosen() == true) {
                    if (validationError) {
                        validationErrorMessage.append(getResources().getString(R.string.error_join));
                    }
                    validationError = true;
                    validationErrorMessage.append(getResources().getString(R.string.error_blank_checkboxes));
                }
                validationErrorMessage.append(getResources().getString(R.string.error_end));

                // If there is a validation error, display the error
                if (validationError) {
                    Toast.makeText(ProfileDetailsActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                PrivateDetails privateDetails = getCurrentProfileData();
                ParserUtilities parserUtilities = new ParserUtilities();
                parserUtilities.uploadObjectToParseAllUserTable(privateDetails);

                parserUtilities.show(privateDetails);


                Intent intent = new Intent(ProfileDetailsActivity.this, HomeActivity.class);
                startActivity(intent);

            }
        });

    }


}