package com.akardas.alertercompose


import Alerter

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose_multiplatform_library_template.sample.shared.generated.resources.Res
import compose_multiplatform_library_template.sample.shared.generated.resources.gift_icon
import compose_multiplatform_library_template.sample.shared.generated.resources.info_icon
import compose_multiplatform_library_template.sample.shared.generated.resources.network
import compose_multiplatform_library_template.sample.shared.generated.resources.warning_icon

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

val Info = Color(0xFF3150EC)
val Warning = Color(0xFFFE9E01)
val Error = Color(0xFFF54F4E)
val Success = Color(0xFF24BF5F)


@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        var isVisibleInfo by remember { mutableStateOf(false) }
        var isVisibleWarning by remember { mutableStateOf(false) }
        var isVisibleOffline by remember { mutableStateOf(false) }
        var isVisibleGetGift by remember { mutableStateOf(false) }

        var alertStyle by remember { mutableStateOf(AlertStyle.Snackbar) }
        val scope = rememberCoroutineScope()

        Column(modifier = Modifier.fillMaxSize().systemBarsPadding(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {

            Text(
                "Alerter Compose", color = Color.Black,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 28.dp).fillMaxWidth()
                    .align(Alignment.CenterHorizontally), textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.body2.copy(
                    fontWeight = FontWeight.Bold, fontSize = 24.sp
                )
            )
            Column(
                Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    16.dp,
                    alignment = Alignment.CenterVertically
                )
            ) {


                LazyRow(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                    items(AlertStyle.entries.toTypedArray()){ item ->
                        Box(modifier = Modifier.padding(horizontal = 8.dp).clip(
                            CircleShape).bounceClick(scaleDown = 0.96f) {
                                isVisibleInfo = false
                                isVisibleWarning = false
                                isVisibleOffline = false
                                isVisibleGetGift = false
                                alertStyle = item
                            }.background(if (alertStyle == item) Color(0xff00a6ff) else Color.Gray.copy(0.8f), CircleShape), contentAlignment = Alignment.Center){
                            Text(text = item.name, color = Color.White,
                                modifier = Modifier.width(100.dp).padding(vertical = 8.dp), textAlign = TextAlign.Center)
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(0.4f))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    CustomButton("Info", color = Color(0xdb6314c5)) {

                        scope.launch {
                            isVisibleInfo = !isVisibleInfo
                        }

                    }
                    CustomButton("Warning", color = Color(0xdb6314c5)) {
                        isVisibleWarning = !isVisibleWarning
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    CustomButton("Offline", color = Color(0xdb6314c5)) {
                        isVisibleOffline = !isVisibleOffline
                    }
                    CustomButton("Get Gift", color = Color(0xdb6314c5)) {
                        isVisibleGetGift = !isVisibleGetGift
                    }
                }

                Spacer(modifier = Modifier.weight(0.4f))



            }
        }


        Alerter(
            isVisible = isVisibleInfo, alertStyle = alertStyle,
            onChanged = { isVisibleInfo = it }, enableDismissWhenAlertClick = false,
            backgroundColor = Color.White
        ) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

                Row(
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clip(RoundedCornerShape(16.dp))
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier =
                        Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(Info.copy(alpha = 0.1f))
                            .padding(8.dp),
                    ) {
                        Image(
                            painterResource(Res.drawable.info_icon),
                            null
                        )
                    }

                    Column {
                        Text(
                            text = "Did you know?",
                            overflow = TextOverflow.Ellipsis,
                            style =
                            MaterialTheme.typography.body1.copy(
                                fontWeight = FontWeight.Bold,
                            ),
                            color = Color.Black.copy(0.8f),
                        )

                        Text(
                            text = "You can switch dark theme from settings page",
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.body2,
                            color = Color.Gray,
                        )
                    }
                }

            }

        }

        Alerter(
            isVisible = isVisibleWarning, alertStyle = alertStyle,
            onChanged = { isVisibleWarning = it }, enableDismissWhenAlertClick = false,
            backgroundColor = Color.White
        ) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

                Row(
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clip(RoundedCornerShape(16.dp))
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier =
                        Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(Color(0xffff801a).copy(alpha = 0.1f))
                            .padding(8.dp),
                    ) {
                        Image(
                            painterResource(Res.drawable.warning_icon),
                            null
                        )
                    }

                    Column {
                        Text(
                            text = "3 Days Left",
                            overflow = TextOverflow.Ellipsis,
                            style =
                            MaterialTheme.typography.body1.copy(
                                fontWeight = FontWeight.Bold,
                            ),
                            color = Color.Black.copy(0.8f),
                        )

                        Text(
                            text = "Your free trial will expire in 3 days. Subscribe now",
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.body2,
                            color = Color.Gray,
                        )
                    }
                }

            }

        }

        Alerter(
            isVisible = isVisibleOffline, alertStyle = alertStyle,
            onChanged = { isVisibleOffline = it }, enableDismissWhenAlertClick = false,
            backgroundColor = Color(0xffda3838)
        ) {
            Box(modifier = Modifier, contentAlignment = Alignment.Center) {

                Row(
                    modifier =
                    Modifier

                        .wrapContentHeight()
                        .clip(RoundedCornerShape(16.dp))
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Image(
                        painterResource(Res.drawable.network),modifier = Modifier.size(24.dp),
                        contentDescription = ""
                    )

                    Text(
                        text = "No connection found!",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.body2,
                        fontWeight = FontWeight.Medium,
                        fontSize = 15f.sp,
                        color = Color.White,
                    )
                }

            }

        }

        Alerter(
            isVisible = isVisibleGetGift, alertStyle = alertStyle,
            onChanged = { isVisibleGetGift = it }, enableDismissWhenAlertClick = false,
            enableDismissAutomatically = false,
            backgroundColor = Color(0xFF9499FF)
        ) {
            Row(modifier = Modifier.fillMaxWidth()
                .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {


                Image(
                    painterResource(Res.drawable.gift_icon),modifier = Modifier.padding(start = 16.dp).size(45.dp),
                    contentDescription = ""
                )

                Column(modifier = Modifier.padding(horizontal = 12.dp)) {
                    Text(text = "Gift", color = Color.White, fontWeight = FontWeight.SemiBold)
                    Text(text = "Claim your gift!", color = Color.White, fontSize = 14.sp)
                }
                Spacer(modifier = Modifier.weight(1f))

                Button(onClick = { isVisibleGetGift = !isVisibleGetGift },
                    shape = RoundedCornerShape(8.dp), colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF4C52C7), contentColor = Color.White),
                    modifier = Modifier.padding(end = 24.dp)) {
                    Text(text = "Claim")
                }
            }

        }
    }
}

@Composable
fun CustomButton(text: String,color: Color = Color(0xff008def), onClick: () -> Unit) {
    Card(
        modifier = Modifier.width(135.dp).shadow(elevation = 8.dp, shape = CircleShape)
            .bounceClick {
                onClick()
            }, backgroundColor = color, shape = CircleShape
    ) {
        Text(
            text, color = Color.White, fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(vertical = 10.dp), textAlign = TextAlign.Center
        )
    }
}