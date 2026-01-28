# ChronoSphere 🌍⏰

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-blue.svg)

**A Java-based console application providing global time insights and smart utility features for time management**


## Features
- 🌍 **World Clock System** - Track time across multiple cities globally
- 🕌 **Prayer Times (Pakistan)** - Accurate prayer timings for major Pakistani cities
- 📅 **Calendar & Task Planner** - Manage your schedule , tasks and events
- ⏰ **Alarm System** - Set and manage alarms with status tracking
- ⏱️ **Utilities** - Stopwatch and Timer functions
- 🎨 **Colorful Console Interface** - ANSI-colored terminal UI

---

## Project Structure
ChronoSphere/

├── ChronoSphere.java           # Main application file

├── README.md                   # Project documentation

├── screenshots/                # Application screenshots

│   ├──all screenshots.JPG   

└── data/                       # Data storage directory

    ├── WorldClock.txt          # Saved world clock cities

    ├── PrayerTimesPAK.csv      # Prayer time database

    ├── TasksManager.txt        # Task storage

    ├── Events.txt              # Event storage

    └── addAlarm.txt            # Alarm storage
    

---

## Modules
1. **World Clock** – Allows users to add or delete cities and view their current local times. Supports multiple time zones and provides a global perspective on time management.
2. **Prayer Times** – Provides accurate prayer timings for major cities in Pakistan. Includes live countdowns to the next prayer, helping users stay organized with religious schedules.
3. **Calendar, Tasks & Events** – Enables task and event management with file-based persistence. Users can create, update, and track tasks or events, ensuring nothing is missed in their schedule.
4. **Alarms** – Lets users set multiple alarms with status tracking and notifications. Alarms can be customized and are persistent between program runs.
5. **Utilities** – Includes stopwatch and timer functions. Useful for tracking time intervals or setting temporary countdowns for productivity or daily routines.

---

## Requirements
- Java Runtime Environment (JRE) 8+
- Terminal supporting ANSI colors
- Windows, Linux, or macOS

---

## Data Files
All user data is stored in the data/ directory:

- Persistent storage of cities, alarms, tasks, and events
- CSV format for prayer times
- Text files for simple data storage
---

## Notes / Tips
- 💻 *Recommended Terminal:* Run the application in *VS Code Terminal* (or any terminal that supports ANSI colors). The default Windows CMD may not display colors properly.  
- 🗂️ *File Paths:* All data files are referenced using absolute paths. To run the application successfully, update the file paths in the code to match your local system. For example in this project the file paths are used like:  
  ```java
  "C:\\Users\\User\\OneDrive\\Desktop\\ChronoSphere\\data\\WorldClock.txt"
Make sure to adjust the path in all 7–8 locations where files are referenced matching your local system. Once these paths are correctly set, the program will work as intended.

✅ Following these steps ensures that all modules (World Clock, Prayer Times, Calendar, Alarms, Utilities) function correctly.

---

## 🎥 Demo Overview
The following screenshots demonstrate ChronoSphere’s complete workflow, from the main dashboard to advanced utilities such as world clock management, prayer times, task and event tracking, smart alarms, and time utilities.  
Each section follows the same order as the application’s menu flow for clarity.
Below are screenshots demonstrating the complete workflow and features of **ChronoSphere**.

### 🏠 Dashboard & Main Menu
- [Dashboard](screenshots/dashboard.JPG)
- [Main Menu](screenshots/main_menu.JPG)

---

### 🌍 World Clock
- [World Clock Menu](screenshots/world_clock/world_clock_menu.JPG)
- [Cities List](screenshots/world_clock/cities.JPG)
- [Custom City](screenshots/world_clock/custom_city.JPG)
- [Add City](screenshots/world_clock/adding_cities.JPG)
- [Delete City](screenshots/world_clock/deleting_cities.JPG)
- [Live Time Updates](screenshots/world_clock/live_times.JPG)

---

### 🕌 Prayer Times
- [Prayer Time Menu](screenshots/prayer_time/main_prayer_time_menu.JPG)
- [Prayer Times Example](screenshots/prayer_time/prayer_times_ex1.JPG)

---

### 📋 Tasks & Events Manager
- [Tasks & Events Main Menu](screenshots/tasks_events/main_menu_of_events_and_tasks.JPG)

**Tasks**
- [Task Manager Menu](screenshots/tasks_events/tasks_manager_menu.JPG)
- [Add Task](screenshots/tasks_events/adding_tasks.JPG)
- [View Tasks](screenshots/tasks_events/viewing_tasks.JPG)
- [Completed Tasks](screenshots/tasks_events/completed_tasks.JPG)

**Events**
- [Add Event](screenshots/tasks_events/adding_events.JPG)
- [View Events](screenshots/tasks_events/viewing_events.JPG)

**Records**
- [Tasks & Events Record](screenshots/tasks_events/tasks_and_events_manager_record.JPG)

---

### ⏰ Smart Alarms
- [Alarm Menu](screenshots/smart_alarm/alarms_menu.JPG)
- [Add Alarm](screenshots/smart_alarm/adding_alarms.JPG)
- [Alarm Status](screenshots/smart_alarm/alarms_status.JPG)
- [Alarm Ringing](screenshots/smart_alarm/alarms_ringing.JPG)
- [Missed Alarms](screenshots/smart_alarm/alarms_missed.JPG)
- [Delete Alarm](screenshots/smart_alarm/deleting_alarms.JPG)

---

### 🛠 Utilities
- [Utilities Menu](screenshots/utilities/utility_menu.JPG)
- [Stopwatch](screenshots/utilities/stopwatch.JPG)
- [Timer](screenshots/utilities/timer.JPG)


---

Author
[**Mohammad Ali Mughal**]

License
This project is for educational purposes.
MIT License