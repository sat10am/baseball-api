package study.sat10am.baseball.model;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Result {

    private List<Integer> numbers;

    public Result(String numbers) {
        if (numbers.length() != 4) {
            throw new IllegalArgumentException("4개의 숫자를 입력하셔야 합니다.");
        }

        this.numbers = Collections.unmodifiableList(
                Arrays.asList(numbers.split(""))
                        .stream()
                        .map(Integer::parseInt)
                        .collect(Collectors.toList())
        );
    }

    public String match(Result otherResult) {
        String result = "";

        for (int i = 0; i < 4; i++) {
            result += otherResult.match(i, numbers.get(i));
        }

        return result;
    }

    private String match(int index, int number) {
        if (numbers.get(index) == number) {
            return "S";
        }

        if (numbers.contains(number)) {
            return "B";
        }

        return "O";
    }

    public String numbers() {
        return numbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

}
