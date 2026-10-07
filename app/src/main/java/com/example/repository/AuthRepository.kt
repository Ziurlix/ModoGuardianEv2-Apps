package com.example.repository

class AuthRepository {
    // Admin
    private val correoAdmin = "admin@guardian.test"
    private val claveAdmin = "123456"

    // Supervisor
    private val correoSupervisor = "supervisor@guardian.test"
    private val claveClaveSupervisor = "123456"

    // Operador

    private val correoOperador = "operador@guardian.test"
    private val claveOperador = "123456"

    fun userExists(correo: String): Boolean {
        val emailTrim = correo.trim()
        return emailTrim.equals(correoAdmin, ignoreCase = true) ||
                emailTrim.equals(correoSupervisor, ignoreCase = true) ||
                emailTrim.equals(correoOperador, ignoreCase = true)
    }

    fun validarCredenciales(correo: String, clave: String): Boolean {
        val emailTrim = correo.trim()
        return when {
            emailTrim.equals(correoAdmin, ignoreCase = true) -> clave == claveAdmin
            emailTrim.equals(correoSupervisor, ignoreCase = true) -> clave == claveClaveSupervisor
            emailTrim.equals(correoOperador, ignoreCase = true) -> clave == claveOperador
            else -> false
        }
    }

    // Retorna el rol según el correo
    fun obtenerRol(correo: String): String {
        return when {
            correo.trim().equals(correoAdmin, ignoreCase = true) -> "Admin"
            correo.trim().equals(correoSupervisor, ignoreCase = true) -> "Supervisor"
            else -> "Operador"
        }
    }
}
