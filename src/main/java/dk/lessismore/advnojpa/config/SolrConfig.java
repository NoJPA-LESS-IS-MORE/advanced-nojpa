package dk.lessismore.advnojpa.config;

import dk.lessismore.advnojpa.model.Person;
import dk.lessismore.nojpa.reflection.db.model.ModelObjectInterface;
import dk.lessismore.nojpa.reflection.db.model.ModelObjectSearchService;
import dk.lessismore.nojpa.reflection.db.model.SolrService;
import dk.lessismore.nojpa.reflection.db.model.SolrServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by niakoi on 4/3/15.
 */
@PropertySource("classpath:/NoJpa.properties")
@Configuration
public class SolrConfig {

    @Bean
    public SolrService personSolrService(@Value("${personSolr.coreName}") String coreName,
                                         @Value("${createDatabase}") boolean cleanOnStartup) {
        return getSolrService(Person.class, coreName, cleanOnStartup);
    }

    private SolrService getSolrService(Class<? extends ModelObjectInterface> clazz, String coreName, boolean cleanOnStartup) {
        SolrServiceImpl solrService = new SolrServiceImpl();
        solrService.setCoreName(coreName);
        solrService.setCleanOnStartup(cleanOnStartup);
        ModelObjectSearchService.addSolrServer(clazz, solrService.getServer());
        return solrService;

    }
}
