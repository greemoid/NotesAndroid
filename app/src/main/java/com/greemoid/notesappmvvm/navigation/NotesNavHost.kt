package com.greemoid.notesappmvvm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.greemoid.notesappmvvm.MainViewModel
import com.greemoid.notesappmvvm.screens.AddScreen
import com.greemoid.notesappmvvm.screens.MainScreen
import com.greemoid.notesappmvvm.screens.NoteScreen
import com.greemoid.notesappmvvm.screens.StartScreen
import com.greemoid.notesappmvvm.utils.Constants
import com.greemoid.notesappmvvm.utils.Constants.SCREENS.ADD_SCREEN
import com.greemoid.notesappmvvm.utils.Constants.SCREENS.MAIN_SCREEN
import com.greemoid.notesappmvvm.utils.Constants.SCREENS.NOTE_SCREEN
import com.greemoid.notesappmvvm.utils.Constants.SCREENS.START_SCREEN

sealed class NavRoute(val route: String) {
    object Start: NavRoute(START_SCREEN)
    object Main: NavRoute(MAIN_SCREEN)
    object Add: NavRoute(ADD_SCREEN)
    object Note: NavRoute(NOTE_SCREEN)
}

@Composable
fun NotesNavHost(viewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRoute.Start.route) {
        composable(NavRoute.Start.route) { StartScreen(navController = navController, viewModel = viewModel) }
        composable(NavRoute.Main.route) { MainScreen(navController = navController, viewModel = viewModel) }
        composable(NavRoute.Add.route) { AddScreen(navController = navController, viewModel = viewModel) }
        composable(NavRoute.Note.route + "/{${Constants.KEYS.ID}}") { backStackEntry ->
            NoteScreen(navController = navController, viewModel = viewModel, noteId = backStackEntry.arguments?.getString(Constants.KEYS.ID)) }
    }
}