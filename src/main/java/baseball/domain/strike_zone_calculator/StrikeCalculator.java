package baseball.domain.strike_zone_calculator;

import java.util.List;

public class StrikeCalculator implements IScoreCalculator {
	@Override
	public int calculate(List<Integer> human, List<Integer> computer) {
		if (human.size() != computer.size()) {
			throw new IllegalArgumentException("두 수의 갯수가 다릅니다!");
		}
		int count = 0;
		for (int i = 0; i < human.size(); i++) {
			if (human.get(i).equals(computer.get(i))) {
				count++;
			}
		}
		return count;
	}
}
