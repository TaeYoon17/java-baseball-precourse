package baseball.domain.strike_zone_calculator;

import java.util.List;

import baseball.domain.entity.BallCountEntity;
import baseball.domain.entity.StrikeZoneResult;
import baseball.domain.score_calculator.IScoreCalculator;

///  Strike Zone Ball Count를 연산하는 객체
public class StrikeZoneCalculator implements IStrikeZoneCalculator {
	private final IScoreCalculator strikeCalculator;
	private final IScoreCalculator ballCalculator;

	public StrikeZoneCalculator(
		IScoreCalculator strikeCalculator,
		IScoreCalculator ballCalculator
	) {
		this.strikeCalculator = strikeCalculator;
		this.ballCalculator = ballCalculator;
	}

	@Override
	public StrikeZoneResult execute(List<Integer> human, List<Integer> computer) {
		if (human.size() != computer.size()) {
			throw new IllegalStateException("각 답의 개수가 맞지 않습니다!");
		}

		int strikeCount = strikeCalculator.calculate(human, computer);
		int ballCount = ballCalculator.calculate(human, computer);
		BallCountEntity entity = new BallCountEntity(strikeCount, ballCount);

		if (strikeCount == computer.size())
			return StrikeZoneResult.complete(entity);
		if (entity.strike == 0 && entity.ball == 0)
			return StrikeZoneResult.nothing();
		return StrikeZoneResult.playing(entity);
	}
}
