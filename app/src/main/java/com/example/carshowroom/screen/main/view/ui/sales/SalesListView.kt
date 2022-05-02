package com.example.carshowroom.screen.main.view.ui.sales


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.carshowroom.repo.auto.entity.Auto
import com.example.carshowroom.repo.sale.entity.Sale
import com.example.carshowroom.screen.main.view.ui.NavigationRoute

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SalesListView(item: Sale, navController: NavHostController) {
    Card(
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp).fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        onClick = {
            navController.navigate("${NavigationRoute.SaleView.value}/${item.id}")
        }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = item.auto.model, style = typography.h6)
            Text(text = item.date, style = typography.caption)
        }
    }
}