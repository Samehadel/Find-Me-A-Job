package com.company.app.repositery;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.company.app.io.entities.PublicationEntity;

@Repository
public interface PublicationsRepository extends CrudRepository<PublicationEntity, Long> {

	public List<PublicationEntity> findByRecieverId(long id);
}
