package hi.hello.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class JsonController {

    @GetMapping("/hihi")
    public String hihi() {
        return "hihi";
    }

    @ResponseBody
    @PostMapping("/hihi")
    public String mod(@RequestParam String sentence, Model model) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // JSON 데이터로 변환
        String jsonBody = "{\"sentence\": \"" + sentence + "\"}";

        HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://6073-34-173-231-220.ngrok.io/model",
                HttpMethod.POST,
                entity,
                String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(response.getBody());
            String result = jsonNode.get("result").asText();
            model.addAttribute("result", result);
        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
        }

        return "resultView";
    }
}
