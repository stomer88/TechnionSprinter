package com.example.izzy.sprinter.Utilities;

import android.widget.EditText;

/**
 * Created by izzy on 25/05/15.
 */
public class UIUtilities {


    static public boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }


    }
}
