/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Student;
import exceptions.DAOException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Gábor
 */
@Stateless
public class StudentDAO {

    @PersistenceContext
    EntityManager em;

    public void registerStudent(String name) {
        try {
            em.persist(new Student(name));
        } catch (Exception ex) {
            throw new DAOException();
        }
    }

    public void persistStudent(Student student) {
        try {
            em.persist(student);
        } catch (Exception ex) {
            throw new DAOException();
        }
    }

    public List<Student> getStudentsList() {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Student> cq = cb.createQuery(Student.class);
            Root<Student> students = cq.from(Student.class);
            cq.select(students);
            return em.createQuery(cq).getResultList();
        } catch (Exception ex) {
            throw new DAOException();
        }
    }

    public Student updateSeen(Student student) {
        try {
            Long id = student.getId();
            student = em.getReference(Student.class, id);
            em.remove(student);
            student.incSeen();
            em.persist(student);
            return student;
        } catch (Exception ex) {
            throw new DAOException();
        }
    }

    public Student updateCorrect(Student student) {
        try {
            Long id = student.getId();
            student = em.getReference(Student.class, id);
            em.remove(student);
            student.incCorrect();
            em.persist(student);
            return student;
        } catch (Exception ex) {
            throw new DAOException();
        }
    }

    public void removeById(Long id) {
        try{
            Student s = em.getReference(Student.class, id);
            em.remove(s);
        }
        catch(Exception ex){
            throw new DAOException();
        }
    }

    public Student findByUsername(String username) {
        try{
        return em.createQuery("SELECT s FROM Student s WHERE s.username = :uname", Student.class)
                .setParameter("uname", username)
                .getSingleResult();
        }
        catch(Exception ex){
            throw new DAOException();
        }
    }

}
