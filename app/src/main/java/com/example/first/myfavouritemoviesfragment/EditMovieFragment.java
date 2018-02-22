package com.example.first.myfavouritemoviesfragment;


import android.content.Context;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.first.myfavouritemoviesfragment.MainActivity.alist;
import static com.example.first.myfavouritemoviesfragment.MainActivity.list;
import static com.example.first.myfavouritemoviesfragment.MainActivity.movies;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditMovieFragment extends Fragment {

    private EditInterface mListener;

    SeekBar seekbar;
    TextView ratingvalue;
    EditText Name;
    EditText Description;
    Spinner Genre;
    EditText Year;
    EditText Imdb;
    String spinnerselected;


    public EditMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_movie, container, false);
    }

    public void oneditclicked(Movie moviex){



    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

       final Movie moviex =  mListener.editing();
        final int which = mListener.which();
        Name = (EditText) getActivity().findViewById(R.id.Name_display);
        Description = (EditText)  getActivity().findViewById(R.id.Description_display);
        Genre = (Spinner)  getActivity().findViewById(R.id.Genre_display);
        spinnerselected = Genre.getSelectedItem().toString();
        Year = (EditText)  getActivity().findViewById(R.id.Year_display);
        Imdb = (EditText)  getActivity().findViewById(R.id.Imdb_display);
        seekbar = (SeekBar)  getActivity().findViewById(R.id.Rating_display);
        ratingvalue = (TextView)  getActivity().findViewById(R.id.rating_value);

        Name.setText(moviex.getName());
        Description.setText(moviex.getDescription());
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.moviegenre, android.R.layout.simple_spinner_item);
        Genre.setSelection(adapter.getPosition(moviex.getGenre()));
        ratingvalue.setText(moviex.getRating());
        seekbar.setProgress(Integer.parseInt(moviex.getRating()));
        Year.setText(moviex.getYear());
        Imdb.setText(moviex.getImdb());

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekbar, int progress, boolean fromUser) {
                // progress = progress+1;
                ratingvalue.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekbar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekbar) {

            }
        });

        getActivity().findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Calendar cal = GregorianCalendar.getInstance();

                if(TextUtils.isEmpty(Name.getText().toString())){
                    Toast.makeText(getActivity(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else if(Name.getText().toString().length() > 50){
                    Toast.makeText(getActivity(), "Name should be Alpha numeric & shouldn't exceed 50 characters", Toast.LENGTH_SHORT).show();
                }
//        else if(Name.getText().toString().trim().matches(nums)){
//            Toast.makeText(this, "Name should be Alphanumeric", Toast.LENGTH_SHORT).show();
//        }
                else if(TextUtils.isEmpty(Description.getText().toString())){
                    Toast.makeText(getActivity(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
               
                else if(Description.getText().toString().length() > 1000){
                    Toast.makeText(getActivity(), "Description should'nt exceed 1000 characters", Toast.LENGTH_SHORT).show();
                }else if((Year.getText().toString().length() != 4) || (Integer.parseInt(Year.getText().toString()) > cal.get(Calendar.YEAR)) || (Integer.parseInt(Year.getText().toString()) < 1870) ) {
                    Toast.makeText(getActivity(),"Provide a valid year!(1870 - "+cal.get(Calendar.YEAR)+")",Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(Imdb.getText().toString()) || !Imdb.getText().toString().matches("^(http://)?[www.]{4}[a-zA-Z]*[.com]{4}$")){
                    Toast.makeText(getActivity(),"Provide a valid link!", Toast.LENGTH_LONG).show();

                }
                else {
                    spinnerselected = Genre.getSelectedItem().toString();

//                    mListener.afteredit(Name.getText().toString(), Description.getText().toString(), spinnerselected, ratingvalue.getText().toString(), Year.getText().toString(), Imdb.getText().toString());
                    moviex.setName(Name.getText().toString());
                    moviex.setDescription(Description.getText().toString());
                    moviex.setGenre(spinnerselected);
                    moviex.setRating(ratingvalue.getText().toString());
                    moviex.setYear(Year.getText().toString());
                    moviex.setImdb(Imdb.getText().toString());

                    alist.remove(which);
                    alist.add(which, moviex);
                    list.remove(which);
                    list.add(which, moviex.getName());

                    movies = new CharSequence[list.size()];

                    for(int i=0;i<list.size();i++) {
                        movies[i] = alist.get(i).getName();
                    }

                    //movies =list.toArray(new CharSequence[list.size()]);

                    Toast.makeText(getActivity(), "Movie Edited", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AddMovieFragment.OnFragmentInteractionListener) {
            mListener = (EditInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public interface EditInterface{

        public void afteredit(String text, String Desc, String genre, String year, String imdb, String rv);
        public Movie editing();
        public int which();
    }

}
