package com.example.chartog.Service;

import com.example.chartog.entity.Fragment;
import com.example.chartog.entity.Papyrus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PapyrusServiceTest {

    static MultipartFile multipartFile = Mockito.mock(MultipartFile.class);

    @BeforeAll
    public static void setMultipartFile() throws IOException {
        when(multipartFile.getBytes()).thenReturn(new byte[5]);
        when(multipartFile.getOriginalFilename()).thenReturn("null");
    }


    @Test
    void checkIdTest() throws IOException {
        PapyrusService papyrusService = new PapyrusService();
        FragmentStorage fragmentStorage = new FragmentStorage();
        fragmentStorage.setFragment(1, 2, 3, 4, 5, multipartFile, "");
        papyrusService.fragmentStorage = fragmentStorage;
        assertTrue(papyrusService.checkId(1));
    }

    @Test
    void checkIdNegativeTest() throws IOException {
        PapyrusService papyrusService = new PapyrusService();
        FragmentStorage fragmentStorage = new FragmentStorage();
        fragmentStorage.setFragment(1, 2, 3, 4, 5, multipartFile, "");
        papyrusService.fragmentStorage = fragmentStorage;
        assertFalse(papyrusService.checkId(2));
    }
    @Test
    void createFiguraTest(){
        PapyrusStorage papyrusStorage = new PapyrusStorage();
        papyrusStorage.setSize(100,100);
        Papyrus papyrus1 = new Papyrus(100,100);
        assertEquals(papyrus1,papyrusStorage.getPapyrus(1));
        //Для этого метода переопределил в классе Papyrus метод equals
    }


    @Test
    void createFragmentTest() throws IOException {
        FragmentStorage fragmentStorage = new FragmentStorage();
        File fileWithFragment = fragmentStorage.createFile(multipartFile,"");
        fragmentStorage.setFragment(1, 10,10, 100,100, multipartFile,"");
        Fragment fragment = new Fragment(10,10,100,100,fileWithFragment.getPath());
        assertEquals(fragment, fragmentStorage.getFragment(1));
      //Для этого метода переопределил в классе FragmentStorage метод equals

    }

    @Test
    void creatPathOfBMPTest() throws IOException {


        PapyrusStorage papyrusStorage = new PapyrusStorage();
        PapyrusService papyrusService = new PapyrusService();
        FragmentStorage fragmentStorage = new FragmentStorage();
        CreatorBMP creatorBMP = new CreatorBMP();
        papyrusService.papyrusStorage = papyrusStorage;
        papyrusService.fragmentStorage = fragmentStorage;
        papyrusService.creatorBMP = creatorBMP;

//        Papyrus papyrus = new Papyrus(100,100);
//        Fragment fragment = new Fragment(10,10,100,100,System.getProperty("user.dir"));
        papyrusService.createFigura(100,100);
        papyrusService.createFragment(1,10,10,100,100,multipartFile);

//        fragment.setOpenFragment(10,10,10,10);
//        List<OpenFragment> openFragments = fragment.getOpenFragment();
//        File file = creatorBMP.createFile(papyrus,fragment,openFragments,"null");
//        Path pathActual = Paths.get(file.getAbsolutePath());
        Path pathActual = Mockito.mock(Path.class);
        when(pathActual.getFileName()).thenReturn(Path.of(((System.getProperty("user.dir") + "\\nulltmpFile2.bmp"))));
        Path pathExpected = papyrusService.creatPathOfBMP(1,10,10,10,10);
        assertEquals(pathExpected,pathActual.getFileName());
    }
    @Test
    void deleteImageTest() throws IOException {
        PapyrusService papyrusService = new PapyrusService();
        FragmentStorage fragmentStorage = new FragmentStorage();
        papyrusService.papyrusStorage = new PapyrusStorage();
        fragmentStorage.setFragment(1, 2, 3, 4, 5, multipartFile, "");
        papyrusService.fragmentStorage = fragmentStorage;
        papyrusService.createFigura(100,100);
        papyrusService.deleteImage(1);
        assertFalse(papyrusService.checkId(1));
    }

    @Test
    void getIdTest() throws IOException {
        PapyrusService papyrusService = new PapyrusService();
        FragmentStorage fragmentStorage = new FragmentStorage();
        papyrusService.papyrusStorage = new PapyrusStorage();
        fragmentStorage.setFragment(1, 2, 3, 4, 5, multipartFile, "");
        papyrusService.fragmentStorage = fragmentStorage;
        papyrusService.createFigura(100,100);
        assertTrue(papyrusService.checkId(1));
    }
}