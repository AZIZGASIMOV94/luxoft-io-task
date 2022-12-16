package com.luxoft.csvrest.controller;

import com.luxoft.csvrest.dto.FileDTO;
import com.luxoft.csvrest.repository.FIleRepository;
import com.luxoft.csvrest.service.inter.FIleServiceInter;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final FIleServiceInter fIleServiceInter;

    @GetMapping(value = "/")
    public String get(){
        return "FILE IO API";
    }

    @PostMapping(value = "/save")
    public void uploadCSVFile(@RequestParam("file") @Valid MultipartFile file, Model model) {
        fIleServiceInter.saveCSVFIle(file, model);
    }

    @GetMapping(value = "/find/{id}")
    public FileDTO getById(@PathVariable("id") Integer id) {
        return fIleServiceInter.findById(id);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        fIleServiceInter.deleteById(id);
    }
}
