package com.yemiekai.vedio_voice.utils.datas.Introduction;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.app.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.yemiekai.vedio_voice.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class IntroductionListFragment extends Fragment {

    static String BaseIP = "http://123.207.108.39:7001";
    Detail_Introduction dintroduction = new Detail_Introduction();
    String IntroductionId = "";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        IntroductionId = getArguments().getString("IntroductionId");
        try {
            Thread getDIntro = GetDIntro(IntroductionId);
            getDIntro.start();
            getDIntro.join();

        }catch (InterruptedException error){
            error.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_introduction_list, container, false);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.intro_imageView);
        TextView textView = (TextView) rootView.findViewById(R.id.intro_textView);
        String des = dintroduction.getRes().getDescription();
        textView.setText(des);
        if (!dintroduction.getRes().getIntroducePhotos().isEmpty()) {
            String urlcheck = dintroduction.getRes().getIntroducePhotos().get(0).getPhotoUrl();
            if(!urlcheck.isEmpty()){
                urlcheck = urlcheck.replace("\\","/");
                String url = BaseIP + urlcheck;
                RequestOptions options = new RequestOptions()
                        .override(180,285)
                        .fitCenter();
                Glide.with(getActivity())
                        .load(url)
                        .apply(options)
                        .into(imageView);//设置按钮图片
            }
        }


        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

    private Thread GetDIntro(final String IntroductionId){
        return new Thread(){
            @Override
            public void run(){
                //第一步：实例化URL对象
                String address= BaseIP + "/android/introduction/one/" + IntroductionId; //设置接口
                HttpURLConnection conn = null;
                BufferedReader reader = null;
                try {
                    URL url =new URL(address);   //实例化URL对象
                    //实例化 HttpURLConnection对象
                    conn = (HttpURLConnection) url.openConnection();
                    //设置链接属性
                    conn.setRequestMethod("GET");//设置请求方法
                    conn.setReadTimeout(5000);//设置超时时间
                    int getnum = conn.getResponseCode();
                    if(getnum==200){ //获取状态码 200表示连接成功
                        //获取输入流
                        InputStream in= conn.getInputStream();
                        //读取输入流
                        reader = new BufferedReader(new InputStreamReader(in));
                        StringBuilder response = new StringBuilder();
                        String line;
                        while((line = reader.readLine()) != null ){
                            response.append(line);
                        }
                        String res = response.toString();
                        try {
                            Gson gson = new Gson();
                            dintroduction = new Gson().fromJson(res, Detail_Introduction.class);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }finally {
                    if(reader != null){
                        try {
                            reader.close();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    if (conn != null){
                        conn.disconnect();
                    }
                }
            }
        };
    }


}
