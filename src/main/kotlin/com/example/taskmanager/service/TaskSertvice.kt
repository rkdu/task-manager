package com.example.taskmanager.service

import com.example.taskmanager.model.Task
import com.example.taskmanager.repository.TaskRepository
import org.springframework.stereotype.Service

@Service
class TaskService(private val repository: TaskRepository) {

    fun getAllTasks(): List<Task> = repository.findAll()

    fun getTaskById(id: Long): Task = repository.findById(id).orElseThrow { 
        NoSuchElementException("Task mit ID $id nicht gefunden") 
    }

    fun createTask(task: Task): Task = repository.save(task)

    fun updateTask(id: Long, updatedTask: Task): Task {
        val existingTask = getTaskById(id)
        existingTask.title = updatedTask.title
        existingTask.description = updatedTask.description
        existingTask.completed = updatedTask.completed
        return repository.save(existingTask)
    }

    fun deleteTask(id: Long) = repository.deleteById(id)
}
