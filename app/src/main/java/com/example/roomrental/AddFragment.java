package com.example.roomrental;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class AddFragment extends Fragment {
    public AddFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_add,container,false);
        Button button =(Button) view.findViewById(R.id.upload_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(),Upload.class);
               in.putExtra("some","somedata");
               startActivity(in);
            }
        });
        return view;
    }
}


   //private Button UploadImage;
    //private Button ChooseImage;
    //private EditText editText;

   // @Override
    //public View onCreateView(LayoutInflater inflater,
      //                       ViewGroup container, Bundle
        //                             savedInstanceState) {

        // Inflate the layout for this fragment
        //View view =  inflater.inflate(R.layout.fragment_add, container, false);
        //editText = (EditText) view.findViewById(R.id.upload_image_name);
          //final Button button=
            //    (Button) view.findViewById(R.id.choose_image_button);

        //button.setOnClickListener(new View.OnClickListener() {
          //  public void onClick(View v) {

            //    buttonClicked(v);
            //}
        //});

        //final Button button1 =
          //      (Button) view.findViewById(R.id.upload_button);


        //button1.setOnClickListener(new View.OnClickListener() {
            //public void onClick(View v){
          //  buttonClicked(v);
        //}
      //  });
    //    return view;
  //  }
    //public void buttonClicked (View view) {

            //}


