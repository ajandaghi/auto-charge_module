package ir.mapsa.autochargemodule;

import ir.mapsa.autochargemodule.models.entities.ProfileEntity;
import ir.mapsa.autochargemodule.services.ProfileService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories
@EnableScheduling
@EnableFeignClients
@EnableWebMvc
public class AutoChargeModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoChargeModuleApplication.class, args);
        ProfileEntity profile=new ProfileEntity();
        profile.setUser("arerzooeeo");
        profile.setMinimumBalance(2000000L);

    }

}
