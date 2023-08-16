package com.podkor.ukrdashboard.controller;

import com.podkor.ukrdashboard.dto.Category;
import com.podkor.ukrdashboard.dto.LoginDetailsDto;
import com.podkor.ukrdashboard.dto.User;
import com.podkor.ukrdashboard.service.UserService;
import com.podkor.ukrdashboard.service.feed.FeedService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.token.Token;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@RestController
@RequestMapping("/app/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/signin")
    public ResponseEntity<String> login(@RequestBody LoginDetailsDto loginDetails) {
        String token = userService.login(loginDetails.getUsername(), loginDetails.getPassword());
        return new ResponseEntity(token, HttpStatus.OK);
    }

    // Login form
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Login form with error
    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
}