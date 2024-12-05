# MyQuoteApp

MyQuoteApp is a simple Android application that allows users to view, navigate, delete, and manage 100 different quotes. The app saves user data locally, so even after restarting the app, your quotes remain intact.

---

## Features

- **View Quotes**: Displays a list of inspiring quotes one at a time.
- **Navigate Quotes**: Navigate between quotes using "Previous" and "Next" buttons.
- **Delete Quotes**: Remove unwanted quotes from the list.
- **Save Progress**: Automatically saves your quotes and position locally.
- **Quit Option**: Easily close the app with the "Quit" button.

---

## How It Works

### Navigation
- **Next Button**: Moves to the next quote in the list.
- **Previous Button**: Moves to the previous quote.

### Deleting Quotes
- Use the "Delete" button to remove the current quote.
- Once deleted, the app automatically updates the list and adjusts navigation accordingly.

### Saving Data
- Quotes are saved using Android's `SharedPreferences`.
- Any changes, like deletions, are saved automatically, ensuring your data persists even after the app is closed.

### Quitting the App
- The "Quit" button allows you to close the app while saving your progress.

---

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/MyQuoteApp.git
2. Open the project in Android Studio.
3. Build and run the app on an emulator or physical device.

---

# Enjoy using MyQuoteApp! 😊

