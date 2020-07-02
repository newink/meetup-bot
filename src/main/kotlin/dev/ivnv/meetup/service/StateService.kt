package dev.ivnv.meetup.service

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.Update
import com.tinder.StateMachine


class StateService(
    private val userService: UserService,
    private val errorHandler: ErrorHandler
) {

    fun buildStateMachine(): StateMachine<State, Event, SideEffect> {
        return StateMachine.create {
            initialState(State.Default)
            state<State.Default> {
                on<Event.OnStart> {
                    transitionTo(State.Registered, SideEffect.SendStartCommand)
                }
            }
            state<State.Registered> {

            }
            onTransition { ts ->
                val transition = ts as? StateMachine.Transition.Valid ?: return@onTransition
                val bot = transition.event.bot
                val update = transition.event.update

                try {
                    when (transition.sideEffect) {
                        SideEffect.SendStartCommand -> userService.handleRegister(bot, update)
                    }
                } catch (ex: Exception) {
                    errorHandler.handleError(bot, update, ex)
                }
            }
        }
    }

    sealed class State {
        object Default : State()
        object Registered : State()
    }

    sealed class Event(open val bot: Bot, open val update: Update) {
        data class OnStart(override val bot: Bot, override val update: Update) : Event(bot, update)
    }

    sealed class SideEffect {
        object SendStartCommand : SideEffect()
    }
}


