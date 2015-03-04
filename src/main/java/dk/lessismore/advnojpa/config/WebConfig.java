package dk.lessismore.advnojpa.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import dk.lessismore.nojpa.reflection.db.DatabaseCreator;
import dk.lessismore.nojpa.reflection.db.model.ModelObjectInterface;
import dk.lessismore.nojpa.rest.NoJpaFormatter;
import dk.lessismore.nojpa.rest.NoJpaMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.format.FormatterRegistry;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by niakoi on 4/3/15.
 */
@EnableSpringDataWebSupport
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        List<NoJpaFormatter> noJpaFormatters = new ArrayList<>();
        for (Class clazz : modelTypes()) {
            if (ModelObjectInterface.class.isAssignableFrom(clazz)) {
                noJpaFormatters.add(new NoJpaFormatter(clazz));
            }
        }
        for (NoJpaFormatter formatter : noJpaFormatters) {
            registry.addFormatterForFieldType(formatter.getClazz(), formatter);
        }
    }

    @Bean
    public MappingJackson2JsonView mappingJackson2JsonView() {
        return new MappingJackson2JsonView();
    }

    @Bean(name = "modelTypes")
    public ArrayList<Class> modelTypes() {
        return DatabaseCreator.getSubtypes("dk.lessismore.advnojpa.model");
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new NoJpaMapper();
    }
}
