package com.example.chartog.Service;

import com.example.chartog.entity.Papyrus;
import com.example.chartog.entity.Fragment;
import com.example.chartog.entity.OpenFragment;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class CreatorBMP {

    public File createFile(Papyrus papyrus, Fragment fragment, List<OpenFragment> openFragments, String uploadPath) throws IOException {
        BufferedImage imgSource = ImageIO.read(new File(fragment.getPath()));
        BufferedImage bufferSource = new BufferedImage(papyrus.getWidth(), papyrus.getHeight(), 1);
        Graphics2D d = bufferSource.createGraphics();
        d.drawImage(imgSource, fragment.getX(), fragment.getY(), fragment.getWidth(), fragment.getHeight(), null);
        File tmp1 = new File(uploadPath+"tmpFile1.bmp");
        ImageIO.write(bufferSource, "bmp", tmp1);
        return writeFragments(tmp1, papyrus, openFragments, uploadPath);
    }
    /**
     Метод читает файл bmp(на основе MultipartFile) в буфер(imgSource), создает буфер данных изображения(с заданой шириной и высотой).
     Рисует этот фаил(с задаными координатами, высотой и шириной) в созданный буфер,
     а потом записыат в фаил(tmp1). И возвращает File - результат метода writeFragments()
     **/

    public File writeFragments(File file, Papyrus papyrus, List<OpenFragment> openFragments, String uploadPath) throws IOException {
        BufferedImage imgFinite = ImageIO.read(file);
        BufferedImage bfFinite = new BufferedImage(papyrus.getWidth(), papyrus.getHeight(), 1);
        Graphics2D d1 = bfFinite.createGraphics();
        File bmpFile = new File(uploadPath+"tmpFile2.bmp");
        for (OpenFragment load:
                openFragments) {
            BufferedImage cropImage = imgFinite.getSubimage(load.getX(), load.getY(), load.getWidth(), load.getHeight());
            d1.drawImage(cropImage, load.getX(), load.getY(), load.getWidth(), load.getHeight(), null);
            ImageIO.write(bfFinite, "bmp", bmpFile);
        }
        return bmpFile;
    }
    /**
     Метод читает файл tmp1(переданный в параметрах) в BufferedImage. Создает файл tmpFile2.bmp. В foreach проходимся по List<OpenFragment>,
     и накладываем(рисуем) обрезки(cropImage) из буфера прочитавшего файл tmpFile2.bmp(с задаными кординатами, шириной и высотой) на созданный(bfFinite)
     буфер данных изображения(с заданой шириной и высотой). Тут же записываем эти открытые(вырезанные) фрагменты в ранее зосданный bmpFile.
     **/
}
