
### 1. **Abre tu proyecto en Android Studio**

Asegúrate de tener el proyecto cargado y listo para realizar cambios.

----------

### 2. **Cambiar el nombre del paquete en el código fuente**     amst.testfirebase.apellido    REEMPLAZAR POR SU APELLIDO

1.  **Navega al directorio del paquete**:
    
    -   Ve al panel de navegación del proyecto, selecciona la vista "Project" y expande `app > java`.
    -   Encontrarás las carpetas que representan el paquete actual (e.g., `com.example.myapp`).
2.  **Refactoriza el nombre del paquete**:
    
    -   Haz clic derecho en la carpeta principal del paquete.
    -   Selecciona **Refactor > Rename**.
    -   En la ventana emergente, elige **Rename Package**.
    -   Cambia el nombre del paquete según sea necesario. Por ejemplo, de `com.example.myapp` a `com.newexample.myapp`.
3.  **Refactorización automática**:
    
    -   Android Studio preguntará si deseas buscar referencias al paquete para actualizarlas automáticamente. Haz clic en **Refactor**.
4.  **Repita para los subpaquetes**: Si cambias múltiples niveles del nombre del paquete, repite este proceso para cada uno.
    

----------

### 3. **Actualizar el `AndroidManifest.xml`**

Ve al archivo `AndroidManifest.xml` y actualiza el atributo `package` en la raíz para reflejar el nuevo nombre del paquete:

`<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.newexample.myapp">` 

----------

### 4. **Actualizar el `build.gradle` (opcional)**

Si tienes referencias específicas al nombre del paquete en el archivo `build.gradle` (por ejemplo, en configuraciones de pruebas), actualízalas:

gradle

Copiar código

`applicationId "com.newexample.myapp"` 

----------

### 5. **Revisar el código restante**

Busca manualmente cualquier referencia al paquete antiguo en tu proyecto, como en las dependencias o configuraciones específicas, y actualízalas.

-   En Android Studio, puedes usar la función **Find in Path** (`Ctrl+Shift+F` o `Cmd+Shift+F` en macOS) para buscar el nombre del paquete antiguo y asegurarte de no dejar referencias sin actualizar.

----------

### 6. **Sincronizar el proyecto**

-   Haz clic en **File > Sync Project with Gradle Files**.
-   Limpia y reconstruye el proyecto: **Build > Clean Project** seguido de **Build > Rebuild Project**.
