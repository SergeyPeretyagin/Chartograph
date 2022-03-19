package com.example.chartog.Service;

import com.example.chartog.entity.Fragment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Component
public class FragmentStorage {

    private Map<Integer, Fragment> fragmentMap = new HashMap<>();

    public FragmentStorage() {

    }

    public String getPaths(int id){
        return fragmentMap.get(id).getPath();
    }


    public void setFragment(int id, int x, int y, int width, int height, MultipartFile file, String uploadPath) throws IOException {
        File fileWithFragment = createFile(file,uploadPath);
        Fragment fragment = new Fragment(x,y,width,height,fileWithFragment.getPath());
        fragmentMap.put(id,fragment);
    }
    /**
     Метод отвечает за создание обьекта фрагмента, а так же ложит это обьект в хранилище(Map) под соответсвующим id
     **/

    public Fragment getFragment(int id){
        return fragmentMap.get(id);
    }

    public File createFile(MultipartFile file, String uploadPath) throws IOException {
        byte[]bytes=file.getBytes();
        Path path = Paths.get(uploadPath+file.getOriginalFilename());
        Files.write(path,bytes);
        return new File(String.valueOf(path));
    }
    /**
     Метод отвечает за создание файла из обьекта MultipartFile, который посылаем в теле post запроса
     **/

    public boolean checkIdFromMap(int id){
        if (fragmentMap.containsKey(id))
            return true;
        else
            return false;
    }
    /**
     Метод отвечает за поверку хранилища(Map) на наличие id
     **/

    public Map<Integer, Fragment> getFragmentMap() {
        return fragmentMap;
    }

    public String removeFragment(int id){
        File file = new File(fragmentMap.get(id).getPath());
        String delete = null;
        if(file.delete())
            delete ="delete";
        fragmentMap.remove(id);
        return delete;
    }
    /**
     Метод отвечает за удаление обьекта из хранилища(Map) и файла по id
     **/
}
