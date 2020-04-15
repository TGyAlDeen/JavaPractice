package jp.co.calace.redwing;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("jp.co.calace.redwing.linememo") //66
public class RedWingApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(RedWingApplication.class, args);
	}

}
