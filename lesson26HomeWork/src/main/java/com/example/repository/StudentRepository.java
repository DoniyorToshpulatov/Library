package com.example.repository;

import com.example.dto.StudentDTO;
import com.example.entity.BookEntity;
import com.example.entity.StudentEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public void saveStudent(StudentEntity student) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(student);

        transaction.commit();
        session.close();
    }

    public StudentEntity getById(Integer id) {
        Session session = sessionFactory.openSession();
        StudentEntity book = session.get(StudentEntity.class, id);
        session.close();
        return book;
    }

    /*public List<Student> studentList() {
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("From Student");
        List<Student> studentList = query.getResultList();
        session.close();


        if(studentList.isEmpty()){
            return null;
        }
        return studentList;
    }

    public Student getStudentById(Integer id) {
        Session session = sessionFactory.openSession();

        Student student = session.get(Student.class, id);
        return student;
    }

    public int deleteStudentById(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
//        session.delete(getStudentById(id));

        Query query = session.createQuery("DELETE from Student where id= :id");
        query.setParameter("id", id);

        session.delete(getStudentById(id));
        transaction.commit();
        session.close();
        return 1;
    }

    public int updateStudent(Student student) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();


        Query query = session.createQuery("Update Student set name = :name ," +
                "surname =:surname, phone= :phone where id = :id");

        query.setParameter("name", student.getName());
        query.setParameter("surname", student.getSurname());
        query.setParameter("phone", student.getPhone());
        query.setParameter("id", student.getId());

        int update = query.executeUpdate();

        transaction.commit();
        session.close();
        return update;
    }*/

}
