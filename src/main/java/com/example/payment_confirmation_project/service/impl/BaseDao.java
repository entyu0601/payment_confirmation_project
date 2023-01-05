package com.example.payment_confirmation_project.service.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.util.CollectionUtils;

public class BaseDao {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected <EntityType> List<EntityType> doQuery(String sql, Map<String, Object> params, Class<EntityType> clazz,
			int pageSize, int startPosition) {
		Query query = entityManager.createQuery(sql, clazz);
		if (!CollectionUtils.isEmpty(params)) {
			for (Parameter p : query.getParameters()) {
				query.setParameter(p, params.get(p.getName()));
			}
		}
		if (pageSize > 0) {
			query.setMaxResults(pageSize); 
		}
		if (startPosition >= 0) {
			query.setFirstResult(startPosition); 
		}
		return query.getResultList();
	}

}
