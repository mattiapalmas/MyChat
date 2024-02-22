package com.mychat.screens.chat

import androidx.lifecycle.ViewModel
import com.mychat.data.model.MessageChat
import com.mychat.utils.Constants.FAKE_MESSAGES
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(): ViewModel() {

    private var _chatMessages: MutableStateFlow<List<MessageChat>> = MutableStateFlow(listOf())
    val chatMessages = _chatMessages.asStateFlow()

    suspend fun fetchChatMessages() {
        delay(1000)
        _chatMessages.value = FAKE_MESSAGES
    }
}