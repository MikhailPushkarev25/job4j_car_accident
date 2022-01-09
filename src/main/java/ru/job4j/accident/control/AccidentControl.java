package ru.job4j.accident.control;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccidentControl {

    private final AccidentService service;

    public AccidentControl(AccidentService service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", service.findAllAccidentType());
        model.addAttribute("rules", service.findAllRule());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        service.saveAccident(accident, ids);
        return "redirect:/";
    }

    @GetMapping
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("rules", service.findAllRule());
        model.addAttribute("types", service.findAllAccidentType());
        model.addAttribute("accident", service.findById(id));
        return "accident/update";
    }
}
