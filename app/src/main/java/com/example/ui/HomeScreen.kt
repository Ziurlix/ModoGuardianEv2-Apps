package com.example.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.viewmodel.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: LoginViewModel,
    onCerrarSesion: () -> Unit
) {
    val rol = viewModel.rolActivo

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Panel de Control: $rol") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Bienvenido al Sistema Guardián",
                style = MaterialTheme.typography.headlineSmall
            )

            Text(
                text = "Rol actual autorizado: $rol",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.secondary
            )

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Funciones disponibles para tu nivel:",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // Contenido condicionado estrictamente al rol del usuario
                    when (rol) {
                        "Admin" -> {
                            Text("• Gestión total de usuarios y privilegios del sistema.")
                            Text("• Auditoría completa de logs de seguridad.")
                        }
                        "Supervisor" -> {
                            Text("• Monitoreo de indicadores y rutas operativas.")
                            Text("• Asignación de turnos a personal de terreno.")
                        }
                        else -> { // Operador
                            Text("• Ver rutas asignadas y reportar estado en terreno.")
                            Text("• Registro de incidentes en tiempo real.")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            OutlinedButton(
                onClick = {
                    viewModel.resetLoginState()
                    onCerrarSesion()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cerrar Sesión")
            }
        }
    }
}
