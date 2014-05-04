package com.entities;

import org.hibernate.Session;
 
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("Maven + Hibernate + MySQL");
        Session session = HibernateUtil.getSessionFactory().openSession();
 
        session.beginTransaction();
        Profile stock = new Profile();
 
        stock.setIdProfile(6);
        stock.setDescription("asdasd11123");
 
        session.save(stock);
        session.getTransaction().commit();
    }
}