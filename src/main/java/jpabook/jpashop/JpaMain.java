package jpabook.jpashop;

import java.lang.reflect.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jpabook.jpashop.domain.Order;


public class JpaMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2");
		
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			
			Order order = em.find(Order.class, 1L);
			Long memberId = order.getMemberId();
			
			Member member = em.find(Member.class, memberId);
			
			
			
			tx.commit();
		} 
		catch (Exception e) {
			tx.rollback();
		} 
		finally {
			em.close();	
		}
		
		emf.close();
	}

}
