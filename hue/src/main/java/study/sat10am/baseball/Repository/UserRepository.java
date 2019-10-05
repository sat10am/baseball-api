package study.sat10am.baseball.Repository;

import org.springframework.stereotype.Repository;
import study.sat10am.baseball.model.Result;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class UserRepository {

    private Map<String, Integer> playCountTable;

    private Map<String, Result> correctResultTable;

    List<Integer> numbers;

    public UserRepository() {
        this.playCountTable = new HashMap<>();
        this.correctResultTable = new ConcurrentHashMap<>();
        this.numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
    }

    public int getPlayCount(String userId) {
        if (playCountTable.containsKey(userId)) {
            return playCountTable.get(userId);
        }

        playCountTable.put(userId, 1);
        return 1;
    }

    public void resetPlayCount(String userId) {
        playCountTable.put(userId, 1);
    }

    public void plusPlayCount(String userId) {
        playCountTable.put(userId, playCountTable.get(userId) + 1);
    }


    public Result getCorrectResult(String userId) {
        if (correctResultTable.containsKey(userId)) {
            return correctResultTable.get(userId);
        }

        Result result = randomResult();
        correctResultTable.put(userId, result);

        return result;
    }

    private Result randomResult() {
        Collections.shuffle(numbers);

        String randomNumbers = numbers.stream()
                .limit(4)
                .map(String::valueOf)
                .collect(Collectors.joining());

        return new Result(randomNumbers);
    }

    public void resetCorrectResult(String userId) {
        correctResultTable.put(userId, randomResult());
    }

}
