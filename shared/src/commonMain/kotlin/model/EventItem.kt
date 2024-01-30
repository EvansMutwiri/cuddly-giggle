package model

import kotlinx.serialization.Serializable

@Serializable
data class EventItem(
    val description: String,
    val src: String
)