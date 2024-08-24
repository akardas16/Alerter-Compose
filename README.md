
## Alerter Compose
Alert library for Compose Multiplatform(Android, IOS, Desktop)

## Installation

### Compose Multiplatform
Add this line to shared build.gradle
```kotlin
sourceSets {
  commonMain.dependencies {
    implementation("io.github.akardas16:alertercompose:1.0.5")
  }
}
```

# Showcases

Standard | Split | Snackbar |
| ---- | ---- | ---- |
| <video align="center" src="https://github.com/user-attachments/assets/79c72ad6-64c1-4651-9ab3-d4a59e597208" width="400">| <video align="center" src="https://github.com/user-attachments/assets/eb14bb78-b277-4e7d-a56b-68a4a498eb76" width="400"> | <video align="center" src="https://github.com/user-attachments/assets/20e63560-e534-4f5f-ae1b-b01ebb2cac9e" width="400"> |


# Usage

#### Basic Usage

```kotlin
 var showAlert by remember { mutableStateOf(false) }

Alerter(isVisible = showAlert, alertStyle = AlertStyle.Standard,
            onChanged = { showAlert = it }, 
            backgroundColor = Color.White
        ) {
    //Your Custom View
}
```

<br />




*  See available parameters

  ```Kotlin
fun Alerter(isVisible: Boolean,  // Show/Hide alerter
            onChanged:(visibility:Boolean) -> Unit,
            backgroundColor: Color,
            alertStyle: AlertStyle = AlertStyle.Standard, //Change alerter style (Standard, Split, Snackbar)
            enableDismissWhenAlertClick:Boolean = true,   // Dismiss alert when click on alert
            enableDismissAutomatically:Boolean = true,   // Dismiss alert automotically with spesified duration
            duration:Long = 5000, // Alert duration if enableDismissAutomatically is true
            enableVibration:Boolean = true,  // enable/disable vibration when alert visible
            content: @Composable () -> Unit) {
```
