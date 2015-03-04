package dk.lessismore.advnojpa;

import dk.lessismore.advnojpa.service.impl.InitDatabaseServiceImpl;
import dk.lessismore.common.nologger.aspect.DefaultLoggingAspect;
import dk.lessismore.nojpa.reflection.db.model.SolrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class Application {

    @Autowired
    SolrService personSolrService;

    @Autowired
    private InitDatabaseServiceImpl initDatabaseService;

    @Bean
    public DefaultLoggingAspect defaultLoggingAspect() {
        return new DefaultLoggingAspect();
    }

    public static void main(String[] args)  {
        SpringApplication.run(Application.class, args);
    }


}