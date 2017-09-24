##Overview
This project is a sample project that will try to do the following:
1. Fetches a list of users from a slack-test-api and displays that list of users
2. Tapping / Long Click on the user row will open the individual pages for each row 
with the appropriate information about the users.

####Libraries used
1. `Timber` for simple and easy logging
2.` Dagger` for dependency injection. Helps us with scoping.
    * Building on top of that, we used dagger-android to avoid repetitive boilerplate code for 
    creating activity scoped components.
3. `RxJava2` to help with binding data streams and manipulating streams of data as well
as handling Asynchronous operations.
4. Takes advantage of `Android-architecture-components`
    * `Room` for ORM for easier handling of SQLite data persistence
    * `ViewModel` components provided by Life-cycle-components so that
    we don't have to handle destroying ViewModels when the activity is finished.
    Android will know how to handle that for us.
5. `OkHttp` and `Retrofit` to simplify networking
6. Uses `Gson` to operate on data returned from Retrofit/OkHttp and represent
the JSON responses as POJOS -> Plain Old Java Objects

7. `Glide` as our image library