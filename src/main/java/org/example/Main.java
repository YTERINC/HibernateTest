package org.example;


import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

//            List<Person> people = session.createQuery("from Person where age > 30").getResultList();
//            List<Person> people = session.createQuery("from Person where name LIKE 'T%'").getResultList();
//            session.createQuery("update Person set name='Test' where age < 40").executeUpdate();
            session.createQuery("delete from Person where age < 40").executeUpdate();
//            for (Person p : people) {
//                System.out.println(p);
//            }

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }


    }
}
