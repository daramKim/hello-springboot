package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/*
 * aop 같은 경우는 component 어노테이션을 쓰기 보다는,
 * 명시적으로 SpringConfig에 적어주는게 좋다고함. 
 * 그래야 프로젝트에 어떤것이 적용되어 있는지 구조적으로 파악하는데 도움이 되기 때문.
*/
@Component // bean 등록
@Aspect // aop 어노테이션 
public class TimeTraceAop {
	@Around("execution(* hello.hellospring..*(..))") // hello.hellospring 패키지 하위 전체에 적용하겠다는 뜻.
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		System.out.println("START: " + joinPoint.toString());
		try {
			return joinPoint.proceed(); // 원래 실행하려던 핵심 로직.. 
		} finally {
			long finish = System.currentTimeMillis();
			long timeMs = finish - start;
			System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
		}
	}
}

// AOP를 적용하기 전에 개념을 알 필요가 있다.. 어떨때 적용하는가?

/*
 * 핵심로직과 핵심로직을 실행할때 필수적이지는 않으나, 관심 정도는 있는? 그 정도의 공통적인 로직이
 * 필요하다고 하자.. 그러면 비즈니스 로직을 구현함에 있어 핵심적인 부분 모두에 그 부수적인 로직을 
 * 모두 추가할 것인가? 비즈니스가 커서 로직을 추가해야 될 곳이 많다면? 그리고 수정할때도 또 같은 코드를
 * 많은 부분에 적용해야 된다면.. 너무 비효율적이지 않은가 그럴때 aop를 쓰는것이다. 관심사항 정도의 공통적인 로직을
 * 핵심로직 바깥으로 빼놓고, 그 로직이 필요한 곳이 어딘지 정의해주면 공통적으로 적용이 가능한 것이다.. 이는 여러가지
 * 방식이 있는데, AOP는 프록시 객체 방식을 쓴다. 간단하게 aop를 적용후, 해당 클래스의 className을 찍어보면 알수 있다.
 * -- aop와 같은 결과물을 내는 방법으로 다른 방식으로 구현된 것도 있다고 하는데, 컴파일 타임에 공통로직을 해당 클래스에 
 *    코드로 박아 준다고 한다.     
 */