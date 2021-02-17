package com.company.app.repositery;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.company.app.io.entities.KeywordEntity;

@Repository
public interface KeywordRepository extends CrudRepository<KeywordEntity, Integer> {
	
	public KeywordEntity findById(int id);
}
