package com.everis.auto.techtalk.quarkus.service;

import com.everis.auto.techtalk.quarkus.entity.GreetingEntity;
import io.netty.util.internal.StringUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.*;
import java.util.List;

@ApplicationScoped
public class GreetingService {

    public static final String GET_ALL_QUERY = "SELECT * FROM greetings";
    private static final String DEFAULT_CONTENT = "HELLO";
    @Inject
    EntityManager em;

    @Inject
    UserTransaction transaction;

    public GreetingEntity createAndSave(String content) {
        final GreetingEntity entity = new GreetingEntity();
        try {
            transaction.begin();
            entity.setContent(!StringUtil.isNullOrEmpty(content) ? content : DEFAULT_CONTENT);
            em.persist(entity);
            transaction.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException e) {
            e.printStackTrace();
        }
        return entity;
    }

    public List<GreetingEntity> getAll() {
        return em.createNativeQuery(GET_ALL_QUERY, GreetingEntity.class).getResultList();
    }

}
