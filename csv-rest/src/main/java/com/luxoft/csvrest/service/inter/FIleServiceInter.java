package com.luxoft.csvrest.service.inter;

import com.luxoft.csvrest.dto.FileDTO;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

public interface FIleServiceInter {

    void saveCSVFIle(MultipartFile file, Model model);

    FileDTO findById(Integer id);

    void deleteById(Integer id);

}
