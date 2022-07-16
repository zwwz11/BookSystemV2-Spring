package com.spring.booksystem.controller;

import com.spring.booksystem.domain.User;
import com.spring.booksystem.domain.UserSex;
import com.spring.booksystem.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.info("field = {}, message = {}",fieldError.getField(), fieldError.getDefaultMessage());
            }
            return "/user/addUserForm";
        }

        log.info("유저 저장됨");
        log.info("user = {}", user.toString());
        User savedUser = userService.join(user);
        return "redirect:/user/users";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userService.findAllUser());
        return "/user/usersForm";
    }
}
