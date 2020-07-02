package dev.ivnv.meetup.dispatcher

import com.github.kotlintelegrambot.dispatcher.Dispatcher
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.dispatcher.sticker
import com.github.kotlintelegrambot.dispatcher.text
import com.tinder.StateMachine
import dev.ivnv.meetup.logging.LoggerDelegate
import dev.ivnv.meetup.service.StateService


class Dispatcher(private val stateMachine: StateMachine<StateService.State, StateService.Event, StateService.SideEffect>) {

    private val log by LoggerDelegate()

    fun build(): Dispatcher.() -> Unit = {
        text { bot, update ->
            log.info("{}, {}", update)
        }
        sticker { bot, update, sticker ->
            log.info("{}, {}", update)
        }
        command("start") { bot, update, list ->
            stateMachine.transition(StateService.Event.OnStart(bot, update))
        }
    }
}


