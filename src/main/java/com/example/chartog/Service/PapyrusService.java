package com.example.chartog.Service;

import com.example.chartog.entity.Papyrus;
import com.example.chartog.entity.Fragment;
import com.example.chartog.entity.OpenFragment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
public class PapyrusService {
    @Autowired
    PapyrusStorage papyrusStorage;
    /**
     Хранилище холстов со своей логикой
     **/

    @Autowired
    CreatorBMP creatorBMP;
    /**
     Класс создающий файл с холстом и наложенным на него фрагментом, и соответсвенно открытыми фрагментами
     **/

    @Autowired
    FragmentStorage fragmentStorage;
    /**
     Хранилище фрагментов и открытых фрагментов (List<OpenFragment>)
     **/

    public static String uploadPath;
    /**
       Это путь, который задаем при запуске jar файла
     **/

    public void createFigura(int width, int height){
        papyrusStorage.setSize(width,height);
    }
    /**
     Создаю холст заданой ширины и высоты
      **/

    public void createFragment(int id, int x, int y, int width,
                               int height, MultipartFile file) throws IOException {
        fragmentStorage.setFragment(id,x,y,width,height,file,uploadPath);
    }
    /**
     Размещаем картинку на холсте с заданными параметрами
     **/

    public boolean checkId(int id){
       return fragmentStorage.checkIdFromMap(id);
    }
    /**
     Проверка в хранилище картинок(фрагментов) есть такой элемент по id
     **/

    public Path creatPathOfBMP(int id, int x, int y, int width,
                               int height) throws IOException {
        Papyrus papyrus = papyrusStorage.getPapyrus(id);
        Fragment fragment = fragmentStorage.getFragment(id);
        fragment.setOpenFragment(x,y,width,height);
        List<OpenFragment> openFragments = fragment.getOpenFragment();
        File file = creatorBMP.createFile(papyrus,fragment, openFragments,uploadPath);
        Path path = Paths.get(file.getAbsolutePath());
        return path;
    }
    /**
     Создаю путь к файлу BMP на котором располагается холст с фрагментом(открытыми фрагментами) изображения
      **/
    public void deleteImage(int id){
        papyrusStorage.removeObject(id);
        fragmentStorage.removeFragment(id);;
    }
    /**
     Удаляю холст и фрагмент(вместе с открытыми фрагментами) по id из хранилищ
     **/

    public int getId(){
        return papyrusStorage.getId();
    }
    /**
     Получаю id из хранилища холстов
     **/

}
