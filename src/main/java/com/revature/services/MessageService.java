package com.revature.services;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.revature.models.Message;

public class MessageService {

	public List<Message> getAll() {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Message.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		session.beginTransaction();

		Query query = session.createQuery("from Message");

		List<Message> messages = query.getResultList();

		factory.close();
		return messages;

	}

	public Message getById(int id) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Message.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		session.beginTransaction();

		Message message = session.get(Message.class, id);

		factory.close();
		return message;

	}

	public Message createMessage(int receiver, int sender, String content) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Message.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		session.beginTransaction();

		Message message = new Message(sender, receiver, content);

		session.save(message);
		session.getTransaction().commit();

		factory.close();
		return message;

	}

	public void deleteMessage(int id) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Message.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		session.beginTransaction();

		Query query = session.createQuery("delete from Message m where m.id = :id");
		query.setParameter("id", id);
		query.executeUpdate();

		session.getTransaction().commit();
		factory.close();
	}
	
}
