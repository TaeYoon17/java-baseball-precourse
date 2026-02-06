package baseball;

import java.util.List;

import baseball.domain.number_generator.INumberGenerator;
import baseball.domain.number_generator.IntNumberGenerator;
import baseball.domain.score_calculator.BallCalculator;
import baseball.domain.score_calculator.IScoreCalculator;
import baseball.domain.score_calculator.StrikeCalculator;
import baseball.domain.strike_zone_calculator.IStrikeZoneCalculator;
import baseball.domain.strike_zone_calculator.StrikeZoneCalculator;

public class DIContainer {
	public static DIContainer shared = new DIContainer();

	public Domain domain;
	public Util util;

	private DIContainer() {
		this.util = new Util();
		this.domain = new Domain(util);
	}

	public static class Util {
		Util() {

		}
	}

	public static class Domain {
		public INumberGenerator<List<Integer>> integerNumberGenerator;
		public IStrikeZoneCalculator strikeZoneCalculator;
		public IScoreCalculator strikeCalculator;
		public IScoreCalculator ballCalculator;

		Domain(Util util) {
			integerNumberGenerator = new IntNumberGenerator();
			strikeCalculator = new StrikeCalculator();
			ballCalculator = new BallCalculator(strikeCalculator);
			strikeZoneCalculator = new StrikeZoneCalculator(this.strikeCalculator, this.ballCalculator);
		}
	}
}
