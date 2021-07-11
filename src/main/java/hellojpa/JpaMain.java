package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

	public static void main(String[] args) {
		// 정하는 예뻐요~s2!
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2");
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			
			Member member = em.find(Member.class, 1L);
			
			em.remove(member);
			
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
