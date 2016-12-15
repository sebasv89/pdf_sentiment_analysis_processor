package com.pdfsentimentanalysis.persistence;

import java.util.List;

import com.pdfsentimentanalysis.model.Entity;

public interface PeopleRepository {

	void save(Entity entity);

	List<Entity> findAll();
}
