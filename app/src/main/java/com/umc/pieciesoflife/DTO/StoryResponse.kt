package com.umc.pieciesoflife.DTO



import java.util.*
import com.google.gson.annotations.SerializedName


data class StoryResponse ( // 이야기 DTO response header 부분

    @SerializedName("status"  ) var status  : Int?     = null,
    @SerializedName("success" ) var success : Boolean? = null,
    @SerializedName("message" ) var message : String?  = null,
    @SerializedName("data"    ) var data    : Data?    = Data() // body로 생각

)
data class Data ( // body 부분

    @SerializedName("story"        ) var story        : Story?                  = Story(),
    @SerializedName("storyTag"        ) var storyTag        : StoryTag?                  = StoryTag(),
    @SerializedName("storyList" ) var storyList : ArrayList<StoryList> = arrayListOf(),
    @SerializedName("Qna" ) var qna : Qna? = Qna(),
    @SerializedName("questionList" ) var questionList : ArrayList<QuestionList> = arrayListOf()

)

data class Story (

    @SerializedName("id"              ) var id              : Int?     = null,
    @SerializedName("title"           ) var title           : String?  = null,
    @SerializedName("description"     ) var description     : String?  = null,
    @SerializedName("date"            ) var date            : String?  = null,
    @SerializedName("backgroundColor" ) var backgroundColor : String?  = null,
    @SerializedName("likeNum"         ) var likeNum         : Int?     = null,
    @SerializedName("isLiked"         ) var isLiked         : Boolean? = null,
    @SerializedName("profileImgUrl"   ) var profileImgUrl   : String?  = null,
    @SerializedName("nickname"        ) var nickname        : String?  = null

)

data class QuestionList (

    @SerializedName("question"      ) var question      : String? = null,
    @SerializedName("tagCategoryId" ) var tagCategoryId : Int?    = null,
    @SerializedName("answer"        ) var answer        : String? = null

)

data class StoryList (

    @SerializedName("title"           ) var title           : String?  = null,
    @SerializedName("description"     ) var description     : String?  = null,
    @SerializedName("date"            ) var date            : String?  = null,
    @SerializedName("backgroundColor" ) var backgroundColor : String?  = null,
    @SerializedName("likeNum"         ) var likeNum         : Int?     = null,
    @SerializedName("isLiked"         ) var isLiked         : Boolean? = null,
    @SerializedName("profileImgUrl"   ) var profileImgUrl   : String?  = null,
    @SerializedName("nickname"        ) var nickname        : String?  = null

)

data class StoryTag (

    @SerializedName("tagId"      ) var tagId      : Int?    = null,
    @SerializedName("tagContent" ) var tagContent : String? = null

)


data class Qna (

    @SerializedName("tagId"    ) var tagId    : Int?    = null,
    @SerializedName("question" ) var question : String? = null,
    @SerializedName("answer"   ) var answer   : String? = null

)

data class DataList (

    @SerializedName("tag"     ) var tag     : String?            = null,
    @SerializedName("stories" ) var stories : ArrayList<Stories> = arrayListOf()

)

data class Stories (

    @SerializedName("id"              ) var id              : Int?    = null,
    @SerializedName("title"           ) var title           : String? = null,
    @SerializedName("description"     ) var description     : String? = null,
    @SerializedName("date"            ) var date            : String? = null,
    @SerializedName("backgroundColor" ) var backgroundColor : String? = null,
    @SerializedName("profileImgUrl"   ) var profileImgUrl   : String? = null,
    @SerializedName("nickname"        ) var nickname        : String? = null,
    @SerializedName("tag"             ) var tag             : String? = null

)