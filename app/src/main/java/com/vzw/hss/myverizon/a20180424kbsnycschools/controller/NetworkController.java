package com.vzw.hss.myverizon.a20180424kbsnycschools.controller;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vzw.hss.myverizon.a20180424kbsnycschools.storage.PersistentStorage;
import com.vzw.hss.myverizon.a20180424kbsnycschools.util.Constants;
import com.vzw.hss.myverizon.a20180424kbsnycschools.util.Utils;
import com.vzw.hss.myverizon.a20180424kbsnycschools.view.ErrorActivity;

import static com.vzw.hss.myverizon.a20180424kbsnycschools.util.Constants.TAG;

public class NetworkController implements Response.ErrorListener, Response.Listener<String> {

    private Context context;
    private static RequestQueue requestQueue;
    private NetworkCallBack callBack;

    public NetworkController(Context context) {
        this.context = context;
        if (requestQueue == null){
            //We just need one queue. we will pass all our request over this queue.
            requestQueue = Volley.newRequestQueue(this.context);
        }
    }

    public void requestData(@NonNull NetworkCallBack callBack){ //This is callback to let caller know that data is ready.
        this.callBack = callBack;
        StringRequest request = new StringRequest(Request.Method.GET, Constants.URL_SCORES, this, this);
        requestQueue.add(request);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e(TAG, error.getMessage());
        Log.d(TAG, "Servers are down.");
        showError("Looks like our servers are down!\nTry again after some time.");
    }

    private void showError(String error) {
        Intent errorIntent = new Intent(context, ErrorActivity.class);
        errorIntent.putExtra("error", error);
        errorIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(errorIntent);
    }

    @Override
    public void onResponse(String response) {
        Log.d(TAG, "Got data from server, save it in preference for offline use.");
        PersistentStorage.saveData(context, response);
        Log.d(TAG, "Initialize hashmap from latest data.");
        Utils.setScoreMap(response);
        if (Utils.getScoreMap() != null) {  //check if hashmap is loaded properly.
            Log.d(TAG, "Server data is in expected format.");
            callBack.onDataAvailable();
        } else {
            Log.e(TAG, "Server data is not in expected format.");
            showError("Looks like our servers are down!\nTry again after some time.");
        }
    }

    public interface NetworkCallBack {
        public void onDataAvailable();
    }
}
