package com.pdfsentimentanalysis.persistence;

import java.io.IOException;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Component;

import com.pdfsentimentanalysis.model.ApplicationDocument;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

@Component
public class ElasticSearchApplicationDocumentRepository implements ApplicationDocumentRepository {

	// XXX:Parameterize me!
	private static final String ELASTIC_SEARCH_URL = "http://localhost:9200";
	private static final String INDEX_NAME = "application_document";
	private static final String TYPE_NAME = "pdf_file";

	@Override
	public void save(ApplicationDocument document) {
		Index index = new Index.Builder(document).index(INDEX_NAME).type(TYPE_NAME).build();
		JestClient client = getClient();
		try {
			client.execute(index);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			closeClient(client);
		}
	}

	@Override
	public List<ApplicationDocument> searchByKeyword(String keyword) {
		return null;
	}

	@Override
	public boolean isFileAlreadyProcessed(String completeFilePath) {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.termQuery("canonicalPath", completeFilePath));

		Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex(INDEX_NAME).build();

		JestClient client = getClient();

		try {
			SearchResult result = client.execute(search);
			if (result.getTotal() > 0) {
				return true;
			}
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			closeClient(client);
		}
	}

	private JestClient getClient() {
		JestClientFactory factory = new JestClientFactory();
		factory.setHttpClientConfig(new HttpClientConfig.Builder(ELASTIC_SEARCH_URL).multiThreaded(true).build());
		return factory.getObject();
	}

	private void closeClient(JestClient client) {
		if (client != null) {
			try {
				client.shutdownClient();
			} catch (Exception ex) {
				// TODO-LOGME!
			}
		}
	}

	// Article a = new Article();
	// a.setFirstName("Alejandra");
	// a.setLastName("Cruz");
	//
	// Index index = new
	// Index.Builder(a).index("articles").type("article").build();
	// client.execute(index);

	// SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
	// searchSourceBuilder.query(QueryBuilders.matchQuery("firstName",
	// "Sebastian"));
	//
	// Search search = new Search.Builder(searchSourceBuilder.toString())
	// // multiple index or types can be added.
	// .addIndex("articles").addType("article").build();
	//
	// SearchResult result = client.execute(search);
	//
	// List<SearchResult.Hit<ApplicationDocument, Void>> hits =
	// result.getHits(ApplicationDocument.class);
	// System.out.println(hits.size());
}
