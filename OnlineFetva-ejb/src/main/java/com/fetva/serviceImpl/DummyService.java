/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fetva.serviceImpl;

import com.fetva.service.DummyServiceLocal;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.hibernate.ogm.demos.ogm101.part1.Hike;
import org.hibernate.ogm.demos.ogm101.part1.HikeSection;
import org.hibernate.ogm.demos.ogm101.part1.Person;

/**
 *
 * @author abdurrahmanturkeri
 */
@Dependent
@Stateless
public class DummyService implements DummyServiceLocal {

    private static EntityManagerFactory entityManagerFactory;

    @Override
    public String getData() {
        return "getData";
    }

    @Override
    public void insertData() throws Exception {

        entityManagerFactory = Persistence.createEntityManagerFactory("fetva_mongo_db_pu");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Person bob = new Person("Bob", "McRobb");

        // and two hikes
        Hike cornwall = new Hike(
                "Visiting Land's End", new Date(), new BigDecimal("5.5"),
                new HikeSection("Penzance", "Mousehole"),
                new HikeSection("Mousehole", "St. Levan"),
                new HikeSection("St. Levan", "Land's End")
        );
        Hike isleOfWight = new Hike(
                "Exploring Carisbrooke Castle", new Date(), new BigDecimal("7.5"),
                new HikeSection("Freshwater", "Calbourne"),
                new HikeSection("Calbourne", "Carisbrooke Castle")
        );

        // let Bob organize the two hikes
        cornwall.setOrganizer(bob);
        bob.getOrganizedHikes().add(cornwall);

        isleOfWight.setOrganizer(bob);
        bob.getOrganizedHikes().add(isleOfWight);

        // persist organizer (will be cascaded to hikes)
        entityManager.getTransaction().begin();
        entityManager.persist(bob);

        entityManager.getTransaction().commit();
        entityManager.close();

    }

    @Override
    public List<Person> getListOfPerson() {
        entityManagerFactory = Persistence.createEntityManagerFactory("fetva_mongo_db_pu");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<Person> personList = new ArrayList<>();
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("select p from Person p", Person.class);

        personList = query.getResultList();
        entityManager.getTransaction().commit();

        entityManager.close();
        return personList;

    }

}
