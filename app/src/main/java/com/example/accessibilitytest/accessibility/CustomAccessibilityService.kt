package com.example.accessibilitytest.accessibility

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import com.example.accessibilitytest.EventData
import com.example.accessibilitytest.EventType


class CustomAccessibilityService: AccessibilityService() {
    private val TAG = javaClass.simpleName

    override fun onServiceConnected() {
        val info = AccessibilityServiceInfo().apply {
            // Set the type of events that this service wants to listen to. Others
            // aren't passed to this service.
            eventTypes = AccessibilityEvent.TYPE_VIEW_CLICKED or
                    AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED or
                    AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED or
                    AccessibilityEvent.TYPE_VIEW_FOCUSED

            // If you only want this service to work with specific apps, set their
            // package names here. Otherwise, when the service is activated, it
            // listens to events from all apps.
            packageNames = arrayOf(
                "com.nehamadiwal.movieapp",
                "com.nehamadiwal.jettest",
                "com.example.jetnews"
            )

            // Set the type of feedback your service provides.
            feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC

            // Default services are invoked only if no package-specific services are
            // present for the type of AccessibilityEvent generated. This service is
            // app-specific, so the flag isn't necessary. For a general-purpose
            // service, consider setting the DEFAULT flag.

             flags = AccessibilityServiceInfo.FLAG_REPORT_VIEW_IDS or
                     AccessibilityServiceInfo.FLAG_RETRIEVE_INTERACTIVE_WINDOWS or
                     AccessibilityServiceInfo.FLAG_INCLUDE_NOT_IMPORTANT_VIEWS

            notificationTimeout = 100
        }

        this.serviceInfo = info
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {

        val source: AccessibilityNodeInfo? = event?.source
        source?.let {
            val eventType = EventType.fromInt(event.eventType)
            val viewId = it.viewIdResourceName ?: "Unknown"
            val text = it.text?.toString() ?: "Unknown"
            val packageName = event.packageName?.toString() ?: "Unknown"
            val className = event.className?.toString() ?: "Unknown"
            val eventTime = event.eventTime
            val contentDescription = it.contentDescription?.toString() ?: "Unknown"

            val eventData = EventData(
                eventType = eventType,
                viewId = viewId,
                text = text,
                packageName = packageName,
                className = className,
                eventTime = eventTime,
                contentDescription = contentDescription
            )
            Log.d(TAG, "EVENT data: $eventData")
        }



    }

    override fun onInterrupt() {
        Log.e(TAG, "onInterrupt")
    }
}