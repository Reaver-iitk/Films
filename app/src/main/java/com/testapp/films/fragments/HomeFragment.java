package com.testapp.films.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.testapp.films.client.ApiService;
import com.testapp.films.client.ClientRetrofit;
import com.testapp.films.retromodels.FilmList;
import com.testapp.films.retromodels.Films;
import com.testapp.films.R;
import com.testapp.films.adapter.FilmAdapter;
import com.testapp.films.models.Head;
import com.testapp.films.models.Item;
import com.testapp.films.models.Menu;
import com.testapp.films.models.Movies;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private List<Item> items = new ArrayList<>();
    private List<Films> films;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle("Главная");
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        items.removeAll(items);//Очищение recyclerview

        RecyclerView recyclerView = view.findViewById(R.id.rV);

        items.add(new Item(0,new Head("Жанры")));
        items.add(new Item(1,new Menu("драма")));
        items.add(new Item(1,new Menu("фэнтези")));
        items.add(new Item(1,new Menu("криминал")));
        items.add(new Item(1,new Menu("детектив")));
        items.add(new Item(1,new Menu("мелодрама")));
        items.add(new Item(1,new Menu("биография")));
        items.add(new Item(1,new Menu("комедия")));
        items.add(new Item(1,new Menu("фантастика")));
        items.add(new Item(1,new Menu("боевик")));
        items.add(new Item(1,new Menu("триллер")));
        items.add(new Item(1,new Menu("мюзикл")));
        items.add(new Item(1,new Menu("приключения")));
        items.add(new Item(1,new Menu("ужасы")));
        items.add(new Item(0,new Head("Фильмы")));
        recyclerView.setAdapter(new FilmAdapter(items,getContext())); //Иллюзия быстрой прогрузки, чтобы не ждать Api.
        final Handler h = new Handler();
        Runnable run = new Runnable() {

            @Override
            public void run() {  //Поиск связи с интернетом
                if (CheckNetwork()){
                    ApiService api = ClientRetrofit.getApiService();

                    Call<FilmList> call = api.getMyJSON();
                    call.enqueue(new Callback<FilmList>() {
                        @Override
                        public void onResponse(Call<FilmList> call, Response<FilmList> response) {
                            films = response.body().getFilms();
                            int i=0;
                            while (i<films.size()){
                                    items.add(new Item(2, new Movies(films.get(i).getImageUrl(),films.get(i).getLocalizedName(),films.get(i).getYear(),
                                        films.get(i).getRating(),films.get(i).getDescription(),films.get(i).getName(),films.get(i).getGenres())));
                                    i++;
                            }
                            recyclerView.setAdapter(new FilmAdapter(items,getContext())); //Издержки одного элемента для навигации и api. Побочный эффект - подёргивание при прогрузке
                        }

                        @Override
                        public void onFailure(Call<FilmList> call, Throwable t) {
                            Toast.makeText(getContext(), "Ошибка", Toast.LENGTH_LONG).show();
                        }
                    });
                    h.removeCallbacks(this);
                }
                else {
                    Toast.makeText(getContext(), "Ошибка интернет-соединения", Toast.LENGTH_SHORT).show();
                    h.postDelayed(this, 2000);
                }

            }
        };
        run.run();

        return view;
    }

    private boolean CheckNetwork() {
        ConnectivityManager conMgr = (ConnectivityManager) Objects.requireNonNull(getActivity()).getSystemService(Context.CONNECTIVITY_SERVICE);
        assert conMgr != null;
        return conMgr.getActiveNetworkInfo() != null
                && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnected();

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

}
