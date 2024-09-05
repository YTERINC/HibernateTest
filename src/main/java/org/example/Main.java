package org.example;

import org.example.model.Item;
import org.example.model.Person;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.w3c.dom.ls.LSOutput;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class).addAnnotatedClass(Item.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Person person = session.get(Person.class, 1);
            System.out.println("Получили человека из таблицы");
            // Получим связанные сущности
            System.out.println(person);

//            System.out.println(person.getItems());
//            Hibernate.initialize(person.getItems()); // чтобы явно подгрузить ленивые сущности

//            Item item =session.get(Item.class, 1);
//            System.out.println("Получили товар");
//
//            System.out.println(item.getOwner());

            session.getTransaction().commit();
            //session.close();
            System.out.println("Сессия закончилась");
            // открываем сессию и транзакцию еще раз
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            System.out.println("Находимся внутри второй транзации");

            person = (Person) session.merge(person);  // Person связываем с новой сессией, но можно просто сделать HQL запрос

            Hibernate.initialize(person.getItems());
            session.getTransaction().commit();

            System.out.println("Вне второй сессии");
            System.out.println(person.getItems());



        } finally {
            sessionFactory.close();
        }


    }
}
