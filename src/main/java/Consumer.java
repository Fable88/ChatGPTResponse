import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

public class Consumer {

    public static void main(String[] args) throws JsonProcessingException {
        String url = "https://api.openai.com/v1/chat/completions";
        String key = "";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + key);

        System.out.println("¬ведите запрос:");
        Scanner scanner = new Scanner(System.in);
        String prompt = scanner.nextLine();
        String jsonBody = "{\"model\": \"gpt-3.5-turbo\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";

        HttpEntity<String> request = new HttpEntity<>(jsonBody, headers);
        GptResponse response = restTemplate.postForObject(url, request, GptResponse.class);

        System.out.println(response.getChoices().get(0).getMessage().getContent());
    }
}
