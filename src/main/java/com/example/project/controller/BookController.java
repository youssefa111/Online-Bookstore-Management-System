package com.example.project.controller;

import com.example.core.base.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${baseUrl/books}")
@RequiredArgsConstructor

@Validated
public class BookController {

//    @PostMapping
//    public ResponseEntity<BaseResponse<?>> addBook(@Valid @ResponseBody ){
//        return ResponseEntity.status(HttpStatus.CREATED).body(null);
//    }

}
