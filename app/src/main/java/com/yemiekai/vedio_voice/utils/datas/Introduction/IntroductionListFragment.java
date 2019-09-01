package com.yemiekai.vedio_voice.utils.datas.Introduction;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.app.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yemiekai.vedio_voice.R;

public class IntroductionListFragment extends Fragment {

    static String BaseIP = "http://123.207.108.39:7001";
    String IntroductionId = "";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        IntroductionId = getArguments().getString("IntroductionId");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_introduction_list, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.intro_textView);
        textView.setText(IntroductionId);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

}
