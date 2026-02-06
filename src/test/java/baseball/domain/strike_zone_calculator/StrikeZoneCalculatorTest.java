package baseball.domain.strike_zone_calculator;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import baseball.domain.entity.BallCountEntity;
import baseball.domain.entity.StrikeZoneResult;
import baseball.domain.score_calculator.BallCalculator;
import baseball.domain.score_calculator.IScoreCalculator;
import baseball.domain.score_calculator.StrikeCalculator;

public class StrikeZoneCalculatorTest {

	IScoreCalculator strikeCalculator;
	IScoreCalculator ballCalculator;

	IStrikeZoneCalculator strikeZoneCalculator;

	static List<Arguments> strikeZoneTestCases() {
		return List.of(
			Arguments.of(List.of(1, 2, 3), List.of(1, 2, 3), StrikeZoneResult.complete(new BallCountEntity(3, 0))),
			Arguments.of(List.of(3, 2, 1), List.of(1, 2, 3), StrikeZoneResult.playing(new BallCountEntity(1, 2))),
			Arguments.of(List.of(3, 1, 2), List.of(1, 2, 3), StrikeZoneResult.playing(new BallCountEntity(0, 3))),
			Arguments.of(List.of(1, 5, 4), List.of(1, 2, 3), StrikeZoneResult.playing(new BallCountEntity(1, 0))),
			Arguments.of(List.of(7, 5, 4), List.of(1, 2, 3), StrikeZoneResult.nothing())
		);
	}

	@BeforeEach
	void setUp() {
		this.strikeCalculator = new StrikeCalculator();
		this.ballCalculator = new BallCalculator(this.strikeCalculator);

	}

	@DisplayName("스트라이크 존 결과 테스트")
	@ParameterizedTest
	@MethodSource("strikeZoneTestCases")
	void strikeZoneResult(List<Integer> human, List<Integer> computer, StrikeZoneResult expected) {

		var sut = new StrikeZoneCalculator(strikeCalculator, ballCalculator);

		var result = sut.execute(human, computer);

		assertThat(result).isEqualTo(expected);
	}
}
