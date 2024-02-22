package com.mychat.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.mychat.data.model.MessageChat
import com.mychat.ui.theme.Green
import com.mychat.ui.theme.MyChatTheme
import com.mychat.ui.theme.PurpleGrey80
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mychat.R
import com.mychat.screens.chat.ChatViewModel
import com.mychat.ui.DefaultPreview
import com.mychat.utils.Constants.CURRENT_USER_ID
import com.mychat.utils.Constants.FAKE_MESSAGES

@Composable
fun DefaultChat(
    modifier: Modifier = Modifier,
    chatViewModel: ChatViewModel = viewModel()
    ) {

    val chatMessages by chatViewModel.chatMessages.collectAsState()
    var chatBoxText by rememberSaveable { mutableStateOf("") }

    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (messages, chatBox) = createRefs()

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(messages) {
                    top.linkTo(parent.top)
                    bottom.linkTo(chatBox.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.fillToConstraints
                }
        ) {
            items(items = chatMessages) { item ->
                ChatItem(message = item, userId = CURRENT_USER_ID)
            }
        }

        Row(
            modifier = Modifier
                .constrainAs(chatBox) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            ChatBox(
                textFieldValue = chatBoxText,
                onTextChanged = { chatBoxText = it }
            )
        }
    }
}

@Composable
fun ChatItem(
    modifier: Modifier = Modifier,
    message: MessageChat,
    userId: Int
) {
    val myMessage = message.isFromCurrentUser(userId)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 8.dp, end = 8.dp)
    ) {
        Row(
            modifier = modifier
                .align(if (myMessage) Alignment.CenterStart else Alignment.CenterEnd)
                .clip(
                    RoundedCornerShape(
                        topStart = 48f,
                        topEnd = 48f,
                        bottomStart = if (myMessage) 0f else 48f,
                        bottomEnd = if (myMessage) 48f else 0f
                    )
                )
                .background(if (myMessage) Green else PurpleGrey80)
                .padding(16.dp)
        ) {
            Text(text = message.content)
        }
    }
}

@Composable
fun ChatBox(
    modifier: Modifier = Modifier,
    textFieldValue: String,
    onTextChanged: (text: String) -> Unit
) {

    Row(
        modifier = modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = modifier.weight(1f),
            value = textFieldValue,
            onValueChange = { onTextChanged(it) },
            placeholder = { Text(text = "Type something") }
        )
        Icon(
            modifier = modifier
                .wrapContentSize()
                .padding(start = 8.dp),
            painter = painterResource(id = R.drawable.ic_send),
            contentDescription = "send message"
        )
    }
}


@DefaultPreview
@Composable
fun DefaultChatPreview() {
    MyChatTheme {
        val chatMessages = FAKE_MESSAGES
        var chatBoxText by rememberSaveable { mutableStateOf("") }

        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (messages, chatBox) = createRefs()

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(messages) {
                        top.linkTo(parent.top)
                        bottom.linkTo(chatBox.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        height = Dimension.fillToConstraints
                    }
            ) {
                items(items = chatMessages) { item ->
                    ChatItem(message = item, userId = CURRENT_USER_ID)
                }
            }

            Row(
                modifier = Modifier
                    .constrainAs(chatBox) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                ChatBox(
                    textFieldValue = chatBoxText,
                    onTextChanged = { chatBoxText = it }
                )
            }
        }
    }
}

@DefaultPreview
@Composable
fun ChatItemUserMessagePreview() {
    MyChatTheme {
        ChatItem(message = MessageChat("this is my message", 1), userId = 1)
    }
}

@DefaultPreview
@Composable
fun ChatItemFriendMessagePreview() {
    MyChatTheme {
        ChatItem(message = MessageChat("this is my friend's message", 1), userId = 2)
    }
}

@DefaultPreview
@Composable
fun ChatBoxPreview() {
    ChatBox(textFieldValue = "", onTextChanged = {})
}