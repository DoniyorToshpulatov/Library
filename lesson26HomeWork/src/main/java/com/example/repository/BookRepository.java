package com.example.repository;

import com.example.entity.BookEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;


@Repository
public class BookRepository {
    @Autowired
    private SessionFactory sessionFactory;


    public void create(BookEntity book) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(book);
        transaction.commit();
        session.close();
    }


    public BookEntity getById(Integer id) {
        Session session = sessionFactory.openSession();
        BookEntity book = session.get(BookEntity.class, id);
        session.close();
        return book;
    }

    public List<BookEntity> getAll() {
        Session session = sessionFactory.openSession();
        List<BookEntity> bookList = session.createQuery("from  BookEntity order by id asc ").getResultList();
        session.close();
        return bookList;
    }


    public BookEntity update(BookEntity book) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(book);
        transaction.commit();
        session.close();
        return book;
    }

    public BookEntity delete(BookEntity book) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(book);
        transaction.commit();
        session.close();
        return book;
    }

    public int delete(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("delete from BookEntity  where id = " + id);
        int result = query.executeUpdate();
        transaction.commit();
        session.close();
        return result;
    }

}
