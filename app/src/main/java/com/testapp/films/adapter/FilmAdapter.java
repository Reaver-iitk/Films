package com.testapp.films.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.testapp.films.fragments.DetailFragment;
import com.testapp.films.R;
import com.testapp.films.models.Head;
import com.testapp.films.models.Item;
import com.testapp.films.models.Menu;
import com.testapp.films.models.Movies;

import java.util.ArrayList;
import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Item> items;
    private List<Item> itemsF;
    private int row_index = -1;
    private Context context;


    public FilmAdapter(List<Item> items, Context context) {
        this.items = items;
        this.itemsF = new ArrayList<>(items);
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new HeadViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.item_head_rv, parent, false
                    )
            );
        } else if (viewType == 1) {
            return new MenuViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.item_menu_rv, parent, false
                    )
            );
        }else {
            return new MoviesViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.item_film_rv, parent, false
                    )
            );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == 0 || getItemViewType(position) == 14){
            Head head = (Head) items.get(position).getObject();
            ((HeadViewHolder) holder).setTextHead(head);
        }else if (getItemViewType(position) == 1) {
            Menu menu = (Menu) items.get(position).getObject();
            ((MenuViewHolder) holder).setTextMenu(menu);
        }

        else {
            Movies moviesL = (Movies) items.get(position).getObject();
            ((MoviesViewHolder) holder).setMovieDate(moviesL);

        }

        if (holder instanceof MenuViewHolder) {
            ((MenuViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    row_index=position;
                    items.removeAll(items); //Защита от дубликата навигации
                    items.addAll(itemsF);
                    String g = ((Menu) items.get(position).getObject()).getMenuName();
                    int i =0;
                    for (i=0; i< items.size(); i++){
                        if (items.get(i).getType()==2) {
                            if (!((Movies) items.get(i).getObject()).getFitler().contains(g)) {
                                items.remove(i);
                                i=0;
                            }
                        }
                    }
                    notifyDataSetChanged();
                }
            });
        }

        if (holder instanceof MoviesViewHolder) {
            ((MoviesViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("Name", ((Movies) items.get(position).getObject()).getNameMovies());
                    bundle.putString("NameEng", ((Movies) items.get(position).getObject()).getEngNameMovies());
                    bundle.putString("Year", ((Movies) items.get(position).getObject()).getYearMovies());
                    bundle.putString("Stars", ((Movies) items.get(position).getObject()).getRatingMovies());
                    bundle.putString("Description", ((Movies) items.get(position).getObject()).getDescriptionMovies());
                    bundle.putString("Image", ((Movies) items.get(position).getObject()).getImageMovies());
                    DetailFragment df = new DetailFragment();
                    df.setArguments(bundle);
                    ((FragmentActivity) context).getSupportFragmentManager().beginTransaction()
                            .addToBackStack("detail")
                            .replace(android.R.id.content, df)
                            .commit();
                }
            });
        }


        if (row_index==position) {
            if (holder instanceof MenuViewHolder) {
                ((MenuViewHolder) holder).textMenu.setBackgroundResource(R.drawable.select_round_button);
            }
        }
        else {
            if (holder instanceof MenuViewHolder) {
                ((MenuViewHolder) holder).textMenu.setBackgroundResource(R.drawable.round_button);
            }
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }


    static class HeadViewHolder extends RecyclerView.ViewHolder{
        private TextView textHead;

        public HeadViewHolder(@NonNull View itemView) {
            super(itemView);
            textHead = itemView.findViewById(R.id.item_head);
        }

        void setTextHead(Head head) {
            textHead.setText(head.getHeadTitle());
        }
    }

    static class MenuViewHolder extends RecyclerView.ViewHolder {
        private TextView textMenu;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            textMenu = itemView.findViewById(R.id.itemMenu);
        }

        void setTextMenu(Menu menu) {
            textMenu.setText(menu.getMenuName());
        }
    }
    static class MoviesViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageMovieL;
        private TextView nameMovieL;

        public MoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            imageMovieL = itemView.findViewById(R.id.imageFilm);
            nameMovieL = itemView.findViewById(R.id.nameFilm);

        }
        void setMovieDate(Movies movies) {
            if (movies.getImageMovies()==null){
                Picasso.get().
                        load(R.drawable.notfound).
                        into(imageMovieL);
            }else {
                Picasso.get().
                    load(movies.getImageMovies()).
                    error(R.drawable.notfound).
                        resize( 150,150).
                        centerCrop().
                    into(imageMovieL);
            }
            nameMovieL.setText(movies.getNameMovies());
        }
    }

}
