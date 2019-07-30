package com.example.roomrental.Home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.roomrental.Adapters.Model.User;
import com.example.roomrental.R;
import com.example.roomrental.models.Room;

import java.util.ArrayList;
import java.util.List;

public class UserFragment extends Fragment {

    private View view;
    private RecyclerView recycler_view;
    private List<com.example.roomrental.Adapters.Model.User> mList;
    private LinearLayoutManager manager;
    private UserRecyclerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_users, container,false);

        setupAdapter();
        Toast.makeText(getContext(), "Home fragment", Toast.LENGTH_SHORT).show();

        return view;
    }

    private void setupAdapter() {
        mList = new ArrayList<>();
        setupTempData();
        recycler_view = view.findViewById(R.id.recycler_view);
        manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recycler_view.setLayoutManager(manager);
        adapter = new UserRecyclerAdapter(getContext(),mList);
        recycler_view.setAdapter(adapter);
    }

    private void setupTempData() {
        User user1 = new User();
        user1.setName("Alisha");
        

        mList.add(user1);



    }


}
