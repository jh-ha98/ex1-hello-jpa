package hellojpa;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

	public static void main(String[] args) {
//		runTeamAndMember();
//		runExtendPractice();
		runSuperClass();
	}

	public static void runSuperClass() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2");

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();
		try {
			Member member = new Member();
			member.setCreatedBy("kim");
			member.setCreatedDate(LocalDateTime.now());
			member.setUsername("user1");
			
			em.persist(member);
			
			em.flush();
			em.clear();

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}

		emf.close();
	}

	public static void runExtendPractice() {
		// 정하는 예뻐요~s2!
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2");

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();
		try {
			Movie movie = new Movie();
			movie.setDirector("용현이");
			movie.setActor("정하");
			movie.setName("Love Stroy");
			movie.setPrice(65535);

			em.persist(movie);

			em.flush();
			em.clear();

			Movie findMovie = em.find(Movie.class, movie.getId());
			System.out.println("findMovie = " + findMovie);

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}

		emf.close();
	}

	public static void runTeamAndMember() {
		// 정하는 예뻐요~s2!
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2");

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();
		try {
			// 저장
			Team team = new Team();
			team.setName("TeamA");
			em.persist(team);

			Member member = new Member();
			member.setUsername("member1");
			em.persist(member);

			// 연관관계 편의 메소드
			team.addMember(member);

			// 역방향(주인이 아닌 방향)만 연관관계 설정
			// team.getMembers().add(member);

			em.flush();
			em.clear();

			Team findTeam = em.find(Team.class, team.getId()); // 1차 캐시
			List<Member> members = findTeam.getMembers();

			System.out.println("====================");
			for (Member m : members) {
				System.out.println("m = " + m.getUsername());
			}
			System.out.println("====================");

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}

		emf.close();
	}
}
