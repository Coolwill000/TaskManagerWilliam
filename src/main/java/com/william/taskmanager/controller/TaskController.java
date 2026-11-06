package com.william.taskmanager.controller;

import com.william.taskmanager.model.Task;
import com.william.taskmanager.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String showTaskList(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "index";
    }

    @GetMapping("/addTask")
    public String showAddTaskPage(Model model) {
        model.addAttribute("task", new Task());
        return "add-task";
    }

    @PostMapping("/addTask")
    public String addTask(@Valid @ModelAttribute Task task, BindingResult result) {
        if (result.hasErrors()) {
            return "add-task";
        }
        taskService.addTask(task);
        return "redirect:/";
    }

    @GetMapping("/editTask/{id}")
    public String showEditTaskPage(@PathVariable int id, Model model) {
        Task task = taskService.getTaskById(id);

        if (task != null) {
            model.addAttribute("task", task);
            return "edit-task";
        }

        return "redirect:/";
    }

    @PostMapping("/updateTask")
    public String updateTask(@Valid @ModelAttribute Task task, BindingResult result) {
        if (result.hasErrors()) {
            return "edit-task";
        }

        taskService.updateTask(task);
        return "redirect:/";
    }

    @GetMapping("/deleteTask/{id}")
    public String deleteTask(@PathVariable int id) {
        taskService.deleteTask(id);
        return "redirect:/";
    }
}