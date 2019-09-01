package com.yemiekai.vedio_voice.utils.datas.Navigation;

import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yemiekai.vedio_voice.R;

public class NavigationListFragment extends Fragment {


    public static final String FIRST_CATEGORY_INDEX = "first_category_index";  // 一级菜单索引(哪栋建筑)
    public static final String SECOND_CATEGORY_INDEX = "second_category_index";  // 二级菜单索引(几楼)

    private int first_category_index;  // 传进来的一级菜单索引号
    private int second_category_index;  // 传进来的二级菜单索引号
    private boolean isIndexValid = false;  // 是否传来了索引
    ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(FIRST_CATEGORY_INDEX) && getArguments().containsKey(SECOND_CATEGORY_INDEX))
        {
            // 得到l楼层索引, 根据这个找到楼层并且显示
            isIndexValid = true;
            first_category_index = getArguments().getInt(FIRST_CATEGORY_INDEX);
            second_category_index = getArguments().getInt(SECOND_CATEGORY_INDEX);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // 加载/res/layout/目录下的fragment_book_detail.xml布局文件
        View rootView = inflater.inflate(R.layout.fragment_navigation_list, container, false);
        imageView = (ImageView) rootView.findViewById(R.id.navigation_ImageView);
        imageView.setImageResource(R.drawable.sample_navigator);


        return rootView;
    }

}
