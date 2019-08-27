package com.yemiekai.vedio_voice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.yemiekai.vedio_voice.utils.datas.DoctorInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DoctorInfoActivity extends AppCompatActivity {

    String BaseIP = "http://123.207.108.39:7001";
    DoctorInfo doctorInfo = new DoctorInfo();
    ImageView imageView = (ImageView) findViewById(R.id.Profile_photo);
    TextView intro = (TextView) findViewById(R.id.Intro);
    TextView detailInfo = (TextView) findViewById(R.id.DetailInfo);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_info);
        Intent intent = getIntent();
        String DoctorID = intent.getStringExtra("DoctorID");
        GetDoctorInfro(DoctorID);
        SetImageView();

    }

    private Thread GetDoctorInfro(final String DoctorID){
        return new Thread(){
            @Override
            public void run(){
                //第一步：实例化URL对象
                String address = BaseIP + "/android/doctor/one/" + DoctorID; //设置接口
                HttpURLConnection conn = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL(address);   //实例化URL对象
                    //实例化 HttpURLConnection对象
                    conn = (HttpURLConnection) url.openConnection();
                    //设置链接属性
                    conn.setRequestMethod("GET");//设置请求方法
                    conn.setReadTimeout(5000);//设置超时时间
                    int getnum = conn.getResponseCode();
                    if (getnum == 200) { //获取状态码 200表示连接成功
                        //获取输入流
                        InputStream in = conn.getInputStream();
                        //读取输入流
                        reader = new BufferedReader(new InputStreamReader(in));
                        StringBuilder response = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }
                        String res = response.toString();
                        try {
                            Gson gson = new Gson();
                            doctorInfo = gson.fromJson(res,DoctorInfo.class);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (conn != null) {
                        conn.disconnect();
                    }
                }
            }
        };
    }

    private void SetImageView(){
        if (doctorInfo.getAvatarUrl()!= null) {
            String urlcheck = doctorInfo.getAvatarUrl().toString();
            urlcheck = urlcheck.replace('\\', '/');
            String url = BaseIP + urlcheck;
            RequestOptions options = new RequestOptions()
                    .override(174,255)
                    .fitCenter();
            Glide.with(this)
                    .load(url)
                    .apply(options)
                    .into(imageView);//设置按钮图片
        }
        else
            imageView.setImageResource(R.drawable.sample_doctor);
    }
    private Thread SetInfo(){
        return new Thread(){
            @Override
            public void run(){
                String name = doctorInfo.getName();
                int sex = doctorInfo.getGender();
                String gender = null;
                if (sex == 1)
                    gender = "男";
                else
                    gender = "女";
                String post = doctorInfo.getPost();
                String title = doctorInfo.getTitle();
                String text = "姓名：" + name + "\r\n"
                            + "性别：" + gender + "\r\n"
                            + "职务：" + post + "\r\n"
                            + "职称：" + title;
                intro.setText(text);

                String resume = "个人简历" + doctorInfo.getResume();


            }
        };
    }


}
