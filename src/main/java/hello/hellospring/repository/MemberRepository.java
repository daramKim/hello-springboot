package hello.hellospring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import hello.hellospring.domain.Member;

public interface MemberRepository {
	
	/* DB가 뭐가 될지 모르니 interface로 정의하고
	 * 해당 DB에서 사용될 기능들을 정해놓는다.
	 */
	
	// 멤버 정보를 받아서 저장후, 저장된 회원정보 반환  
	Member save(Member member);
	
	// 조회 - by{조건} || 전체
	Optional<Member> findById(Long id);
	Optional<Member> findByName(String name);
	List<Member> findAll();
}
