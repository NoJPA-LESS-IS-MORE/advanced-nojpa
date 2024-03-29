package dk.lessismore.advnojpa.controller;

import dk.lessismore.advnojpa.model.Person;
import dk.lessismore.nojpa.cache.GlobalLockService;
import dk.lessismore.nojpa.reflection.db.model.ModelObjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by niakoi on 4/3/15.
 */
@RestController
public class ClusterController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Value("${locker.sleep}")
    private Integer lockerSleep;

    @RequestMapping("/cluster-lock/{person}")
    public void sync(@PathVariable Person person) throws Exception{
        for (int i = 0; i < 5; i++) {
            GlobalLockService.getInstance().lockAndRun(person, new GlobalLockService.LockedExecutor<Person>() {

                @Override
                public void execute(Person ms) throws Exception {
                    log.debug("::::::::::::: EXECUTE - START");
                    for(int i = 0; i < lockerSleep; i ++) {
                        log.debug("------------ Running in executor");
                        Thread.sleep(50);
                    }
                    log.debug("::::::::::::: EXECUTE - END");
                }
            });
        }
    }
}
