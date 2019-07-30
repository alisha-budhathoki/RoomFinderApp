package com.example.roomrental.Home;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.roomrental.R;
import com.example.roomrental.models.Room;
import com.example.roomrental.room.RoomDetailActivity;
import com.example.roomrental.utils.UniversalImageLoader;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class BaseRecyclerAdapter extends RecyclerView.Adapter<BaseRecyclerAdapter.ViewHolder> {

    private Context mContext;
    private List<Room> mList;


    public BaseRecyclerAdapter(Context mContext, List<Room> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public BaseRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.snippet_layout_room, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerAdapter.ViewHolder viewHolder, int i) {

        Room post = mList.get(i);
        initImageLoader();
        UniversalImageLoader.setImage(post.getImage(), viewHolder.image, null, "");
        viewHolder.title.setText(post.getName());
    }
    private void initImageLoader() {
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(mContext);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Intent intent = new Intent(mContext, RoomDetailActivity.class);
                        intent.putExtra(mContext.getString(R.string.calling_room_detail),
                                mList.get(getAdapterPosition()));

                        mContext.startActivity(intent);
                    }
                }
            });

        }
    }
}