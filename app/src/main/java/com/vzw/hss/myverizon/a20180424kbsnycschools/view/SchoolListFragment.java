package com.vzw.hss.myverizon.a20180424kbsnycschools.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vzw.hss.myverizon.a20180424kbsnycschools.R;
import com.vzw.hss.myverizon.a20180424kbsnycschools.controller.RecyclerItemClickListener;
import com.vzw.hss.myverizon.a20180424kbsnycschools.controller.SchoolAdapter;
import com.vzw.hss.myverizon.a20180424kbsnycschools.model.Score;
import com.vzw.hss.myverizon.a20180424kbsnycschools.util.Utils;

import java.util.ArrayList;

import static com.vzw.hss.myverizon.a20180424kbsnycschools.util.Constants.TAG;

/**
 * Created by c0shak8 on 4/25/2018.
 */

public class SchoolListFragment extends Fragment {

    ArrayList<String> schoolList = null;
    private RecyclerView schoolListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Given more time I can create nice UI for list.
        View view = inflater.inflate(R.layout.fragment_school_list, container, false);
        schoolListView = view.findViewById(R.id.list);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        schoolList = getArguments().getStringArrayList("schoolList");
        if (schoolList == null){
            //we shouldn't be here in any case.
            Log.e(TAG, "Unexpected error. List is empty.");
            showError();
            return;
        }
        SchoolAdapter adapter = new SchoolAdapter(schoolList);
        //initialize layoutmanager for recycler view.
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        schoolListView.setLayoutManager(mLayoutManager);
        //just to add nice user experience.
        schoolListView.setItemAnimator(new DefaultItemAnimator());
        schoolListView.addItemDecoration(new DividerItemDecorator(ContextCompat.getDrawable(getActivity(),
                R.drawable.divider)));
        schoolListView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(),
                new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Handle click events on our list.
                schoolList.get(position);
                launchScholDetailFragment(schoolList.get(position));
            }
        }));
        schoolListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void launchScholDetailFragment(String name) {
        SchoolScoreFragment schoolScoreFragment = new SchoolScoreFragment();
        Bundle bundle = new Bundle();
        Score score = Utils.getScoreMap().get(name);
        if (score != null) {
            bundle.putParcelable("score", score);
            schoolScoreFragment.setArguments(bundle);
            //reason for adding back to stack is because we want to come back to list fragment.
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, schoolScoreFragment, "SchoolScoreFragment")
                    .addToBackStack("SchoolScoreFragment").commit();
        } else {
            //impossible use case. Shouldn't be here.
            Log.d(TAG, "We don't have data for this school : "+score.getName());
            Toast.makeText(getActivity(), "We don't have data for this school!", Toast.LENGTH_SHORT).show();
        }
    }

    private void showError() {
        Intent errorIntent = new Intent(getActivity(), ErrorActivity.class);
        startActivity(errorIntent);
    }

}
