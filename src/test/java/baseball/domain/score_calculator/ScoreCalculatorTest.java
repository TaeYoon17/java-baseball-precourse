package baseball.domain.score_calculator;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import baseball.domain.strike_zone_calculator.BallCalculator;
import baseball.domain.strike_zone_calculator.IScoreCalculator;
import baseball.domain.strike_zone_calculator.StrikeCalculator;

public class ScoreCalculatorTest {
	IScoreCalculator strikeCalculator;
	IScoreCalculator ballCalculator;

	static List<Arguments> strikeTestCases() {
		return List.of(
			Arguments.of(List.of(1, 2, 3), List.of(1, 2, 3), 3),
			Arguments.of(List.of(3, 2, 1), List.of(1, 2, 3), 1),
			Arguments.of(List.of(3, 1, 2), List.of(1, 2, 3), 0),
			Arguments.of(List.of(1, 5, 4), List.of(1, 2, 3), 1)
		);
	}

	static List<Arguments> ballTestCases() {
		return List.of(
			Arguments.of(List.of(1, 2, 3), List.of(1, 2, 3), 0),
			Arguments.of(List.of(3, 2, 1), List.of(1, 2, 3), 2),
			Arguments.of(List.of(3, 1, 2), List.of(1, 2, 3), 3),
			Arguments.of(List.of(1, 5, 4), List.of(1, 2, 3), 0)
		);
	}

	@BeforeEach
	void setUp() {
		this.strikeCalculator = new StrikeCalculator();
		this.ballCalculator = new BallCalculator(this.strikeCalculator);
	}

	@DisplayName("볼 계산기 테스트")
	@ParameterizedTest
	@MethodSource("ballTestCases")
	void ballCalculator(List<Integer> human, List<Integer> computer, int expected) {
		int result = ballCalculator.calculate(human, computer);
		assertThat(result).isEqualTo(expected);
	}

	@DisplayName("스트라이크 계산기 테스트")
	@ParameterizedTest
	@MethodSource("strikeTestCases")
	void strikeCalculator(List<Integer> human, List<Integer> computer, int expected) {

		int result = strikeCalculator.calculate(human, computer);
		assertThat(result).isEqualTo(expected);
	}
}
