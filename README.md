## Versiones

<details>
<summary><b>v0.1.1-alpha </b></summary>

Se avanzó en el módulo base de autenticación con los siguientes cambios:

* Redirección directa a la pantalla de Login al iniciar la aplicación.
* Creación de credenciales locales de prueba (`admin@test.com` / `123456`).
* Validación de usuario: mensaje de advertencia si el correo no existe en el sistema para contactar a jefatura/RRHH.
* Control de seguridad: límite de 3 intentos fallidos de contraseña antes de bloquear el acceso.
* Mensaje de confirmación de acceso al autenticar correctamente (pendiente integración completa del flujo).
</details>

<details>
<summary><b> v0.2.0-alpha </b></summary>

Se integró el flujo de navegación y la vista principal de operaciones con los siguientes cambios:

* Implementación de navegación reactiva mediante `NavHost` y rutas (`"login"` y `"home"`).
* Transición automática desde `LoginScreen` hacia `HomeScreen` al validar las credenciales correctamente.
* Creación de `HomeScreen` con el panel base para empleados en terreno y visualización de tareas asignadas.
* Incorporación de botón para cerrar sesión, facilitando el reinicio del flujo para pruebas y demostraciones en vivo.

</details>
