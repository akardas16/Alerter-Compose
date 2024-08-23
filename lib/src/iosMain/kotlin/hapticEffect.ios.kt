import platform.UIKit.UINotificationFeedbackGenerator
import platform.UIKit.UINotificationFeedbackType

actual fun hapticEffect() {
    UINotificationFeedbackGenerator().apply {
        prepare()
        notificationOccurred(UINotificationFeedbackType.UINotificationFeedbackTypeSuccess)
    }
}

actual object AppContext