package com.utils

import com.mychat.data.model.MessageChat

object Constants {
    val FAKE_MESSAGES = listOf(
        MessageChat("Hi Simone", 1),
        MessageChat("Hi Mattia", 2),
        MessageChat("How are you?", 1),
        MessageChat("I'm good thanks. What about you?", 2),
        MessageChat(
            "I'm doing well thanks, are you free tomorrow afternoon to go and play basketball?",
            1
        ),
        MessageChat("Yep, after 3pm I'm free!", 2)
    )

    const val CURRENT_USER_ID = 1
}