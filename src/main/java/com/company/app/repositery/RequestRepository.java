package com.company.app.repositery;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.company.app.io.entities.RequestEntity;

public interface RequestRepository extends CrudRepository<RequestEntity, Long>{
	public RequestEntity findById(long id);
	
	@Query(value = "SELECT * FROM requests req WHERE req.sender_id =:userId OR req.reciever_id =:userId", nativeQuery = true)
	List<RequestEntity> findRequestByUserId(long userId);
}
