package com.company.app.repositery;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.company.app.io.entities.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
	UserEntity findByUserName(String userName);
	UserEntity findById(long id);
	
}
