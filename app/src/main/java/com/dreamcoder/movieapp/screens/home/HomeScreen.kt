package com.dreamcoder.movieapp.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dreamcoder.movieapp.model.Movie
import com.dreamcoder.movieapp.model.getMovies
import com.dreamcoder.movieapp.navigation.MovieScreens
import com.dreamcoder.movieapp.widgets.MovieRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    Scaffold(topBar = {
        TopAppBar(
            title = { Text("Movies") },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.LightGray),
            modifier = Modifier.shadow(5.dp)
        )
    }) { innerPadding ->
        Log.d("MainActivity", "innerPadding:$innerPadding")
        MainContent(navController = navController, paddingValues = innerPadding)
    }
}

@Composable
fun MainContent(
    navController: NavController,
    movieList: List<Movie> = getMovies(),
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier.padding(
            PaddingValues(
                start = paddingValues.calculateStartPadding(LayoutDirection.Ltr) + 12.dp,
                top = paddingValues.calculateTopPadding() + 12.dp,
                end = paddingValues.calculateEndPadding(LayoutDirection.Ltr) + 12.dp,
                bottom = paddingValues.calculateBottomPadding() + 12.dp
            )
        )
    ) {
        LazyColumn {
            items(items = movieList) {
                MovieRow(movie = it) { movie ->
                    navController.navigate(route = MovieScreens.DetailsScreen.name + "/$movie")
                }
            }
        }
    }
}