package com.cipriano.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/pdf")
public class PDFController {

    @Autowired
    KafkaTemplate<String, byte[]> kafkaTemplate;

    @PostMapping
    ResponseEntity<Void> processPDF(@RequestParam("file") MultipartFile file) throws IOException {

        System.out.println("Processando PDF" +  file.getOriginalFilename());

        assert file.getOriginalFilename() != null;
        kafkaTemplate.send("PDF_READER", file.getOriginalFilename(), file.getBytes());
        return ResponseEntity.ok().build();
    }

}
