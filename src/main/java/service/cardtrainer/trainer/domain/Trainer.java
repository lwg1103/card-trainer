package service.cardtrainer.trainer.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Trainer
{
    @Autowired
    HandCalculator handCalculator;

    public Trainer(HandCalculator handCalculator)
    {
        this.handCalculator = handCalculator;
    }

    public Note judgeMove(Move move)
    {
        Score score = handCalculator.scoreHand(move.card1, move.card2);

        switch (move.position) {
            case DEALER:
                return judgeLatePosition(move.decision, score.value);
            case MID:
                return judgeMidPosition(move.decision, score.value);
            case EARLY:
                return judgeEarlyPosition(move.decision, score.value);
        }

        return Note.OK;
    }

    private Note judgeLatePosition(Decision decision, int score)
    {
        return judgeDecisionInBoundaries(decision, score, 25, 29);
    }

    private Note judgeMidPosition(Decision decision, int score)
    {
        return judgeDecisionInBoundaries(decision, score, 27, 31);
    }

    private Note judgeEarlyPosition(Decision decision, int score)
    {
        return judgeDecisionInBoundaries(decision, score, 30, 34);
    }

    private Note judgeDecisionInBoundaries(Decision decision, int score, int lowBoundary, int highBoundary)
    {
        switch (decision) {
            case RAISE:
                return (score >= highBoundary) ? Note.OK : Note.WRONG;
            case CHECK:
                return (score >= lowBoundary && score < highBoundary) ? Note.OK : Note.WRONG;
            case WAIT:
                return (score < lowBoundary) ? Note.OK : Note.WRONG;
            default:
                return Note.WRONG;
        }
    }
}
