package com.example.michal_hit_kody.aplikacja_test;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class CustomAdapter extends ArrayAdapter<String> {

    private final Activity context;
    ArrayList zm = new ArrayList();
    ArrayList zm1 = new ArrayList();
 //   private final String[] zm2;
    public CustomAdapter(Activity context,
                         ArrayList zm,ArrayList zm1) {
        super(context, R.layout.row1, zm);
        this.context = context;
        this.zm = zm;
        this.zm1 = zm1;
       // this.zm2 = zma2;
    }
    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.row1, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.textView13);
       // TextView txtTitle1 = (TextView) rowView.findViewById(R.id.textView12);
        TextView txtTitle2 = (TextView) rowView.findViewById(R.id.textView14);

        if(zm.get(position)!=null & zm1.get(position)!=null) {
            txtTitle2.setText(zm.get(position) + "  ");
            txtTitle.setText(zm1.get(position) + "  ");
            //   txtTitle1.setText(zm2[position]);
        }
        return rowView;
    }
}
