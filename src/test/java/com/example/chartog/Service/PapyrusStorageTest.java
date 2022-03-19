package com.example.chartog.Service;

import com.example.chartog.entity.Papyrus;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PapyrusStorageTest {

    static Map<Integer,Papyrus> papyrusMap=new HashMap<>();

    @Test
    void setSizeTest(){
        PapyrusStorage papyrusStorage = new PapyrusStorage();
        papyrusStorage.setSize(100,100);
        Papyrus papyrus = new Papyrus(100,100);
        assertEquals(papyrusStorage.getPapyrus(1),papyrus);
    }

    @Test
    void getPapyrusTest(){
        PapyrusStorage papyrusStorage = new PapyrusStorage();
        papyrusStorage.setSize(100,100);
        Papyrus papyrus = new Papyrus(100, 100);
        papyrusMap.put(1,papyrus);
        assertEquals(papyrusStorage.getPapyrus(1),papyrusMap.get(1));
    }

}
