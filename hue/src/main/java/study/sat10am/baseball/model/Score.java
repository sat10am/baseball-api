package study.sat10am.baseball.model;

import lombok.*;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Score {

    private String judgement;

    private String triedNumber;

    private boolean correct;

    private Integer tryCount;

    public void update(String result) {
        List<String> list = Arrays.asList(countResult(result, "S"), countResult(result, "B"), countResult(result, "O"));

        judgement = list.stream()
                .filter(a -> !StringUtils.isEmpty(a))
                .collect(Collectors.joining(" "));

        this.correct = "4S".equals(judgement);
    }

    private String countResult(String inputs, String word) {
        long count = Arrays.asList(inputs.split(""))
                .stream()
                .filter(w -> w.equals(word))
                .count();

        if (count == 0) {
            return "";
        }

        return count + word;
    }

    public boolean allStrike() {
        return correct;
    }
}
