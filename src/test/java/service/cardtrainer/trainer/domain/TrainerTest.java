package service.cardtrainer.trainer.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TrainerTest
{
    Trainer testSubject;
    HandCalculator handCalculatorMock;
    Note result;
    Move move;

    @ParameterizedTest
    @MethodSource("itJudgeRaisingHandProperlyProvider")
    public void itJudgeRaisingHandProperly(Position pos, int score, Note expectedNote)
    {
        givenCardScoreIs(score);
        givenPositionIs(pos);
        givenDecisionWasToRaise();
        whenCheckingDecision();
        thenResultIs(expectedNote);
    }

    private static Stream<Arguments> itJudgeRaisingHandProperlyProvider()
    {
        return Stream.of(
                Arguments.of(Position.DEALER, 31, Note.OK),
                Arguments.of(Position.DEALER, 29, Note.OK),
                Arguments.of(Position.DEALER, 27, Note.WRONG),
                Arguments.of(Position.MID, 32, Note.OK),
                Arguments.of(Position.MID, 31, Note.OK),
                Arguments.of(Position.MID, 30, Note.WRONG),
                Arguments.of(Position.EARLY, 35, Note.OK),
                Arguments.of(Position.EARLY, 34, Note.OK),
                Arguments.of(Position.EARLY, 33, Note.WRONG)
        );
    }

    @ParameterizedTest
    @MethodSource("itJudgeCheckHandProperlyProvider")
    public void itJudgeCheckHandProperly(Position pos, int score, Note expectedNote)
    {
        givenCardScoreIs(score);
        givenPositionIs(pos);
        givenDecisionWasToCheck();
        whenCheckingDecision();
        thenResultIs(expectedNote);
    }

    private static Stream<Arguments> itJudgeCheckHandProperlyProvider()
    {
        return Stream.of(
                Arguments.of(Position.DEALER, 31, Note.WRONG),
                Arguments.of(Position.DEALER, 29, Note.WRONG),
                Arguments.of(Position.DEALER, 27, Note.OK),
                Arguments.of(Position.DEALER, 25, Note.OK),
                Arguments.of(Position.DEALER, 24, Note.WRONG),
                Arguments.of(Position.MID, 32, Note.WRONG),
                Arguments.of(Position.MID, 31, Note.WRONG),
                Arguments.of(Position.MID, 30, Note.OK),
                Arguments.of(Position.MID, 27, Note.OK),
                Arguments.of(Position.MID, 26, Note.WRONG),
                Arguments.of(Position.EARLY, 35, Note.WRONG),
                Arguments.of(Position.EARLY, 34, Note.WRONG),
                Arguments.of(Position.EARLY, 33, Note.OK),
                Arguments.of(Position.EARLY, 30, Note.OK),
                Arguments.of(Position.EARLY, 29, Note.WRONG)
        );
    }

    @ParameterizedTest
    @MethodSource("itJudgeWaitingHandProperlyProvider")
    public void itJudgeWaitingHandProperly(Position pos, int score, Note expectedNote)
    {
        givenCardScoreIs(score);
        givenPositionIs(pos);
        givenDecisionWasToWait();
        whenCheckingDecision();
        thenResultIs(expectedNote);
    }

    private static Stream<Arguments> itJudgeWaitingHandProperlyProvider()
    {
        return Stream.of(
                Arguments.of(Position.DEALER, 27, Note.WRONG),
                Arguments.of(Position.DEALER, 25, Note.WRONG),
                Arguments.of(Position.DEALER, 24, Note.OK),
                Arguments.of(Position.MID, 30, Note.WRONG),
                Arguments.of(Position.MID, 27, Note.WRONG),
                Arguments.of(Position.MID, 26, Note.OK),
                Arguments.of(Position.EARLY, 33, Note.WRONG),
                Arguments.of(Position.EARLY, 30, Note.WRONG),
                Arguments.of(Position.EARLY, 29, Note.OK)
        );
    }

    private void givenCardScoreIs(int scoreValue)
    {
        Score score = new Score(scoreValue);
        when(handCalculatorMock.scoreHand(any(Card.class), any(Card.class))).thenReturn(score);
    }

    private void givenPositionIs(Position position)
    {
        move.position = position;
    }

    private void givenDecisionWasToRaise()
    {
        move.decision = Decision.RAISE;
    }

    private void givenDecisionWasToCheck()
    {
        move.decision = Decision.CHECK;
    }

    private void givenDecisionWasToWait()
    {
        move.decision = Decision.WAIT;
    }

    private void whenCheckingDecision()
    {
        result = testSubject.judgeMove(move);
    }

    private void thenResultIs(Note note)
    {
        assertEquals(note, result);
    }

    @BeforeEach
    void setUp()
    {
        handCalculatorMock = mock(HandCalculator.class);
        move = new Move();
        testSubject = new Trainer(handCalculatorMock);
    }
}