package com.company.app.ws.repositery;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.company.app.ws.io.entity.UserAuthorityEntity;
import com.company.app.ws.io.entity.UserEntity;

@Repository
public interface UserAuthorityRepository extends CrudRepository<UserAuthorityEntity, Long> {
}
