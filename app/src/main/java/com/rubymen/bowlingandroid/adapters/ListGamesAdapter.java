package com.rubymen.bowlingandroid.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rubymen.bowlingandroid.R;
import com.rubymen.bowlingandroid.models.Game;

import java.util.ArrayList;

public class ListGamesAdapter extends ArrayAdapter<Game> {

    Activity activity;

    public ListGamesAdapter(Activity context, int resource, ArrayList<Game> objects) {
        super(context, resource, objects);
        this.activity = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = activity.getLayoutInflater().inflate(R.layout.game_template, null);
        final Game currentGame = getItem(position);

        TextView id = (TextView) view.findViewById(R.id.game_id);
        id.setText(String.valueOf(currentGame.getId()));

        TextView lane_id = (TextView) view.findViewById(R.id.game_lane_id);
        lane_id.setText("Piste " + currentGame.getLane_id());

        if (currentGame.getState().equals("finished")) {
            lane_id.setTextColor(Color.parseColor("#FF0000"));
        } else if (currentGame.getState().equals("in progress")) {
            lane_id.setTextColor(Color.parseColor("#39E639"));
        } else if (currentGame.getState().equals("pending")) {
            lane_id.setTextColor(Color.parseColor("#FFBB40"));
        }

        return view;
    }

}
