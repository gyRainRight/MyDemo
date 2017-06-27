package com.gy.mydemo.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.gy.mydemo.IListener.MyItemClickListener;
import com.gy.mydemo.R;
import com.gy.mydemo.bean.NewData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 名 on 2017/5/9.
 */

public class FirstFragmen extends Fragment implements MyItemClickListener{
    private RecyclerView mRecyclerView;
     private GalleryAdapter mAdapter;
//    private List<Integer> mDatas;
    private List<NewData> mDatas;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=LayoutInflater.from(getActivity()).inflate(R.layout.fragment_shouye,container,false);
//        initDatas(view);
        //得到控件

        return view;
    }



















    private void initDatas(View view)
    {
        mRecyclerView = (RecyclerView)view.findViewById(R.id.id_recyclerview_horizontal);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        //设置适配器
        mAdapter = new GalleryAdapter(getContext(), mDatas);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
       mDatas=new ArrayList<NewData>();
        for (int i=0;i<10;i++){
           NewData newData=new NewData();
            newData.setName("高雨");
            newData.setCheck(false);
            mDatas.add(i,newData);
        }

    }

    @Override
    public void OnItemClick(View view, int postion) {
        NewData integer=mDatas.get(postion);
        if (integer!=null){
            integer.setCheck(true);
            mAdapter.getIsSelected().put(postion,true);
            mAdapter.notifyDataSetChanged();

        }
    }

    public class GalleryAdapter extends
            RecyclerView.Adapter<GalleryAdapter.ViewHolder>
    {
        private LayoutInflater mInflater;
        private List<NewData> mDatas;
        private MyItemClickListener mItemClickListener;
        private HashMap<Integer, Boolean> isSelected;
        public GalleryAdapter(Context context, List<NewData> datats)
        {
            mInflater = LayoutInflater.from(context);
            mDatas = datats;
            isSelected = new HashMap<Integer, Boolean>();
            initDate();
        }

        public void setIsSelected(HashMap<Integer, Boolean> isSelected) {
            this.isSelected = isSelected;
        }
        public  HashMap<Integer, Boolean> getIsSelected() {
            return isSelected;
        }
        private void initDate() {
            for (int i = 0; i < mDatas.size(); i++) {
                getIsSelected().put(i, false);
            }
        }
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
            private MyItemClickListener mListener;
            ImageView mImg;
            TextView mTxt;
            public ViewHolder(View view,MyItemClickListener listener)
            {
                super(view);
                mImg = (ImageView) view
                        .findViewById(R.id.id_index_gallery_item_image);

                mTxt = (TextView) view
                        .findViewById(R.id.id_index_gallery_item_text);
                this.mListener=listener;
                view.setOnClickListener(this);
//                mTxt.setOnClickListener(this);

            }



            @Override
            public void onClick(View v) {
                if (mListener!=null){
                    mListener.OnItemClick(v,getPosition());
                }
            }

            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        }

        @Override
        public int getItemCount()
        {
            return mDatas.size();
        }

        /**
         * 创建ViewHolder
         */
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, final
        int i)
        {
            View view = mInflater.inflate(R.layout.activity_recycler_item,
                    viewGroup, false);
            final ViewHolder viewHolder = new ViewHolder(view,mItemClickListener);

//
//            holder.mTxt.setTextColor(getResources().getColor(R.color.Green));
            viewHolder.mImg = (ImageView) view
                    .findViewById(R.id.id_index_gallery_item_image);

            viewHolder.mTxt = (TextView) view
                    .findViewById(R.id.id_index_gallery_item_text);

            if (mDatas.get(i).getCheck()&&getIsSelected().get(i)){
                viewHolder.mTxt.setTextColor(getResources().getColor(R.color.Green));
            }
            return viewHolder;
        }

        /**
         * 设置值
         */
        @Override
        public void onBindViewHolder(final ViewHolder viewHolder, final int i)
        {
            viewHolder.mTxt.setText(mDatas.get(i).getName());
        }
        public void setOnItemClickListener(MyItemClickListener listener){
                this.mItemClickListener=listener;

       }

    }
}
