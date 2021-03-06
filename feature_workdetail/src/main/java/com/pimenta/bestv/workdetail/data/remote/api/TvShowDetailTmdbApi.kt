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

package com.pimenta.bestv.workdetail.data.remote.api

import com.pimenta.bestv.model.data.remote.CastListResponse
import com.pimenta.bestv.model.data.remote.TvShowPageResponse
import com.pimenta.bestv.workdetail.data.remote.model.VideoListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by marcus on 20-10-2019.
 */
interface TvShowDetailTmdbApi {

    @GET("tv/{tv_id}/credits")
    fun getCastByTvShow(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Single<CastListResponse>

    @GET("tv/{tv_id}/videos")
    fun getVideosByTvShow(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Single<VideoListResponse>

    @GET("tv/{tv_id}/recommendations")
    fun getRecommendationByTvShow(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Single<TvShowPageResponse>

    @GET("tv/{tv_id}/similar")
    fun getSimilarByTvShow(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Single<TvShowPageResponse>
}
