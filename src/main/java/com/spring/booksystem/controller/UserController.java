package com.spring.booksystem.controller;

import com.spring.booksystem.domain.User;
import com.spring.booksystem.domain.UserSex;
import com.spring.booksystem.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @ModelAttribute("userSexes")
    private UserSex[] userSexes(){
        return UserSex.values();
    }

    @GetMapping("/register")
    public String addUserForm(Model model) {
        log.info("addUserForm 호출");
        model.addAttribute("user", new User());
        return "user/addUserForm";
    }

    @PostMapping("/register")
    public String addUser(@Validated @ModelAttribute User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("에러 있음");
            return "user/addUserForm";
        }

        log.info("유저 저장됨");
        User savedUser = userService.join(user);
        return "redirect:localhost:8080/index.html";
    }
}
