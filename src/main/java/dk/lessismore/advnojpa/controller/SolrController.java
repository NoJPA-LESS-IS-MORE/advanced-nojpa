package dk.lessismore.advnojpa.controller;

import dk.lessismore.advnojpa.model.Person;
import dk.lessismore.nojpa.db.methodquery.NQL;
import dk.lessismore.nojpa.reflection.db.model.ModelObject;
import dk.lessismore.nojpa.reflection.db.model.SolrService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by niakoi on 4/3/15.
 */
@RestController
public class SolrController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private SolrService personSolrService;


    @RequestMapping("/solr-debug")
    public List<SolrDocument> list() throws Exception {
        SolrQuery query = new SolrQuery("*:*");
        QueryResponse response = personSolrService.getServer().query(query);
        return response.getResults();
    }

    @RequestMapping("/solr-log-fields")
    public void logFields() throws Exception {
        Person mPerson = NQL.mock(Person.class);
        log.debug(NQL.asString(mPerson.getName()));
        log.debug(NQL.asString(mPerson.getBooks()[NQL.ANY].getTitle()));
        log.debug(NQL.asString(mPerson.getBooks()[NQL.ANY].getWriter().getName()));
    }

    @RequestMapping("/nql-log-attr")
    public void nqlFields() {
        Person mPerson = NQL.mock(Person.class);
        Person first = NQL.search(mPerson).getFirst();
        ModelObject object = (ModelObject) first;

    }
}
