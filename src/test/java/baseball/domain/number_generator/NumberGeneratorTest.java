package baseball.domain.number_generator;

import static org.assertj.core.api.Assertions.*;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NumberGeneratorTest {
	static int generateNumberCount = 3;
	private final INumberGenerator<List<Integer>> numberGenerator = new IntNumberGenerator();

	@DisplayName("1에서 9까지 서로 다른 임의의 수 3개를 생성한다")
	@Test
	void generateIntNumbers() {
		// given
		Date now = new Date();

		// when
		List<Integer> generatedNumbers = numberGenerator.execute(now, 3);

		// then
		assertThat(generatedNumbers).hasSize(3);
		assertThat(generatedNumbers).allMatch(number -> number >= 1 && number <= 9);
	}

	@DisplayName("고정된 날짜(시드)를 입력하면 항상 동일한 숫자 목록을 반환한다")
	@Test
	void generateFixedNumbers() {
		// given
		Date fixedDate = new Date(1000L); // 고정된 타임스탬프 사용

		// when
		List<Integer> result1 = numberGenerator.execute(fixedDate, generateNumberCount);
		List<Integer> result2 = numberGenerator.execute(fixedDate, generateNumberCount);

		// then
		assertThat(result1).isEqualTo(result2);
	}
}
