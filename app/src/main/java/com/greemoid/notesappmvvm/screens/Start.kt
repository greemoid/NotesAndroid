package com.greemoid.notesappmvvm.screens

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.greemoid.notesappmvvm.MainViewModel
import com.greemoid.notesappmvvm.MainViewModelFactory
import com.greemoid.notesappmvvm.navigation.NavRoute
import com.greemoid.notesappmvvm.ui.theme.NotesAppMVVMTheme
import com.greemoid.notesappmvvm.utils.EMAIL
import com.greemoid.notesappmvvm.utils.PASSWORD
import com.greemoid.notesappmvvm.utils.TYPE_FIREBASE
import com.greemoid.notesappmvvm.utils.TYPE_ROOM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StartScreen(navController: NavHostController, viewModel: MainViewModel) {
    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isButtonEnabled by remember { mutableStateOf(false) }

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetElevation = 5.dp,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        sheetContent = {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                ) {
                    Text(text = "Sign In")
                    OutlinedTextField(
                        value = email,
                        onValueChange = {
                            email = it
                            isButtonEnabled = email.isNotEmpty() && password.isNotEmpty()
                        },
                        label = { Text(text = "Email") },
                        isError = email.isEmpty()
                    )
                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                            isButtonEnabled = email.isNotEmpty() && password.isNotEmpty()
                        },
                        label = { Text(text = "Password") },
                        isError = password.isEmpty()
                    )
                    Button(
                        modifier = Modifier.padding(top = 16.dp),
                        enabled = isButtonEnabled,
                        onClick = {
                            EMAIL = email
                            PASSWORD = password
                            viewModel.initDatabase(TYPE_FIREBASE) {

                            }
                            }
                    ) {
                        Text(text = "Sign In")
                    }
                }
            }
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "What will we use?")
                Button(
                    onClick = {
                        viewModel.initDatabase(TYPE_ROOM){
                            navController.navigate(route = NavRoute.Main.route)
                        }
                    },
                    modifier = Modifier
                        .width(200.dp)
                        .padding(vertical = 8.dp)
                ) {
                    Text(text = "Room database")
                }
                Button(
                    onClick = {
                        coroutineScope.launch {
                            bottomSheetState.show()
                        }
                    },
                    modifier = Modifier
                        .width(200.dp)
                        .padding(vertical = 8.dp)
                ) {
                    Text(text = "Firebase database")
                }
            }
        }
    }
    }





@Preview(showBackground = true)
@Composable
fun prevStartScreen() {
    NotesAppMVVMTheme {
        val context = LocalContext.current
        val viewModel: MainViewModel =
            viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
        StartScreen(navController = rememberNavController(), viewModel = viewModel)
    }
}