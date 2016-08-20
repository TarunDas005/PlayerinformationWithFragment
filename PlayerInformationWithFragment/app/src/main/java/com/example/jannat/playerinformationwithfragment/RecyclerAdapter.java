package com.example.jannat.playerinformationwithfragment;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Jannat on 8/13/2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    private static RecyclerViewSelectedItem selectedItem;
    Context context;
    ArrayList<String> playerNames;
    int selectedPosition=0;
    public RecyclerAdapter(PlayerNameFragment context, ArrayList<String> playerNames,int position) {
        selectedItem= (RecyclerViewSelectedItem) context;
        this.playerNames = playerNames;
        selectedPosition=position;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.player_name,parent,false);
        RecyclerViewHolder recyclerViewHolder=new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        String names=playerNames.get(position);
        if(selectedPosition==position)
            holder.linearLayout.setBackgroundColor(Color.parseColor("#010afd"));
        else
            holder.linearLayout.setBackgroundColor(Color.parseColor("#fa02b0"));
        holder.playerNameTextView.setText(names);
    }

    @Override
    public int getItemCount() {
        return playerNames.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView playerNameTextView;
        LinearLayout linearLayout;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            playerNameTextView= (TextView) itemView.findViewById(R.id.playerNameTextView);
            linearLayout= (LinearLayout) itemView.findViewById(R.id.linearLayout);
        }

        @Override
        public void onClick(View v) {
            selectedItem.itemSelected(selectedPosition,getAdapterPosition());
            setSelectedPosition(getAdapterPosition());
            //notifyDataSetChanged();
        }
    }

    private void setSelectedPosition(int position){
        selectedPosition=position;
    }

    public interface RecyclerViewSelectedItem{
        public void itemSelected(int oldPosition,int newPosition);
    }
}
