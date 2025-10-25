package com.example.practica3room

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.example.practica3room.model.AppDatabase
import com.example.practica3room.model.TaskRepository
import com.example.practica3room.ui.Screens.AppNavigation
import com.example.practica3room.ui.theme.Practica3RoomTheme
import com.example.practica3room.viewmodel.TaskViewModel

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inicializar la base de datos
        val database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "task_database"
        ).build()

        // Crear el repositorio y el ViewModel
        val repository = TaskRepository(database.taskDao())
        viewModel = TaskViewModel(repository)

        setContent {
            Practica3RoomTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}