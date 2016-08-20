package com.example.jannat.playerinformationwithfragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PlayerNameFragment.GetPlayerListInterface,PlayerDetailsFragment.GetPlayerListInterface{
    ArrayList<Player> players;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        players=new ArrayList<Player>();
        createPlayerArrayList();
        if(savedInstanceState!=null)
        {
            position=savedInstanceState.getInt("savedpositionInMain");
        }else{
            position=0;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        //Toast.makeText(getApplicationContext(),""+players.size(),Toast.LENGTH_LONG).show();
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();

            PlayerNameFragment playerNameFragment=new PlayerNameFragment();
            playerNameFragment.setArguments(bundle);

            transaction.replace(R.id.playerNameContainer, playerNameFragment);
            transaction.commit();
        }
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            clearBackStack();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();

            PlayerNameFragment playerNameFragment=new PlayerNameFragment();
            playerNameFragment.setArguments(bundle);

            transaction.replace(R.id.playerNameContainer,playerNameFragment);
            transaction.commit();

            FragmentManager manager1 = getSupportFragmentManager();
            FragmentTransaction transaction1 = manager1.beginTransaction();
            PlayerDetailsFragment playerDetailsFragment=new PlayerDetailsFragment();
            playerDetailsFragment.setArguments(bundle);
            transaction1.replace(R.id.playerDetailsContainer, playerDetailsFragment);
            transaction1.commit();
        }

    }

    public void createPlayerArrayList(){
        try{
            JSONObject jsonObject=new JSONObject(loadJSONFromAsset());
            JSONArray jsonArray=(JSONArray) jsonObject.getJSONArray("info");
            for(int i=0;i<jsonArray.length();i++){
                JSONObject object= (JSONObject) jsonArray.get(i);
                String url=object.getString("url");
                int resourceImage = this.getResources().getIdentifier(url, "drawable", this.getPackageName());
                String name= object.getString("name");
                String battingStyle=object.getString("Batting_Style");
                String bowlingStyle=object.getString("Bowling_Style");
                double batingAvgInT20=object.getDouble("Batting_Average_T20");
                double batingAvgInODI=object.getDouble("Batting_Average_ODI");
                double bowlingAvgInT20=object.getDouble("Bowling_Average_T20");
                double bowlingAvgInODI=object.getDouble("Bowling_Average_ODI");
                String role=object.getString("role");
                Player player=new Player(resourceImage,name,battingStyle,bowlingStyle,batingAvgInT20,batingAvgInODI,bowlingAvgInT20,bowlingAvgInODI,role);
                players.add(player);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String loadJSONFromAsset(){
        String json=null;
        try{
            InputStream textInfo=getAssets().open("information.json");
            int size=textInfo.available();
            byte[] buffer=new byte[size];
            textInfo.read(buffer);
            textInfo.close();
            json=new String(buffer,"UTF-8");
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    public ArrayList<Player> getAllPlayerInformation() {
        return players;
    }

    @Override
    public void selectedPosition(int position) {
        this.position=position;
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            PlayerDetailsFragment playerDetailsFragment=new PlayerDetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("pos", position);
            playerDetailsFragment.setArguments(bundle);
            transaction.replace(R.id.playerNameContainer, playerDetailsFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            PlayerDetailsFragment playerDetailsFragment=new PlayerDetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("pos", position);
            playerDetailsFragment.setArguments(bundle);
            transaction.replace(R.id.playerDetailsContainer, playerDetailsFragment);
            transaction.commit();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("savedpositionInMain", position);
    }

    public void clearBackStack(){
        FragmentManager fm = getSupportFragmentManager();
        for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
    }

}
