package com.example.adviseriltc;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

public class News_Adapter extends RecyclerView.Adapter<News_Adapter.ViewHolder> {
    private  static final String TAG ="RecyclerNewsView";
    private Context mContextNews;
    private ArrayList<New> NewsList;

    public News_Adapter(Context mContextNews, ArrayList<New> NewsList){
        this.mContextNews=mContextNews;
        this.NewsList=NewsList;
    }

    @NonNull
    @Override
    public News_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_news, parent, false);

        return new News_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull News_Adapter.ViewHolder holder, int position) {
        holder.textView.setText(NewsList.get(position).getTitle());
        holder.textView1.setText(NewsList.get(position).getDescription());
        //holder.link.setText(NewsList.get(position).getLink());
        Glide.with(mContextNews).load(NewsList.get(position).getImg()).into(holder.imageView);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(NewsList.get(position).getLink()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContextNews.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return NewsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView, textView1, link;
        CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            imageView=itemView.findViewById(R.id.img_news);
            textView=itemView.findViewById(R.id.title_news);
            textView1=itemView.findViewById(R.id.dsc_news);
            link=itemView.findViewById(R.id.link_news);
            cardView=(CardView) itemView.findViewById(R.id.cv_news);
        }
    }
}
