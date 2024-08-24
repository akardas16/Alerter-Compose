
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

### Standard

### Split

### Snackbar


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
