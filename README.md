# Cine-Mate

Cine-Mate is an Android application that allows users to explore movies, view details, watch trailers, and discover trending movies. This repository contains the source code for the Cine-Mate app developed by Vishal Mishra.

## Table of Contents

1. [Overview](#overview)
2. [Architecture](#architecture)
3. [Screenshots](#screenshots)
4. [Features](#features)
5. [Dependencies](#dependencies)
6. [Installation](#installation)
7. [How To Run](#how-to-run)
8. [Issues](#issues)
9. [Contributing](#contributing)
10. [Developed And Maintained by](#developed-and-maintained-by)
11. [Social Media](#social-media)
12. [License](#license)

---

## Overview

Cine-Mate provides a user-friendly interface to browse movies, view detailed information such as synopsis, genres, and production companies, and watch trailers directly within the app. It utilizes [The Movie Database (TMDb) API](https://www.themoviedb.org/documentation/api) for fetching movie data including now playing, popular, top-rated movies, and more.

---

## Screenshots📷
<table>
  <tr>
    <td>splash Screen by lottie</td>
     <td>Home wiht loading screen </td>
     <td>Main home</td>
     <td>Main home scroll</td>
  </tr>
  <tr>
    <td><img src="https://github.com/Vishallab/Cine-Mate/blob/master/app/src/main/res/raw/splash.jpeg" width=240 height=410/></td>
    <td><img src="https://github.com/Vishallab/Cine-Mate/blob/master/app/src/main/res/raw/homewithload.jpeg" width=240 height=410/></td>
    <td><img src="https://github.com/Vishallab/Cine-Mate/blob/master/app/src/main/res/raw/mainhome.jpeg" width=240 height=410/></td>
    <td><img src="https://github.com/Vishallab/Cine-Mate/blob/master/app/src/main/res/raw/homemain.jpeg" width=240 height=410/></td>
    
  </tr>
  
  <tr>
    <td>movie detail</td>
    <td>movie detail scroll</td>
     <td>Now Playing</td>
     <td>Up Coming</td>
  </tr>
  <tr>
    <td><img src="https://github.com/Vishallab/Cine-Mate/blob/master/app/src/main/res/raw/moviedetail1.jpeg" width=240 height=410/></td>
    <td><img src="https://github.com/Vishallab/Cine-Mate/blob/master/app/src/main/res/raw/moviedetail2.jpeg" width=240 height=410/></td>
    <td><img src="https://github.com/Vishallab/Cine-Mate/blob/master/app/src/main/res/raw/nowplaying.jpeg" width=240 height=410/></td>
    <td><img src="https://github.com/Vishallab/Cine-Mate/blob/master/app/src/main/res/raw/upcoming.jpeg" width=240 height=410/></td>
  </tr>
 </table>

<br>



## Features

- **Now Playing**: Displaying movies currently playing in theaters.
- **Upcoming**: Showing upcoming movie releases.
- **Popular**: Listing popular movies based on user interactions.
- **Top Rated**: Displaying top-rated movies as per user ratings.
- **Movie Details**: Detailed view including synopsis, genres, production companies, and trailers.
- **Similar Movies**: Displaying movies similar to the selected movie.
- **Trending**: Showing trending movies based on media type and time window.

---


## Design Architechture - Model–view–presenter(MVP) 
Cine-Mate uses the Model–view–presenter (MVP) is a derivation of the model–view–controller (MVC) architectural pattern which mostly used for building user interfaces MVP is an architecture pattern that you can use to deal with some of the shortcomings of MVC, and is a good alternative architecture. It provides an easy way to think about the structure of your app. It provides <b>modularity, testability and, in general, a more clean and maintainable codebase</b>.

Picking apart the acronym, MVP is composed of the following components:


- **Model**: Your model package contains classes like <b>, Movies, NowPlaying, Popular, etc., </b> bwhich are likely used to represent data retrieved from the API and interact with the database or backend.
  
- **View**: Activities  <b>(AnimeSplashActivity, MainMenuActivity, MovieDetailActivity, etc.)  </b>and Fragments <b> (HomeFragment, MovieDetailFragment, NowPlayingFragment, etc.) </b> serve as the UI components that interact with the user and display data.

- **Presenter**: While not explicitly named "presenter," the logic in your viewmodels  <b>(MovieViewModel, TrendingViewModel, etc.)  </b> can act as presenters, handling the business logic, coordinating data retrieval from models, and updating views accordingly.

### MVP Characteristics:

- **Modularity**: Components like activities, fragments, models, and viewmodels are modular, making it easier to maintain and test individual parts of the application.

- **Testability**: MVP facilitates unit testing by separating business logic (presenter/viewmodel) from UI components (activities/fragments), allowing for easier isolation of logic for testing.

- **Maintainability**: By separating concerns, MVP improves code readability and maintainability, as each component has a clear responsibility. 

![image](https://github.com/Vishallab/Cine-Mate/assets/74778363/20b1bebf-73f5-439c-a7b8-0473d48d9eb5)<br>
###MVP architecture diagram for your Cine-Mate Android application
![image](https://github.com/Vishallab/Cine-Mate/assets/74778363/bce056f2-60b8-466b-b527-64995e258526)


---

## Dependencies
<br>

### • Retrofit2 (<a href="https://github.com/square/retrofit">More Info</a>)
A type-safe HTTP client for Android and Java.<br><br>
<b>Note: Retrofit requires at minimum Java 8+ or Android API 21+.</b><br>
<b>Dependency for Retrofit:</b>
```
com.squareup.retrofit2:retrofit:2.9.0
```
<b>Depencency for Retrofit Gson Converter:</b>
```
implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
```
<br>

### • Gson (<a href="https://github.com/google/gson">More Info</a>)
<br>
Gson is a Java library that can be used to convert Java Objects into their JSON representation and vice versa.

<b>Dependency for Gson:</b>
```
implementation 'com.google.code.gson:gson:2.8.8'
```

<br>

### • Glide (<a href="https://github.com/bumptech/glide">More Info</a>)
<br>
Glide is an image loading and caching library for Android.

<b>Dependency for glide:</b>
```
implementation 'com.github.bumptech.glide:glide:4.12.0'
```
<br>

### • Lottie (<a href="https://github.com/airbnb/lottie-android">More Info</a>)
<br>
Lottie is an animation library that renders After Effects animations natively on Android and iOS.
<b>Dependency for glide:</b>
```
implementation 'com.airbnb.android:lottie:3.7.0'
```
<br>


### • Neumorphism (<a href="https://github.com/fornewid/neumorphism">More Info</a>)
<br>
Neumorphism is a library for creating skeuomorphic UI components with a soft, tactile feel.

<b>Dependency for glide:</b>
```
implementation 'com.github.fornewid:neumorphism:0.3.0'
```
<br>


### • Other Dependencies
<b>• AndroidX libraries for UI components and navigation <br> • JUnit for testing</b>



### •  Installation
To build and run this project, you will need:

- Android Studio IDE <br>

- Android SDK with compile SDK version 34 and minimum SDK version 26

### Steps:
1. Clone this repository:
```
git clone https://github.com/Vishallab/Cine-Mate.git
```

2. Open the project in Android Studio.

3. Build and run the project on an Android emulator or a physical device.

### How To Run
Follow these steps to run the Cine-Mate app on your Android device or emulator:

1. Open your terminal (preferably Git Bash or Terminal in Android Studio).
2. Clone the repository:

```
git clone https://github.com/Vishallab/Cine-Mate.git
```
3. Navigate to the project directory.
4. Sync the project with Gradle files.
5. Open an emulator or connect a real device.
6. Run the app using Android Studio's run/debug configurations.

 ## Video Demo

https://github.com/Vishallab/Cine-Mate/assets/74778363/7a8a8d1f-a5f3-4780-8dd2-3dd31464eb6d

![image](https://github.com/Vishallab/Cine-Mate/assets/74778363/5fdb1cf2-452e-432c-9691-2783e6fa004b)

![image](https://github.com/Vishallab/Cine-Mate/assets/74778363/0ea7b87c-30de-40e9-995e-20121d7b03e1)



## Issues

### Issue 1: Blank Screen After SplashScreen

- Description: After the SplashScreen finishes and transitions to the MainMenuActivity, a blank screen appears for 1-2 seconds before the actual content of the MainMenuActivity is displayed.

### Issue 2: App Closes Abruptly After MainMenuActivity

- Description: After navigating to the MainMenuActivity, pressing the back button results in a blank screen appearing momentarily before the app closes abruptly without returning to the previous screen or behaving as expected.

## Contributing
Contributions are welcome! Please fork this repository and submit pull requests for any enhancements or bug fixes.

- Fork the repository
- Create your feature branch (git checkout -b feature/AmazingFeature)
- Commit your changes (git commit -m 'Add some AmazingFeature')
- Push to the branch (git push origin feature/AmazingFeature)
- Open a pull request
- Developed And Maintained by 
 ### 😎 Vishal Mishra

## Social Media
Connect with me:
- [LinkedIn](https://www.linkedin.com/in/vishalmishra01)
- [Instagram](https://www.instagram.com/ig_viishal)
- [GitHub](https://www.github.com/Vishallab)


