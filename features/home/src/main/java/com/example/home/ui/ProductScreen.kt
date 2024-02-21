package com.example.home.ui

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.home.R
import com.example.home.data.CardItem
import com.example.home.navigation.HomeModuleNavigation
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ProductScreen(navigation: HomeModuleNavigation) {

    Column() {
        ProductHeader(modifier = Modifier.fillMaxWidth())
        ProductContent()
    }
}

@Composable
fun ProductHeader(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = "",
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(text = "Details", fontSize = 20.sp)
        }
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
            Icon(
                imageVector = Icons.Outlined.ShoppingCart,
                contentDescription = null,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            Icon(imageVector = Icons.Outlined.MoreVert, contentDescription = null)
        }
    }

}


@Composable
fun ProductContent() {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp),
        content = {
            item {
                Image(
                    painter = painterResource(id = R.drawable.sneaker),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(fraction = 0.3f)
                        .clip(RoundedCornerShape(6.dp)),
                    contentScale = ContentScale.Crop,

                    )
            }

            item {
                Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 16.dp)) {
                    Text("Shoes")

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "Demira Sneakers",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Our Canvas Shoes are Great for many occasions such as, casual wear in house or in office, hiking ,running , shopping , driving , and gym etc, all year round style ,very suitable especially for summer ,spring ,autumn and well paired with any kind of clothes.",
                        style = TextStyle(
                            color = Color.LightGray,
                            fontSize = 16.sp,
                            lineHeight = 1.8.em
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "KES 1,500", style = TextStyle(
                                fontSize = 25.sp,
                            )
                        )
                        Text(
                            text = "KES 1,900",
                            style = TextStyle(
                                fontSize = 15.sp,
                                textDecoration = TextDecoration.LineThrough,
                                color = Color.LightGray
                            ),
                            modifier = Modifier.padding(horizontal = 10.dp)
                        )
                        AssistChip(
                            onClick = { },
                            label = { Text("-17%") },
                        )


                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(
                            onClick = { /*TODO*/ }) {
                            Text(text = "Buy Now")
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        OutlinedButton(onClick = {}) {
                            Icon(imageVector = Icons.Outlined.Favorite, contentDescription = "")
                        }

                    }
                }
            }

            item {
                RelatedProducts()
            }


        })
}


@Composable
fun RelatedProducts() {
    Column(
        modifier = Modifier.padding(vertical = 20.dp)
    ) {
        Text(
            text = "Related Products", style = TextStyle(
                color = Color.LightGray,
                fontSize = 25.sp
            ),
            modifier = Modifier.padding(horizontal = 10.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyVerticalGrid(
            modifier = Modifier.height(400.dp),
            columns = GridCells.Fixed(3),
            content = {
                items(6) { index ->
                    Box(
                        modifier = Modifier
                            .height(200.dp)
                            .padding(6.dp)
                            .clip(RoundedCornerShape(8.dp))
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.sneaker2),
                            contentDescription = "sneaker",
                            contentScale = ContentScale.Crop
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent, Color.Black
                                        ),
                                        startY = 100f
                                    )
                                )
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            contentAlignment = Alignment.BottomStart
                        ) {
                            Column() {
                                Text(
                                    text = "Sneaker",
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        color = Color.White
                                    )
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    text = "KE 700",
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        color = Color.White
                                    )
                                )

                            }
                        }


                    }
                }
            })
    }
}