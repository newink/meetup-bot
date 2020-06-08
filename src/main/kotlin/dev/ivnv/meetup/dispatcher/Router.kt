package dev.ivnv.meetup.dispatcher

import com.github.kotlintelegrambot.dispatcher.Dispatcher
import com.github.kotlintelegrambot.dispatcher.sticker
import com.github.kotlintelegrambot.dispatcher.text


class Router {

    fun build(): Dispatcher.() -> Unit = {
        text { bot, update ->
            //todo
        }
        sticker { bot, update, sticker ->
            //todo
        }
    }
}


