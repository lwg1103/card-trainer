package service.cardtrainer.trainer.infrastructure.dto;

import org.springframework.stereotype.Component;
import service.cardtrainer.trainer.domain.Card;
import service.cardtrainer.trainer.domain.Decision;
import service.cardtrainer.trainer.domain.Position;
import service.cardtrainer.trainer.domain.exception.InvalidDecisionException;
import service.cardtrainer.trainer.domain.exception.InvalidPositionException;

@Component
public class MoveFactory implements service.cardtrainer.trainer.domain.MoveFactory<Move>
{
    @Override
    public service.cardtrainer.trainer.domain.Move create(Move input)
    {
        service.cardtrainer.trainer.domain.Move move = new service.cardtrainer.trainer.domain.Move();

        move.decision = convertDecision(input.move);
        move.position = convertPosition(input.position);

        move.card1.figure = input.card1Figure;
        move.card1.color = input.card1Color;
        move.card2.figure = input.card2Figure;
        move.card2.color = input.card2Color;

        return move;
    }

    private Decision convertDecision(String move)
    {
        switch (move) {
            case "wait":
                return Decision.WAIT;
            case "check":
                return Decision.CHECK;
            case "raise":
                return Decision.RAISE;
            default:
                throw new InvalidDecisionException();
        }
    }

    private Position convertPosition(String position)
    {
        switch (position) {
            case "dealer":
                return Position.DEALER;
            case "mid":
                return Position.MID;
            case "early":
                return Position.EARLY;
            default:
                throw new InvalidPositionException();
        }
    }
}
