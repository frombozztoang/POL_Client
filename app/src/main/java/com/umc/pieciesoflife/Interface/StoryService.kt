package com.umc.pieciesoflife.Interface


import com.umc.pieciesoflife.DTO.StoryDto.*
import retrofit2.Call
import retrofit2.http.*

interface StoryService {

    // 자서전 둘러보기
    @Headers("content-type: application/json",
        "Authorization: Token")
    @GET("/story")
    fun getStoryExplore(
        @Query("cursorId") cursorId : Int,
        @Query("page") page : Int,
        @Query("size") size : Int,
        @Query("sort") sort : String
    ): Call<StoryExplore>


    // 홈 화면
    @Headers("content-type: application/json",
        "Authorization: Token")
    @GET("/story/main")
    fun getStoryHome(
        @Query("cursorId") cursorId : Int,
        @Query("page") page : Int,
        @Query("size") size : Int,
        @Query("sort") sort : String
    ): Call<StoryHome>

    // 이야기 생성
    @POST("/Story")
    fun postStory(
        @Body title: String,
        @Body description: String,
        @Body color: String,
        @Body storyTag: StoryTag,
        @Body storyQna: StoryQna
    ) : Call<StoryPost>

    // 태그 필터링 조회 (나의 자서전1)
    @GET("story/filter/{tagId}")
    @Headers("content-type: application/json",
        "Authorization: Token")
    fun getStoryTagFilter(
        @Path("tagId") tagId: Int,
        @Query("page") page : Int,
        @Query("size") size : Int,
        @Query("sort") sort : String
    ) : Call<StoryTagFilter>

    // 둘러보기 - 상세보기
    @GET("story/{storyId}")
    @Headers("content-type: application/json")
    fun getStoryDetailExplore(
        @Path("storyId") storyId: Int
    ) : Call<StoryDetailExplore>

    // 좋아요
    @POST("story/{storyId}/like")
    @Headers("content-type: application/json",
        "Authorization: Token")
    fun postStoryLike(
        @Path("storyId") storyId: Int,
        @Body isLiked: Boolean
    ) : Call<StoryLike>

    // 대표이야기로 설정
    @PATCH("story/{storyId}/main")
    @Headers("content-type: application/json",
        "Authorization: Token")
    fun patchStoryMain(
        @Path("storyId") storyId: Int,
        @Body isMain: Boolean
    ) : Call<StoryMain>

    // 공개로 설정
    @PATCH("story/{storyId}/open")
    @Headers("content-type: application/json",
        "Authorization: Token")
    fun patchStoryOpen(
        @Path("storyId") storyId: Int,
        @Body isOpened: Boolean
    ) : Call<StoryOpen>

    // 표지색 설정
    @PATCH("story/{storyId}/color")
    @Headers("content-type: application/json",
        "Authorization: Token")
    fun patchStoryColor(
        @Path("storyId") storyId: Int,
        @Body color: String
    ) : Call<StoryColor>

    // 이야기 삭제
    @DELETE("story/{storyId}")
    @Headers("content-type: application/json",
        "Authorization: Token")
    fun deleteStory(
        @Path("storyId") storyId: Int
    ) : Call<StoryDelete> // StoryPost를 해야될수도, ResponseBody 일수도?
}