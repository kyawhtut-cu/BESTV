/*
 * Copyright (C) 2018 Marcus Pimenta
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.pimenta.bestv.manager;

import android.support.annotation.StringRes;

import com.pimenta.bestv.BesTV;
import com.pimenta.bestv.R;
import com.pimenta.bestv.repository.entity.Movie;
import com.pimenta.bestv.repository.entity.MovieList;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by marcus on 05-03-2018.
 */
public interface MovieManager {

    /**
     * Checks if the {@link Movie} is favorite
     *
     * @return          {@code true} if yes, {@code false} otherwise
     */
    boolean isFavorite(Movie movie);

    /**
     * Checks if there is any {@link Movie} saved as favorite
     *
     * @return          {@code true} if there any {@link Movie} saved as favorite,
     *                  {@code false} otherwise
     */
    boolean hasFavoriteMovie();

    /**
     * Saves a {@link Movie} as favorites
     *
     * @param movie     {@link Movie} to be saved as favorite
     * @return          {@code true} if the {@link Movie} was saved with success,
     *                  {@code false} otherwise
     */
    boolean saveFavoriteMovie(Movie movie);

    /**
     * Deletes a {@link Movie} from favorites
     *
     * @param movie     {@link Movie} to be deleted from favorite
     * @return          {@code true} if the {@link Movie} was deleted with success,
     *                  {@code false} otherwise
     */
    boolean deleteFavoriteMovie(Movie movie);

    /**
     * Gets the favorites {@link List<Movie>}
     *
     * @return          Favorite {@link Single<List<Movie>>}
     */
    Single<List<Movie>> getFavoriteMovies();

    /**
     * Loads the {@link MovieList} by {@link MovieManager.MovieListType}
     *
     * @param page              Page to be loaded
     * @param movieListType     {@link MovieManager.MovieListType}
     * @return                  {@link Single<MovieList>}
     */
    Single<MovieList> loadMoviesByType(int page, MovieManager.MovieListType movieListType);

    /**
     * Represents the movie list type
     */
    public enum MovieListType {
        FAVORITES(R.string.favorites),
        NOW_PLAYING(R.string.now_playing),
        POPULAR(R.string.popular),
        TOP_RATED(R.string.top_rated),
        UP_COMING(R.string.up_coming);

        private String mName;

        MovieListType(@StringRes int nameResource) {
            mName = BesTV.get().getString(nameResource);
        }

        public String getName() {
            return mName;
        }
    }

}