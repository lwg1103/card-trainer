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
    public service.cardtrainer.trainer.infrastructure.dto.Note handle(@RequestBody service.cardtrainer.trainer.infrastructure.dto.Move dtoMove) {
        Move move = moveFactory.create(dtoMove);

        service.cardtrainer.trainer.infrastructure.dto.Note result = new service.cardtrainer.trainer.infrastructure.dto.Note();

        result.value = (trainer.judgeMove(move) == Note.OK) ? "ok" : "wrong";

        return result;
    }
}
