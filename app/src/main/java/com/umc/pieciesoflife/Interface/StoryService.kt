package com.umc.pieciesoflife.Interface

import com.umc.pieciesoflife.DTO.StoryResponse
import retrofit2.Call
import retrofit2.http.*


interface StoryService {

    // 자서전 둘러보기
    @GET("/story")
    fun getStoryExplore(): Call<StoryResponse>

    // 메인화면
    @GET("/story/main")
    fun getMain(): Call<StoryResponse>

    // 이야기 생성
    @POST("/story")
    fun postStory(
        @Query("title") title: String,
        @Query("description") description: String,
        @Query("date") date: String,
        @Query("backgroundColor") backgroundColor: String,
        @Query("likeNum") likeNum: String,
        @Query("isLiked") isLiked: String,
        @Query("profileImgUrl") profileImgUrl: String,
        @Query("nickname") nickname: String
    ) : Call<StoryResponse>

    // 태그 필터링 조회 (나의 자서전1)
    @GET("story/filter/{tagId}")
    fun tagStory(
        @Query("tagId") tagId: Int
    ) : Call<StoryResponse>

    // 둘러보기 - 상세보기
    @GET("story/{storyId}")
    fun detailStory(
        @Query("storyId") storyId: Int
    ) : Call<StoryResponse>

    // 좋아요
    @POST("story/{storyId}/like")
    fun likeStory(
        @Query("storyId") storyId: Int,
        @Query("isLiked") isLiked: Boolean
    ) : Call<StoryResponse>

    // 대표이야기로 설정
    @PATCH("story/{storyId}/main")
    fun mainStory(
        @Query("storyId") storyId: Int,
        @Query("isMain") isMain: Boolean
    ) : Call<StoryResponse>

    // 공개로 설정
    @PATCH("story/{storyId}/open")
    fun openStory(
        @Query("storyId") storyId: Int,
        @Query("isOpen") isOpen: Boolean
    ) : Call<StoryResponse>

    // 표지색 설정
    @PATCH("story/{storyId}/color")
    fun colorStory(
        @Query("storyId") storyId: Int,
        @Query("color") color: String
    ) : Call<StoryResponse>

    // 이야기 삭제
    @DELETE("story/{storyId}")
    fun deleteStory(
        @Query("storyId") storyId: Int
    ) : Call<StoryResponse>
}