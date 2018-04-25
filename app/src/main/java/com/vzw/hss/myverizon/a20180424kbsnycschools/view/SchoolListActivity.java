package com.vzw.hss.myverizon.a20180424kbsnycschools.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.vzw.hss.myverizon.a20180424kbsnycschools.R;
import com.vzw.hss.myverizon.a20180424kbsnycschools.util.Utils;

import java.util.ArrayList;

/**
 * Created by c0shak8 on 4/25/2018.
 */

public class SchoolListActivity extends AppCompatActivity {

//    SchoolDataSet schoolDataSet = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //We will use fragment mechanism as it is best for this use case.
        launchSchoolListFragment();
    }

    private void launchSchoolListFragment() {
        SchoolListFragment schoolListFragment = new SchoolListFragment();
        ArrayList<String> list = new ArrayList<>();
        list.addAll(Utils.getScoreMap().keySet());
        Bundle bundle = new Bundle();

        //list is static field in this app. I don't need to pass it as argument,
        // but this is just to show that I know how to pass data to fragment.
        bundle.putStringArrayList("schoolList", list);
        schoolListFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, schoolListFragment, "SchoolListFragment").commit();
    }

}
