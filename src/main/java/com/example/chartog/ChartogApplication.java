package com.example.chartog;

import com.example.chartog.Service.PapyrusService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ChartogApplication {

    public static void main(String[] args) {
        PapyrusService.uploadPath=args[0];
        /**
         Лучше способа, как в соответствии с заданием запускать jar не нашел
         **/
        SpringApplication.run(ChartogApplication.class, args);
    }

}
