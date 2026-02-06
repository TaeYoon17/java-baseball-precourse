package baseball.domain.score_calculator;

import java.util.List;

public interface IScoreCalculator {
	int calculate(List<Integer> human, List<Integer> computer);
}
