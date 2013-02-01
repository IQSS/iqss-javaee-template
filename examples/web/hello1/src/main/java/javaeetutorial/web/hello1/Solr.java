package javaeetutorial.web.hello1;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import java.net.MalformedURLException;
import java.util.logging.Logger;

@Named
@RequestScoped
public class Solr {

    private static final Logger dbgLog = Logger.getLogger(Solr.class.getCanonicalName());
    private String resultString;

    public Solr() {
    }

    public String getResultString() {
        return resultString;
    }

    public void setResultString(String search_term) throws MalformedURLException, SolrServerException {
        /**
         * @fixme what if solr is down?
         */
        String url = "http://localhost:8983/solr";
        /*
         Warning from https://wiki.apache.org/solr/Solrj
         
         HttpSolrServer is thread-safe and if you are using the following constructor,
         you *MUST* re-use the same instance for all requests.  If instances are created on
         the fly, it can cause a connection leak. The recommended practice is to keep a
         static instance of HttpSolrServer per solr server url and share it for all requests.
         See https://issues.apache.org/jira/browse/SOLR-861 for more details
         */
        SolrServer server = new HttpSolrServer(url);
        SolrQuery query = new SolrQuery();
        query.setQuery(search_term);
        QueryResponse rsp = server.query(query);
        SolrDocumentList docs = rsp.getResults();
        /**
         * @todo results look ugly... all on one line
         */
        this.resultString = docs.size() + " results\n";
        dbgLog.info("Search term: " + search_term);
        for (SolrDocument doc : docs) {
            dbgLog.info(doc.toString());
            String name = doc.getFieldValue("name").toString();
            String manu = doc.getFieldValue("manu").toString();
            dbgLog.info(name + " (" + manu + ")");
            this.resultString += name + " (" + manu + ")\n";
        }
    }
}
