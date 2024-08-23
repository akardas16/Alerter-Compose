import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

inline val Int.toDp: Dp
    @Composable get() = with(LocalDensity.current) { this@toDp.toDp() }
@Stable
enum class AlertStyle {
    Standard,
    Split,
    Snackbar,
}

@Composable
fun Alerter(isVisible: Boolean,
            onChanged:(visibility:Boolean) -> Unit,
            backgroundColor: Color,
            alertStyle: AlertStyle = AlertStyle.Standard,
            enableDismissWhenAlertClick:Boolean = true,
            enableDismissAutomatically:Boolean = true,
            duration:Long = 5000,
            enableVibration:Boolean = true,
            content: @Composable () -> Unit) {


    when (alertStyle) {
        AlertStyle.Standard -> {
            LaunchedEffect(isVisible){
                if (enableVibration.and(isVisible)) hapticEffect()
                if (enableDismissAutomatically.and(isVisible)){
                    delay(duration)
                    onChanged(false)
                }
            }

            var height by remember { mutableStateOf(0.dp) }


            val currentDensity = LocalDensity.current
            var isShown by remember { mutableStateOf(false) }
            val scope = rememberCoroutineScope()

            val offsetY by animateDpAsState(targetValue = if (isVisible.not()) (-height.value.plus(50)).dp else (-40).dp,
                animationSpec = spring(visibilityThreshold = Dp.VisibilityThreshold,
                    stiffness = Spring.StiffnessLow, dampingRatio = Spring.DampingRatioMediumBouncy)
            )
            Card(
                modifier = Modifier.offset(y = offsetY).padding(horizontal = 0.dp).clickable {
                    if (enableDismissWhenAlertClick) onChanged(false)
                }
                    .fillMaxWidth().wrapContentHeight().alpha(if (isShown) 1f else 0f)
                    .shadow(elevation = 8.dp, shape = RoundedCornerShape(0.dp)).onSizeChanged {

                        scope.launch {
                            height = with(currentDensity) { it.height.toDp() }
                            delay(1000)
                            isShown = true
                        }

                    },
                shape = RoundedCornerShape(0.dp), backgroundColor = backgroundColor
            ) {

                Column(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.height(40.dp + WindowInsets.statusBars.getTop(currentDensity).toDp).fillMaxWidth().background(backgroundColor))
                    content()
                }


            }
        }
        AlertStyle.Split -> {

            LaunchedEffect(isVisible){
                if (enableVibration.and(isVisible)) hapticEffect()
                if (enableDismissAutomatically.and(isVisible)){
                    delay(duration)
                    onChanged(false)
                }
            }

            var height by remember { mutableStateOf(0.dp) }


            val currentDensity = LocalDensity.current
            var isShown by remember { mutableStateOf(false) }
            val scope = rememberCoroutineScope()

            val offsetY by animateDpAsState(targetValue = if (isVisible.not()) (-height.value.plus(80)).dp else (0).dp,
                animationSpec = spring(visibilityThreshold = Dp.VisibilityThreshold,
                    stiffness = Spring.StiffnessLow, dampingRatio = Spring.DampingRatioMediumBouncy)
            )

            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height( WindowInsets.statusBars.getTop(currentDensity).toDp).fillMaxWidth())

                Card(
                    modifier = Modifier.offset(y = offsetY).padding(16.dp)
                        .wrapContentHeight().alpha(if (isShown) 1f else 0f)
                        .shadow(elevation = 8.dp, shape = RoundedCornerShape(16.dp)).onSizeChanged {

                            scope.launch {
                                height = with(currentDensity) { it.height.toDp() }
                                delay(1000)
                                isShown = true
                            }

                        }.clickable {
                            if (enableDismissWhenAlertClick) onChanged(false) },
                    shape = RoundedCornerShape(16.dp), backgroundColor = backgroundColor
                ) {

                    Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                        content()
                    }


                }
            }
        }
        AlertStyle.Snackbar -> {
            LaunchedEffect(isVisible){
                if (enableVibration.and(isVisible)) hapticEffect()
                if (enableDismissAutomatically.and(isVisible)){
                    delay(duration)
                    onChanged(false)
                }
            }

            var height by remember { mutableStateOf(0.dp) }


            val currentDensity = LocalDensity.current
            var isShown by remember { mutableStateOf(false) }
            val scope = rememberCoroutineScope()

            val offsetY by animateDpAsState(targetValue = if (isVisible.not()) (height.value.plus(90)).dp else (0).dp,
                animationSpec = spring(visibilityThreshold = Dp.VisibilityThreshold,
                    stiffness = Spring.StiffnessLow, dampingRatio = Spring.DampingRatioMediumBouncy)
            )

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter){
                Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(modifier = Modifier.height( WindowInsets.navigationBars.getBottom(currentDensity).toDp).fillMaxWidth())

                    Card(
                        modifier = Modifier.offset(y = offsetY).padding(horizontal = 16.dp, vertical = 24.dp)
                            .wrapContentHeight().alpha(if (isShown) 1f else 0f)
                            .shadow(elevation = 8.dp, shape = RoundedCornerShape(16.dp)).onSizeChanged {

                                scope.launch {
                                    height = with(currentDensity) { it.height.toDp() }
                                    delay(1000)
                                    isShown = true
                                }

                            }.clickable {
                                if (enableDismissWhenAlertClick) onChanged(false) },
                        shape = RoundedCornerShape(16.dp), backgroundColor = backgroundColor
                    ) {

                        Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                            content()
                        }


                    }
                }


            }
        }
    }

}

