package hello.hellospring;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;

@Configuration
public class SpringConfig {

	 @Autowired
	 private final MemberRepository memberRepository;
	
	 public SpringConfig(MemberRepository memberRepository) {
		 this.memberRepository = memberRepository;
	 }
	 
	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}

	/*
	 * 개방-폐쇄 원칙(OCP, Open-Closed Principle)
	 * - 확장에는 열려있고, 수정, 변경에는 닫혀있다.
	 * 
	 * 스프링의 DI(Dependencies Injection)을 사용
	 * - 기존 코드를 전혀 손대지 않고, 설정만으로 구현 클래스를 변경할 수 있다.
	 */
	@Bean
	public MemberRepository memberRepository() {
		// return new MemoryMemberRepository();
		// return new JdbcMemberRepository(dataSource);
		// return new JdbcTemplateMemberRepository(dataSource);
		return new JpaMemberRepository(em);
	}
}
