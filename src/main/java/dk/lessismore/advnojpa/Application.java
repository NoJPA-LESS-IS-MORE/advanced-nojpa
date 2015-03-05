package dk.lessismore.advnojpa;

import dk.lessismore.advnojpa.service.impl.InitDatabaseServiceImpl;
import dk.lessismore.common.nologger.aspect.DefaultLoggingAspect;
import dk.lessismore.nojpa.cache.ObjectCacheRemote;
import dk.lessismore.nojpa.reflection.db.model.SolrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class Application {

    @Autowired
    private SolrService personSolrService;

    @Autowired
    private InitDatabaseServiceImpl initDatabaseService;

    @Bean
    public DefaultLoggingAspect defaultLoggingAspect() {
        return new DefaultLoggingAspect();
    }

    @Bean
    @Profile("serverA")
    public ServletContextListener listenerA() {
        ObjectCacheRemote remote = new ObjectCacheRemote();
        remote.clusterFilenameForTest = "cluster-serverA";
        return remote;
    }

    @Bean
    @Profile("serverB")
    public ServletContextListener listenerB() {
        ObjectCacheRemote remote = new ObjectCacheRemote();
        remote.clusterFilenameForTest = "cluster-serverB";
        return remote;
    }

    public static void main(String[] args)  {
        SpringApplication.run(Application.class, args);
    }


}