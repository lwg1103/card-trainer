package service.cardtrainer.trainer.infrastructure;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import service.cardtrainer.trainer.domain.Card;
import service.cardtrainer.trainer.domain.HandCalculator;
import service.cardtrainer.trainer.domain.Score;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Objects;

@Component
public class HandCalculatorService implements HandCalculator
{
    @Override
    public Score scoreHand(Card card1, Card card2)
    {
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);

        HttpClient client = HttpClient.newHttpClient();
        try {

            ObjectMapper objectMapper = new ObjectMapper();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://card-hand-calc:8080/score-cards"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(cards)))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            service.cardtrainer.trainer.infrastructure.dto.Score score = objectMapper.readValue(response.body(), new TypeReference<>() {});

            return new Score(score.value);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            System.out.println(e);
        }
        return new Score(0);
    }
}
