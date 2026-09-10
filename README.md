# TVMaze TV App

A simple TV show browser application built with Kotlin and Jetpack Compose using the TVMaze API.

## Features

* Display TV shows from TVMaze API
* Show TV show poster, title, and rating
* Handle null ratings
* TV show detail screen
* Display original poster
* Display title, summary, and premiere date
* Handle HTML content in TV show summaries
* Share TV show information
* Loading state
* Error state with retry
* Success state
* Unit tests for ViewModel/Data Layer

## Tech Stack

* Kotlin
* Jetpack Compose
* Retrofit
* Kotlin Coroutines
* ViewModel
* MVVM Architecture
* JUnit
* TVMaze API

## How to Run

1. Clone this repository.
2. Open the project using Android Studio.
3. Wait for Gradle synchronization to finish.
4. Connect an Android device or start an Android Emulator.
5. Run the application using Android Studio.

No API key is required because the TVMaze API is publicly accessible.

## Architecture

The application uses the MVVM architecture.

1. UI Layer

Jetpack Compose is used to build the application interface.

The UI observes the state provided by the ViewModel and displays the appropriate screen depending on whether the application is loading, successful, or has encountered an error.

2. ViewModel

The ViewModel manages UI state and communicates with the Repository.

It prevents the UI from directly handling API requests and keeps the application logic separated from the UI.

3. Repository

The Repository acts as an abstraction between the ViewModel and the API service.

It is responsible for retrieving TV show data from the TVMaze API.

4. API Service

Retrofit is used to communicate with the TVMaze API.

The API service defines the endpoints required to retrieve the TV show list and individual TV show details.


## What I Would Improve With More Time

If I had more time, I would improve the application in several areas:

1. **Pagination**

   The current implementation loads the first page of TV shows. I would add pagination to efficiently load more shows.

2. **Better UI/UX**

   I would improve animations, loading placeholders, error messages, and overall visual consistency.

3. **Image Loading Improvements**

   I would improve image loading and caching to make scrolling through the TV show list smoother.

## Walkthrough Video

A short walkthrough video demonstrating the application, error state, code explanation, and an AI-generated issue that was fixed is available below.

[Watch the Walkthrough Video](VIDEO_LINK)
