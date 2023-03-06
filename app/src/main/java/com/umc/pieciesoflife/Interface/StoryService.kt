package com.umc.pieciesoflife.Interface


import com.umc.pieciesoflife.DTO.StoryDto.Story
import com.umc.pieciesoflife.DTO.StoryDto.*
import com.umc.pieciesoflife.DTO.StoryDto.myStory.MyStory
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface StoryService {

    // 자서전 둘러보기
    @GET("/story")
    fun getStoryExplore(
        @Query("cursorId") cursorId : Int,
        @Query("page") page : Int,
        @Query("size") size : Int,
        @Query("sort") sort : String
    ): Call<Story>


    // 홈 화면(HomeFragment)
    @GET("/story/main")
    fun getStoryHome(
        @Header("content-type") contentType : String,
        @Header("Authorization") accessToken : String,
        @Query("cursorId") cursorId : Int,
        @Query("page") page : Int,
        @Query("size") size : Int,
        @Query("sort") sort : String
    ): Call<Story>

    // 이야기 생성
    @POST("/story")
    fun postStory(
        @Header("content-type") contentType : String,
        @Header("Authorization") accessToken : String,
        @Body story: StoryPost
    ): Call<StoryPostResult>

    // 태그 필터링 조회 (나의 자서전1)
    @GET("story/filter/{tagId}")
    fun getStoryFilter(
        @Header("content-type") contentType : String,
        @Header("Authorization") accessToken : String,
        @Path("tagId") tagId: Int,
        @Query("page") page : Int,
        @Query("size") size : Int,
        @Query("sort") sort : String
    ) : Call<StoryFilter>

    // 내가 작성한 자서전 전체조회
    @GET("story/my")
    fun getMyStory(
        @Header("Authorization") accessToken : String,
        @Query("page") page : Int,
        @Query("size") size : Int,
        @Query("sort") sort : String
    ): Call<MyStory>

    // 둘러보기 - 상세보기
    @GET("story/{storyId}")
    fun getStoryDetail(
        @Header("Authorization") accessToken : String,
        @Path("storyId") storyId: Int
    ) : Call<StoryDetail>


    // 좋아요
    @POST("story/{storyId}/like")
    fun postStoryLike(
        @Header("Authorization") accessToken : String,
        @Path("storyId") storyId: Int,
        @Body isLiked: StoryLikeData
    ) : Call<StoryLike>


    // 대표이야기로 설정
    @PATCH("story/{storyId}/main")
    fun patchStoryMain(
        @Header("Authorization") accessToken : String,
        @Path("storyId") storyId: Int,
        @Body isMain: StoryMain
    ) : Call<StoryMainResult>


    // 공개로 설정
    @PATCH("story/{storyId}/open")
    fun patchStoryOpen(
        @Header("Authorization") accessToken : String,
        @Path("storyId") storyId: Int,
        @Body isOpened: StoryOpen
    ) : Call<StoryOpenResult>


    // 표지색 설정
    @PATCH("story/{storyId}/color")
    fun patchStoryColor(
        @Header("Authorization") accessToken : String,
        @Path("storyId") storyId: Int,
        @Body color: StoryColor
    ) : Call<StoryColorResult>


    // 이야기 삭제
    @DELETE("story/{storyId}")
    fun deleteStory(
        @Header("content-type") contentType : String,
        @Header("Authorization") accessToken : String,
        @Path("storyId") storyId: Int
    ) : Call<Void>
// StoryPost를 해야될수도, StoryDelete 일수도? (이전주석)
}