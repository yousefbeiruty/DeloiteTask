package com.example.navigationtutorial.model

import com.google.gson.annotations.SerializedName


data class MoviesListResponse(
    @SerializedName("page")
    val page: Int?=null, // 1
    @SerializedName("results")
    val results: List<Result>?=null,
    @SerializedName("total_pages")
    val totalPages: Int?=null, // 34218
    @SerializedName("total_results")
    val totalResults: Int?=null // 684341
) {
    data class Result(
        @SerializedName("adult")
        val adult: Boolean?=null, // false
        @SerializedName("backdrop_path")
        val backdropPath: String?=null, // /wcKFYIiVDvRURrzglV9kGu7fpfY.jpg
        @SerializedName("genre_ids")
        val genreIds: List<Int>?=null,
        @SerializedName("id")
        val id: Int?=null, // 453395
        @SerializedName("original_language")
        val originalLanguage: String?=null, // en
        @SerializedName("original_title")
        val originalTitle: String?=null, // Doctor Strange in the Multiverse of Madness
        @SerializedName("overview")
        val overview: String?=null, // Doctor Strange, with the help of mystical allies both old and new, traverses the mind-bending and dangerous alternate realities of the Multiverse to confront a mysterious new adversary.
        @SerializedName("popularity")
        val popularity: Double?=null, // 7931.499
        @SerializedName("poster_path")
        val posterPath: String?=null, // /9Gtg2DzBhmYamXBS1hKAhiwbBKS.jpg
        @SerializedName("release_date")
        val releaseDate: String?=null, // 2022-05-04
        @SerializedName("title")
        val title: String?=null, // Doctor Strange in the Multiverse of Madness
        @SerializedName("video")
        val video: Boolean?=null, // false
        @SerializedName("vote_average")
        val voteAverage: Double?=null, // 7.5
        @SerializedName("vote_count")
        val voteCount: Int?=null // 3987
    )
}