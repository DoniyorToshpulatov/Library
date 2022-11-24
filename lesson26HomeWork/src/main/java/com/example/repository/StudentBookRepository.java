package com.example.repository;

import com.example.entity.BookEntity;
import com.example.entity.StudentBookEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentBookRepository {

    @Autowired
    private SessionFactory sessionFactory;


    public void create(StudentBookEntity studentBook) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(studentBook);
        transaction.commit();
        session.close();
    }

    public StudentBookEntity getById(Integer id) {
        Session session = sessionFactory.openSession();
        StudentBookEntity studentBook = session.get(StudentBookEntity.class, id);
        // session.close();
        return studentBook;
    }

    public List<StudentBookEntity> getAll() {
        Session session = sessionFactory.openSession();
        List<StudentBookEntity> list = session.createQuery("from  StudentBookEntity order by id asc ").getResultList();
        session.close();
        return list;
    }


}
