package com.example.genk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Customadapter extends ArrayAdapter<Docbao> {
    ArrayList<Docbao> arr;
    Context context;
    int resource;

    public Customadapter(Context context, int resource, List<Docbao> items) {
        super(context, resource, items);
        this.arr= (ArrayList<Docbao>) items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view =  inflater.inflate(R.layout.dong_layout_listview, null);
        }
        Docbao p = getItem(position);
        if (p != null) {
            // Anh xa + Gan gia tri
            TextView txttitle = (TextView) view.findViewById(R.id.textviewtitle);
            txttitle.setText(p.title);

            ImageView imageView= view.findViewById(R.id.imageView);
            Picasso.get().load(p.image).into(imageView);



        }
        return view;
    }
    public void sortTintuc(String s){
        s=s.toUpperCase();
        int k=0;
        for (int i=0;i<arr.size();i++){
            Docbao a=arr.get(i);
            String ten=a.title.toUpperCase();
            if(ten.equals(s)){
                arr.set(k,a);
                k++;
            }
        }
        notifyDataSetChanged();
    }

}