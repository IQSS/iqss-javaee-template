package javaeetutorial.web.hello1;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.ModifiableSolrParams;
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
         * @fixme stop using deprecated API
         */
        CommonsHttpSolrServer solr = new CommonsHttpSolrServer("http://localhost:8983/solr");

        ModifiableSolrParams params = new ModifiableSolrParams();
        //params.set("q", "cat:electronics");
        //params.set("q", "*:*");
        params.set("q", search_term);
        params.set("defType", "edismax");
        params.set("start", "0");

        QueryResponse response = solr.query(params);
        SolrDocumentList results = response.getResults();
        /**
         * @todo show the actual results, not just the number of results
         */
        this.resultString = results.size() + " results";
        //for (int i = 0; i < results.size(); ++i) {
        //    System.out.println(results.get(i));
        //}

    }
}
