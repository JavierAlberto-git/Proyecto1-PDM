package com.example.practica3room

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.practica3room.ui.screens.MenuScreen
import com.example.practica3room.ui.theme.Practica3RoomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practica3RoomTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MenuScreen(
                        onNavigateToTaskList = {
                            // TODO: Navegación a lista de tareas
                        },
                        onNavigateToAddTask = {
                            // TODO: Navegación a agregar tarea
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}