package com.example.chartog.controllers;

import com.example.chartog.Service.PapyrusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.RasterFormatException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;


@Controller
@RequestMapping("/chartas")
public class MyController {

    @Autowired
    PapyrusService papyrusService;


    @PostMapping("/")
    public ResponseEntity createChart(@RequestParam("width") int width,
                                      @RequestParam("height") int height) {
        if (width>=20000 || height>=50000){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(HttpStatus.BAD_REQUEST);
        }
        papyrusService.createFigura(width,height);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(HttpStatus.CREATED+" id = "+ papyrusService.getId());
    }

    @PostMapping("/{id}")
    public ResponseEntity saveFragment(@PathVariable("id") int id,
                                       @RequestParam("x") int x,
                                       @RequestParam("y") int y,
                                       @RequestParam("width") int width,
                                       @RequestParam("height") int height,
                                       @RequestParam("file")MultipartFile file)
            throws IOException {
        if (width>=5000 || height>=5000){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(HttpStatus.BAD_REQUEST);
        }
        papyrusService.createFragment(id, x, y, width, height, file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(HttpStatus.OK);
    }




    @GetMapping("/{id}")
    public ResponseEntity createBMP (@PathVariable("id") int id,
                                     @RequestParam("x") int x,
                                     @RequestParam("y") int y,
                                     @RequestParam("width") int width,
                                     @RequestParam("height") int height)
            throws IOException {
        if (!papyrusService.checkId(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(HttpStatus.NOT_FOUND);
        }
        Path path;
        try {
            path = papyrusService.creatPathOfBMP(id, x,y,width,height);
        }catch (RasterFormatException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(e.getMessage()+" Incorrectly entered parameter");
        }
        return ResponseEntity.status(HttpStatus.OK).
                contentType(MediaType.valueOf(Files.probeContentType(path)))
                .body(Files.readAllBytes(path));

    }



    @DeleteMapping("/{id}")
    public ResponseEntity deleteCanvas(@PathVariable ("id") int id){
        if (!papyrusService.checkId(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(HttpStatus.NOT_FOUND);
        }
        papyrusService.deleteImage(id);
        return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK);
    }
}
