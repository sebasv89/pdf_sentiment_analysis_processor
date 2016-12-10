package com.pdfsentimentanalysis.persistence;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.pdfsentimentanalysis.model.ApplicationDocument;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Index;

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

	private void closeClient(JestClient client) {
		if (client != null) {
			try {
				client.shutdownClient();
			} catch (Exception ex) {
				// TODO-LOGME!
			}
		}
	}

	@Override
	public List<ApplicationDocument> searchByKeyword(String keyword) {
		return null;
	}

	@Override
	public boolean isFileProcessed(String completeFilePath) {
		return false;
	}

	private JestClient getClient() {
		JestClientFactory factory = new JestClientFactory();
		factory.setHttpClientConfig(new HttpClientConfig.Builder(ELASTIC_SEARCH_URL).multiThreaded(true).build());
		return factory.getObject();
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
