package com.example.roomrental.Home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.roomrental.Adapters.Model.User;
import com.example.roomrental.R;
import com.example.roomrental.models.Room;

import java.util.List;

public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder> {

    private Context mContext;
    private List<User> mList;


    public UserRecyclerAdapter(Context mContext, List<User> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public UserRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.snippet_layout_room, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserRecyclerAdapter.ViewHolder viewHolder, int i) {

        viewHolder.username.setText(mList.get(i).getName());
//        viewHolder.location.setText(mList.get(i).getRoom_owner_name());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView username;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            username = itemView.findViewById(R.id.username);

        }
    }
}