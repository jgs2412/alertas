package com.example.alertas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.alertas.ui.theme.AlertasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AlertasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column (modifier = Modifier.fillMaxSize().padding(innerPadding)) {
                        AlertDialogsScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun AlertDialogsScreen() {
    var textState by remember { mutableStateOf("Texto inicial") }
    var showDialog by remember { mutableStateOf<String?>(null) }

    fun showConfirmationDialog() {
        showDialog = "confirmation"
    }

    fun showDeletionDialog() {
        showDialog = "deletion"
    }

    fun showInfoDialog() {
        showDialog = "info"
    }

    fun showAuthenticationDialog() {
        showDialog = "authentication"
    }

    fun showErrorDialog() {
        showDialog = "error"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Button(onClick = { showConfirmationDialog() }) {
            Text("Confirmación de Acción")
        }
        Button(onClick = { showDeletionDialog() }) {
            Text("Eliminación Permanente")
        }
        Button(onClick = { showInfoDialog() }) {
            Text("Información Importante")
        }
        Button(onClick = { showAuthenticationDialog() }) {
            Text("Autenticación Requerida")
        }
        Button(onClick = { showErrorDialog() }) {
            Text("Error Crítico")
        }
        Text(text = textState, style = MaterialTheme.typography.bodySmall)

    }


    if (showDialog != null) {
        when (showDialog) {
            "confirmation" -> {
                AlertDialog(
                    onDismissRequest = { showDialog = null },
                    title = { Text("Confirmación de Acción") },
                    text = { Text("¿Estás seguro de que deseas continuar con esta acción?") },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                textState = "Acción Confirmada"
                                showDialog = null
                            }
                        ) {
                            Text("Sí")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDialog = null }) {
                            Text("No")
                        }
                    }
                )
            }
            "deletion" -> {
                AlertDialog(
                    onDismissRequest = { showDialog = null },
                    title = { Text("Eliminar Elemento") },
                    text = { Text("Esta acción es irreversible. ¿Deseas eliminar este elemento?") },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                textState = "Eliminación Exitosa"
                                showDialog = null
                            }
                        ) {
                            Text("Eliminar")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDialog = null }) {
                            Text("Cancelar")
                        }
                    }
                )
            }
            "info" -> {
                AlertDialog(
                    onDismissRequest = { showDialog = null },
                    title = { Text("Aviso Importante") },
                    text = { Text("Recuerda que los cambios realizados no se pueden deshacer.") },
                    confirmButton = {
                        TextButton(onClick = { showDialog = null }) {
                            Text("Entendido")
                        }
                    }
                )
            }
            "authentication" -> {
                AlertDialog(
                    onDismissRequest = { showDialog = null },
                    title = { Text("Requiere Autenticación") },
                    text = { Text("Para continuar, necesitas autenticarte de nuevo.") },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                textState = "Usuario Autenticado"
                                showDialog = null
                            }
                        ) {
                            Text("Autenticar")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDialog = null }) {
                            Text("Cancelar")
                        }
                    }
                )
            }
            "error" -> {
                AlertDialog(
                    onDismissRequest = { showDialog = null },
                    title = { Text("Error Crítico") },
                    text = { Text("Se ha producido un error crítico. ¿Deseas intentar nuevamente?") },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                textState = "Intento de Reintento"
                                showDialog = null
                            }
                        ) {
                            Text("Reintentar")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDialog = null }) {
                            Text("Cancelar")
                        }
                    }
                )
            }
        }
    }
}
