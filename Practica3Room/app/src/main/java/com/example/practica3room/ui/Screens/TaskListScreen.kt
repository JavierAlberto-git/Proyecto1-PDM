package com.example.practica3room.ui.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practica3room.model.Task
import com.example.practica3room.ui.theme.BackgroundCream
import com.example.practica3room.ui.theme.Black
import com.example.practica3room.ui.theme.PrimaryBlue
import com.example.practica3room.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    viewModel: TaskViewModel,
    onBackToMenu: () -> Unit,
    modifier: Modifier = Modifier
) {
    val tasks by viewModel.allTasks.collectAsState(initial = emptyList())

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundCream)
    ) {
        // TopBar
        TopAppBar(
            title = {
                Text(
                    text = "Todas las Tareas",
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                IconButton(onClick = onBackToMenu) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Volver al menú"
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = PrimaryBlue,
                titleContentColor = BackgroundCream,
                navigationIconContentColor = BackgroundCream
            )
        )

        // Contenido
        if (tasks.isEmpty()) {
            // Mensaje cuando no hay tareas
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.List,
                        contentDescription = null,
                        modifier = Modifier.size(80.dp),
                        tint = Black.copy(alpha = 0.3f)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "No hay tareas aún",
                        fontSize = 20.sp,
                        color = Black.copy(alpha = 0.6f)
                    )
                }
            }
        } else {
            // Lista de tareas
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(tasks, key = { it.id }) { task ->
                    TaskItem(
                        task = task,
                        onToggleStatus = {
                            viewModel.updateTask(task.copy(status = !task.status))
                        },
                        onDelete = {
                            viewModel.deleteTask(task.id)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun TaskItem(
    task: Task,
    onToggleStatus: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showDeleteDialog by remember { mutableStateOf(false) }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (task.status) {
                PrimaryBlue.copy(alpha = 0.1f)
            } else {
                BackgroundCream
            }
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Checkbox para marcar como completada
            Checkbox(
                checked = task.status,
                onCheckedChange = { onToggleStatus() },
                colors = CheckboxDefaults.colors(
                    checkedColor = PrimaryBlue,
                    uncheckedColor = Black.copy(alpha = 0.4f)
                )
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Información de la tarea
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = task.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Black,
                    textDecoration = if (task.status) {
                        TextDecoration.LineThrough
                    } else {
                        TextDecoration.None
                    }
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = Black.copy(alpha = 0.6f)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = task.plannedD,
                        fontSize = 14.sp,
                        color = Black.copy(alpha = 0.6f)
                    )
                }
            }

            // Botón de eliminar
            IconButton(onClick = { showDeleteDialog = true }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar tarea",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }

    // Diálogo de confirmación de eliminación
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            icon = {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error,
                    modifier = Modifier.size(48.dp)
                )
            },
            title = {
                Text(
                    text = "Eliminar Tarea",
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text("¿Estás seguro de que deseas eliminar la tarea '${task.name}'?")
            },
            confirmButton = {
                Button(
                    onClick = {
                        onDelete()
                        showDeleteDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("Eliminar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}