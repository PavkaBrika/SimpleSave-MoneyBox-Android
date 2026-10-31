# MoneyBox 💰

<p align="center">
  <img src="app/src/main/ic_launcher-playstore.png" alt="SimpleSave app icon" width="144" />
</p>

<p align="center"><strong>SimpleSave — small steps toward your savings goals.</strong></p>

<p align="center"><a href="https://play.google.com/store/apps/details?id=com.breackneck.mysecondprojectmoneybox"><strong>▶ Get it on Google Play</strong></a></p>

## 💛 A personal milestone

MoneyBox is my very first personal project that I built entirely on my own. It marked the beginning of my serious work with Android development. I created the app independently and published it on Google Play. It will always be a special milestone in my Android journey. 🚀

## ⚠️ Maintenance status

MoneyBox is no longer actively maintained or supported. The app remains available on Google Play, but new updates and support are not planned.

## 📲 Get the app

[Download SimpleSave from Google Play](https://play.google.com/store/apps/details?id=com.breackneck.mysecondprojectmoneybox).

## ✨ What the app does

SimpleSave helps turn a savings target into visible progress. Set a goal, add or withdraw money as you go, and watch your virtual money jar fill up.

- 🎯 Create and manage multiple savings goals.
- 🪙 Add to or withdraw from the saved amount.
- 📊 See how much is left and track progress visually.
- 🗂️ Switch between goals from the goals list.
- 🎭 Choose a character and configure sound and vibration.
- 💾 Keep goals and settings on the device.

## 🧰 Built with

- Kotlin and AndroidX
- Material Components and View Binding
- MVVM with Kotlin Coroutines
- Koin for dependency injection
- Room for local goal storage, with SharedPreferences for app settings
- Yandex Mobile Ads

## 🏗️ Project structure

The project is split into three Gradle modules:

| Module | Responsibility |
| --- | --- |
| `app` | Android UI, activities, ViewModel, and dependency setup |
| `domain` | Goal models, repository contracts, and use cases |
| `data` | Room database, local settings, repositories, and legacy data migration |

## 🚀 Build

### Requirements

- Android Studio
- JDK 11 or newer
- Android SDK 34

Clone the repository and build the debug app:

```bash
git clone https://github.com/PavkaBrika/MoneyBox.git
cd MoneyBox
bash gradlew :app:assembleDebug
```

You can also open the cloned directory in Android Studio, let Gradle sync, then run the `app` configuration on a device or emulator.

📱 **Minimum Android version:** Android 8.0 (API 26).

## 🌍 Languages

The app includes English, French, Russian, Belarusian, and Ukrainian resource translations.
