package com.example.firstproject.mapper;

import com.example.firstproject.dto.JobDTO;
import com.example.firstproject.entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AddressMapper addressMapper;

    public JobDTO toJobDTO(Job job) {
       JobDTO jobDTO = new JobDTO();
       jobDTO.setId(job.getId());
       jobDTO.setJobDescription(job.getJobDescription());
       jobDTO.setJobTime(job.getJobTime());
       jobDTO.setMode(job.getMode());
       jobDTO.setPayment(job.getPayment());
       jobDTO.setStatus(job.getStatus());
       jobDTO.setUser(userMapper.toUserDTO(job.getUser()));
       jobDTO.setAddress(addressMapper.toAddressDTO(job.getAddress()));
       jobDTO.setCategory(job.getCategory());
       jobDTO.setAddedTime(job.getAddedTime());
       jobDTO.setAssignedTime(job.getAssignerTime());
       jobDTO.setModifiedTime(job.getModifiedTime());
       jobDTO.setClosingTime(job.getClosingTime());
       if(job.getImageId()== 0L){
           jobDTO.setImageId(0);
       }
       else {
       jobDTO.setImageId(job.getImageId());
       }
       if(job.getAssigner() == null){
           jobDTO.setAssigner(null);
       }
       else {
           jobDTO.setAssigner(userMapper.toUserDTO(job.getAssigner()));
       }
        return jobDTO;
    }

}
