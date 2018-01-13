package ilyatkachev.github.com.mycinema.movies;

import ilyatkachev.github.com.mycinema.util.Constants;

public enum MoviesFilterType {
    POPULAR, TOP_RATED, UPCOMING, NOW_PLAYING;

    public String toApiString() {
        switch (this) {
            case POPULAR:
                return Constants.ApiValues.POPULAR;
            case TOP_RATED:
                return Constants.ApiValues.TOP_RATED;
            case UPCOMING:
                return Constants.ApiValues.UPCOMING;
            case NOW_PLAYING:
                return Constants.ApiValues.NOW_PLAYING;
            default:
                return super.toString();
        }
    }
}
