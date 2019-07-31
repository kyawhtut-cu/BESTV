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

package com.pimenta.bestv.di.module

import com.pimenta.bestv.data.repository.MediaRepository
import com.pimenta.bestv.data.repository.MediaRepositoryImpl
import com.pimenta.bestv.data.repository.local.MediaLocalRepository
import com.pimenta.bestv.data.repository.local.MediaLocalRepositoryImpl
import com.pimenta.bestv.data.repository.remote.MediaRemoteRepository
import com.pimenta.bestv.data.repository.remote.TmdbMediaRemoteRepository
import com.pimenta.bestv.manager.permission.PermissionManager
import com.pimenta.bestv.manager.permission.PermissionManagerImpl
import com.pimenta.bestv.manager.preference.PreferenceManager
import com.pimenta.bestv.manager.preference.PreferenceManagerImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Created by marcus on 09-02-2018.
 */
@Module
interface ImplModule {

    @Binds
    @Singleton
    fun provideMediaRemote(connector: TmdbMediaRemoteRepository): MediaRemoteRepository

    @Binds
    @Singleton
    fun provideMediaLocal(mediaLocalImplModule: MediaLocalRepositoryImpl): MediaLocalRepository

    @Binds
    @Singleton
    fun provideMovieRepository(repository: MediaRepositoryImpl): MediaRepository

    @Binds
    @Singleton
    fun providePreferenceManager(manager: PreferenceManagerImpl): PreferenceManager

    @Binds
    @Singleton
    fun providePermissionManager(manager: PermissionManagerImpl): PermissionManager

}