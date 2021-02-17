package com.company.app.repositery;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.company.app.io.entities.ConnectionEntity;

public interface ConnectionRepository extends CrudRepository<ConnectionEntity, Long> {

	@Query(value = "SELECT * FROM connections conn WHERE conn.first_user_id =:userId OR conn.second_user_id =:userId", nativeQuery = true)
	List<ConnectionEntity> findByUserId(long userId);
}
