package com.umc.pieciesoflife.Interface


import com.umc.pieciesoflife.DTO.StoryDto.*
import retrofit2.Call
import retrofit2.http.*

interface StoryService {

    // 자서전 둘러보기
    @GET("/story")
    fun getStoryExplore(): Call<StoryExplore>

    // 메인화면
    @GET("/story/main")
    fun getStoryHomeExplore(): Call<StoryHomeExplore>

    // 이야기 생성[서영]



    // 태그 필터링 조회 (나의 자서전1)
    @GET("story/filter/{tagId}")
    fun getStoryTagFilter(
        @Path("tagId") tagId: Int
    ) : Call<StoryTagFilter>

    // 둘러보기 - 상세보기
    @GET("story/{storyId}")
    fun getStoryDetailExplore(
        @Path("storyId") storyId: Int
    ) : Call<StoryDetailExplore>

    // 좋아요
    @POST("story/{storyId}/like")
    fun postStoryLike(
        @Path("storyId") storyId: Int,
        @Path("isLiked") isLiked: Boolean
    ) : Call<StoryLike>

    // 대표이야기로 설정
    @PATCH("story/{storyId}/main")
    fun patchStoryMain(
        @Path("storyId") storyId: Int,
        @Path("isMain") isMain: Boolean
    ) : Call<StoryMain>

    // 공개로 설정
    @PATCH("story/{storyId}/open")
    fun patchStoryOpen(
        @Path("storyId") storyId: Int,
        @Path("isOpen") isOpen: Boolean
    ) : Call<StoryOpen>

    // 표지색 설정
    @PATCH("story/{storyId}/color")
    fun patchStoryColor(
        @Path("storyId") storyId: Int,
        @Path("color") color: String
    ) : Call<StoryColor>

    // 이야기 삭제
    @DELETE("story/{storyId}")
    fun deleteStory(
        @Path("storyId") storyId: Int
    ) : Call<StoryDelete>
}