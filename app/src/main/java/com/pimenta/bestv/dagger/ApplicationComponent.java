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

package com.pimenta.bestv.dagger;

import android.app.Application;

import com.pimenta.bestv.activity.MainActivity;
import com.pimenta.bestv.activity.MovieDetailsActivity;
import com.pimenta.bestv.broadcastreceiver.BootBroadcastReceiver;
import com.pimenta.bestv.dagger.module.ApplicationModule;
import com.pimenta.bestv.database.DatabaseHelper;
import com.pimenta.bestv.fragment.ErrorFragment;
import com.pimenta.bestv.fragment.GenreMovieGridFragment;
import com.pimenta.bestv.fragment.MovieBrowseFragment;
import com.pimenta.bestv.fragment.MovieDetailsFragment;
import com.pimenta.bestv.fragment.SearchFragment;
import com.pimenta.bestv.fragment.TopMovieGridFragment;
import com.pimenta.bestv.service.RecommendationService;
import com.pimenta.bestv.widget.CastCardPresenter;
import com.pimenta.bestv.widget.MovieCardPresenter;
import com.pimenta.bestv.widget.VideoCardPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by marcus on 07-02-2018.
 */
@Singleton
@Component(modules = {
        ApplicationModule.class
})
public interface ApplicationComponent {

    Application getApplication();

    DatabaseHelper getDatabaseHelper();

    void inject(BootBroadcastReceiver receiver);

    void inject(RecommendationService service);

    void inject(MainActivity activity);

    void inject(MovieDetailsActivity activity);

    void inject(MovieBrowseFragment fragment);

    void inject(GenreMovieGridFragment fragment);

    void inject(TopMovieGridFragment fragment);

    void inject(SearchFragment fragment);

    void inject(MovieDetailsFragment fragment);

    void inject(ErrorFragment fragment);

    void inject(CastCardPresenter presenter);

    void inject(MovieCardPresenter presenter);

    void inject(VideoCardPresenter presenter);

}