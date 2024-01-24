package vue.server.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {

	private final String externalUrl = "https://kauth.kakao.com/oauth/authorize";
	
	public String sendGetRequest() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(externalUrl, String.class);
		
		// 이제 response에서 필요한 작업을 수행할 수 있습니다.
		return response.getBody();
	}
	
	@GetMapping("/login")
	@ResponseBody
	public void  login( HttpServletResponse res) throws IOException {
		log.debug("login.............");
		PrintWriter out  = res.getWriter();
		out.println(sendGetRequest());
		out.flush();
	}

}
