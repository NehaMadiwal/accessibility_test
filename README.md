# accessibility_test

![accessibility](https://github.com/user-attachments/assets/592179b9-a1c6-4cf7-860e-26a7364d2bee)


<h2>AccessibilityService Sample app</h2>

In this app we have configures AccessibilityService to listen to click, text change, window state change, view foused events from  `com.example.jetcomposehybridproject`  and `com.example.jetnews`
The test app are available here 


1. com.example.jetcomposehybridproject -> https://github.com/NehaMadiwal/compose_test_app

2. com.example.jetnews -> https://github.com/android/compose-samples/tree/main/JetNews

<h2>Important classes</h2>

1. CustomAccessibilityService -> Implements AccessibilityService and provides definitions for `onServiceConnected()` and `onAccessibilityEvent()`
  
2. EventData -> Data class represents the data being read from AccessibilityEvent

3. EventType -> Enum mapping AccessibilityEvent types to more readable types.
   
4. ServiceControlActivity -> Settings activity for AccessibilityService. It shows the service status and provides UI to toggle it on or off.

<h2>Issue faced with Accessibility service</h2>

1. When user turns on AccessibilityService and launches any of the supported apps (JetNews or Compose test app), `AccessibilityEvent.TYPE_VIEW_CLICKED` is not received for Compose views but when XML Button is clicked from Compose test app, it's received by `onAccessibilityEvent()`. Following are the logs from logcat for **Test App Launch -> Compose button click -> XML BUTTON click** when AccessibilityService is enabled.

```
EVENT data: EventData(eventType=WINDOW_STATE_CHANGED, viewId=Unknown, text=Unknown, packageName=com.example.jetcomposehybridproject, className=com.example.jetcomposehybridproject.MainActivity, eventTime=78602542, contentDescription=Unknown)
EVENT data: EventData(eventType=VIEW_CLICKED, viewId=com.example.jetcomposehybridproject:id/xml_button, text=XML BUTTON, packageName=com.example.jetcomposehybridproject, className=android.widget.Button, eventTime=78606970, contentDescription=Xml Button)
```


<h2>Other Observations</h2> 

1. Adding semantics and `testTagsAsResourceId` along with `testTag` for clickable components didn't resolve the issue.
2. Adding support for `AccessibilityServiceInfo.FLAG_INCLUDE_NOT_IMPORTANT_VIEWS` didn't resolve the issue.
3. When AccessibilityService in current project is enabled along with TalkBack service, click events for comopse views are received, though the `event.classname` shows "android.view.View". Refer the logcat when running the app.
Following are the logs from logcat for **Test App Launch -> Compose button click -> XML BUTTON click** when AccessibilityService is enabled along with TalkBack service.

```
EVENT data: EventData(eventType=WINDOW_STATE_CHANGED, viewId=Unknown, text=Unknown, packageName=com.example.jetcomposehybridproject, className=com.example.jetcomposehybridproject.MainActivity, eventTime=80315450, contentDescription=Unknown)
EVENT data: EventData(eventType=VIEW_CLICKED, viewId=btn_compose, text=Unknown, packageName=com.example.jetcomposehybridproject, className=android.view.View, eventTime=80341096, contentDescription=Unknown)
EVENT data: EventData(eventType=VIEW_CLICKED, viewId=com.example.jetcomposehybridproject:id/xml_button, text=XML BUTTON, packageName=com.example.jetcomposehybridproject, className=android.widget.Button, eventTime=80350724, contentDescription=Xml Button)
```




