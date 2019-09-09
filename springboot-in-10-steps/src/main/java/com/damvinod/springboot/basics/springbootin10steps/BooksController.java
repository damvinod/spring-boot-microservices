package com.damvinod.springboot.basics.springbootin10steps;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksController {

  @GetMapping("/books")
    public List<Object> getAllBooks() {
    return Arrays.asList(new Book(1L, "Vinod", "Spring Boot"));
  }
}

