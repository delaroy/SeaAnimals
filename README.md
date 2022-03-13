
# SeaAnimals
This is a native eCommerce App based on REST APIs to display the various information gotten from this sea animals endpoint (https://www.fishwatch.gov/api/species)
There are two layouts in this application, the first displays a list of sea animals information which are species name, habitat impacts and the Specie Illustration Photo src
The second display a list of content of the image gallery.

# Dev Notes
* The entire codebase is in [Kotlin](https://kotlinlang.org/)
* Networking with [Retrofit](https://square.github.io/retrofit/)
* Kotlin [Coroutines](https://kotlinlang.org/docs/reference/coroutines/coroutines-guide.html).
* MVVM Architecture by [Architecture Components](https://developer.android.com/topic/libraries/architecture/)
* Android JetPack Navigation [Navigation](https://developer.android.com/guide/navigation?gclid=CjwKCAiAsYyRBhACEiwAkJFKooM0Q7gkBnrwFLrSCU9Pgrg3zaA7bpDILtEtY34wWzeGa7LMe4HhZBoCD0AQAvD_BwE&gclsrc=aw.ds)
* [Hilt Android](https://developer.android.com/training/dependency-injection/hilt-android) with [Dagger](https://dagger.dev/) for dependency injection
* Unit Testing by [Mockito](https://github.com/mockito/mockito)
* Tests Coroutines and architecture components like ViewModel
* Uses [Kotlin Coroutines Test](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-test/) to unit test Kotlin Coroutines

## How to run the app and test
This app runs on all Android device not lower than SDK 21. Launch apk on Android device make sure there is internet availability to fetch data from the web service.
the first screen fetched is the list of sea animals details a click of an item on the list, navigates you to the detail page of that particular sea animal displaying its image gallery.

Running this app unit testing is quite simple cclick on the test folder on the file tree in Android Studio after right click for options you see the run icon click to run the test and wait for the result.

##Future Improvements
+Code Structuring
The API response need to be more consistent in data struture

+ Additional Features
The codebase follows a single Activity approach code Architecture with [SOLID principles](https://en.wikipedia.org/wiki/SOLID).

```
Copyright (c) 2022 Bamidele Oguntuga
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software").
```
