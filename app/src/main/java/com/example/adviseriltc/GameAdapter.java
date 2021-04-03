package com.example.adviseriltc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GameAdapter  extends RecyclerView.Adapter<GameAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<Game> states;

    GameAdapter(Context context, List<Game> states) {
        this.states = states;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public GameAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GameAdapter.ViewHolder holder, int position) {
        Game state = states.get(position);
        holder.bannerView.setImageResource(state.getGameResource());
        holder.nameView.setText(state.getName());
        holder.tagsView.setText(state.getTags());
        holder.priceView.setText(state.getPrice());

    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView bannerView;
        final TextView nameView, tagsView, priceView;
        ViewHolder(View view){
            super(view);
            bannerView = (ImageView)view.findViewById(R.id.flag);
            nameView = (TextView) view.findViewById(R.id.name);
            tagsView = (TextView) view.findViewById(R.id.tag);
            priceView = (TextView) view.findViewById(R.id.price);
        }
    }
}
