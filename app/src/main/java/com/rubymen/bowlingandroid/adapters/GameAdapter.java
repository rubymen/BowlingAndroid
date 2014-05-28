package com.rubymen.bowlingandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.rubymen.bowlingandroid.R;
import com.rubymen.bowlingandroid.models.Game;
import com.rubymen.bowlingandroid.models.Player;
import com.rubymen.bowlingandroid.models.Throw;
import com.rubymen.bowlingandroid.models.Turn;

import java.util.ArrayList;


public class GameAdapter extends BaseExpandableListAdapter {

    private Context context;

    private ArrayList<Player> players;

    public GameAdapter(Context context, ArrayList<Player> players) {
        this.context = context;
        this.players = players;
    }

    public int getGroupCount() {
        return players.size();
    }

    public int getChildrenCount(int childPosition) {
        return players.get(childPosition).getTurns().length;
    }

    public Object getGroup(int groupPosition) {
        return players.get(groupPosition);
    }

    public Object getChild(int groupPosition, int childPosition) {
        return players.get(groupPosition).getTurns()[childPosition];
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    public boolean hasStableIds() {
        return true;
    }

    public View getGroupView(int groupPosition, boolean b, View view, ViewGroup viewGroup) {
        Player player = (Player) getGroup(groupPosition);

        if (view == null) {
            view = ((LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.player_template, null);
        }

        TextView playerText = (TextView) view.findViewById(R.id.player_text);
        playerText.setText(player.getPseudo());

        int gameTotal = 0;

        for (Turn t : player.getTurns()) {
            gameTotal += t.getScore();
        }

        TextView gameTotalText = (TextView) view.findViewById(R.id.game_total_text);
        gameTotalText.setText(Integer.toString(gameTotal));

        return view;
    }

    public View getChildView(int groupPosition, int childPosition, boolean b, View view, ViewGroup viewGroup) {
        Turn turn = (Turn) getChild(groupPosition, childPosition);

        if (view == null) {
            view = ((LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.turn_template, null);
        }

        String turnDetail = new String();

        for (Throw t : turn.getThrowsList()) {
            turnDetail += t.getFallenSkittles() + " ";
        }

        TextView turnText = (TextView) view.findViewById(R.id.turn_text);
        turnText.setText(turnDetail);

        TextView turnTotalText = (TextView) view.findViewById(R.id.turn_total_text);
        turnTotalText.setText(Integer.toString(turn.getScore()));

        return view;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

}
