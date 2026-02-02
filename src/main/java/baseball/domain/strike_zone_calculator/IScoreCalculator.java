package baseball.domain.strike_zone_calculator;

import java.util.List;

public interface IScoreCalculator {
	int calculate(List<Integer> human, List<Integer> computer);
}
