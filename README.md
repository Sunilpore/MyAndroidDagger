# MyAndroidDagger
Android DaggerHilt Example

References:-

-Dagger Hilt Series By Coding With Mitch
 . https://www.youtube.com/watch?v=zTpM2olXCok&list=PLgCYzUzKIBE_MUlyvbCiOWsfq0nFgGXQ9

DaggerHilt official documentation:
-https://dagger.dev/hilt/
-https://developer.android.com/training/dependency-injection/hilt-android

Android Scope Reference:-
-https://developer.android.com/training/dependency-injection/hilt-android#component-scopes
-https://medium.com/androiddevelopers/scoping-in-android-and-hilt-c2e5222317c0

Topic Covered
- Dagger Hilt Constructor Injection Issue [di/HiltIssues]
- Dagger Hilt Constructor Injection Solution (Using @Binds, @Provides annotations) [di/HiltIssues]
- Dagger Hilt Scoping @NamedScope Annotation alternative (Custom Qualifiers) [di/HiltScopes]

- Frgment Injection issue resolved, using custom fragment factory and custom NavigationHostFragment
-Reference Video link
 . https://www.youtube.com/watch?time_continue=1463&v=lH6n4--3R5k&feature=emb_logo
-Reference Project code:-
 . https://github.com/mitchtabian/Dagger-Hilt-Playerground/tree/Fragment-Constructor-Injection

-Hilt Testing
 . https://github.com/mitchtabian/Dagger-Hilt-Playerground/tree/hilt-testing
 . https://developer.android.com/oauth2callback?state=%7B%22csrf_token%22%3A+%223ce9b75bf49780d74ec9ea92a28a5c818a71549e8c75db4b14dc7107e2d50b0d%22%2C+%22return_url%22%3A+%22https%3A%2F%2Fdeveloper.android.com%2Ftraining%2Fdependency-injection%2Fhilt-testing%22%7D&error_subtype=access_denied&error=interaction_required
 . https://dagger.dev/hilt/testing.html

 -Fragment Injection Issue solution
    .launchFragmentInContainer-> Below provide its solution link
    https://github.com/android/architecture-samples/blob/dev-hilt/app/src/androidTest/java/com/example/android/architecture/blueprints/todoapp/HiltExt.kt#L38
