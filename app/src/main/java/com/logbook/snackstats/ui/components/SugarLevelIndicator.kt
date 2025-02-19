package com.logbook.snackstats.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.geometry.Size
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.logbook.snackstats.ui.theme.SnackStatsTheme

@Composable
fun SugarLevelIndicator(
    canvasSize: Dp = 400.dp,
    indicatorValue: Int = 0,
    maxIndicatorValue: Int = 500,
    backgroundIndicatorColor: Color = MaterialTheme.colorScheme.primary.copy(.2f),
    backgroundIndicatorStrokeWidth: Float =  50f,
    foregroundIndicatorColor: Color = MaterialTheme.colorScheme.primary,
    foregroundIndicatorStrokeWidth: Float = 50f,
    bigTextFontSize: TextUnit = MaterialTheme.typography.titleMedium.fontSize,
    bigTextColor: Color = MaterialTheme.colorScheme.onSurface,
    bigTextSuffix: String = "mg/dl",
    smallText: String = "Sugar Level",
    smallTextFontSize: TextUnit = MaterialTheme.typography.titleSmall.fontSize,
    smallTextColor: Color = MaterialTheme.colorScheme.onSurfaceVariant

) {

    var allowedIndicatorValue by remember {
        mutableStateOf(maxIndicatorValue)
    }
    allowedIndicatorValue = if (indicatorValue <= maxIndicatorValue){
        indicatorValue
    } else{
        maxIndicatorValue
    }

    var animatedIndicatorValue by remember {
       mutableStateOf(0f)
    }

    LaunchedEffect(key1 = allowedIndicatorValue) {
        animatedIndicatorValue = allowedIndicatorValue.toFloat()
    }

    val percentage = (animatedIndicatorValue/ maxIndicatorValue ) * 100
    val sweepAngle by animateFloatAsState(
        targetValue = (2.4 * percentage).toFloat(),
        animationSpec = tween(1000)
    )

    val receivedValue by animateIntAsState(
        targetValue = allowedIndicatorValue,
        animationSpec = tween(1000)
    )
    Column(
        modifier = Modifier
            .size(canvasSize)
            .drawBehind {
                val componentSize = size / 1.25f

                backgroundIndicator(
                    componentSize = componentSize,
                    indicatorColor = backgroundIndicatorColor,
                    indicatorStrokeWidth = backgroundIndicatorStrokeWidth
                )

                foregroundIndicator(
                    sweepAngle = sweepAngle,
                    componentSize = componentSize,
                    indicatorColor = foregroundIndicatorColor,
                    indicatorStrokeWidth = foregroundIndicatorStrokeWidth
                )
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            EmbeddedElements(
                bigText = receivedValue,
                bigTextColor = bigTextColor,
                bigTextFontSize = bigTextFontSize,
                bigTextSuffix = bigTextSuffix,
                smallText = smallText,
                smallTextColor = smallTextColor,
                smallTextFontSize = smallTextFontSize
            )
    }

}

fun DrawScope.backgroundIndicator(
    componentSize: Size,
    indicatorColor: Color,
    indicatorStrokeWidth: Float,
    ){
    drawArc(
        size = componentSize,
        color = indicatorColor,
        startAngle = 150f,
        sweepAngle = 240f,
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = StrokeCap.Round
        ),
        topLeft = Offset(
            x = (size.width -componentSize.width) /2f,
            y = (size.height - componentSize.height) /2f
        )
        )

}
fun DrawScope.foregroundIndicator(
    sweepAngle : Float,
    componentSize: Size,
    indicatorColor: Color,
    indicatorStrokeWidth: Float,
){
    drawArc(
        size = componentSize,
        color = indicatorColor,
        startAngle = 150f,
        sweepAngle = sweepAngle,
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = StrokeCap.Round
        ),
        topLeft = Offset(
            x = (size.width -componentSize.width) /2f,
            y = (size.height - componentSize.height) /2f
        )
    )

}

@Composable
fun EmbeddedElements(
    bigText: Int,
    bigTextFontSize: TextUnit,
    bigTextColor: Color,
    bigTextSuffix: String,
    smallText: String,
    smallTextFontSize: TextUnit,
    smallTextColor: Color,
    modifier: Modifier = Modifier
) {

    Text(
        text = "$bigText ${bigTextSuffix}",
        color = bigTextColor,
        fontSize = bigTextFontSize,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )
    Text(
        text = smallText,
        color = smallTextColor,
        fontSize = smallTextFontSize,
        textAlign = TextAlign.Center
    )
}


@Preview(showBackground = true)
@Composable
private fun CircularIndicatorPreview() {
    SnackStatsTheme {
        SugarLevelIndicator(

        )
    }
}