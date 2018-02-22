package com.example.first.myfavouritemoviesfragment;

import java.io.Serializable;

/**
 * Created by Chaithanya on 1/28/2017.
 */

public class Movie implements Serializable{

    String name;
    String description;
    String genre;
    String rating;
    String year;
    String imdb;


    public Movie(String name, String description, String genre, String rating, String year, String imdb) {
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.rating = rating;
        this.year = year;
        this.imdb = imdb;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", genre='" + genre + '\'' +
                ", rating='" + rating + '\'' +
                ", year='" + year + '\'' +
                ", imdb='" + imdb + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }
}
