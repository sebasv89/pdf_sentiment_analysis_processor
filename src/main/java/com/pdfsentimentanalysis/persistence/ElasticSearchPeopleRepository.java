package com.pdfsentimentanalysis.persistence;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Component;

import com.pdfsentimentanalysis.model.Entity;

import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.core.SearchResult.Hit;

@Component
public class ElasticSearchPeopleRepository implements PeopleRepository {

	private static final int MAX_SIZE_OF_ENTITIES_TO_RETURN = 10000;
	private static final String ENTITY_INDEX_NAME = "people";
	private static final String ENTITY_TYPE_NAME = "person";

	@Override
	public void save(Entity entity) {
		if (doesEntityNameExist(entity.getName())) {
			// TODO-LOG
			System.out.println("entity already exists...");
			return;
		}
		Index index = new Index.Builder(entity).index(ENTITY_INDEX_NAME).type(ENTITY_TYPE_NAME).build();
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

	private boolean doesEntityNameExist(String entityName) {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.termQuery("name", entityName));

		Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex(ENTITY_INDEX_NAME).build();

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

	@Override
	public List<Entity> findAll() {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.queryStringQuery("*")).size(MAX_SIZE_OF_ENTITIES_TO_RETURN);
		Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex(ENTITY_INDEX_NAME).build();
		JestClient client = ElasticSearchUtils.getClient();

		try {
			SearchResult result = client.execute(search);
			List<Hit<Entity, Void>> entities = result.getHits(Entity.class);
			return entities.stream().map(this::getEntity).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			ElasticSearchUtils.closeClient(client);
		}
	}

	private Entity getEntity(Hit<Entity, Void> hit) {
		return hit.source;
	}

}
