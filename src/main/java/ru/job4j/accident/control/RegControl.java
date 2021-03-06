package ru.job4j.accident.control;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.User;
import ru.job4j.accident.repository.AuthorityRepository;
import ru.job4j.accident.repository.UserRepository;

@Controller
public class RegControl {

    private final PasswordEncoder encoder;
    private final AuthorityRepository repository;
    private final UserRepository users;

    public RegControl(PasswordEncoder encoder,
                      AuthorityRepository repository,
                      UserRepository users) {
        this.encoder = encoder;
        this.repository = repository;
        this.users = users;
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user, Model model) {
        if (users.findByUsername(user.getUsername()) != null) {
            model.addAttribute("errorMassage", "Пользователь уже существует!");
            return "/reg";
        } else {
            user.setEnabled(true);
            user.setPassword(encoder.encode(user.getPassword()));
            user.setAuthority(repository.findByAuthority("ROLE_USER"));
            users.save(user);
        }
        return "redirect:/login";
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }
}
