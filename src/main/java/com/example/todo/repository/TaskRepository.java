package com.example.todo.repository;

import com.example.todo.domain.Status;
import com.example.todo.domain.Task;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TaskRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Task task) {
        em.persist(task);
    }

    public Task findOne(Long id) {
        return em.find(Task.class, id);
    }

    public List<Task> findByName(String name) {
        return em.createQuery("select t from Task t where t.name = :name", Task.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Task> findAllOrderedByRegDate() {
        return em.createQuery("select t from Task t order by t.regDate desc", Task.class)
                .getResultList();
    }

    public void deleteById(Long id) {
        Task findTask = em.find(Task.class, id);
        em.remove(findTask);
    }
}
