package study.sat10am.baseball.service;

import org.springframework.stereotype.Service;
import study.sat10am.baseball.Repository.UserRepository;
import study.sat10am.baseball.model.Result;
import study.sat10am.baseball.model.Score;
import study.sat10am.baseball.model.ScoreManager;

@Service
public class BaseballService {

    private UserRepository userRepository;

    public BaseballService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Score play(Result userSubmitResult, String userId) {

        int playCount = userRepository.getPlayCount(userId);

        if (playCount > 20) {
            playCount = reset(userId);
        }

        Result correctResult = userRepository.getCorrectResult(userId);
        Score score = ScoreManager.matchResult(correctResult, userSubmitResult, playCount);

        if (score.allStrike()) {
            reset(userId);
            return score;
        }

        userRepository.plusPlayCount(userId);
        return score;
    }

    private int reset(String userId) {
        userRepository.resetPlayCount(userId);
        userRepository.resetCorrectResult(userId);

        return userRepository.getPlayCount(userId);
    }

    public String findCorrectResult(String userId) {
        return userRepository.getCorrectResult(userId).numbers();
    }
}
