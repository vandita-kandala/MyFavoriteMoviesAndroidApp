package com.example.first.myfavouritemoviesfragment;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainFragment.OnFragmentInteractionListener, AddMovieFragment.OnFragmentInteractionListener, EditMovieFragment.EditInterface {

    static ArrayList<Movie> alist;
    static CharSequence[] movies;
    Movie selectedmovie;
    int finalwhich = 0;

    static List<String> list = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alist = new ArrayList<>();

        getFragmentManager().beginTransaction().add(R.id.container, new MainFragment(), "first").commit();

    }




    @Override
    public void onFragmentInteraction(String text, String Desc, String genre, String rv, String year, String imdb) {
    //from add movie

        Movie movie = new Movie(text, Desc, genre, rv, year, imdb);
        alist.add(movie);
        for(Movie as: alist) {
            if(!list.contains(as.getName())){
                list.add(as.getName());
            }
        }
        movies =list.toArray(new CharSequence[list.size()]);

    }

    @Override
    public void onFragmentInteraction() {

        getFragmentManager().beginTransaction().replace(R.id.container, new AddMovieFragment(), "add").addToBackStack(null).commit();


    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() >0){
            getFragmentManager().popBackStack();
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public void edit() {
        MainFragment f = (MainFragment) getFragmentManager().findFragmentByTag("first");

        if(alist!=null){
            f.editclicked(movies, alist);
            Log.d("deeeemmmm",alist.toString());

        }
    }

    @Override
    public void openedit(Movie movie, int which) {

        Log.d("deeeemmmmooooooosssssss",movie.toString());

        selectedmovie = movie;
        finalwhich=which;

        getFragmentManager().beginTransaction().replace(R.id.container, new EditMovieFragment(), "edit").addToBackStack(null).commit();


//        EditMovieFragment ef = (EditMovieFragment) getFragmentManager().findFragmentByTag("edit");
//
//        ef.oneditclicked(movie);

    }

    @Override
    public void delete() {

    }

    @Override
    public void displayyear() {
        getFragmentManager().beginTransaction().replace(R.id.container, new MovieByYear(), "displayyear").addToBackStack(null).commit();


    }

    @Override
    public void displayrating() {

        getFragmentManager().beginTransaction().replace(R.id.container, new MovieByRating(), "displayrating").addToBackStack(null).commit();

    }


    @Override
    public void afteredit(String text, String Desc, String genre, String year, String imdb, String rv) {
//        Movie movie = new Movie(text, Desc, genre, year, imdb, rv);
//        alist.add(movie);
//        for(Movie as: alist) {
//                list.add(as.getName());
//        }
//        movies =list.toArray(new CharSequence[list.size()]);




    }



    @Override
    public Movie editing() {


        return selectedmovie;
    }
    @Override
    public int which() {


        return finalwhich;
    }
}
