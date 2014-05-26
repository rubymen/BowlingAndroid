package com.rubymen.bowlingandroid.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rubymen.bowlingandroid.R;
import com.rubymen.bowlingandroid.models.Game;

public class ListGamesAdapter extends ArrayAdapter<Game> {

    Activity activity;

    public ListGamesAdapter(Activity context, int resource, Game[] objects) {
        super(context, resource, objects);
        this.activity = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = activity.getLayoutInflater().inflate(R.layout.game_template, null);
        final Game currentGame = getItem(position);
        TextView id = (TextView) view.findViewById(R.id.id);
        id.setText(currentGame.getId());
        TextView state = (TextView) view.findViewById(R.id.state);
        state.setText(currentGame.getState());
        TextView lane_id = (TextView) view.findViewById(R.id.lane_id);
        lane_id.setText("Piste " + currentGame.getLane_id());

        if(currentGame.getState().equals("finished")){
            state.setTextColor(Color.parseColor("#FF0000"));
            lane_id.setTextColor(Color.parseColor("#FF0000"));
        }else if(currentGame.getState().equals("in progress")){
            state.setTextColor(Color.parseColor("#39E639"));
            lane_id.setTextColor(Color.parseColor("#39E639"));
        }else if(currentGame.getState().equals("pending")){
            state.setTextColor(Color.parseColor("#FFBB40"));
            lane_id.setTextColor(Color.parseColor("#FFBB40"));
        }
        return view;
    }

}
