package com.example.taskmanager.controller

import com.example.taskmanager.model.Task
import com.example.taskmanager.service.TaskService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tasks")
class TaskController(private val service: TaskService) {

    @GetMapping
    fun getAllTasks(): List<Task> = service.getAllTasks()

    @GetMapping("/{id}")
    fun getTaskById(@PathVariable id: Long): Task = service.getTaskById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createTask(@RequestBody task: Task): Task = service.createTask(task)

    @PutMapping("/{id}")
    fun updateTask(@PathVariable id: Long, @RequestBody task: Task): Task = service.updateTask(id, task)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTask(@PathVariable id: Long) = service.deleteTask(id)
}
