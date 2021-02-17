package com.company.app.repositery;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.company.app.io.entities.UserAuthorityEntity;

@Repository
public interface UserAuthorityRepository extends CrudRepository<UserAuthorityEntity, Long> {
}
