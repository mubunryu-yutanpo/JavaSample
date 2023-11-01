package  com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SpringTest {
	
	@GetMapping("springtest") // ここがURLになる
	public String test() {
		return "SpringBootの勉強中";
	}
}