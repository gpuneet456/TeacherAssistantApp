package com.example.madproject;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class exampleAdapter extends RecyclerView.Adapter<exampleAdapter.ExampleViewHolder> {
    private ArrayList<attendance_card_item> mExampleList;
    public ArrayList<attendance_card_item> iattend=new ArrayList<attendance_card_item>();
    Fragment_Teacher_Take_Attendance frag;
    Boolean flag=true;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public ImageView mImageView2;
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView2);
            mTextView1 = itemView.findViewById(R.id.textView);
            mTextView2 = itemView.findViewById(R.id.textView2);
            mTextView3 = itemView.findViewById(R.id.textView3);

        }
    }

    public exampleAdapter(ArrayList<attendance_card_item> exampleList,Fragment_Teacher_Take_Attendance frag) {
        mExampleList = exampleList;
        this.frag=frag;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.attendance_card, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);

        return evh;
    }

    @Override
    public void onBindViewHolder(final ExampleViewHolder holder, final int position) {
        final attendance_card_item currentItem = mExampleList.get(position);

        holder.mImageView.setImageResource(R.drawable.ic_check_circle_black_24dp);
        //holder.mImageView2.setImageResource(R.drawable.ic_cancel_black_24dp);
        holder.mTextView1.setText(currentItem.getUsn());
        holder.mTextView2.setText(currentItem.getName());
        holder.mTextView3.setText(currentItem.getAttended());

        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag) {
                    holder.mImageView.setImageResource(R.drawable.ic_cancel_black_24dp);
                    frag.set(currentItem);
                    flag=false;
                }
                else{
                    holder.mImageView.setImageResource(R.drawable.ic_check_circle_black_24dp);
                    frag.unset(currentItem);
                    flag=true;

                }


                /*iattend.add(addItem1);
                iattend.add(addItem2);
                Fragment_Teacher_Take_Attendance fb=new Fragment_Teacher_Take_Attendance();
                Bundle bu=new Bundle();
                bu.putStringArrayList("key",iattend);
                fb.setArguments(bu);*/
            }
        });


    }


    @Override
    public int getItemCount() {
        return mExampleList.size();
    }


}