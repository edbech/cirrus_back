package com.revature.services;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import com.revature.models.Message;
import com.revature.util.HibernateUtil;

@Service
public class MessageService {

	public List<Message> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();

			Query query = session.createQuery("from Message");

			List<Message> messages = query.getResultList();
			HibernateUtil.getSessionFactory().close();
			return messages;

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			HibernateUtil.getSessionFactory().close();
			return null;
		}
	}

	public Message getById(int id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			Message message = session.get(Message.class, id);
			HibernateUtil.getSessionFactory().close();
			return message;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			HibernateUtil.getSessionFactory().close();
			return null;
		} 
	}

	public Message createMessage(int receiver, int sender, String content) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();

			Message message = new Message(sender, receiver, content);

			session.save(message);
			session.getTransaction().commit();
			HibernateUtil.getSessionFactory().close();
			return message;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			HibernateUtil.getSessionFactory().close();
			return null;
		} 

	}

	public void deleteMessage(int id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();

			Query query = session.createQuery("delete from Message m where m.id = :id");
			query.setParameter("id", id);
			query.executeUpdate();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.getSessionFactory().close();
		}
	}

}
