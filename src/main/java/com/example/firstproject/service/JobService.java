package com.example.firstproject.service;

import com.example.firstproject.config.AppProperties;
import com.example.firstproject.dto.JobStatus;
import com.example.firstproject.entity.*;
import com.example.firstproject.exception.NotFoundException;
import com.example.firstproject.repository.*;
import com.example.firstproject.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private CreditService creditService;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImageService imageService;

    private AppProperties appProperties;

    public JobService(AppProperties appProperties) {
        this.appProperties = appProperties;
    }


    public Job addJob(String token,String jobDescription,long category_id,long address_id,String mode,long payment, String jobTime, String imageName, byte[] imageData) {
        Long userId = tokenProvider.extractUserId(token);
        User user = userRepository.findById(userId).orElseThrow(()->new NotFoundException("User id not match"));
        Address address = addressRepository.findById(address_id).orElseThrow(()->new NotFoundException("Address id not match"));
        Category category = categoryRepository.findById(category_id).orElseThrow(()->new NotFoundException("Category id not match"));

        Image imageId = imageService.saveImage(token,imageName,imageData);


        String inputString = jobTime;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(inputString, formatter);
        ZoneId zoneId = ZoneId.systemDefault();
        ZoneOffset zoneOffset = zoneId.getRules().getOffset(localDateTime);
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(localDateTime, zoneOffset, zoneId);


        long credit = appProperties.getCredits().getCreditForCreate();

        Optional<Object> user_Id = creditRepository.findByUserId(user.getId());

        if (user_Id.isPresent()) {
            Credit existCredit = (Credit) user_Id.get();
            if(existCredit.getCredit()>0) {
                creditService.updateMinusCredit(user.getId(),credit);
            }
            else {
                throw  new NotFoundException("You have not enough credit balance.");
            }
        }
        else {
            throw new NotFoundException("User id not match");
        }

        Job job = new Job();
        job.setJobDescription(jobDescription);
        job.setUser(user);
        job.setAddress(address);
        job.setCategory(category);
        job.setCreditForCreate(appProperties.getCredits().getCreditForCreate());
        job.setMode(mode);
        job.setJobTime(zonedDateTime);
        job.setPayment(payment);
        job.setStatus(JobStatus.open.toString());
        job.setModifiedTime(ZonedDateTime.now());
        job.setAddedTime(ZonedDateTime.now());
        job.setImageId(imageId.getId());

        return jobRepository.save(job);
    }

    public Job updateJob(long jobId, String token) {
        Long user_Id = tokenProvider.extractUserId(token);
        Optional<Job> job = jobRepository.findById(jobId);
        User user = userRepository.findById(user_Id).orElseThrow(()->new NotFoundException("User id not match"));

        if(job.isPresent()) {

            Job updateJobAssigner = job.get();
            if(user.getId() != updateJobAssigner.getUser().getId()){
                long credit = appProperties.getCredits().getCreditForAssigner();

                Optional<Object> userId = creditRepository.findByUserId(user.getId());

                if (userId.isPresent()) {
                    Credit existCredit = (Credit) userId.get();
                    if(existCredit.getCredit()>0) {
                        creditService.updateMinusCredit(user.getId(),credit);
                    }
                    else {
                        throw  new NotFoundException("you have not enough credit balance");
                    }
                }
                else {
                    throw new NotFoundException("User id not match");
                }
                updateJobAssigner.setAssigner(user);
                updateJobAssigner.setCreditForPick(appProperties.getCredits().getCreditForAssigner());
                updateJobAssigner.setAssignerTime(ZonedDateTime.now());
                updateJobAssigner.setStatus(JobStatus.working.toString());
                updateJobAssigner.setModifiedTime(ZonedDateTime.now());
                return jobRepository.save(updateJobAssigner);
            }
            else {
                throw new NotFoundException("Your are the job added no pick the job");
            }
        }
        else {
            throw new NotFoundException("Job id not match");
        }
    }

    public Job addClosingTime(long jobId,String token) {
        Long user_Id = tokenProvider.extractUserId(token);
        Optional<Job> job = jobRepository.findById(jobId);
        User user = userRepository.findById(user_Id).orElseThrow(()->new NotFoundException("User id not match"));

        if(job.isPresent()) {
            Job updateJobAssigner = job.get();

            if(updateJobAssigner.getAssigner() == user) {
                updateJobAssigner.setClosingTime(ZonedDateTime.now());
                updateJobAssigner.setModifiedTime(ZonedDateTime.now());
                updateJobAssigner.setStatus(JobStatus.close.toString());
            }
            else {
                throw new NotFoundException("User id not match");
            }
            return jobRepository.save(updateJobAssigner);
        }
        else {
            throw new NotFoundException("Job id not match");
        }
    }

}
