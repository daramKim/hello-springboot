package hello.hellospring.service;

import java.util.List;
import java.util.Optional;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;


/**
 * 레파지토리에는 [save, findByName, findById] 처럼 기술적으로 명료하다면
 * 서비스에 있는 로직들의 네이밍 컨벤션은 좀더 비즈니스에 가까워야 한다. 그래야 추후에 
 * 기획자든 누가 묻든 찾기가 쉬워진다고 함. 언어적으로 비즈니스에 매칭이 되기 때문
 */
public class MemberService {
	
	private final MemberRepository repo = new MemoryMemberRepository();
	
	
	/**
	 * 회원가입
	 * @param member	
	 * @return id
	 */
	public Long join(Member member) {
		
		// 1. 회원의 이름이 중복되지 않아야 된다는 규칙
		Optional<Member> result = repo.findByName(member.getName());
		validationDupName(result);
		
		// Exception이 발생하지 않았다면 로직이 이까지 올수 있음.
		// 회원가입 
		repo.save(member);
		return member.getId();
		
	}

	/**
	 * 전체회원 조회
	 */
	public List<Member> findAllMembers(){
		return repo.findAll();
	}
	
	
	public Optional<Member> findOne(Long memberId) {
		return repo.findById(memberId);
	}
	
	
	
	
	
	// 이 클래스 내부에서만 사용하는 유틸성 이면 private로 지정하고 보기좋게 좀 띄워놓자. 
	private void validationDupName(Optional<Member> result) {
		result.ifPresent(item -> { // 해당 이름으로 된 아이디를 검색해서 1개라도 있으면 Excepiton 발생 
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		});
	}
}
