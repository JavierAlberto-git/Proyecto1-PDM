package com.example.practica3room.ui.Screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.practica3room.ui.Screens.AddTaskScreen
import com.example.practica3room.ui.screens.MenuScreen
import com.example.practica3room.ui.Screens.TaskListScreen
import com.example.practica3room.viewmodel.TaskViewModel

// Definición de rutas
object Routes {
    const val MENU = "menu"
    const val TASK_LIST = "task_list"
    const val ADD_TASK = "add_task"
}

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    viewModel: TaskViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Routes.MENU,
        modifier = modifier
    ) {
        // Pantalla de Menú
        composable(Routes.MENU) {
            MenuScreen(
                onNavigateToTaskList = {
                    navController.navigate(Routes.TASK_LIST)
                },
                onNavigateToAddTask = {
                    navController.navigate(Routes.ADD_TASK)
                }
            )
        }

        // Pantalla de Lista de Tareas
        composable(Routes.TASK_LIST) {
            TaskListScreen(
                viewModel = viewModel,
                onBackToMenu = {
                    navController.popBackStack()
                }
            )
        }

        // Pantalla de Agregar Tarea
        composable(Routes.ADD_TASK) {
            AddTaskScreen(
                viewModel = viewModel,
                onTaskAdded = {
                    navController.popBackStack()
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}