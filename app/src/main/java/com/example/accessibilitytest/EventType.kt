package com.example.accessibilitytest

import android.view.accessibility.AccessibilityEvent

enum class EventType(val type: Int) {
    VIEW_CLICKED(AccessibilityEvent.TYPE_VIEW_CLICKED),
    VIEW_SELECTED(AccessibilityEvent.TYPE_VIEW_SELECTED),
    VIEW_FOCUSED(AccessibilityEvent.TYPE_VIEW_FOCUSED),
    VIEW_TEXT_CHANGED(AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED),
    VIEW_TEXT_SELECTION_CHANGED(AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED),
    WINDOW_STATE_CHANGED(AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED),
    WINDOW_CONTENT_CHANGED(AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED),
    VIEW_SCROLLED(AccessibilityEvent.TYPE_VIEW_SCROLLED),
    UNKNOWN(-1);

    companion object {
        @JvmStatic
        fun fromInt(type: Int): EventType =
            entries.find { value -> value.type == type } ?: UNKNOWN
    }

}
