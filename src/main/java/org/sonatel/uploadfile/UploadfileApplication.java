package org.sonatel.uploadfile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "org.sonatel.uploadfile")
@EnableJpaRepositories(basePackages = "org.sonatel.uploadfile.repository")
@EntityScan(basePackages = "org.sonatel.uploadfile.domain.entity")

public class UploadfileApplication {

    public static void main(String[] args) {
        SpringApplication.run(UploadfileApplication.class, args);
    }

}
