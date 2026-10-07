package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.repository.AuthRepository

class LoginViewModel : ViewModel() {
    private val authRepository = AuthRepository()

    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    var intentosFallidos by mutableStateOf(0)
        private set

    var isBlocked by mutableStateOf(false)
        private set

    var msgError by mutableStateOf<String?>(null)
        private set

    var loginSuccess by mutableStateOf(false)
        private set

    // Guarda el rol del usuario conectado para que las pantallas lo consulten
    var rolActivo by mutableStateOf("")
        private set

    fun onEmailChange(newValue: String) {
        email = newValue
        msgError = null // Limpia error al escribir
    }

    fun onPasswordChange(newValue: String) {
        password = newValue
        msgError = null
    }

    fun isLoginEnabled(): Boolean {
        return email.isNotBlank() && password.isNotBlank() && !isBlocked
    }

    fun iniciarSesion() {
        if (isBlocked) return

        // Verificar si usuario existe
        if (!authRepository.userExists(email)) {
            msgError = "Correo no existe. Contacta a tu supervisor o a RRHH."
            return
        }

        // Validar credenciales completas
        if (authRepository.validarCredenciales(email, password)) {
            msgError = null
            rolActivo = authRepository.obtenerRol(email)
            loginSuccess = true
        } else {
            intentosFallidos++
            if (intentosFallidos >= 3) {
                isBlocked = true
                msgError = "Aplicación bloqueada por exceso de intentos fallidos."
            } else {
                msgError = "Contraseña incorrecta. Intentos restantes: ${3 - intentosFallidos}"
            }
        }
    }

    fun resetLoginState() {
        email = ""
        password = ""
        loginSuccess = false
        rolActivo = ""
    }
}
