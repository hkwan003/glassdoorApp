package com.example.calvinkwan.glassdoor_app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by calvinkwan on 4/1/16.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>
{
    private LayoutInflater inflater;

    List<TextDescription> data = Collections.EMPTY_LIST;


    public RecyclerAdapter(Context context)
    {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = inflater.inflate(R.layout.customrow, parent, false);            //view represents represents linearlayout of XML customrow
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {

    }

    @Override
    public int getItemCount()
    {
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView messageID;

        TextView message;

        public MyViewHolder(View itemView)
        {
            super(itemView);


        }
    }
}
