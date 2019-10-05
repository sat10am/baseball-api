package study.sat10am.baseball.model;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreManagerTest {

    private Result correctResult;

    private Result userSubmitResult;

    private Score expectedScore;

    private Score scoreResult;

    @Before
    public void setup() {
        correctResult = new Result("1234");
    }

    @Test(expected = IllegalArgumentException.class)
    public void 입력_가능한_길이와_다른_숫자가_들어온_경우() {
        userSubmitResult = new Result("12345");
    }

    @Test
    public void 모두_맞춘_경우() {
        String inputNumber = "1234";
        userSubmitResult = new Result(inputNumber);


        scoreResult = ScoreManager.matchResult(correctResult, userSubmitResult, 4);

        expectedScore = Score.builder()
                .judgement("4S")
                .correct(true)
                .triedNumber(inputNumber)
                .tryCount(4)
                .build();

        assertThat(scoreResult).isEqualTo(expectedScore);
    }

    @Test
    public void 모두_볼인_경우() {
        String inputNumber = "4123";
        userSubmitResult = new Result(inputNumber);

        scoreResult = ScoreManager.matchResult(correctResult, userSubmitResult, 3);

        expectedScore = Score.builder()
                .judgement("4B")
                .correct(false)
                .triedNumber(inputNumber)
                .tryCount(3)
                .build();

        assertThat(scoreResult).isEqualTo(expectedScore);
    }


    @Test
    public void 모두_아웃인_경우() {
        String inputNumber = "5678";
        userSubmitResult = new Result(inputNumber);

        scoreResult = ScoreManager.matchResult(correctResult, userSubmitResult, 2);

        expectedScore = Score.builder()
                .judgement("4O")
                .correct(false)
                .triedNumber(inputNumber)
                .tryCount(2)
                .build();

        assertThat(scoreResult).isEqualTo(expectedScore);
    }

    @Test
    public void 여러_값이_섞인_경우() {
        String inputNumber = "1347";
        userSubmitResult = new Result(inputNumber);

        scoreResult = ScoreManager.matchResult(correctResult, userSubmitResult, 1);

        expectedScore = Score.builder()
                .judgement("1S 2B 1O")
                .correct(false)
                .triedNumber(inputNumber)
                .tryCount(1)
                .build();

        assertThat(scoreResult).isEqualTo(expectedScore);

    }
}
