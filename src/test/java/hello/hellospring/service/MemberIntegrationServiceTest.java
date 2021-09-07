package hello.hellospring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;


/*
 * 
*/

@SpringBootTest // 스프링부트 실행환경과 동일하게 테스트환경을 셋팅한다. -- Spring 컨테이너의 bean(components)을 사용함. 
@Transactional // 테스트 환경에서 @Transactional을 쓰면 테스트코드를 DB에 commit하지 않음. -- 테스트 시작전 트랜잭션 처리, 끝나면 롤백 
class MemberIntegrationServiceTest {

	@Autowired MemberRepository repo;
	@Autowired MemberService memberService; 
	
	@Test
	void 회원가입() { // 테스트 코드는 build 되는 코드가 아니다 보니 직관적으로 메소드명을 한글형으로 쓰는 경우도 많다고 함. 
		
		//given
		Member m1 = new Member();
		m1.setName("daram");
		
		//when
		Long saveId = memberService.join(m1);
		
		//then
		Member findOneFromService = memberService.findOne(saveId).get();
		assertThat(m1.getName()).isEqualTo(findOneFromService.getName());
	}
	
	@Test
	void 중복가입예외발생() { 
		// given
		Member mem1 = new Member();
		mem1.setName("daram1");
		
		Member mem2 = new Member();
		mem2.setName("daram1");
		
		// when
		memberService.join(mem1); 
		/*// exception 발생시 이런식으로 TEST_CODE에 try_cactch 절을 사용해서 테스트가 가능하나
		  // 권장되는 방식이 아니다. 이런 경우를 위해 Assertions 에는 assertThrows 라는 메소드를 지원한다.    
		try { 
			memberService.join(mem2);
			fail("가입 돼면 안돼는데?");
		} catch (IllegalStateException e) {
			// TODO: handle exception
			assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
		}
		*/
		
		// assertThrows(Exception class, ramda[excutable] )
		// mem2가 회원가입하려 할때 IllegalStateException 이 발생하면 통과(정상) 
		IllegalStateException testReturnException = assertThrows(IllegalStateException.class, () -> memberService.join(mem2)); 
		
		// assertThrows는 테스트하는 ramda의 Exception을 반환하는데, 이것을 통해 에러메세지 까지 세부적으로 테스트 가능하다. 
		assertThat(testReturnException.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
		
		// then 
		
	}

	@Test
	void testFindAllMembers() {
		// fail("Not yet implemented");
	}

	@Test
	void testFindOne() {
		// fail("Not yet implemented");
	}

}
