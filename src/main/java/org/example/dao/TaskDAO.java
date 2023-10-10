package org.example.dao;

import org.example.domain.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
// @PropertySource("classpath:application.properties")
public class TaskDAO {
    private final SessionFactory sessionFactory;

    public TaskDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<Task> getAll(int offset, int limit){
        Query<Task> query = getSession().createQuery("select t from Task t", Task.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public int getCount(){
        Query<Long> query = getSession().createQuery("select count(t) from Task t", Long.class);
        return Math.toIntExact(query.uniqueResult());
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public Task getById(int id){
        Query <Task> query = getSession().createQuery("select t from Task t where t.id=:ID", Task.class);
        query.setParameter("ID", id);
        return query.uniqueResult();
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public void create(Task task){
        getSession().persist(task);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public void deleteTask(Task task){
        getSession().remove(task);
    }

    private Session getSession(){
      return sessionFactory.openSession();
    }
}
