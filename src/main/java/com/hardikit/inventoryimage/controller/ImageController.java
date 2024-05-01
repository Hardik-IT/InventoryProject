package com.hardikit.inventoryimage.controller;

import com.hardikit.inventoryimage.entity.Image;
import com.hardikit.inventoryimage.repository.ImageRepository;
import org.aspectj.bridge.IMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("api/image")
@CrossOrigin(origins = "http://localhost:3000")
public class ImageController {
    @Autowired
    private ImageRepository imageRepository;

    @PutMapping
    public ResponseEntity<Image> uploadImage(@RequestParam("image")MultipartFile image) throws IOException {
        Image img = new Image();
        img.setImage(image.getBytes());
        Image savedImage = imageRepository.save(img);
        return ResponseEntity.ok(savedImage);
    }
    @GetMapping("/{id}")
    public ResponseEntity<InputStreamResource> downloadImage(@PathVariable Long id){
        Image image = imageRepository.findById(id).orElseThrow();
        ByteArrayInputStream bis = new ByteArrayInputStream(image.getImage());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentDisposition(ContentDisposition.builder("inline").filename("image.jpg").build())   ;
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(bis));
    }

}
