package com.example.payment_confirmation_project.service.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.util.CollectionUtils;

public class BaseDao {

	// JPA專有注釋
	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * @param pageSize:每次結果要返回的筆數，同limit
	 * 
	 * @param startPosition:返回結果的起始筆數位置
	 */
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
			query.setMaxResults(pageSize); // 設置返回筆數
		}
		if (startPosition >= 0) {
			query.setFirstResult(startPosition); // FirstResult只設定位置 //設置取值位置
		}
		return query.getResultList();
	}

}
