package io.kdbrian.bookercomposeapp.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.kdbrian.bookercomposeapp.data.util.Resource
import io.kdbrian.bookercomposeapp.ui.theme.BookerComposeAppTheme
import src.main.graphql.GetAllRoutesQuery
import src.main.graphql.GetAllVehiclesQuery
import src.main.graphql.GetCompaniesQuery
import src.main.graphql.GetSchedulesQuery
import io.kdbrian.bookercomposeapp.R
import io.kdbrian.bookercomposeapp.ui.theme.montserrat


@Composable
fun StatsScreen(
    modifier: Modifier = Modifier,
    allCompanies: Resource<GetCompaniesQuery.Data>,
    allVehicles: Resource<GetAllVehiclesQuery.Data>,
    allRoutes: Resource<GetAllRoutesQuery.Data>,
    allSchedules: Resource<GetSchedulesQuery.Data>
) {
    val statsCards = listOf(
        StatCardInfo(
            leadingText = "Over ${allCompanies.data?.getCompanies?.size} Companies",
            supportingText = "What you need, when you need it.",
            image = R.drawable.travellogos
        ),
        StatCardInfo(
            leadingText = "Locomotive to offer fultime support.",
            supportingText = allVehicles.data?.getVehicles?.size.toString(),
            image = R.drawable.vehicles
        ),
        StatCardInfo(
            leadingText = "Routes distributed globallu",
            supportingText = "Explore over ${allRoutes.data?.getRoutes?.size.toString()} varieties.",
            image = R.drawable.companies
        )
    )

    LazyColumn {
        items(items = statsCards) {
            StatCard(
                statsCard = it
            )
        }
    }

}

@Preview
@Composable
private fun StatPrev() {
    BookerComposeAppTheme {
//        StatCard()
    }
}

@Composable
private fun StatCard(
    modifier: Modifier = Modifier,
    statsCard: StatCardInfo
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(12.dp))
        ) {
            Image(
                painter = painterResource(statsCard.image),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color(0xFFF48668)
                            )
                        )
                    )
            )

            Column(
                modifier = Modifier.align(Alignment.Center)
            ) {
                Text(
                    text = statsCard.leadingText,
                    fontSize = 30.sp,
                    style = TextStyle(
                        fontFamily = montserrat,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        lineHeight = 26.sp,

                        )
                )

                Spacer(Modifier.height(12.dp))


            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(
                        shape = RoundedCornerShape(0.dp),
                        color = Color(0xFFDD614A)
                    )
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column(
                    modifier = Modifier.fillMaxWidth(1f)
                ) {

                    Text(
                        text = statsCard.supportingText,
                        fontSize = 20.sp,
                        fontFamily = montserrat,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

            }

            IconButton(
                onClick = {},
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 12.dp)
                    .wrapContentSize()
                    .background(shape = CircleShape, color = Color(0xFFFFE8D1))
                    .animateContentSize(
                        animationSpec = tween(
                            durationMillis = 300
                        )
                    )
            ) {

                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowForward,
                    contentDescription = null,
                    tint = Color(0xFFBA5624)
                )
            }

        }


    }
}

data class StatCardInfo(
    val leadingText: String,
    val supportingText: String,
    @DrawableRes val image: Int
)