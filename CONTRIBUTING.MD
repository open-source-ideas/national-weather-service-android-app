## Contributing to the NWS Android App Project

### We're excited that you want to contribute to this project! 
### Here is how you can contribute:

* Check for open issues and feature requests. 
**If there is an issue that you are interested in, leave a comment, and the issue can be assigned to you!**
* Join the slack channel hosted [here](https://opensourceideas.herokuapp.com)
* Fork a copy of the repository, clone it to your local environment, and open the project in Android Studio!
* Once you finish and commit your changes to your forked repository, open a pull request, 
attribute the request to the issue that is assigned to you, and submit your changes! We'll review them and accept/decline the changes!


### Application Architecture Guidelines

This project uses the standard MVP architecture (Model View Presenter) and was 
originated from the android-boilerplate project created by the UK company Ribot 
([Original Repository](https://github.com/ribot/android-boilerplate))

* __View (UI layer)__: this is where Activities, Fragments and other standard Android components live. It's responsible for displaying the data received from the presenters to the user. It also handles user interactions and inputs (click listeners, etc) and triggers the right action in the Presenter if needed.

* __Presenter__: presenters subscribe to RxJava Observables provided by the `DataManager`. They are in charge of handling the subscription lifecycle, analysing/modifying the data returned by the `DataManager` and calling the appropriate methods in the View in order to display the data.

* __Model (Data Layer)__: this is responsible for retrieving, saving, caching and massaging data. It can communicate with local databases and other data stores as well as with restful APIs or third party SDKs. It is divided in two parts: a group of helpers and a `DataManager`. The number of helpers vary between project and each of them has a very specific function, e.g. talking to an API or saving data in `SharedPreferences`. The `DataManager` combines and transforms the outputs from different helpers using Rx operators so it can: 1) provide meaningful data to the Presenter,  2) group actions that will always happen together. This layer also contains the actual model classes that define how the data structure is.

**Here is a diagram of how we implement the MVP architecture (courtesy of ribot)**

![](https://github.com/ribot/android-guidelines/raw/master/architecture_guidelines/architecture_diagram.png)

### How to implement a new screen following MVP

1. Create a new package under `ui` called `signin` (or whatever screen you are working on)
2. Create an new Activity called `ActivitySignIn`. You could also use a Fragment.
3. Define the view interface that your Activity is going to implement. Create a new interface called `SignInMvpView` that extends `MvpView`. Add the methods that you think will be necessary, e.g. `showSignInSuccessful()`
4. Create a `SignInPresenter` class that extends `BasePresenter<SignInMvpView>`
5. Implement the methods in `SignInPresenter` that your Activity requires to perform the necessary actions, e.g. `signIn(String email)`. Once the sign in action finishes you should call `getMvpView().showSignInSuccessful()`.
6. Create a `SignInPresenterTest`and write unit tests for `signIn(email)`. Remember to mock the  `SignInMvpView` and also the `DataManager`.
7. Make your  `ActivitySignIn` implement `SignInMvpView` and implement the required methods like `showSignInSuccessful()`
8. In your activity, inject a new instance of `SignInPresenter` and call `presenter.attachView(this)` from `onCreate` and `presenter.detachView()` from `onDestroy()`. Also, set up a click listener in your button that calls `presenter.signIn(email)`.
