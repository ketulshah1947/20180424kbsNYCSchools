package com.vzw.hss.myverizon.a20180424kbsnycschools.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vzw.hss.myverizon.a20180424kbsnycschools.R;

/**
 * Created by c0shak8 on 4/25/2018.
 */

public class ErrorFragment extends Fragment {

    private TextView error;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_error, container, false);
        error = (TextView) view.findViewById(R.id.text_error);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        error.setText("We don't have data for this school!");
    }

}
