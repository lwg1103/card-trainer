package service.cardtrainer.trainer.domain;

public interface MoveFactory<T>
{
    Move create(T input);
}
