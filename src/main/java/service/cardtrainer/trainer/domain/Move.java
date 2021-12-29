package service.cardtrainer.trainer.domain;

public class Move
{
    public Card card1;
    public Card card2;
    public Position position;
    public Decision decision;

    public Move()
    {
        card1 = new Card();
        card2 = new Card();
    }
}
