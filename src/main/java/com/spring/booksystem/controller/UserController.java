package com.spring.booksystem.controller;

import com.spring.booksystem.common.Message;
import com.spring.booksystem.domain.user.*;
import com.spring.booksystem.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.HttpResource;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpResponse;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @ModelAttribute("userSexes")
    private UserSex[] userSexes() {
        return UserSex.values();
    }

    @ModelAttribute("userAuths")
    private UserAuth[] userAuths() {
        return UserAuth.values();
    }

    @GetMapping("/register")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("alertData", null);
        return "user/addUserForm";
    }

    @PostMapping("/register")
    public String addUser(@Validated @ModelAttribute("user") UserInsertDTO userInsertDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/user/addUserForm";
        }

        if (userService.findUser(userInsertDTO.getId()) != null) {
            bindingResult.rejectValue("id", "아이디 중복", "이미 등록된 아이디입니다.");
            return "/user/addUserForm";
        }

        User user = new User();
        user.setId(userInsertDTO.getId());
        user.setPassword(userInsertDTO.getPassword());
        user.setName(userInsertDTO.getName());
        user.setAge(userInsertDTO.getAge());
        user.setPhone(userInsertDTO.getPhone());
        user.setEmail(userInsertDTO.getEmail());
        user.setSex(userInsertDTO.getSex());
        user.setAuth(UserAuth.GENERAL); // 등록폼에서는 권한 지정 불가 초기값인 일반사용자로 지정
        userService.join(user);
        return "redirect:/user/users";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userService.findAllUser());
        return "/user/usersForm";
    }

    @GetMapping("/{userId}/edit")
    public String editUserForm(@PathVariable String userId, Model model) {
        model.addAttribute("user", userService.findUser(userId));
        return "/user/editUserForm";
    }

    @PostMapping("/{userId}/edit")
    public String editUser(@Validated @ModelAttribute("user") UserUpdateDTO userUpdateDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/user/editUserForm";
        }

        User updatedUser = new User();
        updatedUser.setId(userUpdateDTO.getId());
        updatedUser.setPassword(userUpdateDTO.getPassword());
        updatedUser.setName(userUpdateDTO.getName());
        updatedUser.setAge(userUpdateDTO.getAge());
        updatedUser.setPhone(userUpdateDTO.getPhone());
        updatedUser.setEmail(userUpdateDTO.getEmail());
        updatedUser.setSex(userUpdateDTO.getSex());
        updatedUser.setAuth(userUpdateDTO.getAuth());
        userService.editUser(userUpdateDTO.getId(), updatedUser);

        return "redirect:/user/users";
    }

    @GetMapping("/{userId}/delete")
    public String userDelete(@PathVariable String userId) {
        userService.deleteUser(userId);
        return "redirect:/user/users";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "/user/loginForm";
    }

    @PostMapping("/login")
    public String login(HttpSession session, @ModelAttribute User user) {

        if ("admin".equals(user.getId()) && "1234".equals(user.getPassword())) {
            session.setAttribute("SID", user.getId());
            session.setAttribute("SNAME", user.getName());
            return "redirect:/";
        }

        return "/user/loginForm";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        try {
            session.invalidate();
            return "redirect:/";
        }
        catch (IllegalStateException exception) {
            return "redirect:/";
        }
    }
}
