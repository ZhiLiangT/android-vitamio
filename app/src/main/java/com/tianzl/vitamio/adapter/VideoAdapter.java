package com.tianzl.vitamio.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tianzl.vitamio.R;
import com.tianzl.vitamio.been.VideoInfo;
import com.tianzl.vitamio.utils.CommTools;

import java.util.List;

/**
 * Created by tianzl on 2017/8/21.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    private List<VideoInfo> mData;
    private Context context;
    private LayoutInflater inflater;
    public VideoAdapter(Context context,List<VideoInfo> mData){
        this.context=context;
        this.mData=mData;
        inflater=LayoutInflater.from(context);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.video_item,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.ivIcon.setImageBitmap(mData.get(position).getB());
        holder.tvName.setText(mData.get(position).getTitle());
        holder.tvSize.setText("视频大小为："+mData.get(position).getSize());
        holder.tvTime.setText("视频时长为："+mData.get(position).getTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(mData.get(position));
            }
        });
    }
    public interface OnItemClickListener{
        void onItemClick(VideoInfo videoInfo);
    }
    private OnItemClickListener listener;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivIcon;
        TextView tvName;
        TextView tvSize;
        TextView tvTime;
        public ViewHolder(View itemView) {
            super(itemView);
            ivIcon=itemView.findViewById(R.id.item_icon);
            tvName=itemView.findViewById(R.id.item_tv_name);
            tvSize=itemView.findViewById(R.id.item_tv_size);
            tvTime=itemView.findViewById(R.id.item_tv_time);
        }
    }
}
