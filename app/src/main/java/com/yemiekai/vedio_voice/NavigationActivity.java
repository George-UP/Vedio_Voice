package com.yemiekai.vedio_voice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yemiekai.vedio_voice.utils.datas.Navigation.Detail_Navigation;
import com.yemiekai.vedio_voice.utils.datas.Navigation.NavigationAll;
import com.yemiekai.vedio_voice.utils.datas.Navigation.NavigationListFragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 科室导航
 */
public class NavigationActivity extends BasicActivity {

    private String BaseIP = "http://123.207.108.39:7001";
    private ExpandableListView listView;  // 界面左边的科室列表

    NavigationAll navigationAll = new NavigationAll();
    List<NavigationAll.ResBean.RowsBean> mGroups = new ArrayList<>();
    List<List<Detail_Navigation.ResBean.FloorsBean>> mChilds = new ArrayList<>();


//    private String[] mGroups = {"总揽图","A楼", "B楼", "C楼", "D楼"};
//    private String[][] mChilds = {
//            {"总揽图","1F", "2F", "3F", "4F", "5F", "6F"},
//            {"总揽图","1F", "2F", "3F", "4F", "5F", "6F"},
//            {"总揽图","1F", "2F", "3F", "4F", "5F", "6F"},
//            {"总揽图","1F", "2F", "3F", "4F", "5F", "6F"}
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        listView = (ExpandableListView)findViewById(R.id.navigation_category_list);  // 界面左边的科室列表

        try {
            Thread getmGroups = GetmGroups();
            getmGroups.start();
            getmGroups.join();

            Thread getmChilds = GetmChilds();
            getmChilds.start();
            getmChilds.join();

        }catch (InterruptedException error){
            error.printStackTrace();
        }

        listView.setAdapter(new MyExpandableAdapter(mGroups, mChilds));
        listView.setOnChildClickListener(getChildClickListener());
    }

    //获取一级导航
    private Thread GetmGroups(){
        return new Thread(){
            @Override
            public void run(){
                //第一步：实例化URL对象
                String address= BaseIP + "/android/building/all"; //设置接口
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
                            navigationAll = gson.fromJson(res,NavigationAll.class);
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

    //获取所有二级导航(存在List中)
    private Thread GetmChilds(){
        return new Thread(){
            @Override
            public void run() {
                mGroups = navigationAll.getRes().getRows();
                for (int i = 0; i < mGroups.size() ; i++) {
                    //第一步：实例化URL对象
                    String address = BaseIP + "/android/building/one/" + mGroups.get(i).getId(); //设置接口
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
                                Detail_Navigation DNavigation = new Detail_Navigation();
                                Gson gson = new Gson();
                                DNavigation = gson.fromJson(res,Detail_Navigation.class);
                                Detail_Navigation.ResBean.FloorsBean first = new Detail_Navigation.ResBean.FloorsBean();
                                first.setId(DNavigation.getRes().get(0).getId());
                                first.setName(0);
                                first.setPhotoUrl(DNavigation.getRes().get(0).getPhotoUrl());
                                List<Detail_Navigation.ResBean.FloorsBean> list = new ArrayList<>();
                                list = DNavigation.getRes().get(0).getFloors();
                                list.add(0,first);
                                mChilds.add(list);
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
            }
        };

    }




    // 点击了列表中某个科室
    private ExpandableListView.OnChildClickListener getChildClickListener(){
        return new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View view,
                                        int groupPosition, int childPosition, long id) {

                // 创建Bundle，准备向Fragment传入参数, 传入点了哪个科室
                Bundle arguments = new Bundle();
                arguments.putString("Url",mChilds.get(groupPosition).get(childPosition).getPhotoUrl());

                /**经过测试, 这里new了Fragment并启动后, 原来的会被销毁, 所以可以不断地new, 不会增加内存*/
                NavigationListFragment fragment = new NavigationListFragment();
                fragment.setArguments(arguments);  // 向Fragment传入参数

                // 使用fragment替换doctor_detail_container容器当前显示的Fragment
                getFragmentManager().beginTransaction()
                        .replace(R.id.navigation_detail_container, fragment)
                        .commit();
                return false;
            }
        };
    }

    // 科室列表的适配器
    class MyExpandableAdapter extends BaseExpandableListAdapter {
//        private String[] groups = {"总揽图","A楼", "B楼", "C楼", "D楼"};
//        private String[][] childs = {
//                {"总揽图","1F", "2F", "3F", "4F", "5F", "6F"},
//                {"总揽图","1F", "2F", "3F", "4F", "5F", "6F"},
//                {"总揽图","1F", "2F", "3F", "4F", "5F", "6F"},
//                {"总揽图","1F", "2F", "3F", "4F", "5F", "6F"}
//        };

        private List<NavigationAll.ResBean.RowsBean> groups = new ArrayList<>();
        private List<List<Detail_Navigation.ResBean.FloorsBean>> childs = new ArrayList<>();

        public MyExpandableAdapter(){

        }

        public MyExpandableAdapter(List<NavigationAll.ResBean.RowsBean> groups, List<List<Detail_Navigation.ResBean.FloorsBean>> childs){
            this.groups = groups;
            this.childs = childs;
        }

        @Override
        public View getGroupView(int index, boolean arg1, View view, ViewGroup parent) {
            if(view == null){
                view = getLayoutInflater().inflate(R.layout.layout_navigation_category_first, parent, false);
            }
            TextView tv = (TextView)view.findViewById(R.id.navigation_category_first_title);
            tv.setText(groups.get(index).getName());
            return view;
        }

        @Override
        public View getChildView(int index1, int index2, boolean arg2, View view, ViewGroup parent) {
            if(view == null){
                view = getLayoutInflater().inflate(R.layout.layout_navigation_category_second, parent, false);
            }
            TextView tv = (TextView)view.findViewById(R.id.navigation_category_second_title);
            int text = childs.get(index1).get(index2).getName();
            tv.setText(String.valueOf(text));
            return view;
        }


        @Override
        public boolean areAllItemsEnabled() { return true; }

        @Override
        public Object getChild(int index1, int index2) {
            return childs.get(index1).get(index2);
        }

        @Override
        public long getChildId(int index1, int index2) {
            return index2;
        }

        @Override
        public int getChildrenCount(int index) {
            return childs.get(index).size();
        }

        @Override
        public long getCombinedChildId(long arg0, long arg1) { return 0; }

        @Override
        public long getCombinedGroupId(long arg0) { return 0; }

        @Override
        public Object getGroup(int index) {
            return groups.get(index);
        }

        @Override
        public int getGroupCount() {
            return groups.size();
        }

        @Override
        public long getGroupId(int index) {
            return index;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

}
