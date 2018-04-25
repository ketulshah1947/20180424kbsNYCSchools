package com.vzw.hss.myverizon.a20180424kbsnycschools.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.vzw.hss.myverizon.a20180424kbsnycschools.R;
import com.vzw.hss.myverizon.a20180424kbsnycschools.controller.NetworkController;
import com.vzw.hss.myverizon.a20180424kbsnycschools.storage.PersistentStorage;
import com.vzw.hss.myverizon.a20180424kbsnycschools.util.Constants;
import com.vzw.hss.myverizon.a20180424kbsnycschools.util.Utils;


/*
This activity downloads and parses data to be displayed.
 */
public class SplashActivity extends AppCompatActivity implements NetworkController.NetworkCallBack {


    private NetworkController networkController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Utils.isNetworkConnected(this)) {
            //We are connected to Internet, get latest data.
            networkController = new NetworkController(this);
            networkController.requestData(this);
        } else {
            //We are not connected to Internet, check if we have any preserved data.
            Log.d(Constants.TAG, "We are not connected to Internet, check if we have any preserved data.");
            String persistentResponse = PersistentStorage.getData(this);
            if (persistentResponse != null) {  //We do have preserved data.
                Log.d(Constants.TAG, "We do have preserved data. Initialize hashmap");
                Utils.setScoreMap(persistentResponse);
                //launch list activity with preserved data.
                Log.d(Constants.TAG, "launch list activity with preserved data.");
                launchListActivity();
            } else {
                //We do not have preserved data. Show error.
                Log.d(Constants.TAG, "We do not have preserved data. Show error.");
                launchErrorActivity();
            }
        }
    }

    private void launchErrorActivity() {
        Intent errorIntent = new Intent(this, ErrorActivity.class);
        errorIntent.putExtra("error", "Internet not available.");
        this.startActivity(errorIntent);
        finish();
    }

    @Override
    public void onDataAvailable() {
        launchListActivity();
    }

    private void launchListActivity() {
        Intent intent = new Intent(this, SchoolListActivity.class);
        startActivity(intent);
        finish();
    }
}
