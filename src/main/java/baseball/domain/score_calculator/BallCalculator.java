package baseball.domain.score_calculator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BallCalculator implements IScoreCalculator {

	private final IScoreCalculator strikeCalculator;

	public BallCalculator(IScoreCalculator strikeCalculator) {
		this.strikeCalculator = strikeCalculator;
	}

	@Override
	public int calculate(List<Integer> human, List<Integer> computer) {
		if (human.size() != computer.size()) {
			throw new IllegalArgumentException("두 수의 갯수가 다릅니다!");
		}
		Set<Integer> humanSet = new HashSet<>(human);
		Set<Integer> computerSet = new HashSet<>(computer);

		humanSet.retainAll(computerSet);
		return Integer.max(0, humanSet.size() - strikeCalculator.calculate(human, computer));
	}
}
