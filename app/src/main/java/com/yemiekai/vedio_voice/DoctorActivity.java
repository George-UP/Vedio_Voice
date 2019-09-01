package com.yemiekai.vedio_voice;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.yemiekai.vedio_voice.utils.datas.Doctor.DoctorListFragment;

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
 * (1)ExpandableListView
 *
 * (2)重写BaseExpandableListAdapter实现Group和Child上的按钮均可点击
 * https://blog.csdn.net/tt18949344290/article/details/64479490
 *
 * (3)Android使用RecyclerView实现树形列表升级版
 * https://blog.csdn.net/q1113225201/article/details/79328247
 *
 * (4)Android 多级菜单联动操作
 * https://blog.csdn.net/hzy670800844/article/details/62037033
 */

public class DoctorActivity extends BasicActivity {
    private String BaseIP = "http://123.207.108.39:7001";
    private ExpandableListView listView;  // 界面左边的科室列表
    //private String[] mGroups = {"名医馆", "内科", "外科", "医技科室"};
    //一级科室
    private String[] mGroups = null;
    //二级科室
    private List<List<String>> mChildstemp = null;
    private String[][] mChilds = null;
    private ArrayList<Integer> mChild = new ArrayList<>();
    private Integer mChildsNum = 0;
    //二级科室下医生信息预览
    public ArrayList<String> mAllDoctorsPreview = null;

