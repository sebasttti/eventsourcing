# 🟩 Ejemplo practico de Event Sourcing

Aplicación base en **Spring Boot 3** que combina:

- **Thymeleaf** → para renderizar vistas HTML.
- **Jackson** → para leer y escribir datos en un archivo JSON público.
- **SQLite** → como base de datos embebida.

Permite conocer las bases del comportamiento del Patrón Event Sourcing.

---

## ⚙️ Tecnologías

- Java 17+
- Spring Boot 3.x
- Maven
- Thymeleaf
- Spring Data JPA
- SQLite (Driver Xerial)
- Jackson Databind
- Lombok (opcional)

---

## 🚀 Pasos para ejecutar el proyecto

1. **Clonar el repositorio**

2. **Abrir el proyecto en IntelliJ IDEA o VS Code**

3. **Verificar dependencias Maven**
   ```bash
   mvn clean install
   ```

4. **Ejecutar la aplicación**
   ```bash
   mvn spring-boot:run
   ```

5. **Abrir en el navegador**
   ```
   http://localhost:8080
   ```

---

## 🌐 Archivos públicos

Todo lo que se coloque en `src/main/resources/static/` será accesible públicamente desde el navegador.





