package model

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

data class EventsState(
    val events: List<EventItem> = emptyList()
)

class EventsViewModel : ViewModel(){

    private val _uiState = MutableStateFlow<EventsState>(EventsState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchEvents()
    }

    private val client = HttpClient{
        install(ContentNegotiation) {
            json()
        }
    }

    fun fetchEvents() {
        viewModelScope.launch {
            val events = getEvents()

            _uiState.update {
                it.copy(events = events)
            }
        }
    }

//    private suspend fun getEvents(): List<EventItem> {
//        val json = kotlinx.serialization.json.Json {
//            ignoreUnknownKeys = true
//        }
//        val events =  client.get("https://ventsapi.onrender.com/events").body<String>()
//        return json.decodeFromString(events)
//    }

    private suspend fun getEvents(retryCount: Int = 4): List<EventItem> {
        val json = Json {
            ignoreUnknownKeys = true
        }

        var lastException: Exception? = null

        repeat(retryCount) { attempt ->
            try {
                val events = client.get("https://ventsapi.onrender.com/events").body<String>()
                return json.decodeFromString(events)
            } catch (e: Exception) {
                lastException = e
                println("Attempt $attempt failed. Retrying...")
            }
        }

        throw lastException ?: RuntimeException("Failed to fetch events after $retryCount retries.")
    }

    override fun onCleared() {
        client.close()
    }
}
