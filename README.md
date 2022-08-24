# WatchMoviesSampleApp

Libraries Used
--------------
  * [Lifecycles][3] Create a UI that automatically responds to lifecycle events.
  * [Navigation][4] Handle everything needed for in-app navigation.
  * [Flow][5] used for kotlin flow on android 
  * [StateFlow][18] StateFlow A Flow that represents a read-only state with a single updatable data value that emits updates to the value to it
  * [Paging3][21] The Paging library used is to you load and display pages of data from a larger dataset from local storage or over network
  * [ViewModel][6] Easily schedule asynchronous tasks for optimal execution.
  * [LiveData][20] Build data objects that notify views when the underlying database changes.
  * [Retrofit][9] for turns your HTTP API into a Java interface
  * [Gson][10] for convert Java Objects into their JSON representation
  * [Hilt][11] for dependency injection
  * [Glide][13] for Image.
  * [Room][8] Access your app's SQLite database with in-app objects and compile-time checks.
  * [Coroutines][16] is used for asynchronous programming on Android
  * [koltin-parecelize][17] is used between screens to data carry 
  * [Youtube][21] 

 UI Layer: MVVM 
 ------------------------
The app uses [MVVM architecture][15] to have a unidirectional flow of data, separation of concern, testability, and a lot more.

![Architecture](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)

Preview
-------------------------
https://user-images.githubusercontent.com/52752443/186473794-d4ab7ef4-f97b-4543-821f-8b36a391ea44.mp4



[1]: https://www.balldontlie.io/#introduction
[2]: https://developer.android.com/topic/architecture/intro
[3]: https://developer.android.com/guide/components/activities/activity-lifecycle
[4]: https://developer.android.com/guide/navigation/navigation-getting-started
[5]: https://developer.android.com/kotlin/flow
[6]: https://developer.android.com/topic/libraries/architecture/viewmodel
[7]: https://developer.android.com/kotlin/coroutines
[8]: https://developer.android.com/training/data-storage/room
[9]: https://square.github.io/retrofit/
[10]: https://github.com/google/gson
[11]: https://developer.android.com/training/dependency-injection/hilt-android
[12]: https://github.com/airbnb/lottie-android
[13]: https://github.com/bumptech/glide
[14]: https://github.com/google/ExoPlayer
[15]: https://developer.android.com/topic/architecture
[16]: https://developer.android.com/kotlin/coroutines
[17]: https://developer.android.com/kotlin/parcelize
[18]: https://developer.android.com/kotlin/flow/stateflow-and-sharedflow
[19]: https://developer.android.com/topic/libraries/architecture/datastore?gclid=CjwKCAjw9NeXBhAMEiwAbaY4liy2YHqQkrxCEEXe3Cq1ZBmkYnQK6q7Zs5C5juMPBLgguRXqPNn0pRoCTPYQAvD_BwE&gclsrc=aw.ds
[20]: https://developer.android.com/topic/libraries/architecture/livedata
[21]: https://github.com/PierfrancescoSoffritti/android-youtube-player
