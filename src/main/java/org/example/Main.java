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

//            Person person = session.get(Person.class, 4);
//            System.out.println(person.getPassport().getPassportNumber());

//            Passport passport = session.get(Passport.class, 4);
//            System.out.println(passport.getPerson().getName());

//            Person person = session.get(Person.class, 4);
//            person.getPassport().setPassportNumber(77777);

            Person person = session.get(Person.class, 4);
            session.remove(person);



            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }


    }
}
