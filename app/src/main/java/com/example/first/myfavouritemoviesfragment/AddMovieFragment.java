package com.example.first.myfavouritemoviesfragment;

import android.content.Context;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class AddMovieFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public AddMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_movie, container, false);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

               EditText Name = (EditText) getActivity().findViewById(R.id.Name_display);
               EditText Description = (EditText) getActivity().findViewById(R.id.Description_display);
               Spinner Genre = (Spinner) getActivity().findViewById(R.id.Genre_display);
                String spinnerselected = Genre.getSelectedItem().toString();

                EditText Year = (EditText) getActivity().findViewById(R.id.Year_display);
               EditText Imdb = (EditText) getActivity().findViewById(R.id.Imdb_display);
               SeekBar seekbar = (SeekBar) getActivity().findViewById(R.id.Rating_display);



               final TextView ratingvalue = (TextView) getActivity().findViewById(R.id.rating_value);
                seekbar.setMax(5);
                ratingvalue.setText(String.valueOf(seekbar.getProgress()));
                //ratingvalue.setText(String.valueOf(1));
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
                else if(TextUtils.isEmpty(Description.getText().toString()) || Description.getText().toString().length() > 255){
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
                    mListener.onFragmentInteraction(Name.getText().toString(), Description.getText().toString(), spinnerselected, ratingvalue.getText().toString(), Year.getText().toString(), Imdb.getText().toString());
                    Toast.makeText(getActivity(), "Movie Added", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String text, String Desc, String genre, String year, String imdb, String rv);
    }
}
