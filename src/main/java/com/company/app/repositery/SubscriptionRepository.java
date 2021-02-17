package com.company.app.repositery;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.company.app.io.entities.SubscriptionEntity;

@Repository
public interface SubscriptionRepository extends CrudRepository<SubscriptionEntity, Long> {
	
	public List<SubscriptionEntity> findAllByUserId(long id);
	
	@Query(value = "SELECT * FROM subscriptions subs WHERE subs.keyword_id in :keywords AND subs.user_id != :userId", nativeQuery = true)
	public List<SubscriptionEntity> findSimilarSubscriptions(List<Integer> keywords, long userId);
}
