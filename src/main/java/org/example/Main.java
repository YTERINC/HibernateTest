package org.example;


import org.example.model.Passport;
import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class).addAnnotatedClass(Passport.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();
            Person person = new Person("Test person", 50);
            Passport passport = new Passport(person, 12345);
            person.setPassport(passport); // и без этого hibernate сохранил бы человека
            session.save(person); // сохранение будет каскадировано на паспорт


            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }


    }
}
