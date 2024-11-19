# **City Map App**

This repository contains the City Map App, an interactive tool to search, view, and manage city data with advanced features and a responsive design.

---

## **Features**

- 🔍 **Search Functionality**: Search cities by name with real-time filtering.
- ⭐ **Favorites Management**: Mark/unmark cities as favorites and persist data across app restarts.
- 🗺️ **Interactive Map**: View city locations on a map and navigate to their coordinates.
- 📱 **Responsive Design**: Adapts layouts for both portrait and landscape modes.
- 💾 **Persistent Storage**: Saves user preferences using SharedPreferences.

---

## **Architecture**

The app is built using **MVVM (Model-View-ViewModel)** architecture:

- **Model**: Manages city data and API responses.
- **ViewModel**: Handles business logic, state management, and data persistence.
- **View**: Implements the UI using Jetpack Compose.

---

## **Implementation Details**

### **1. Data Management**
- Favorites are persisted using SharedPreferences, serialized/deserialized with Gson.

### **2. User Interface**
- Developed with **Jetpack Compose**.
- Components are dynamically updated using `mutableStateOf` for efficient state management.

### **3. Floating Action Button (FAB)**
- Toggles between all cities and favorite cities.
- Displays "Favorites" or "List" label based on the current view.

### **4. Map Integration**
- Utilizes Google Maps SDK.
- Supports zoom and marker updates for selected cities.

---

## **Main Components**

### **ViewModel**
- Handles user interactions like searching and managing favorites.
- Filters cities based on user input and selected mode (favorites or all).

### **MainScreen**
- Entry point of the app.
- Adapts layouts for portrait and landscape orientations.
- Handles navigation between the city list and map views.

### **CityListScreen**
- Displays a list of cities with search functionality.
- Shows detailed information using cards:
  - City and country names.
  - Latitude and longitude.
  - Favorite toggle and navigation buttons.

### **Persistent Storage**
- Saves and retrieves favorite cities using SharedPreferences.
- Ensures data is preserved across app sessions.

---

pdf:
[Mobile Challenge - v0.5.pdf](https://github.com/user-attachments/files/17815427/Mobile.Challenge.-.v0.5.pdf)
