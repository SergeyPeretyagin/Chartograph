package com.example.chartog.Service;

import com.example.chartog.entity.Fragment;
import com.example.chartog.entity.OpenFragment;
import com.example.chartog.entity.Papyrus;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreatorBMPTest {

    @Test
    void createFileTest() throws IOException {
        CreatorBMP creatorBMP = new CreatorBMP();
        Papyrus papyrus = new Papyrus(100,100);
        Fragment fragment = new Fragment();
        fragment.setPath(System.getProperty("user.dir")+ "\\src\\test\\resources\\test.bmp");//Здесь нужно указать путь к файлу bmp в папке test/resources
        File file = creatorBMP.createFile(papyrus, fragment, List.of(new OpenFragment(1, 2, 50, 50)), "");
        assertEquals("tmpFile2.bmp", file.getName());
    }
}