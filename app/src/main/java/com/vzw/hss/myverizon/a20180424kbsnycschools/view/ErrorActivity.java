package com.vzw.hss.myverizon.a20180424kbsnycschools.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;

import com.vzw.hss.myverizon.a20180424kbsnycschools.R;

public class ErrorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
        String error = getIntent().getStringExtra("error");

        //Check if there is any custom message to be displayed as error.
        if (!TextUtils.isEmpty(error)){
            ((TextView)findViewById(R.id.text_error)).setText(error);
        }
    }

}
