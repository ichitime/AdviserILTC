package com.example.adviseriltc;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements Filterable{

    private  static final String TAG ="RecyclerView";
    public   Context mContext;
    public ArrayList<Game> gamesList, filterList;
    CustomFilter filter;


    public RecyclerAdapter(Context mContext, ArrayList<Game> gamesList){
        this.mContext=mContext;
        this.gamesList=gamesList;
        this.filterList=gamesList;

    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_games_items, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
    holder.textView.setText(gamesList.get(position).getTitle());
    holder.textView1.setText(gamesList.get(position).getGenre());
    holder.textView2.setText(gamesList.get(position).getPrice());
    Glide.with(mContext).load(gamesList.get(position).getImage()).into(holder.imageView);

    holder.cardView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(mContext, Current_Game.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("Title", gamesList.get(position).getTitle());
            intent.putExtra("Price", gamesList.get(position).getPrice());
            intent.putExtra("Link",gamesList.get(position).getLink());
            intent.putExtra("Video",gamesList.get(position).getVideo());
            intent.putExtra("Publisher", gamesList.get(position).getPublisher());
            intent.putExtra("Developer", gamesList.get(position).getDeveloper());
            intent.putExtra("Release", gamesList.get(position).getRelease_date());
            intent.putExtra("Description", gamesList.get(position).getDescription());
            intent.putExtra("Genre", gamesList.get(position).getGenre());
            //intent.putExtra("Image", gamesList.get(position).getImage());
            mContext.startActivity(intent);
        }
    });
    }

    @Override
    public int getItemCount() {
        return gamesList.size();
    }

    @Override
    public Filter getFilter() {

        if(filter==null){
            filter = new CustomFilter(filterList, this);
        }

        return filter;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView, textView1, textView2;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            imageView=itemView.findViewById(R.id.game_img_id);
            textView=itemView.findViewById(R.id.game_title_id);
            textView1=itemView.findViewById(R.id.game_tags_id);
            textView2=itemView.findViewById(R.id.game_price_id);
            cardView=(CardView) itemView.findViewById(R.id.cv_id);

        }
    }
}

