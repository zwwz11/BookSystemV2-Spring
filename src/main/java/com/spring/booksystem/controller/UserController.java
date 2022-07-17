package com.spring.booksystem.controller;

import com.spring.booksystem.domain.User;
import com.spring.booksystem.domain.UserSex;
import com.spring.booksystem.domain.UserUpdateDTO;
import com.spring.booksystem.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.info("field = {}, message = {}",fieldError.getField(), fieldError.getDefaultMessage());
            }
            return "/user/addUserForm";
        }

        User savedUser = userService.join(user);
        return "redirect:/user/users";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userService.findAllUser());
        return "/user/usersForm";
    }

    @GetMapping("/{userId}/edit")
    public String editUserForm(@PathVariable Long userId, Model model){
        model.addAttribute("user", userService.findUser(userId));
        return "/user/editUserForm";
    }

    @PostMapping("/{userId}/edit")
    public String editUser(@PathVariable Long userId, @Validated @ModelAttribute("user") UserUpdateDTO userUpdateDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("edit 에러있음");
            return "/user/editUserForm";
        }

        log.info("edit 에러없음");
        User updatedUser = new User();
        updatedUser.setId(userUpdateDTO.getId());
        updatedUser.setName(userUpdateDTO.getName());
        updatedUser.setAge(userUpdateDTO.getAge());
        updatedUser.setPhone(userUpdateDTO.getPhone());
        updatedUser.setEmail(userUpdateDTO.getEmail());
        userService.editUser(userId, updatedUser);

        return "redirect:/user/users";
    }
}
