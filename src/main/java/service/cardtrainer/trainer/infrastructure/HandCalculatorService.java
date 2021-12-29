package service.cardtrainer.trainer.infrastructure;

import org.springframework.stereotype.Component;
import service.cardtrainer.trainer.domain.Card;
import service.cardtrainer.trainer.domain.HandCalculator;
import service.cardtrainer.trainer.domain.Score;

@Component
public class HandCalculatorService implements HandCalculator
{
    @Override
    public Score scoreHand(Card card1, Card card2)
    {
        return new Score(30);
    }
}
