package com.example.firstproject.service;

import com.example.firstproject.entity.Recharge;
import com.example.firstproject.entity.User;
import com.example.firstproject.exception.NotFoundException;
import com.example.firstproject.repository.RechargeRepository;
import com.example.firstproject.repository.UserRepository;
import com.example.firstproject.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class RechargeService {

    @Autowired
    private RechargeRepository rechargeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CreditService creditService;

    @Autowired
    private TokenProvider tokenProvider;

    public Recharge addRecharge(String token,long amount) {
        Long userId = tokenProvider.extractUserId(token);
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User id not match"));
        creditService.updatePlusCredit(userId, amount);

        Recharge recharge = new Recharge();
        recharge.setUser(user);
        recharge.setAmount(amount);
        recharge.setAddedTime(ZonedDateTime.now());
        recharge.setModifiedTime(ZonedDateTime.now());

        return rechargeRepository.save(recharge);
    }

    public List<Recharge> getRecharge(String token) {
        Long userId = tokenProvider.extractUserId(token);
        return rechargeRepository.findByUserId(userId);
    }

}
