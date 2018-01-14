package ilyatkachev.github.com.mycinema.movies.domain.model;


import java.util.List;

import ilyatkachev.github.com.mycinema.data.remote.gson.IBaseCinemaObject;

public interface IMovie extends IBaseCinemaObject {

    int getVoteСount();

    int getId();

    boolean isVideo();

    float getVoteAverage();

    String getTitle();

    float getPopularity() ;

    String getPosterPath() ;

    String getOriginalLanguage();

    String getOriginalTitle() ;

    List<Integer> getGenreIds() ;

    String getBackdropPath();

    boolean isAdult() ;

    String getOverview();

    String getReleaseDate();

    String getReleaseYear();

    int getPage();

}
