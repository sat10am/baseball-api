package study.sat10am.baseball.model;

public class ScoreManager {

    public static Score matchResult(Result correctResult, Result userSubmitResult, int playCount) {
        String result = correctResult.match(userSubmitResult);

        Score score = Score.builder()
                .triedNumber(userSubmitResult.numbers())
                .tryCount(playCount)
                .build();

        score.update(result);

        return score;
    }

}
