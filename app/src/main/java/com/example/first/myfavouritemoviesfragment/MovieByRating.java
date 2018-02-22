package com.example.first.myfavouritemoviesfragment;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.Comparator;

import static com.example.first.myfavouritemoviesfragment.MainActivity.alist;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieByRating extends Fragment {

    TextView seekbar;
    TextView ratingvalue;
    TextView Name;
    TextView Description;
    TextView Genre;
    TextView Year;
    TextView Imdb;
    int x =0;
    int length=0;
    Toast toast;
    public MovieByRating() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_by_rating, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Collections.sort(alist, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return Integer.parseInt(o1.getRating()) - Integer.parseInt(o2.getRating());
            }
        });


        Name = (TextView) getActivity().findViewById(R.id.Name_display);
        Description = (TextView) getActivity().findViewById(R.id.Description_display);
        Genre = (TextView) getActivity().findViewById(R.id.Genre_display);
        Year = (TextView) getActivity().findViewById(R.id.Year_display);
        Imdb = (TextView) getActivity().findViewById(R.id.Imdb_display);
        ratingvalue = (TextView) getActivity().findViewById(R.id.Rating_display);

        Movie moviex = alist.get(x);

        Name.setText(moviex.getName());
        Description.setText(moviex.getDescription());
        Genre.setText(moviex.getGenre());
        ratingvalue.setText(moviex.getRating());
        Year.setText(moviex.getYear());
        Imdb.setText(moviex.getImdb());

        length = alist.size();

        getActivity().findViewById(R.id.imageButton6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("value of X:after right", " " + x);

                if(x < length-1) {
                    x++;
                    Movie moviex = alist.get(x);
                    Name.setText(moviex.getName());
                    Description.setText(moviex.getDescription());
                    Genre.setText(moviex.getGenre());
                    ratingvalue.setText(moviex.getRating());
                    Year.setText(moviex.getYear());
                    Imdb.setText(moviex.getImdb());
                }else {
                    toast = Toast.makeText(getActivity(),"No movies found after this rating!",Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        getActivity().findViewById(R.id.imageButton5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(x >0) {
                    x--;
                    Movie moviex = alist.get(x);
                    Name.setText(moviex.getName());
                    Description.setText(moviex.getDescription());
                    Genre.setText(moviex.getGenre());
                    ratingvalue.setText(moviex.getRating());
                    Year.setText(moviex.getYear());
                    Imdb.setText(moviex.getImdb());
                }else {
                    toast = Toast.makeText(getActivity(),"No movies found before this rating!",Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        getActivity().findViewById(R.id.imageButton4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x =0;
                Movie moviex = alist.get(x);
                Name.setText(moviex.getName());
                Description.setText(moviex.getDescription());
                Genre.setText(moviex.getGenre());
                ratingvalue.setText(moviex.getRating());
                Year.setText(moviex.getYear());
                Imdb.setText(moviex.getImdb());

            }
        });

        getActivity().findViewById(R.id.imageButton7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = length-1;
                Movie moviex = alist.get(x);
                Name.setText(moviex.getName());
                Description.setText(moviex.getDescription());
                Genre.setText(moviex.getGenre());
                ratingvalue.setText(moviex.getRating());
                Year.setText(moviex.getYear());
                Imdb.setText(moviex.getImdb());
            }
        });


        getActivity().findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager().popBackStack();


            }
        });


    }

}
