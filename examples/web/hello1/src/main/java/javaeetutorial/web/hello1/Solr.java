package javaeetutorial.web.hello1;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import java.net.MalformedURLException;

@Named
@RequestScoped
public class Solr {

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
        this.resultString = docs.size() + " results";
        /**
         * @todo show the actual results, not just the number of results
         */
        //this.resultString = results.size() + " results";
        //for (int i = 0; i < results.size(); ++i) {
        //    System.out.println(results.get(i));
        //}
    }
}
