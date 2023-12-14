package com.example.firstproject.service;

import com.example.firstproject.config.AppProperties;
import com.example.firstproject.entity.Credit;
import com.example.firstproject.entity.User;
import com.example.firstproject.exception.NotFoundException;
import com.example.firstproject.repository.CreditRepository;
import com.example.firstproject.repository.UserRepository;
import com.example.firstproject.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Optional;

@Service
public class CreditService {

    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenProvider tokenProvider;

    private AppProperties appProperties;

    public CreditService(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public Credit addCredit(long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User id not match"));

        Credit credit = new Credit();
        credit.setUser(user);
        credit.setAddedTime(ZonedDateTime.now());
        credit.setModifiedTime(ZonedDateTime.now());
        credit.setCredit(appProperties.getCredits().getCredit());

        return creditRepository.save(credit);
    }

    public Credit updateMinusCredit(long userId, long credit) {
        Credit existingcredit = (Credit) creditRepository.findByUserId(userId).orElseThrow(() -> new NotFoundException("User id not match"));

        existingcredit.setUser(existingcredit.getUser());
        existingcredit.setModifiedTime(ZonedDateTime.now());
        existingcredit.setCredit(existingcredit.getCredit()- credit);

        return creditRepository.save(existingcredit);
    }

    public Optional<?> getCredit(String token) {

        Long userId = tokenProvider.extractUserId(token);
        Credit credit = (Credit) creditRepository.findByUserId(userId).orElseThrow(() -> new NotFoundException("User id not match"));
        return Optional.ofNullable(credit);
    }

    public Credit updatePlusCredit(long userId,long credit) {

        Credit existingcredit = (Credit) creditRepository.findByUserId(userId).orElseThrow(() -> new NotFoundException("User id not match"));

        existingcredit.setUser(existingcredit.getUser());
        existingcredit.setModifiedTime(ZonedDateTime.now());
        existingcredit.setCredit(existingcredit.getCredit()+ credit);

        return creditRepository.save(existingcredit);
    }

}
