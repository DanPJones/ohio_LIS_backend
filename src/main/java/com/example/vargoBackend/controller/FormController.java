package com.example.vargoBackend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vargoBackend.model.Form;
import com.example.vargoBackend.repo.FormRepository;

@RestController
@RequestMapping("/api")
public class FormController {

    private final FormRepository repo;

    public FormController(FormRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/forms")
    public List<Form> getForms() {
        return repo.findAllForms();
    }

    @PostMapping("/forms")
    public String postForm(@RequestBody Form form) {
         repo.submitForm(form);
         return "Form submitted successfully";
    }
}