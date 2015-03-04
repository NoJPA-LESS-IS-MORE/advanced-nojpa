package dk.lessismore.advnojpa.service.impl;

import dk.lessismore.advnojpa.model.Book;
import dk.lessismore.advnojpa.model.Person;
import dk.lessismore.advnojpa.service.BookService;
import dk.lessismore.advnojpa.service.PersonService;
import dk.lessismore.nojpa.db.connectionpool.ConnectionPoolFactory;
import dk.lessismore.nojpa.reflection.db.DatabaseCreator;
import dk.lessismore.nojpa.spring.db.NoJpaDatabaseProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * Created by niakoi on 4/3/15.
 */
@PropertySource("classpath:/NoJpa.properties")
@Service
public class InitDatabaseServiceImpl {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PersonService personService;

    @Autowired
    private BookService bookService;

    @Autowired
    private Environment environment;

    @Autowired
    private DataSource dataSource;

    @Value("${createDatabase}")
    private boolean createDatabase;
    @Value("${alterDatabase}")
    private boolean alterDatabase;
    @Value("${createTestdata}")
    private boolean createTestdata;

    @PostConstruct
    public void init() throws Exception {
        try {
            if (dataSource != null) {
                ConnectionPoolFactory.configure(NoJpaDatabaseProperties.from(environment));
            }
        } catch (Exception e) {
            log.error("InitDatabaseServiceImpl init(): getting dataSource e.getMessage() = " + e.getMessage(), e);
        }
        try {
            if (createDatabase) {
                createDatabase();
            }
            if (alterDatabase) {
                alterDatabase();
            }
            if (createTestdata) {
                createPeople();
            }
        } catch (Exception e) {
            log.error("InitDatabaseServiceImpl init(): craeting data e.getMessage() = " + e.getMessage(), e);
        }
    }

    private void createPeople() {
        for (int i = 0; i < 5; i++) {
            Person writer = personService.create("Writer " + (char)('a' + i));
            for (int j = 0; j < i + 2; j++) {
                Book book = bookService.create("Title " + (char) ('a' + i) + (char) ('a' + j));
                bookService.assign(book, writer);
            }
        }
    }

    private void createDatabase() {
        log.debug("------------------- Will add database");
        DatabaseCreator.createDatabase("dk.lessismore.advnojpa.model");
    }

    private void alterDatabase() throws Exception {
        log.debug("------------------- Will alter database");
        DatabaseCreator.alterDatabase("dk.lessismore.advnojpa.model");
    }

}
