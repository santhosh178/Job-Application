package com.example.firstproject.controller;

import com.example.firstproject.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/credit")
public class CreditController {

    @Autowired
    private CreditService creditService;

    @GetMapping("/get_credit")
    public Optional<?> getCredit(@RequestHeader("Authorization") String token) {
        return creditService.getCredit(token);
    }

}
