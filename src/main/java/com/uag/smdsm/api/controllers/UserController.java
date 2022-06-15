package com.uag.smdsm.api.controllers;

import com.uag.smdsm.api.models.UserResponse;
import com.uag.smdsm.api.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    ResponseEntity<List<UserResponse>> getAll(){
        var users = userService.findAll();
        log.info("##### GetAll users:");
        users.forEach(user -> log.info("- " + user));
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<UserResponse> getById(@PathVariable Long id){
        var user = userService.findById(id);
        log.info("##### GetById user:");
        log.info("- " + user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/count-disease") // ?disease={}&gender={}
    ResponseEntity<Integer> countByDisease(
            @RequestParam(defaultValue = "diabetes", required = false) String disease,
            @RequestParam(defaultValue = "male", required = false) String gender
    ){
        var totalUsers = userService.countByGenderAndDisease(gender, disease);
        log.info("##### CountByDisease users:");
        log.info("- " + totalUsers);
        return new ResponseEntity<>(totalUsers, HttpStatus.OK);
    }

    @GetMapping("/count-risk-factor") // ?riskFactor={}&gender={}
    ResponseEntity<Integer> countByRiskFactor(
            @RequestParam(defaultValue = "hdl", required = false) String riskFactor,
            @RequestParam(defaultValue = "female", required = false) String gender
    ){
        var totalUsers = userService.countByGenderAndRiskFactor(gender, riskFactor);
        log.info("##### CountByRiskFactor users:");
        log.info("- " + totalUsers);
        return new ResponseEntity<>(totalUsers, HttpStatus.OK);
    }

    @GetMapping("/count-date-range") // ?startDate={}&endDate={}&gender={}
    ResponseEntity<Integer> countByDateRange(
            @RequestParam(defaultValue = "1900-01-01", required = false) String startDate,
            @RequestParam(defaultValue = "2099-12-31", required = false) String endDate,
            @RequestParam(defaultValue = "female", required = false) String gender
    ){
        var totalUsers = userService.countByGenderAndBirthdayBetween(gender, startDate, endDate);
        log.info("##### CountByDateRange users:");
        log.info("- " + totalUsers);
        return new ResponseEntity<>(totalUsers, HttpStatus.OK);
    }
}
