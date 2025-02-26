package com.example.taskmanager.controller

import com.example.taskmanager.model.Task
import com.example.taskmanager.service.TaskService
import io.mockk.every
import io.mockk.verify
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ninjasquad.springmockk.MockkBean
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put
import org.springframework.test.web.servlet.delete

@ExtendWith(SpringExtension::class)
@WebMvcTest(TaskController::class)
class TaskControllerTest(@Autowired private val mockMvc: MockMvc) {

    @MockkBean
    private lateinit var taskService: TaskService

    private val objectMapper = jacksonObjectMapper()

    @Test
    fun `should return all tasks`() {
        val tasks = listOf(
            Task(id = 1, title = "Buy groceries", description = "Milk, Bread, Cheese", completed = false),
            Task(id = 2, title = "Doctor Appointment", description = "Visit at 10 AM", completed = false)
        )

        every { taskService.getAllTasks() } returns tasks

        val response = mockMvc.get("/tasks")
            .andExpect { status { isOk() } }
            .andReturn()
            .response
            .contentAsString

        assertEquals(objectMapper.writeValueAsString(tasks), response)
        verify { taskService.getAllTasks() }
    }

    @Test
    fun `should create a new task`() {
        val newTask = Task(title = "Gym", description = "Workout at 6 PM", completed = false)
        val savedTask = newTask.copy(id = 3)

        every { taskService.createTask(newTask) } returns savedTask

        mockMvc.post("/tasks") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(newTask)
        }.andExpect {
            status { isCreated() }
            content { json(objectMapper.writeValueAsString(savedTask)) }
        }

        verify { taskService.createTask(any()) }
    }

    @Test
    fun `should get task by id`() {
        val task = Task(id = 1, title = "Buy groceries", description = "Milk, Bread, Cheese", completed = false)

        every { taskService.getTaskById(1) } returns task

        mockMvc.get("/tasks/1")
            .andExpect { status { isOk() } }
            .andExpect { content { json(objectMapper.writeValueAsString(task)) } }

        verify { taskService.getTaskById(1) }
    }

    @Test
    fun `should update a task`() {
        val updatedTask = Task(id = 1, title = "Buy groceries", description = "Updated list", completed = true)

        every { taskService.updateTask(1, any()) } returns updatedTask

        mockMvc.put("/tasks/1") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(updatedTask)
        }.andExpect {
            status { isOk() }
            content { json(objectMapper.writeValueAsString(updatedTask)) }
        }

        verify { taskService.updateTask(1, any()) }
    }

    @Test
    fun `should delete a task`() {
        every { taskService.deleteTask(1) } returns Unit

        mockMvc.delete("/tasks/1")
            .andExpect { status { isNoContent() } }

        verify { taskService.deleteTask(1) }
    }
}
