package com.example.accessibilitytest

data class EventData(
    val eventType: EventType,
    val viewId: String,
    val text: String,
    val packageName: String? = null,
    val className: String? = null,
    val eventTime: Long = 0L,
    val contentDescription: String? = null,
)
