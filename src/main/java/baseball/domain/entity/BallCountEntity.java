package baseball.domain.entity;

import java.util.Objects;

public class BallCountEntity {
	public int ball;
	public int strike;

	public BallCountEntity(int strikeCount, int ballCount) {
		this.strike = strikeCount;
		this.ball = ballCount;
	}

	@Override
	public boolean equals(Object o) {
		// 1. 자기 자신과 비교하면 true
		if (this == o)
			return true;

		// 2. null이거나 클래스 종류가 다르면 false
		if (o == null || getClass() != o.getClass())
			return false;

		// 3. 값을 비교 (strike와 ball이 모두 같아야 true)
		BallCountEntity that = (BallCountEntity)o;
		return ball == that.ball && strike == that.strike;
	}

	@Override
	public int hashCode() {
		// 객체의 값을 기반으로 고유 번호 생성 (HashMap 등에서 필수)
		return Objects.hash(ball, strike);
	}
}
