package com.example.jannat.playerinformationwithfragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlayerNameFragment extends Fragment implements RecyclerAdapter.RecyclerViewSelectedItem{
    GetPlayerListInterface playerListInterface;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    int newPosition;
    ArrayList<Player> players;
    public PlayerNameFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_player_name, container, false);
        recyclerView= (RecyclerView) view.findViewById(R.id.recyclerView);
        players=new ArrayList<Player>();
        players=playerListInterface.getAllPlayerInformation();
        ArrayList<String> names=new ArrayList<String>();
        names=getPlayerNames();

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            newPosition=-1;
        }
        else
            newPosition=getArguments().getInt("pos");
        adapter=new RecyclerAdapter(PlayerNameFragment.this,names,newPosition);
        layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        playerListInterface= (GetPlayerListInterface) context;
    }

    @Override
    public void itemSelected(int oldPosition,int newPosition) {
        //Toast.makeText(getContext(),""+position,Toast.LENGTH_LONG).show();
        adapter.notifyItemChanged(oldPosition);
        adapter.notifyItemChanged(newPosition);
        this.newPosition=newPosition;
        playerListInterface.selectedPosition(newPosition);
    }

    public interface GetPlayerListInterface{
        public ArrayList<Player> getAllPlayerInformation();
        public void selectedPosition(int position);
    }

    private ArrayList<String> getPlayerNames(){
        ArrayList<String> names=new ArrayList<String>();
        names.clear();
        for (Player player:players){
            names.add(player.getName());
        }
        return names;
    }

    @Override
    public void onPause() {
        super.onPause();

    }

}
