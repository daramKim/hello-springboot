package hello.hellospring.repository;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import hello.hellospring.domain.Member;

/**
 * Long과 HashMap은 동시성 문제가 고려되어 있지 않다고 함.
 * 실무에서는 ConcurrentHashMap, AtomicLong을 사용한다 함.
 */
public class MemoryMemberRepository implements MemberRepository {
	private static Map<Long, Member> store = new HashMap<>();
	private static long id = 0;
	
	@Override
	public Member save(Member member) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Optional<Member> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Optional<Member> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Member> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
