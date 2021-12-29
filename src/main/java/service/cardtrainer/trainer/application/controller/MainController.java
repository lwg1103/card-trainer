package service.cardtrainer.trainer.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import service.cardtrainer.trainer.domain.*;

@RestController
public class MainController
{
    @Autowired
    private Trainer trainer;
    @Autowired
    private MoveFactory moveFactory;

    @PostMapping(path = "/judge-move", produces = "application/json")
    public Note handle(@RequestBody service.cardtrainer.trainer.infrastructure.dto.Move dtoMove) {
        Move move = moveFactory.create(dtoMove);

        return trainer.judgeMove(move);
    }
}
