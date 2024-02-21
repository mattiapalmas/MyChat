package com.mychat.data.model

data class MessageChat(
    val content: String,
    val senderId: Int
) {
    fun isFromCurrentUser(userId: Int): Boolean = senderId == userId
}
