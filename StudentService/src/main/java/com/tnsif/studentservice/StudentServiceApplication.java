//package com.tnsif.studentservice;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class StudentServiceApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(StudentServiceApplication.class, args);
//	}
//
//}
//
//
//package com.tnsif.studentservice;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//
//@SpringBootApplication
//@EntityScan(basePackages = "com.tnsif.studentservice")
//public class StudentServiceApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(StudentServiceApplication.class, args);
//    }
//}

//package com.tnsif.studentservice;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//
//@SpringBootApplication
//@EntityScan(basePackages = "com.tnsif.studentservice")
//@EnableJpaRepositories(basePackages = "com.tnsif.studentservice.repository")
//public class StudentServiceApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(StudentServiceApplication.class, args);
//	}
//}

package com.tnsif.studentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.tnsif.studentservice")
@EnableJpaRepositories(basePackages = "com.tnsif.studentservice.repository")
public class StudentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentServiceApplication.class, args);
    }
}



