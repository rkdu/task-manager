# 📝 Task Manager API (Kotlin + Spring Boot)

Dies ist eine einfache RESTful API zur Verwaltung von Aufgaben.  
Die API basiert auf **Kotlin**, **Spring Boot** und verwendet eine **H2-Datenbank** als lokale Speicherung.

## 🚀 Installation & Ausführung

### 📌 Voraussetzungen
- **JDK 21+**
- **Gradle**
- **Spring Boot**

### 🔧 **Projekt klonen und starten**
1. **Repository klonen**
   ```bash
   git clone https://github.com/rkdu/task-manager.git
   cd task-manager
   ```
   
2. **Projekt starten**

```bash
./gradlew bootRun
```

3. **Prjekt Struktur**

```
task-manager/
├── src/main/kotlin/com/example/taskmanager
│   ├── controller/TaskController.kt
│   ├── model/Task.kt
│   ├── repository/TaskRepository.kt
│   ├── service/TaskService.kt
│   ├── TaskManagerApplication.kt
├── src/main/resources/application.yml
├── build.gradle.kts
└── README.md
```