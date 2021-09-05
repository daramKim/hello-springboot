package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	// 컨트롤러에 있는 경로가 static에 있는경로와 똑같이 겹히게 되면 컨트롤러의 우선순위가 높다.
	@GetMapping("/")
	public String home() {
		return "home";
	}
}
