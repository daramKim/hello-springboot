package hello.hellospring.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;


/**
 * MemoryMemberRepository의 테스트 클래스이다.    
 * 네이밍 컨벤션: {테스트클래스명}Test.java 
 * 테스트 하고자하는 곳에서 shortcut [ctrl+1] 을 누르면 편리하게 만들수 있다.
 * 네이밍 컨벤션이 적용되고, 파일을 생성위치 및 생성할 테스트 메소드를 선택가능하다.
 */
class MemoryMemberRepositoryTest {
	
	MemoryMemberRepository repository = new MemoryMemberRepository(); 
	
	// 각각의 함수가 끝날때 마다 실행  
	@AfterEach
	public void afterEach() {
		/** 
		 * 유닛테스트는 각각의 테스트마다 개별적으로 진행되어야 한다.(테스트 순서와 상관없이 의존관계가 없어야 된다.) 
		 * 그러므로 스토어를 매 테스트가 끝날때마다 초기화 해주어야 한다. 
		 */
		repository.clearStore(); 
	}
	
	
	@Test
	void testSave() {
//		fail("Not yet implemented");
		
		// 회원정보 하나 저장
		Member member = new Member();
		member.setName("daram-k");
		repository.save(member);
		
		// 저장했던 회원의 id로 회원정보 조회  
		Optional<Member> saveResult = repository.findById(member.getId());
		Member result = saveResult.get(); // 옵셔널의 내부 객체를 가져오는것. 값이 없으면 NoSuchElementException 발생 

		// 같은지?  
		assertThat(member).isEqualTo(result); 
	}

	@Test
	void testFindById() {
		// fail("Not yet implemented");
		
		Member member1 = new Member();
		member1.setName("daram1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("daram2");
		repository.save(member2);
		
		// 회원ID로 검색했을때 조건이 맞는지. 여기선 이름으로 한다.
		Member result1 = repository.findById(member1.getId()).get();
		Member result2 = repository.findById(member2.getId()).get();
		
		assertThat(result1.getName()).isEqualTo("daram1");
		assertThat(result2.getName()).isEqualTo("daram2");
		
	}

	@Test
	void testFindByName() {
//		fail("Not yet implemented");
		
		Member member1 = new Member();
		member1.setName("daram");
		repository.save(member1);
		
		// tip. 복사후 변수 일괄변경할 것    
		// 같은변수 일괄변경 - 숏컷 [ alt+shift+r ]
		Member member2 = new Member();
		member2.setName("daram2");
		repository.save(member2);
		

		// "daram"이라는 이름을 가진 회원을 조회한 결과가 member1 과 같아야 한다.
		Member result = repository.findByName("daram").get();
		assertThat(result).isEqualTo(member1); 
	}

	@Test
	void testFindAll() {
//		fail("Not yet implemented");
		
		Member member1 = new Member();
		member1.setName("daram1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("daram2");
		repository.save(member2);
		
		Member member3 = new Member();
		member3.setName("daram3");
		repository.save(member3);
	
		// 회원 리스트를 뽑았을때 총회원수가 3이 맞는지?
		List<Member> result = repository.findAll();
		assertThat(result.size()).isEqualTo(3);
	}

}
/*
 * 이렇게 테스트를 해보았는데.. 테스트코드 작성 순서가 class 작성 순서보다 먼저하게 되면  
 * TDD[Test Driven Development] - "테스트주도개발" 이라고 부른다.
 * 마치 틀을 먼저 만들어 놓고 제품을 틀에 맞추는 것과 같은 개념이다. 
 */