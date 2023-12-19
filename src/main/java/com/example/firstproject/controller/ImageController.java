package com.example.firstproject.controller;

import com.example.firstproject.dto.ApiResponse;
import com.example.firstproject.entity.Image;
import com.example.firstproject.exception.NotFoundException;
import com.example.firstproject.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/images")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @PostMapping("/add_image")
    public ResponseEntity<String> uploadImage(@RequestHeader("Authorization") String token,@RequestParam("file") MultipartFile file) {
        try {
            imageService.saveImage(token,file.getOriginalFilename(), file.getBytes());
            return ResponseEntity.ok("Image uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
        }
    }

    @GetMapping("/get_image")
    public ResponseEntity<byte[]> getImage(@RequestHeader("Authorization") String token,@RequestParam long imageId) {
        Optional<Image> optionalImage = imageService.getImageById(token,imageId);

        if (optionalImage.isPresent()) {
            Image image = optionalImage.get();
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image.getImageData());
        } else {
            throw new NotFoundException("image id not match");
        }
    }

    @PostMapping("/update_image")
    public ResponseEntity<?> updateStudent(@RequestHeader("Authorization") String token,@RequestBody MultipartFile file) throws IOException {
        imageService.updateImage(token, file);
        return ResponseEntity.ok(new ApiResponse(true,"image updated success"));
    }

    @GetMapping("/remove_image")
    public ResponseEntity<?> deleteStudent(@RequestHeader("Authorization") String token,@RequestParam long imageId) {
        imageService.deleteImage(token,imageId);
        return ResponseEntity.ok(new ApiResponse(true,"image removed success"));
    }

}
