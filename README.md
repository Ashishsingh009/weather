# WeatherApp
##Android Jetpack ,MVVM and TDD implementations 
###for API used weather and forecastApi from  https://openweathermap.org/api


## Getting Started
* Focus on Android JETPACK https://developer.android.com/jetpack/
* Android Test Driven development https://developer.android.com/training/testing/fundamentals.
* Used JaCoCo for line coverage tool, that is used to measure how many lines of our code are tested.

*MVVM Architecture: In MVVM, ViewModel exposes streams of events to which the Views can bind to. Like this, the ViewModel does not need to hold a reference to the View anymore, like the Presenter is. This also means that all the interfaces that the MVP pattern requires, are now dropped.

*Android Databinding: Part of Android Jetpack, is a support library that allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically.

*Dependancy Injection(Daggar2):- Dagger is a fully static, compile-time dependency injection framework for both Java and Android. It is an adaptation of an earlier version created by Square and now maintained by Google. 

*Android JUNIT4: Build local unit Test cases.

*Retrofit- For n/w calling using okhttp client.



### Prerequisites

Android Studio3.2 or above

 

### Installing

A step by step series of examples that tell you how to get a development env running



```
Checkout The code with Gitlink in android Studio.
file->new->open from versioncontrol->git
```

```
Build the project.
```

## Running the tests

Build the Jacoco report from gradle, Please connect to device and accept the location permission. 
Please see the the report for this project.
* Espresso Test 
![alt text](https://user-images.githubusercontent.com/2376791/72666325-6ffb5080-3a2a-11ea-85bb-f9ad289c2596.png)
*Unit Test Cases.
![alt text](https://user-images.githubusercontent.com/2376791/72666329-77baf500-3a2a-11ea-9303-ebfea7932e71.png)

*for Forecast Graph.
![alt text](https://user-images.githubusercontent.com/2376791/72666729-7d1a3e80-3a2e-11ea-88d3-4e3147dfc7dd.png)
![alt text](https://user-images.githubusercontent.com/2376791/72666734-81465c00-3a2e-11ea-89e7-ffe96cc68ef9.png)

## For Bar Chart
* Reference https://github.com/PhilJay/MPAndroidChart

## Authors

* **Ashish Singh** - *Complete work* - [AshishSingh](https://github.com/Ashishsingh009)
