package com.rubymen.bowlingandroid.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.rubymen.bowlingandroid.R;
import com.rubymen.bowlingandroid.models.Game;

public class ListGamesAdapter extends ArrayAdapter<Game>{
    Activity activity;

    public ListGamesAdapter(Activity context, int resource, Game[] objects){
        super(context, resource, objects);
        this.activity = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view = activity.getLayoutInflater().inflate(R.layout.game_template, null);
        final Game currentGame = getItem(position);
        TextView id = (TextView) view.findViewById(R.id.id);
        id.setText(currentGame.getId());

        TextView lane_id = (TextView) view.findViewById(R.id.lane_id);
        lane_id.setText(currentGame.getLane_id());
        return view;
    }
}
