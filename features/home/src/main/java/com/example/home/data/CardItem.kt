package com.example.home.data

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.painter.Painter

data class CardItem (
    val type:String,
    val title:String,
    val category:String,
    val price:Double,
    val color:Brush,
    val image:Painter,
)