package com.testapp.films.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.squareup.picasso.Picasso;
import com.testapp.films.R;

import java.util.Objects;

public class DetailFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = getArguments();

        final View view = inflater.inflate(R.layout.fragment_detail, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        assert bundle != null;
        toolbar.setTitle(bundle.getString("Name"));
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getActivity().getSupportFragmentManager().popBackStack();}
                ((FragmentActivity) requireContext()).getSupportFragmentManager().beginTransaction()
                        .replace(android.R.id.content, new HomeFragment()).commit();
            }
        });

        ImageView imageMovie = view.findViewById(R.id.detailImage);
        TextView tvNameEng = view.findViewById(R.id.tvNameEng);
        TextView tvYear = view.findViewById(R.id.tvYear);
        TextView tvStars = view.findViewById(R.id.tvStars);
        TextView tvDescription = view.findViewById(R.id.tvDescription);

        if (bundle.getString("Image")==null){
            Picasso.get().
                    load(R.drawable.notfound).
                    into(imageMovie);
        }else {
            Picasso.get().
                    load(bundle.getString("Image")).
                    error(R.drawable.notfound).
                    into(imageMovie);
        }

        String Y = bundle.getString("Year");
        if (Y==null) Y = "Неизвестно";
        String S = bundle.getString("Stars");
        if (S==null) S = "Неизвестно";
        String D = bundle.getString("Description");
        if (D==null) D = "Нет описания";

        tvNameEng.setText(bundle.getString("NameEng"));
        tvYear.setText("Год: "+Y);
        tvStars.setText("Рейтинг: "+S);
        tvDescription.setText(D);

        return view;
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
