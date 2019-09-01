package com.yemiekai.vedio_voice.utils.datas.Navigation;

import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yemiekai.vedio_voice.R;

public class NavigationListFragment extends Fragment {

    private String BaseIP = "http://123.207.108.39:7001";
    String Url;
    ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Url = getArguments().getString("Url");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // 加载/res/layout/目录下的fragment_book_detail.xml布局文件
        View rootView = inflater.inflate(R.layout.fragment_navigation_list, container, false);
        imageView = (ImageView) rootView.findViewById(R.id.navigation_ImageView);
        if (Url == null){
            imageView.setImageResource(R.drawable.sample_navigator);
        }
        else {
            Url = Url.replace("\\","/");
            Url = BaseIP + Url;
            Glide.with(getActivity())
                    .load(Url)
                    .into(imageView);//设置按钮图片
        }



        return rootView;
    }

}
