package study.sat10am.baseball.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.sat10am.baseball.model.Result;
import study.sat10am.baseball.service.BaseballService;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/baseball")
public class BaseBallController {

    private BaseballService baseballService;

    public BaseBallController(BaseballService baseballService) {
        this.baseballService = baseballService;
    }

    @GetMapping
    public ResponseEntity play(String numbers, HttpSession session) {
        return ResponseEntity.ok(
                baseballService.play(new Result(numbers), session.getId())
        );
    }

    @GetMapping("/result")
    public ResponseEntity getCorrectResult(HttpSession session) {
        return ResponseEntity.ok(
                baseballService.findCorrectResult(session.getId())
        );
    }
}
