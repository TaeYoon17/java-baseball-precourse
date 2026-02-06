package baseball.domain.strike_zone_calculator;

import java.util.List;

import baseball.domain.entity.StrikeZoneResult;
import baseball.domain.score_calculator.IScoreCalculator;

public interface IStrikeZoneCalculator {
	IScoreCalculator strikeCalculator = null;
	IScoreCalculator ballCalculator = null;

	StrikeZoneResult execute(List<Integer> human, List<Integer> computer);
}
