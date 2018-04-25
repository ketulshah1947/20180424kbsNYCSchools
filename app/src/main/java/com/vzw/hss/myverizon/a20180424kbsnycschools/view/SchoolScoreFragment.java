package com.vzw.hss.myverizon.a20180424kbsnycschools.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vzw.hss.myverizon.a20180424kbsnycschools.R;
import com.vzw.hss.myverizon.a20180424kbsnycschools.model.Score;

/**
 * Created by c0shak8 on 4/25/2018.
 */

public class SchoolScoreFragment extends Fragment {

    private TextView name;
    private TextView math;
    private TextView reading;
    private TextView writing;
    private TextView takers;
    private Score score;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Given more time I can create nice UI for scores. Wanted to focus more on functionality.
        View view = inflater.inflate(R.layout.fragment_score, container, false);
        name = (TextView) view.findViewById(R.id.text_name);
        math = (TextView) view.findViewById(R.id.text_math);
        reading = (TextView) view.findViewById(R.id.text_reading);
        writing = (TextView) view.findViewById(R.id.text_writing);
        takers = (TextView) view.findViewById(R.id.text_takers);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        score = (Score) getArguments().getParcelable("score");
        if (score == null) {
            showError();
            return;
        }
        name.setText(score.getName());
        math.setText(String.valueOf(score.getMath()));
        reading.setText(String.valueOf(score.getReading()));
        writing.setText(String.valueOf(score.getWriting()));
        takers.setText(String.valueOf(score.getTest_takers()));
    }

    private void showError() {
        ErrorFragment errorFragment = new ErrorFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, errorFragment, "ErrorFragment").commit();
    }

}
