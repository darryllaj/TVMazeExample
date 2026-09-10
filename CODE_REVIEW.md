1. Network Request Is Performed Directly in the ViewModel
- Problem : The ViewModel directly creates a URL and performs the network request.
- Fix : Move the network logic into a Repository and let the ViewModel communicate with the Repository and the API Service can use retrofit to handle HTTPS Request
2. Network Request Runs on the Main Thread
- Problem : loadMovies() is a normal function and url.readText() is a blocking operation. If this function is called from the main thread, it can block the UI and potentially cause an ANR (Application Not Responding).
- Fix : Use Kotlin Coroutines and perform the network request on an appropriate dispatcher.
3. No Error Handling
- Problem : The network request and parsing operation are not wrapped in error handling.
Several things can fail:
  ## 1. No internet connection
  ## 2. Server error
  ## 3. Invalid response
  ## 4. JSON parsing error
  ## 5. Timeout
  ## 6. An exception could therefore crash the application
- Fix : Catch expected exceptions and expose an error state to the UI.
4. Hardcoded API URL
- Problem : The API URL is directly written inside the ViewModel:
URL("https://api.example.com/movies")
This makes the code harder to maintain and test.
- Fix : Move the base URL and endpoint definitions into the API service.
