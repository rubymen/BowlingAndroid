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

import java.util.ArrayList;


public class GameAdapter extends BaseExpandableListAdapter {

    private Context context;

    private Game game;

    private ArrayList<Player> players;

    public GameAdapter(Context context, Game game) {
        this.context = context;
        this.game = game;
        this.players = new ArrayList<Player>();

        for (Player p : game.getPlayers()) {
            this.players.add(p);
        }
    }

    public int getGroupCount() {
        return players.size();
    }

    public int getChildrenCount(int i) {
        return 0;
    }

    public Object getGroup(int i) {
        return players.get(i);
    }

    public Object getChild(int i, int i2) {
        return null;
    }

    public long getGroupId(int i) {
        return i;
    }

    public long getChildId(int i, int i2) {
        return 0;
    }

    public boolean hasStableIds() {
        return true;
    }

    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        Player player = (Player) getGroup(i);

        if (view == null) {
            view = ((LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.player_template, null);
        }

        TextView turn = (TextView) view.findViewById(R.id.player_text);
        turn.setText(player.getPseudo());

        return view;
    }

    public View getChildView(int i, int i2, boolean b, View view, ViewGroup viewGroup) {
        return null;
    }

    public boolean isChildSelectable(int i, int i2) {
        return false;
    }

}
