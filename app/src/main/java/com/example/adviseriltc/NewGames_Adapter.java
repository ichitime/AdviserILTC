package com.example.adviseriltc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class NewGames_Adapter extends RecyclerView.Adapter<NewGames_Adapter.ViewHolder>{
    private  static final String TAG ="RecyclerCalendarView";
    private Context mContextCalendar;
    private ArrayList<NewGames_Calendar> gamesCalendarList;

    public NewGames_Adapter(Context mContextCalendar, ArrayList<NewGames_Calendar> gamesCalendarList){
        this.mContextCalendar=mContextCalendar;
        this.gamesCalendarList=gamesCalendarList;
    }

    @NonNull
    @Override
    public NewGames_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_calendar_new, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(gamesCalendarList.get(position).getTitle());
        holder.textView1.setText(gamesCalendarList.get(position).getRelease_date());
        Glide.with(mContextCalendar).load(gamesCalendarList.get(position).getImg()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return gamesCalendarList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView, textView1;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            imageView=itemView.findViewById(R.id.game_img_calendar_new_id);
            textView=itemView.findViewById(R.id.game_title_calendar_new_id);
            textView1=itemView.findViewById(R.id.game_release_date_calendar_new_id);
            cardView=(CardView) itemView.findViewById(R.id.cv_calendar_new);

        }
    }
}
