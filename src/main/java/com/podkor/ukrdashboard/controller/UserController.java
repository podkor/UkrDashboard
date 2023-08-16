package com.podkor.ukrdashboard.controller;

import com.podkor.ukrdashboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    @PostMapping(value = "/signin")
//    public ResponseEntity<String> login(@RequestBody LoginDetailsDto loginDetails) {
//        String token = userService.login(loginDetails.getUsername(), loginDetails.getPassword());
//        return new ResponseEntity(token, HttpStatus.OK);
//    }

}