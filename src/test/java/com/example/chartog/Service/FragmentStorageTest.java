package com.example.chartog.Service;

import com.example.chartog.entity.Fragment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class FragmentStorageTest {
    static MultipartFile multipartFile = Mockito.mock(MultipartFile.class);

    @BeforeAll
    public static void setMultipartFile() throws IOException {
        when(multipartFile.getBytes()).thenReturn(new byte[5]);
        when(multipartFile.getOriginalFilename()).thenReturn("null");
        /**Указываю "null", потому что в классе PapyrusService методы createFragment()
         и creatPathOfBMP() принимать конкретный путь(path).
         * **/
    }


    @Test
    void setFragmentTest() throws IOException {
        Fragment fragment = new Fragment(10,10,100,100,"null");
        FragmentStorage fragmentStorage = new FragmentStorage();
        fragmentStorage.setFragment(1,10,10,100,100, multipartFile,"");
        assertEquals(fragmentStorage.getFragment(1),fragment);
    }
    @Test
    void createFailTest() throws IOException {
        FragmentStorage fragmentStorage = new FragmentStorage();
        File fileActual = Mockito.mock(File.class);
        when(fileActual.getAbsolutePath()).thenReturn(System.getProperty("user.dir")+"\\null");
        File fileExpected = fragmentStorage.createFile(multipartFile,"");
        assertEquals(fileExpected.getAbsolutePath(),fileActual.getAbsolutePath());
    }
    @Test
    void checkIdFromMapTest() throws IOException {
        FragmentStorage fragmentStorage = new FragmentStorage();
        fragmentStorage.setFragment(1,10,10,100,100,multipartFile,"");
        assertTrue(fragmentStorage.checkIdFromMap(1));
    }
    @Test
    void removeFragmentTest() throws IOException {
        FragmentStorage fragmentStorage = new FragmentStorage();
        fragmentStorage.setFragment(1,10,10,100,100,multipartFile,"");
        assertEquals("delete", fragmentStorage.removeFragment(1));
    }
}
