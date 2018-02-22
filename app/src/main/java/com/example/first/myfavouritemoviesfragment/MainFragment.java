package com.example.first.myfavouritemoviesfragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.first.myfavouritemoviesfragment.MainActivity.alist;
import static com.example.first.myfavouritemoviesfragment.MainActivity.list;
import static com.example.first.myfavouritemoviesfragment.MainActivity.movies;


public class MainFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Context context;
    private String mParam1;
    private String mParam2;
    Toast toast;

    AlertDialog.Builder builder;
    private OnFragmentInteractionListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }


    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    } @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentInteraction();
            }
        });

        getActivity().findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.edit();
            }
        });

        getActivity().findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.delete();
                if(movies == null || movies.length == 0) {
                    toast  = Toast.makeText(getActivity(),"Sorry, there are no movies to delete",Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {

                    builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Pick a movie")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Log.d("Edit Movie","Clicked Ok");
                                    toast.show();
                                }
                            })
                            .setItems(movies, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //  Log.d(TAG,movies[which].toString());

                                    alist.remove(which);
                                    list.remove(which);
                                    movies =list.toArray(new CharSequence[list.size()]);

                                }
                            });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });

        getActivity().findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(movies == null || movies.length == 0) {
                    toast  = Toast.makeText(getActivity(),"Sorry, there are no movies to show",Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    mListener.displayyear();
                }
            }
        });
        getActivity().findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(movies == null || movies.length == 0) {
                    toast  = Toast.makeText(getActivity(),"Sorry, there are no movies to show",Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    mListener.displayrating();
                }
            }
        });

    }

    public void editclicked(CharSequence[] movies, final ArrayList<Movie> alist){

        if(movies == null || movies.length == 0) {
            toast  = Toast.makeText(getActivity(),"Sorry, there are no movies to edit",Toast.LENGTH_SHORT);
            toast.show();
        }
        else {

            builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Pick a movie")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.d("Edit Movie","Clicked Ok");
                            toast  = Toast.makeText(getActivity(),"No changes made!",Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    })
                    .setItems(movies, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.d("deeeemmmmooooooo",alist.get(which)+"");

                            mListener.openedit(alist.get(which), which);

                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
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


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction();
        void edit();
        void openedit(Movie movie, int which);
        void delete();
        void displayyear();
        void displayrating();
    }
}
