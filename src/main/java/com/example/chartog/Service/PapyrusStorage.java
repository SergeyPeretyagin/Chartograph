package com.example.chartog.Service;

import com.example.chartog.entity.Papyrus;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;


@Component
public class PapyrusStorage {
    private int id;
    private Map<Integer,Papyrus>papyrusMap=new HashMap<>();
    /**
     Это хранилище холстов (<id, холст>)
    **/
    public PapyrusStorage() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setSize(int width, int height){  //
        Papyrus papyrus = new Papyrus(width,height);
        ++id;
        papyrusMap.put(id,papyrus);
    }

    public Map<Integer, Papyrus> getPapyrusMap() {
        return papyrusMap;
    }

    public void setPapyrusMap(Map<Integer, Papyrus> papyrusMap) {
        this.papyrusMap = papyrusMap;
    }
    public Papyrus getPapyrus(int id){
        return papyrusMap.get(id);
    }

    public void removeObject(int id){
        papyrusMap.remove(id);
    }

}
