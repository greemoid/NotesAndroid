package com.greemoid.notesappmvvm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.greemoid.notesappmvvm.screens.Add
import com.greemoid.notesappmvvm.screens.Main
import com.greemoid.notesappmvvm.screens.Note
import com.greemoid.notesappmvvm.screens.Start


sealed class NavRoute(val route: String) {

    object Start: NavRoute("start_screen")
    object Main: NavRoute("main_screen")
    object Add: NavRoute("add_screen")
    object Note: NavRoute("note_screen")


}

@Composable
fun NotesNavHost() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRoute.Start.route) {
        composable(NavRoute.Start.route) { Start(navHostController = navController)}
        composable(NavRoute.Main.route) { Main(navHostController = navController)}
        composable(NavRoute.Add.route) { Add(navHostController = navController)}
        composable(NavRoute.Note.route) { Note(navHostController = navController)}
    }

}