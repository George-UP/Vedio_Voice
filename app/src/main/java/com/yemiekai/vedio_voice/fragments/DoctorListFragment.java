package com.yemiekai.vedio_voice.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.yemiekai.vedio_voice.DoctorActivity;
import com.yemiekai.vedio_voice.DoctorInfoActivity;
import com.yemiekai.vedio_voice.R;
import com.yemiekai.vedio_voice.utils.datas.AllDoctorsPreview;

import java.util.ArrayList;
import java.util.List;

/**
 * 这个Fragment的布局是一个RecyclerView, 一个垂直滑动的列表, 每一行显示4个医生
 * https://blog.csdn.net/cqx13763055264/article/details/79573400 ——RecyclerView例子
 * https://blog.csdn.net/hanxiongwei/article/details/86625333 ——加载图片的框架Glide使用简介
 * */
public class DoctorListFragment extends Fragment
{
	public static final String FIRST_CATEGORY_INDEX = "first_category_index";  // 一级菜单索引(名医馆:0 内科:1 外科:1 ...)
	public static final String SECOND_CATEGORY_INDEX = "second_category_index";  // 二级菜单索引(心血管内科:0 内分泌科:1 ...)

	private ArrayList<Integer> mChild = null;
	private ArrayList<AllDoctorsPreview> allDoctorsPreviews = null; //传进来的二级科室所有医生的预览信息
	private int first_category_index;  // 传进来的一级菜单索引号
	private int second_category_index;  // 传进来的二级菜单索引号
	private boolean isIndexValid = false;  // 是否传来了科室索引, 或者科室索引是否有效
	RecyclerView recyclerView;
	LinearLayoutManager layoutManager;
	static String BaseIP = "http://123.207.108.39:7001";
	String DoctorID = "";
    public ImageButton mButton1;


	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		ArrayList<String> change = new ArrayList<>();
		change = getArguments().getStringArrayList("allDoctorsPreviews");
		mChild = getArguments().getIntegerArrayList("mChild");
		GetDoctorPreviewList(change);
		if (getArguments().containsKey(FIRST_CATEGORY_INDEX)
                && getArguments().containsKey(SECOND_CATEGORY_INDEX))
		{
			// 得到科室索引, 根据这个找到医生并且显示
			isIndexValid = true;
			first_category_index = getArguments().getInt(FIRST_CATEGORY_INDEX);
			second_category_index = getArguments().getInt(SECOND_CATEGORY_INDEX);
			/*
			 * todo: 根据科室索引找到该科室所有医生的信息, 以便在adapter里面设置显示各个医生信息, 多少行
			 *       (暂定每行4个医生, 见layout_doctors.xml)
			 *       以及每个医生的信息
			 */
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// 加载/res/layout/目录下的fragment_book_detail.xml布局文件
		View rootView = inflater.inflate(R.layout.fragment_doctors_list, container, false);
		recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_doctors_recycler);

		// 设置布局管理器
		layoutManager = new GridLayoutManager(this.getActivity(),
				4,RecyclerView.VERTICAL,false);
		recyclerView.setLayoutManager(layoutManager);

		// 设置适配器
		recyclerView.setAdapter(new MyDoctorAdapter());
        mButton1 = (ImageButton) getActivity().findViewById(R.id.layout_doctors_button1);
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),DoctorInfoActivity.class);
                intent.putExtra("DoctorID",DoctorID);
                startActivity(intent);
            }
        });

		return rootView;
	}

	public class MyDoctorAdapter extends RecyclerView.Adapter<MyDoctorAdapter.ViewHolder> {

		class ViewHolder extends RecyclerView.ViewHolder {
            ImageButton mButton1;
            TextView mTextView1;

			public ViewHolder(View itemView) {
				super(itemView);
				mButton1 = itemView.findViewById(R.id.layout_doctors_button1);
				mTextView1 = itemView.findViewById(R.id.layout_doctors_text1);

			}
		}

		public MyDoctorAdapter(){
			// 构造函数, 暂时没有传参
		}

		@Override
		public @NonNull ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_doctors,parent,false);
			return new ViewHolder(view);
		}

		@Override
		public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

            int x = 0;
            int y = second_category_index;
            int num = 0;
            for (int i = 0;i < first_category_index;i++){
                x += mChild.get(i);
            }
            x += y;

            AllDoctorsPreview.RowsBean Doctor = allDoctorsPreviews.get(x).getRows().get(position);

            if (Doctor.getAvatarUrl()!= null) {
				String urlcheck = Doctor.getAvatarUrl().toString();
				urlcheck = urlcheck.replace('\\', '/');
				String url = BaseIP + urlcheck;
				RequestOptions options = new RequestOptions()
//                        .override(174,255)
						.override(180,285)
                        .fitCenter();
//                Glide.with(fragment).load(url).into(holder.mButton1);
				Glide.with(getActivity())
                        .load(url)
                        .apply(options)
                        .into(holder.mButton1);//设置按钮图片
			}
            else
            	holder.mButton1.setImageResource(R.drawable.sample_doctor);


            String name = Doctor.getName();
            String post = Doctor.getPost();
            String title = Doctor.getTitle();
            String text = name + "\r\n" + post + "\r\n" + title;

			holder.mTextView1.setText(text);  // 设置文字

            DoctorID = Doctor.getId();



			// todo:
			//      设置每个按钮的点击响应时间, 切换fragment, 显示医生详细信息
			//      切换时要传入当前fragment的科室索引, 在客户按下"返回"按钮时要切换回现在的fragment
			//      (因为切换了fragment后, 现在的fragment好像被destroy了)
		}

		@Override
		public int getItemCount() {
			/*
			 * todo 这是设置列表的长度。  列表一行有4个医生
			 *		到时候根据多少医生, 返回值为 (医生总数/4)
			 * */
			int x = 0;
			int y = second_category_index;
			int num = 0;
			for (int i = 0;i < first_category_index;i++){
				x += mChild.get(i);
			}
			x += y;
			num = allDoctorsPreviews.get(x).getCount();
            return num;
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

	}

	//将json数据转成相应List（二级科室所有医生的预览信息）
	private void GetDoctorPreviewList(ArrayList<String> change){
		allDoctorsPreviews = new ArrayList<>();
		for(int i = 0;i < change.size();i++){
			Gson gson = new Gson();
			AllDoctorsPreview allDoctorsPreview = new AllDoctorsPreview();
			allDoctorsPreview = gson.fromJson(change.get(i),AllDoctorsPreview.class);
			allDoctorsPreviews.add(allDoctorsPreview);
		}
	}





}
