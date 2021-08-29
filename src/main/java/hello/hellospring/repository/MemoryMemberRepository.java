package hello.hellospring.repository;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import hello.hellospring.domain.Member;

/**
 * Long과 HashMap은 동시성 문제가 고려되어 있지 않다고 함.
 * 실무에서는 ConcurrentHashMap, AtomicLong을 사용한다 함.
 */
public class MemoryMemberRepository implements MemberRepository {
	private static Map<Long, Member> store = new HashMap<>(); // 임시 DB {메모리} 
	private static long seq = 0L;
	
	@Override
	public Member save(Member member) {
		// TODO Auto-generated method stub
		member.setId(++seq);
		store.put(member.getId(), member);
		return member;
	}
	
	@Override
	public Optional<Member> findById(Long id) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(store.get(id));
	}
	
	@Override
	public Optional<Member> findByName(String name) {
		// TODO Auto-generated method stub
		Collection<Member> values = store.values();
		Stream<Member> stream = values.stream();
		Optional<Member> result = stream.filter(item -> item.getName().equals(name))
				  	   					.findAny();
		return result; 
	}
	
	@Override
	public List<Member> findAll() {
		// TODO Auto-generated method stub
		Collection<Member> values = store.values();
		ArrayList<Member> list = new ArrayList<Member>(values);
		return list;
	}

	public void clearStore() {
		store.clear();
	}
	
}
