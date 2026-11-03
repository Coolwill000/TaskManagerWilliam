package com.william.taskmanager.controller;
import com.william.taskmanager.model.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
@Controller
public class TaskController {
    @GetMapping("/")
    public String home(Model model) {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1, "Finish Java exercises", "Complete part 5 of the tutorial",
                LocalDate.parse("2026-04-10"), false));
        tasks.add(new Task(2, "Prepare presentation", "Create slides for class",
                LocalDate.parse("2026-04-12"), true));
        tasks.add(new Task(3, "Review code", "Check the controller and HTML page",
                LocalDate.parse("2026-04-15"), false));

        model.addAttribute("tasks", tasks);
        return "index";
    }
}