    //获取一级科室
    private Thread GetmGroups(){
        return new Thread(){
            @Override
            public void run(){
                //第一步：实例化URL对象
                String address= BaseIP + "/android/office/first"; //设置接口
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
                        String result = "";
                        try {
                            JSONArray jsonArray = new JSONArray(response.toString());
                            mGroups = new String[jsonArray.length()];
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String name = jsonObject.getString("name");
                                mGroups[i] = name;
                            }
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

    //获取所有二级科室(存在List中)
    private Thread GetmChilds(){
        return new Thread(){
            @Override
            public void run() {
                mChildstemp = new ArrayList();
                for (int j = 1; j < mGroups.length + 1; j++) {
                    //第一步：实例化URL对象
                    String address = BaseIP + "/android/office/second/" + j; //设置接口
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
                            String result = "";
                            try {
                                JSONArray jsonArray = new JSONArray(response.toString());
                                List<String> temp = new ArrayList<String>();
                                mChild.add(jsonArray.length());
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    mChildsNum ++;
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    String name = jsonObject.getString("name");
                                    temp.add(name);
                                }
                                mChildstemp.add(temp);
                                temp = new ArrayList<>();
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
                mChilds = new String[mChildstemp.size()][];
                String[] temp = null;
                for (int i = 0;i<mChildstemp.size();i++){
                    temp = new String[mChildstemp.get(i).size()];
                    mChildstemp.get(i).toArray(temp);
                    mChilds[i] = temp;
                }
            }
        };

    }

    //获取所有二级科室的所有医生信息预览
    private Thread GetDoctorPreviewList(){
        return new Thread(){
            @Override
            public void run() {
                mAllDoctorsPreview = new ArrayList<>();
                for (int i = 1; i < mChildsNum + 1; i++) {
                    //第一步：实例化URL对象
                    String address = BaseIP + "/android/doctor/all/" + i; //设置接口
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
                                mAllDoctorsPreview.add(res);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        try {
            Thread getmGroups = GetmGroups();
            getmGroups.start();
            getmGroups.join();

            Thread getmChilds = GetmChilds();
            getmChilds.start();
            getmChilds.join();

            Thread getAllDoctorsPreview = GetDoctorPreviewList();
            getAllDoctorsPreview.start();
            getAllDoctorsPreview.join();

        }catch (InterruptedException error){
            error.printStackTrace();
        }
        listView = (ExpandableListView)findViewById(R.id.doctor_category_list);  // 界面左边的科室列表
        listView.setAdapter(new MyExpandableAdapter(mGroups, mChilds));
        listView.setOnChildClickListener(getChildClickListener());
    }

    // 点击了列表中某个科室
    private ExpandableListView.OnChildClickListener getChildClickListener(){
        return new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View view,
                                        int groupPosition, int childPosition, long id) {

                // 创建Bundle，准备向Fragment传入参数, 传入点了哪个科室
                Bundle arguments = new Bundle();
                arguments.putInt(DoctorListFragment.FIRST_CATEGORY_INDEX, groupPosition);
                arguments.putInt(DoctorListFragment.SECOND_CATEGORY_INDEX, childPosition);
                arguments.putStringArrayList("allDoctorsPreviews",mAllDoctorsPreview);
                arguments.putIntegerArrayList("mChild",mChild);

                /**经过测试, 这里new了Fragment并启动后, 原来的会被销毁, 所以可以不断地new, 不会增加内存*/
                DoctorListFragment fragment = new DoctorListFragment();
                fragment.setArguments(arguments);  // 向Fragment传入参数

                // 使用fragment替换doctor_detail_container容器当前显示的Fragment
                getFragmentManager().beginTransaction()
                        .replace(R.id.doctor_detail_container, fragment)
                        .commit();
                return false;
            }
        };
    }

    // 科室列表的适配器
    class MyExpandableAdapter extends BaseExpandableListAdapter{
        private String[] groups = {"名医馆", "内科", "外科", "医技科室"};
        private String[][] childs = {
                {"心血管内一科", "内分泌科", "消化内科", "神经内科", "全科医学科", "儿科", "中医科",
                 "康复医学科", "胃肠疝外科", "肝胆胰外科", "甲状腺乳腺外科", "血管疝外科", "关节外科",
                 "脊柱外科", "神经外科", "心胸外科", "男性医学科", "烧伤整形外科", "眼科", "耳鼻咽喉头颈外科",
                 "医学影像科", "微创介入科"},
                {"心血管内一科", "心血管内二科", "消化内科", "呼吸与危重症医学科", "老年病科", "肿瘤科",
                 "内分泌科" , "风湿科", "神经内科", "肾内科", "血液内科", "中医科", "儿科", "医保科",
                 "感染性疾病科" , "脑卒中筛查基地工作办公室", "全科医学科", "心理科"},
                {"胃肠疝外科", "脊柱外科", "运动医学科", "神经外科", "泌尿外科", "心胸外科", "口腔颌面外科",
                 "口腔科", "麻醉科", "疼痛科", "妇科", "产科", "眼科", "耳鼻咽喉头颈外科", "皮肤科", "急诊科",
                 "ICU", "烧伤整形外科", "生殖医学科", "肝胆胰外科", "甲状腺乳腺外科", "血管疝外科",
                 "急诊创伤外科", "关节外科", "男性医学科"},
                {"康复医学科", "微创介入科", "检验科", "药剂科", "器械科", "超声科", "病理科", "医学影像科",
                 "体检科", "输血科","门诊部","静脉药物配置中心","核医学科","放射科"}
        };

        public MyExpandableAdapter(){

        }

        public MyExpandableAdapter(String[] groups, String[][] childs){
            this.groups = groups;
            this.childs = childs;
        }

        @Override
        public View getGroupView(int index, boolean arg1, View view, ViewGroup parent) {
            if(view == null){
                view = getLayoutInflater().inflate(R.layout.layout_doctor_category_first, parent, false);
            }
            TextView tv = (TextView)view.findViewById(R.id.doctor_category_first_title);
            tv.setText(groups[index]);
            return view;
        }

        @Override
        public View getChildView(int index1, int index2, boolean arg2, View view, ViewGroup parent) {
            if(view == null){
                view = getLayoutInflater().inflate(R.layout.layout_doctor_category_second, parent, false);
            }
            TextView tv = (TextView)view.findViewById(R.id.doctor_category_second_title);
            tv.setText(childs[index1][index2]);
            return view;
        }


        @Override
        public boolean areAllItemsEnabled() { return true; }

        @Override
        public Object getChild(int index1, int index2) {
            return childs[index1][index2];
        }

        @Override
        public long getChildId(int index1, int index2) {
            return index2;
        }

        @Override
        public int getChildrenCount(int index) {
            return childs[index].length;
        }

        @Override
        public long getCombinedChildId(long arg0, long arg1) { return 0; }

        @Override
        public long getCombinedGroupId(long arg0) { return 0; }

        @Override
        public Object getGroup(int index) {
            return groups[index];
        }

        @Override
        public int getGroupCount() {
            return groups.length;
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
