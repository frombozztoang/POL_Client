package com.umc.pieciesoflife.Interface

import com.umc.pieciesoflife.DTO.QnA
import com.umc.pieciesoflife.DTO.Story
import com.umc.pieciesoflife.DTO.TagPost
import retrofit2.Call
import retrofit2.http.*

interface StoryPostService {

    //Response


    //Request
    @FormUrlEncoded
    @POST("/Story")
    @Headers("content-type: application/json",
        "Authorization: Token")
    fun storyTagPost(
        @Field("tagId") tagId : Int,
        @Field("tagContent") tagContent : String,
        //@Body tagPost : TagPost, //보니까 이케 보내주는것도 있어서 일단 두가지 방법 다 가져옴 ... .. . .. . . ..
    ) : Call<TagPost>

    @FormUrlEncoded
    @POST("/Story")
    @Headers("content-type: application/json",
        "Authorization: Token")
    fun storyPost(
        @Field("title") title : String,
        @Field("description") description : String,
        @Field("color") color : String,
        //@Body story : Story,
    ) : Call<Story>

    @FormUrlEncoded
    @POST("/Story")
    @Headers("content-type: application/json",
        "Authorization: Token")
    fun qnaPost(
        @Field("tagId") tagId : Int,
        @Field("question") question : String,
        @Field("answer") answer : String,
        //@Body qna : QnA,
    ) : Call<QnA>
}