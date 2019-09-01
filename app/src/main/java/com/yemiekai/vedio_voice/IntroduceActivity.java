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

import com.yemiekai.vedio_voice.utils.datas.Introduction.IntroductionListFragment;

import java.util.List;

/**
 * 医院介绍
 */
public class IntroduceActivity extends BasicActivity {

    private String[] introList = {"医院介绍1","医院介绍2","医院介绍3","医院介绍4","医院介绍5"};

    private String BaseIP = "http://123.207.108.39:7001";
    private TextView textView;
    private ListView listView; // 界面左边的列表
    String IntroductionId = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);
        listView = findViewById(R.id.introduction_list);
        listView.setAdapter(new ListAdapter(introList));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                textView = (TextView) view.findViewById(R.id.intro_first_title);
                IntroductionId = textView.getText().toString();
                Toast.makeText(IntroduceActivity.this,IntroductionId,Toast.LENGTH_SHORT).show();
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

    class ListAdapter extends BaseAdapter{
        private String[] introList = {"医院介绍1","医院介绍2","医院介绍3","医院介绍4","医院介绍5"};

        public ListAdapter(String[] groups){
            this.introList = groups;
        }


        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return introList.length;//数目
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
            textView.setText(introList[position]);
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
