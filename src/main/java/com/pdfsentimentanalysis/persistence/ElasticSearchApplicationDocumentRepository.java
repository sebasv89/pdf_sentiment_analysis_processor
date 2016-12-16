package com.pdfsentimentanalysis.persistence;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Component;

import com.pdfsentimentanalysis.model.ApplicationDocument;

import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.core.SearchResult.Hit;

@Component
public class ElasticSearchApplicationDocumentRepository implements ApplicationDocumentRepository {

	private static final String APPLICATION_DOCUMENT_INDEX_NAME = "application_document";
	private static final String TYPE_NAME = "pdf_file";
	private static final int MAX_SIZE_OF_ENTITIES_TO_RETURN = 10000;

	@Override
	public void save(ApplicationDocument document) {
		Index index = new Index.Builder(document).index(APPLICATION_DOCUMENT_INDEX_NAME).type(TYPE_NAME).build();
		JestClient client = ElasticSearchUtils.getClient();
		try {
			client.execute(index);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			ElasticSearchUtils.closeClient(client);
		}
	}

	@Override
	public List<ApplicationDocument> searchByKeyword(String keyword) {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.queryStringQuery(keyword)).size(MAX_SIZE_OF_ENTITIES_TO_RETURN);
		Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex(APPLICATION_DOCUMENT_INDEX_NAME)
				.build();
		JestClient client = ElasticSearchUtils.getClient();

		try {
			SearchResult result = client.execute(search);
			List<Hit<ApplicationDocument, Void>> entities = result.getHits(ApplicationDocument.class);
			return entities.stream().map(this::getApplicationDocument).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			ElasticSearchUtils.closeClient(client);
		}
	}

	@Override
	public boolean isFileAlreadyProcessed(String completeFilePath) {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.termQuery("canonicalPath", completeFilePath));

		Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex(APPLICATION_DOCUMENT_INDEX_NAME)
				.build();

		JestClient client = ElasticSearchUtils.getClient();

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
			ElasticSearchUtils.closeClient(client);
		}
	}

	private ApplicationDocument getApplicationDocument(Hit<ApplicationDocument, Void> hit) {
		return hit.source;
	}

}
