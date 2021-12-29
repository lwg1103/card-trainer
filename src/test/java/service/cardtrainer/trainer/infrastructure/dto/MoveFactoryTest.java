package service.cardtrainer.trainer.infrastructure.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.cardtrainer.trainer.domain.Decision;
import service.cardtrainer.trainer.domain.Move;
import service.cardtrainer.trainer.domain.Position;
import service.cardtrainer.trainer.domain.exception.InvalidDecisionException;
import service.cardtrainer.trainer.domain.exception.InvalidPositionException;

import static org.junit.jupiter.api.Assertions.*;

class MoveFactoryTest
{
    MoveFactory testSubject;
    private service.cardtrainer.trainer.infrastructure.dto.Move dtoMove;

    @Test
    public void itCreatesMove()
    {
        assertInstanceOf(Move.class, testSubject.create(dtoMove));
    }

    @Test
    public void itConvertsDecision()
    {
        dtoMove.move = "wait";
        assertEquals(Decision.WAIT, testSubject.create(dtoMove).decision);
        dtoMove.move = "check";
        assertEquals(Decision.CHECK, testSubject.create(dtoMove).decision);
        dtoMove.move = "raise";
        assertEquals(Decision.RAISE, testSubject.create(dtoMove).decision);
    }

    @Test
    public void itThrowsExceptionIfMoveIsNotValid()
    {
        dtoMove = new service.cardtrainer.trainer.infrastructure.dto.Move();
        dtoMove.move = "blabla";

        assertThrows(InvalidDecisionException.class, () -> {testSubject.create(dtoMove);});
    }

    @Test
    public void itConvertsPosition()
    {
        dtoMove.position = "dealer";
        assertEquals(Position.DEALER, testSubject.create(dtoMove).position);
        dtoMove.position = "mid";
        assertEquals(Position.MID, testSubject.create(dtoMove).position);
        dtoMove.position = "early";
        assertEquals(Position.EARLY, testSubject.create(dtoMove).position);
    }

    @Test
    public void ItThrowsExceptionIfPositionIsNotValid()
    {
        dtoMove.position = "blabla";

        assertThrows(InvalidPositionException.class, () -> {testSubject.create(dtoMove);});
    }

    @Test
    public void itFillsCardFields()
    {
        dtoMove.card1Color = "aaa";
        dtoMove.card1Figure = "bbb";
        dtoMove.card2Color = "ccc";
        dtoMove.card2Figure = "ddd";

        Move domainMove = testSubject.create(dtoMove);

        assertEquals(dtoMove.card1Color, domainMove.card1.color);
        assertEquals(dtoMove.card1Figure, domainMove.card1.figure);
        assertEquals(dtoMove.card2Color, domainMove.card2.color);
        assertEquals(dtoMove.card2Figure, domainMove.card2.figure);
    }

    @BeforeEach
    void setUp()
    {
        dtoMove = new service.cardtrainer.trainer.infrastructure.dto.Move();
        dtoMove.move = "check";
        dtoMove.position = "dealer";
        testSubject = new MoveFactory();
    }
}