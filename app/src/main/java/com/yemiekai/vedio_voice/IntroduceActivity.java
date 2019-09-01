package com.yemiekai.vedio_voice;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yemiekai.vedio_voice.utils.datas.Introduction.Introduction;
import com.yemiekai.vedio_voice.utils.datas.Introduction.IntroductionListFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 医院介绍
 */
public class IntroduceActivity extends BasicActivity {

    private String[] introList = {"医院介绍1","医院介绍2","医院介绍3","医院介绍4","医院介绍5"};

    private String BaseIP = "http://123.207.108.39:7001";
    private TextView textView;
    private ListView listView; // 界面左边的列表
    Introduction introduction = new Introduction();
    List<Introduction.ResBean.RowsBean> rows = new ArrayList<>();
    String IntroductionId = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);
        listView = findViewById(R.id.introduction_list);

        try {
            Thread getmGroups = GetmGroups();
            getmGroups.start();
            getmGroups.join();

        }catch (InterruptedException error){
            error.printStackTrace();
        }

        listView.setAdapter(new ListAdapter(introduction));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Introduction.ResBean.RowsBean intr = new Introduction.ResBean.RowsBean();
                intr = introduction.getRes().getRows().get(position);
                textView = (TextView) view.findViewById(R.id.intro_first_title);
                IntroductionId = intr.getId();
                Bundle arguments = new Bundle();
                arguments.putString("IntroductionId",IntroductionId);

                IntroductionListFragment fragment = new IntroductionListFragment();
                fragment.setArguments(arguments);
                getFragmentManager().beginTransaction()
                        .replace(R.id.introduction_detail_container,fragment)
                        .commit();
            }
        });
    }

    private Thread GetmGroups(){
        return new Thread(){
            @Override
            public void run(){
                //第一步：实例化URL对象
                String address= BaseIP + "/android/introduction/all"; //设置接口
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
                            introduction = new Gson().fromJson(res, Introduction.class);
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


    class ListAdapter extends BaseAdapter{
//        private String[] introList = {"医院介绍1","医院介绍2","医院介绍3","医院介绍4","医院介绍5"};
        private Introduction introduction = new Introduction();
        private List<Introduction.ResBean.RowsBean> rows = new ArrayList<>();


        public ListAdapter(Introduction introduction){
            this.introduction = introduction;
            rows = introduction.getRes().getRows();
        }


        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return rows.size();//数目
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = IntroduceActivity.this.getLayoutInflater();
            View view;

            if (convertView==null) {
                //因为getView()返回的对象，adapter会自动赋给ListView
                view = inflater.inflate(R.layout.layout_intro_first, null);
            }else{
                view=convertView;
                Log.i("info","有缓存，不需要重新生成"+position);
            }
            textView = (TextView) view.findViewById(R.id.intro_first_title);//找到Textviewname
            textView.setText(rows.get(position).getMessage());
            return view;
        }



        @Override
        public long getItemId(int position) {//取在列表中与指定索引对应的行id
            return 0;
        }
        @Override
        public Object getItem(int position) {//获取数据集中与指定索引对应的数据项
            return null;
        }
    }

}
