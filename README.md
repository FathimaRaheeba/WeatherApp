# WeatherApp

A modern Android application that displays real-time weather data using the OpenWeatherMap API. This project demonstrates Jetpack Compose for UI and Retrofit for network operations.

## 🚀 Tech Stack

*   **Language:** [Kotlin](https://kotlinlang.org/)
*   **UI Framework:** [Jetpack Compose](https://developer.android.com/jetpack/compose)
*   **Networking:** [Retrofit 2.9.0](https://square.github.io/retrofit/) & [Gson Converter](https://github.com/google/gson)
*   **Asynchronous Programming:** [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
*   **Dependency Management:** Gradle Version Catalog (`libs.versions.toml`)
*   **Architecture:** MVVM (Model-View-ViewModel)

---

## ☁️ OpenWeather API Usage

This app fetches weather data from the **Current Weather Data** endpoint.

### Prerequisites
To run this project, you need an API key:
1.  Sign up at [OpenWeatherMap](https://openweathermap.org/api).
2.  Generate an **API Key** (AppID) from your dashboard.

### API Integration
*   **Base URL:** `https://api.openweathermap.org/data/2.5/`
*   **Endpoint:** `/weather`
*   **Query Params:** `q` (City), `appid` (Key), `units` (metric/imperial).

### Data Model
The app maps the JSON response to Kotlin data classes:
- `WeatherResponse`: Root object containing temperature and description.
- `Main`: Holds the `temp` (Double).
- `Weather`: Holds the `description` (String).

---
#Build by [Fathima Raheeba](https://raheeba.awwads.in/)
    
