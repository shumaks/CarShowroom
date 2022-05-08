package com.example.carshowroom.screen.main.view.ui.mode

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.carshowroom.R
import com.example.carshowroom.repo.employee.entity.Employee
import com.example.carshowroom.repo.mode.entity.Mode
import com.example.carshowroom.screen.main.view.ui.NavigationRoute
import com.example.carshowroom.screen.main.view.ui.SearchView
import com.example.carshowroom.screen.main.viewmodel.MainViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.util.Locale

@Composable
fun ModeScreen(
    viewModel: MainViewModel,
    navController: NavHostController
) {
    val modeList by viewModel.modeListStateFlow.collectAsState()
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    var filteredModeList: List<Mode>
    val isRefreshing by viewModel.isRefreshing.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .wrapContentSize(Alignment.TopStart)
    ) {
        Row(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            SearchView(textState)
        }
        if (modeList.isEmpty()) {
            Row(
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Row(
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        navController.navigate(NavigationRoute.AddMode.value)
                    }
                ) {
                    Text("+")
                }
            }
        }
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = {
                viewModel.modeListStateFlow.value = emptyList()
                viewModel.getModeList()
            },
        ) {
            LazyColumn(
                modifier = Modifier.padding(bottom = 50.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                val searchedText = textState.value.text
                filteredModeList = if (searchedText.isEmpty()) {
                    modeList
                } else {
                    val resultList = ArrayList<Mode>()
                    for (mode in modeList) {
                        if (mode.name.lowercase(Locale.getDefault())
                                .contains(searchedText.lowercase(Locale.getDefault()))
                        ) {
                            resultList.add(mode)
                        }
                    }
                    resultList
                }
                items(
                    items = filteredModeList,
                    itemContent = {
                        ModeListView(it, navController)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AutoScreenPreview() {
//    AutoScreen()
}