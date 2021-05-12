package com.example.adviseriltc;

import android.widget.Filter;

import java.util.ArrayList;

public class CustomFilter extends Filter {

    ArrayList<Game> filterList;
    RecyclerAdapter adapter;

    public CustomFilter(ArrayList<Game> filterList, RecyclerAdapter adapter) {
        this.filterList = filterList;
        this.adapter = adapter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {

        FilterResults results=new FilterResults();

        if(constraint!=null && constraint.length()>0) {

            constraint =constraint.toString().toUpperCase();

            ArrayList<Game> filterGames = new ArrayList<>();

            for (int i=0; i<filterList.size(); i++){

                if(filterList.get(i).getTitle().toUpperCase().contains(constraint)){

                    filterGames.add(filterList.get(i));
                }
            }

            results.count = filterGames.size();
            results.values = filterGames;
        }

        else {
            results.count = filterList.size();
            results.values=filterList;
        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

        adapter.gamesList = (ArrayList<Game>) results.values;
        adapter.notifyDataSetChanged();
    }
}
