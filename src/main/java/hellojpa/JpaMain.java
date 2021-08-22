package hellojpa;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.Hibernate;

public class JpaMain {

	public static void main(String[] args) {
		// runTeamAndMember();
		// runExtendPractice();
		// runSuperClass();
		runProxy();
	}

	public static void runProxy() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2");

		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			// chapter1(em);
			// chapter2(em);
			// chapter3(em);
			// chapter4(em);
			chapter5(em, emf);

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}

		emf.close();
	}

	private static void chapter1(EntityManager em) {
		Member member = new Member();
		member.setUsername("hello1");
		em.persist(member);

		em.flush();
		em.clear();

		// Member findMember = em.find(Member.class, member.getId());
		Member findMember = em.getReference(Member.class, member.getId());

		// getReference 사용시 실제 필요한 값을 가져올때 없으면 조회
		System.out.println("findMember.id = " + findMember.getId());
		System.out.println("findMember.username = " + findMember.getUsername());
		System.out.println("findMember = " + findMember.getClass());
	}

	private static void chapter2(EntityManager em) {
		Member member1 = new Member();
		member1.setUsername("hello1");
		em.persist(member1);

		Member member2 = new Member();
		member2.setUsername("hello2");
		em.persist(member2);

		em.flush();
		em.clear();

		Member m1 = em.find(Member.class, member1.getId());
		// Member m2 = em.find(Member.class, member2.getId());
		Member m2 = em.getReference(Member.class, member2.getId());

		// em.find == em.find => true
		// em.find == em.getReference => false
		// 타입 체크 주의
		System.out.println("m1 == m2: " + (m1.getClass() == m2.getClass()));
		System.out.println("m1 == m2: " + (m1 instanceof Member));
	}

	private static void chapter3(EntityManager em) {
		Member member1 = new Member();
		member1.setUsername("hello1");
		em.persist(member1);

		em.flush();
		em.clear();

		Member m1 = em.find(Member.class, member1.getId());
		System.out.println("m1 = " + m1.getClass());

		// 먼저 조회한 값이 이미 있을 경우 영속성 컨텍스트(em)에서 그대로 가져옴
		Member reference = em.getReference(Member.class, member1.getId());
		System.out.println("reference = " + reference.getClass());

		System.out.println("m1 = ref: " + (m1.getClass() == reference.getClass()));
		System.out.println("m1 = ref: " + (m1 == reference));
	}

	private static void chapter4(EntityManager em) {
		Member member1 = new Member();
		member1.setUsername("hello1");
		em.persist(member1);

		em.flush();
		em.clear();

		Member refMember = em.getReference(Member.class, member1.getId());
		System.out.println("reference = " + refMember.getClass());

		/* LazyInitializationException 에러 */
		em.detach(refMember);
		em.clear();

		System.out.println("refMember = " + refMember.getUsername());
	}

	private static void chapter5(EntityManager em, EntityManagerFactory emf) {
		Member member1 = new Member();
		member1.setUsername("hello1");
		em.persist(member1);

		em.flush();
		em.clear();

		Member refMember = em.getReference(Member.class, member1.getId());
		System.out.println("reference = " + refMember.getClass());

		// 없어보이는 초기화
		// refMember.getUsername();
		// 강제 초기화
		Hibernate.initialize(refMember);

		System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember));
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
