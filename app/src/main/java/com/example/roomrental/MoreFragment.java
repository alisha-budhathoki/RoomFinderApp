package com.example.roomrental;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import static java.lang.System.in;

public class MoreFragment extends Fragment {
    private TextView mTextView;
    public MoreFragment(){

    }
    //public MoreFragment(){

    //}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_more, container, false);
        ImageButton imageButton = view.findViewById(R.id.setting_button);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Setting.class);
                in.putExtra("some", "somedata");
                startActivity(in);

            }
        });
        /*TextView mTextView = view.findViewById(R.id.user_detail);
        mTextView.setOnClickListener({
                Intent in = new Intent(getActivity(),Userprofile.class);
         in.putExtra("some","somedata");
        startActivity(in);
        });*/
        // }
        return view;
    }
   //TextView mtextview = view.findViewById(R.id.user_detail);
}
