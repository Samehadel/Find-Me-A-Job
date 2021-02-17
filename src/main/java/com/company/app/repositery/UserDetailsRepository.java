package com.company.app.repositery;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.company.app.io.entities.UserDetailsEntity;

@Repository
public interface UserDetailsRepository extends CrudRepository<UserDetailsEntity, Long> {
}
