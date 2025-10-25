package com.example.practica3room.ui.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practica3room.model.Task
import com.example.practica3room.ui.theme.BackgroundCream
import com.example.practica3room.ui.theme.Black
import com.example.practica3room.ui.theme.PrimaryBlue
import com.example.practica3room.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    viewModel: TaskViewModel,
    onTaskAdded: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var taskName by remember { mutableStateOf("") }
    var plannedDate by remember { mutableStateOf("") }
    var showSuccessDialog by remember { mutableStateOf(false) }
    var showErrorDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundCream)
    ) {
        // TopBar
        TopAppBar(
            title = {
                Text(
                    text = "Agregar Nueva Tarea",
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Volver"
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // Campo: Nombre de la tarea
            OutlinedTextField(
                value = taskName,
                onValueChange = { taskName = it },
                label = { Text("Nombre de la tarea") },
                placeholder = { Text("Ej: Estudiar Kotlin") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryBlue,
                    unfocusedBorderColor = Black.copy(alpha = 0.3f),
                    focusedLabelColor = PrimaryBlue,
                    cursorColor = PrimaryBlue
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Campo: Fecha planeada
            OutlinedTextField(
                value = plannedDate,
                onValueChange = { plannedDate = it },
                label = { Text("Fecha planeada") },
                placeholder = { Text("Ej: 25/10/2025") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = null,
                        tint = PrimaryBlue
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryBlue,
                    unfocusedBorderColor = Black.copy(alpha = 0.3f),
                    focusedLabelColor = PrimaryBlue,
                    cursorColor = PrimaryBlue
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Botón: Guardar tarea
            Button(
                onClick = {
                    if (taskName.isBlank()) {
                        errorMessage = "El nombre de la tarea no puede estar vacío"
                        showErrorDialog = true
                    } else if (plannedDate.isBlank()) {
                        errorMessage = "La fecha planeada no puede estar vacía"
                        showErrorDialog = true
                    } else {
                        val newTask = Task(
                            name = taskName.trim(),
                            plannedD = plannedDate.trim(),
                            status = false // Por defecto, la tarea está pendiente
                        )
                        viewModel.insertTask(newTask)
                        showSuccessDialog = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryBlue
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Guardar Tarea",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón: Cancelar
            OutlinedButton(
                onClick = onBack,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = PrimaryBlue
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Cancelar",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }

    // Diálogo de éxito
    if (showSuccessDialog) {
        AlertDialog(
            onDismissRequest = { },
            icon = {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = PrimaryBlue,
                    modifier = Modifier.size(48.dp)
                )
            },
            title = {
                Text(
                    text = "¡Tarea Agregada!",
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text("La tarea '$taskName' ha sido guardada exitosamente.")
            },
            confirmButton = {
                Button(
                    onClick = {
                        showSuccessDialog = false
                        onTaskAdded()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryBlue
                    )
                ) {
                    Text("Aceptar")
                }
            }
        )
    }

    // Diálogo de error
    if (showErrorDialog) {
        AlertDialog(
            onDismissRequest = { showErrorDialog = false },
            icon = {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error,
                    modifier = Modifier.size(48.dp)
                )
            },
            title = {
                Text(
                    text = "Error",
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(errorMessage)
            },
            confirmButton = {
                Button(
                    onClick = { showErrorDialog = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("Entendido")
                }
            }
        )
    }
}