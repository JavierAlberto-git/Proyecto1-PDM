package com.example.practica3room.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practica3room.ui.theme.PrimaryBlue
import com.example.practica3room.ui.theme.BackgroundCream
import com.example.practica3room.ui.theme.Black

@Composable
fun MenuScreen(
    onNavigateToTaskList: () -> Unit,
    onNavigateToAddTask: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundCream)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Título
        Text(
            text = "Gestor de Tareas",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Botón: Ver todas las tareas
        MenuButton(
            text = "Ver Todas las Tareas",
            icon = Icons.Default.List,
            onClick = onNavigateToTaskList
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón: Agregar nueva tarea
        MenuButton(
            text = "Agregar Nueva Tarea",
            icon = Icons.Default.Add,
            onClick = onNavigateToAddTask
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón: Editar tareas
        MenuButton(
            text = "Editar Tareas",
            icon = Icons.Default.Edit,
            onClick = onNavigateToTaskList // Navegará a la lista donde se puede editar
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón: Eliminar tareas
        MenuButton(
            text = "Eliminar Tareas",
            icon = Icons.Default.Delete,
            onClick = onNavigateToTaskList // Navegará a la lista donde se puede eliminar
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón: Marcar como completadas/pendientes
        MenuButton(
            text = "Gestionar Estado de Tareas",
            icon = Icons.Default.CheckCircle,
            onClick = onNavigateToTaskList // Navegará a la lista donde se puede cambiar el estado
        )
    }
}

@Composable
fun MenuButton(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryBlue
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
    }
}