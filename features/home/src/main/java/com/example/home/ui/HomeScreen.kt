package com.example.home.ui

import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.example.home.R
import com.example.home.data.CardItem
import com.example.home.data.TopTabEnum
import com.example.home.navigation.HomeModuleNavigation
import com.example.home.viewModel.HomeViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Named



@OptIn(ExperimentalFoundationApi::class)
@Destination
@Composable
fun HomeScreen(
    navigation: HomeModuleNavigation,
    viewModel: HomeViewModel = hiltViewModel()
    ) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { TopTabEnum.entries.size })
    val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }



    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Header(navigation)

        ScrollableTabRow(
            edgePadding = 0.dp,
            selectedTabIndex = selectedTabIndex.value,
            modifier = Modifier
                .fillMaxWidth() // Apply horizontalScroll here
        ) {
            TopTabEnum.entries.forEachIndexed { index, currentTab ->
                Tab(
                    selected = selectedTabIndex.value == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(currentTab.ordinal)
                        }
                    },
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color.Gray,
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 12.dp)
                    ) {
                        Icon(
                            imageVector = if (selectedTabIndex.value == index) {
                                currentTab.selectedIcon
                            } else {
                                currentTab.unselectedIcon
                            },
                            contentDescription = ""
                        )
                        Text(text = currentTab.title)
                    }
                }

            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalPager(state = pagerState, key = { TopTabEnum.entries[it] }) {
            Content(navigation)
        }

        Spacer(modifier = Modifier.height(16.dp))
    }

}


@Composable
fun Header(
    navigation: HomeModuleNavigation
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.diamond),
                contentDescription = "",
                modifier = Modifier
                    .height(40.dp)
                    .width(40.dp)
                    .padding(end = 10.dp)
                    .clickable {
                        navigation.navigateToAccountScreen()
                    },
                tint = MaterialTheme.colorScheme.inversePrimary
            )
            Text(
                text = "SMShop",
                fontSize = 18.sp
            )
        }

//        Search Icon

        Box(
            modifier = Modifier
                .clickable {

                }
                .clip(RoundedCornerShape(15.dp))
                .background(MaterialTheme.colorScheme.inversePrimary)
                .padding(10.dp)
        ) {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = "",
            )
        }
    }
}

@Composable
fun Content(navigation: HomeModuleNavigation) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        item {
            FeaturedProducts(navigation)

            Spacer(modifier = Modifier.height(16.dp))

            TopSellingProducts()

            Spacer(modifier = Modifier.height(16.dp))


            TopSellingProducts()

            Spacer(modifier = Modifier.height(16.dp))

            TopCategoriesProducts()

            Spacer(modifier = Modifier.height(16.dp))

            MostViewedProducts()
        }

    }
}


@Composable
fun FeaturedProducts(navigation: HomeModuleNavigation) {
    val cardItems: List<CardItem> = listOf(
        CardItem(
            type = "shoes",
            price = 22.33,
            category = "men",
            title = "Damira Sneaker",
            color = getGradient(
                MaterialTheme.colorScheme.inversePrimary,
                MaterialTheme.colorScheme.onBackground
            ),
            image = painterResource(id = R.drawable.sneaker)
        ),
        CardItem(
            type = "shoes",
            price = 22.33,
            category = "men",
            title = "Damira Sneaker",
            color = getGradient(
                MaterialTheme.colorScheme.inversePrimary,
                MaterialTheme.colorScheme.onBackground
            ),
            image = painterResource(id = R.drawable.sneaker2)
        ),
        CardItem(
            type = "shoes",
            price = 22.33,
            category = "men",
            title = "Damira Sneaker",
            color = getGradient(
                MaterialTheme.colorScheme.inversePrimary,
                MaterialTheme.colorScheme.tertiaryContainer
            ),
            image = painterResource(id = R.drawable.sneaker3)
        )
    )


    LazyRow {
        items(cardItems.size) { index ->
            var lastItemPadding = if (index == cardItems.size - 1) 16.dp else 0.dp
            ElevatedCard(
                modifier = Modifier
                    .width(300.dp)
                    .padding(start = 16.dp, end = lastItemPadding)
                    .clickable {
                               navigation.navigateToProductScreen()
                    },
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(5.dp)
            ) {
                Box(
                    modifier = Modifier
                        .height(200.dp)
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    Image(
                        painter = cardItems[index].image,
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
                                text = cardItems[index].title,
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    color = Color.White
                                )
                            )

                            Spacer(modifier = Modifier.height(10.dp))
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "KE ${cardItems[index].price.toString()}",
                                    style = TextStyle(
                                        fontSize = 20.sp,
                                        color = Color.White
                                    )
                                )

                            }
                        }
                    }
                }
            }
        }
    }
}

fun getGradient(startColor: Color, endColor: Color): Brush {
    return Brush.horizontalGradient(colors = listOf(startColor, endColor))
}

@Preview
@Composable
fun TopSellingProducts() {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Text(
            text = "Top Selling", style = TextStyle(
                color = Color.LightGray,
                fontSize = 18.sp
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyVerticalGrid(
            modifier = Modifier.height(600.dp),
            columns = GridCells.Fixed(2),
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
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = "KE 700",
                                        style = TextStyle(
                                            fontSize = 18.sp,
                                            color = Color.White
                                        )
                                    )

                                    Row(
                                        horizontalArrangement = Arrangement.End
                                    ) {
                                        Button(
                                            onClick = { /*TODO*/ },
                                            modifier = Modifier.size(60.dp, 30.dp)
                                        ) {
                                            Icon(
                                                Icons.Rounded.ShoppingCart,
                                                contentDescription = "",
                                                modifier = Modifier.size(30.dp)
                                            )
                                        }

                                        Spacer(modifier = Modifier.width(10.dp))


                                        Icon(
                                            Icons.Rounded.Star,
                                            contentDescription = "",
                                            modifier = Modifier.size(30.dp)
                                        )
                                    }
                                }
                            }
                        }


                    }
                }
            })
    }
}


@Composable
fun TopCategoriesProducts() {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Text(
            text = "Top Categories", style = TextStyle(
                color = Color.LightGray,
                fontSize = 18.sp
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyVerticalGrid(
            modifier = Modifier.height(600.dp),
            columns = GridCells.Fixed(2),
            content = {
                items(6) { index ->
                    Box(
                        modifier = Modifier
                            .height(200.dp)
                            .padding(6.dp)
                            .clip(RoundedCornerShape(8.dp))
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.sneaker),
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
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = "KE 500",
                                        style = TextStyle(
                                            fontSize = 18.sp,
                                            color = Color.White
                                        )
                                    )

                                    Row(
                                        horizontalArrangement = Arrangement.End
                                    ) {
                                        Button(
                                            onClick = { /*TODO*/ },
                                            modifier = Modifier.size(60.dp, 30.dp)
                                        ) {
                                            Icon(
                                                Icons.Rounded.ShoppingCart,
                                                contentDescription = "",
                                                modifier = Modifier.size(30.dp)
                                            )
                                        }

                                        Spacer(modifier = Modifier.width(10.dp))

                                        OutlinedButton(
                                            onClick = { /*TODO*/ },
                                            modifier = Modifier.size(60.dp, 30.dp)
                                        ) {
                                            Icon(
                                                Icons.Outlined.Favorite,
                                                contentDescription = "",
                                                modifier = Modifier.size(30.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }


                    }
                }
            })
    }
}


@Composable
fun MostViewedProducts() {
}