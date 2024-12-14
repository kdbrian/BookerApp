package io.kdbrian.bookercomposeapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import io.kdbrian.bookercomposeapp.R


val lexend = FontFamily(
    Font(resId = R.font.lexendregular, style = FontStyle.Normal),
    Font(resId = R.font.lexendbold, style = FontStyle.Normal),
    Font(resId = R.font.lexendlight, style = FontStyle.Normal),
    Font(resId = R.font.lexendlight, style = FontStyle.Normal)
)

val montserrat = FontFamily(
    Font(resId = R.font.montserratregular, style = FontStyle.Normal),
    Font(resId = R.font.montserratbold, style = FontStyle.Normal),
    Font(resId = R.font.montserratitalic, style = FontStyle.Normal),
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)