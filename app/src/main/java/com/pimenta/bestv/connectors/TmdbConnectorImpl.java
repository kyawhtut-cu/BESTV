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

package com.pimenta.bestv.connectors;

import android.util.Log;

import com.google.gson.Gson;
import com.pimenta.bestv.R;
import com.pimenta.bestv.tmdb.Tmdb;
import com.pimenta.bestv.models.Genre;
import com.pimenta.bestv.models.Movie;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by marcus on 08-02-2018.
 */
public class TmdbConnectorImpl extends BasePreferences implements TmdbConnector {

    private static final String TAG = "TmdbConnectorImpl";

    private String mApiKey;
    private String mLanguage;
    private String mSortBy;

    private Tmdb mTmdb;

    @Inject
    public TmdbConnectorImpl(Gson gson) {
        mApiKey = getString(R.string.tmdb_api_key);
        mLanguage = getString(R.string.tmdb_filter_language);
        mSortBy = getString(R.string.tmdb_filer_sort_by_desc);
        mTmdb = new Tmdb(getString(R.string.tmdb_base_url_api), gson, getThreadPool());
    }

    @Override
    public List<Genre> getGenres() {
        try {
            return mTmdb.getGenreApi().getGenres(mApiKey, mLanguage).execute().body().getGenres();
        } catch (IOException e) {
            Log.e(TAG, "Failed to get the genres", e);
            return null;
        }
    }

    @Override
    public List<Movie> getMoviesByGenre(final Genre genre) {
        try {
            return mTmdb.getGenreApi().getMovies(genre.getId(), mApiKey, mLanguage, false, mSortBy).execute().body().getMovies();
        } catch (IOException e) {
            Log.e(TAG, "Failed to get the movies by genre", e);
            return null;
        }
    }

}