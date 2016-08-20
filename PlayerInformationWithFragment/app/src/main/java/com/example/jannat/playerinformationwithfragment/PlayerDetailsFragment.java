package com.example.jannat.playerinformationwithfragment;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlayerDetailsFragment extends Fragment {
    ImageView imageView;
    TextView nameTextView, battingStyleTextView, bowlingStyleTextView, battingAverageT20TextView, bowlingAverageT20TextView, battingAverageODITextView, bowlingAverageODITextView, roleTextView;
    GetPlayerListInterface playerListInterface;
    ArrayList<Player> players;
    int pos;
    public PlayerDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_player_details, container, false);
        players=new ArrayList<Player>();
        players=playerListInterface.getAllPlayerInformation();
        /*if(savedInstanceState!=null)
        {
            pos=savedInstanceState.getInt("savedposition");
        }else{
            pos=getArguments().getInt("pos");
        }*/
        pos=getArguments().getInt("pos");
        imageView= (ImageView) view.findViewById(R.id.imageView);
        TextView nameTextView= (TextView) view.findViewById(R.id.nameTextView);
        TextView battingStyleTextView= (TextView) view.findViewById(R.id.battingStyleTextView);
        TextView bowlingStyleTextView= (TextView) view.findViewById(R.id.bowlingStyleTextView);
        TextView battingAverageT20TextView= (TextView) view.findViewById(R.id.battingAverageT20TextView);
        TextView bowlingAverageT20TextView= (TextView) view.findViewById(R.id.bowlingAverageT20TextView);
        TextView battingAverageODITextView= (TextView) view.findViewById(R.id.battingAverageODITextView);
        TextView bowlingAverageODITextView= (TextView) view.findViewById(R.id.bowlingAverageODITextView);
        TextView roleTextView= (TextView) view.findViewById(R.id.roleTextView);

        int imageId=players.get(pos).getImageId();
        String name=players.get(pos).getName();
        String batStyle=players.get(pos).getBatingStyle();
        String bowlingStyle=players.get(pos).getBowlingStyle();
        String batingAvgInT20=players.get(pos).getBatingAvgInT20()+"";
        String batingAvgInODI=players.get(pos).getBatingAvgInODI()+"";
        String bowlingAvgInT20=players.get(pos).getBowlingAvgInT20()+"";
        String bowlingAvgInODI=players.get(pos).getBowlingAvgInODI()+"";
        String role=players.get(pos).getRole();

        imageView.setImageResource(imageId);
        nameTextView.setText(name);
        battingStyleTextView.setText(batStyle);
        bowlingStyleTextView.setText(bowlingStyle);
        battingAverageT20TextView.setText(batingAvgInT20);
        bowlingAverageT20TextView.setText(bowlingAvgInT20);
        battingAverageODITextView.setText(batingAvgInODI);
        bowlingAverageODITextView.setText(bowlingAvgInODI);
        roleTextView.setText(role);
        return view;
    }


    /*@Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("savedposition", pos);
    }*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        playerListInterface= (GetPlayerListInterface) context;
    }
    public interface GetPlayerListInterface{
        public ArrayList<Player> getAllPlayerInformation();
    }

}
