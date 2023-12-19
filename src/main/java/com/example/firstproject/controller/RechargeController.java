package com.example.firstproject.controller;

import com.example.firstproject.dto.ApiResponse;
import com.example.firstproject.entity.Recharge;
import com.example.firstproject.service.RechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recharge")
public class RechargeController {

    @Autowired
    private RechargeService rechargeService;

    @PostMapping("/add_recharge")
    public ResponseEntity<?> uploadRecharge(@RequestHeader("Authorization") String token,@RequestParam long amount) {
        rechargeService.addRecharge(token,amount);
        return ResponseEntity.ok(new ApiResponse(true,"add Recharge success"));
    }

    @GetMapping("/get_recharge")
    public List<Recharge> getRecharge(@RequestHeader("Authorization") String token) {
        return rechargeService.getRecharge(token);
    }

}
