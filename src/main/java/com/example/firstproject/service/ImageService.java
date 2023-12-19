package com.example.firstproject.service;

import com.example.firstproject.entity.Image;
import com.example.firstproject.entity.User;
import com.example.firstproject.exception.NotFoundException;
import com.example.firstproject.repository.ImageRepository;
import com.example.firstproject.repository.UserRepository;
import com.example.firstproject.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    public Image saveImage(String token,String imageName, byte[] imageData) {
        Long userId = tokenProvider.extractUserId(token);
        userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User id not match"));
        if (imageRepository.existsByImageName(imageName)) {
            throw new NotFoundException("Image already uploaded.");
        }

        Image image = new Image();
        image.setImageName(imageName);
        image.setImageData(imageData);
        image.setAddedTime(ZonedDateTime.now());
        image.setModifiedTime(ZonedDateTime.now());

       return imageRepository.save(image);

    }

    public Optional<Image> getImageById(String token,long imageId) {
        Long userId = tokenProvider.extractUserId(token);
        userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User id not match"));
        return imageRepository.findById(imageId);
    }

    public Image updateImage(String token,MultipartFile file) throws IOException {
        Long userId = tokenProvider.extractUserId(token);
        User user =  userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User id not match"));
        Image existingImage = imageRepository.findById(user.getImageId()).orElseThrow(() -> new NotFoundException("image id not match"));

        existingImage.setImageData(file.getBytes());
        existingImage.setImageName(file.getOriginalFilename());
        existingImage.setModifiedTime(ZonedDateTime.now());

        return imageRepository.save(existingImage);
    }

    public void deleteImage(String token,long imageId) {
        Long userId = tokenProvider.extractUserId(token);
        userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User id not match"));
        Image image = imageRepository.findById(imageId).orElseThrow(() -> new NotFoundException("image id not match"));
        imageRepository.deleteById(image.getId());
    }

}
