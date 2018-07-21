package com.example.demo.util;

import org.hibernate.SessionFactory;


public class HibernateUtils {
    private static SessionFactory sessionFactory;
     private HibernateUtils(){}; //单例模式

    public static SessionFactory getSessionFactory() {
//       sessionFactory=new Configuration().configure().buildSessionFactory(); //版本已经把Sessionfactory 全部都拿到了crud，而且是Spring一直在帮助着管理所有Session等，包括事物处理等
        return sessionFactory;
    }
}
