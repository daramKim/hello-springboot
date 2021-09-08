package hello.hellospring.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import hello.hellospring.domain.Member;

public class JpaMemberRepository implements MemberRepository {

	private final EntityManager em;

	// 그리고 생성자를 이용해 외부에서 주입받게 한다. 이는 이전 MemoryRepository와 같은방식인, Spring Config에서
	// 주입해주는 방식으로 한다.
	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public Member save(Member member) {
		// persist : 영속하다, 영구히 저장한다 이런뜻..
		// 객체기반으로 동작해서 save문에 객체 넘기면 알아서 저장된다. 이때 application.properties에
		// auto-ddl 설정을 create(default)로 해놓으면 ddl이 자동으로 실행됨.
		em.persist(member);
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		// pk 기반으로 select를 할때는 sql문을 쓰지 않아도됌.
		Member member = em.find(Member.class, id);
		return Optional.ofNullable(member);
	}

	@Override
	public Optional<Member> findByName(String name) {
		List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
							    .setParameter("name", name).getResultList();
		return result.stream().findAny();
	}

	@Override
	public List<Member> findAll() {
		return em.createQuery("select m from Member m", Member.class)
				 .getResultList();
	}

}
