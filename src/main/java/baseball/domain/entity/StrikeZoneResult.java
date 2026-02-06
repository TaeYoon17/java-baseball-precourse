package baseball.domain.entity;

import java.util.Objects;

public class StrikeZoneResult {
	private final Type type;
	private final BallCountEntity ballCount;

	// 생성자들을 private으로 막고 Factory 메서드 사용
	private StrikeZoneResult(Type type, BallCountEntity ballCount) {
		this.type = type;
		this.ballCount = ballCount;
	}

	// Factory 메서드들 (Swift의 case 생성자 역할)
	public static StrikeZoneResult nothing() {
		return new StrikeZoneResult(Type.NOTHING, new BallCountEntity(0, 0));
	}

	public static StrikeZoneResult playing(BallCountEntity ballCount) {
		return new StrikeZoneResult(Type.PLAYING, ballCount);
	}

	public static StrikeZoneResult complete(BallCountEntity ballCount) {
		return new StrikeZoneResult(Type.COMPLETE, ballCount);
	}

	// Getter
	public Type getType() {
		return type;
	}

	public BallCountEntity get() {
		return this.ballCount;
	}

	public int getBall() {
		return this.ballCount.ball;
	}

	public int getStrike() {
		return this.ballCount.strike;
	}

	@Override
	public boolean equals(Object o) {
		// 1. 주소값이 같으면 같은 객체
		if (this == o)
			return true;
		// 2. null이거나 클래스 타입이 다르면 다른 객체
		if (o == null || getClass() != o.getClass())
			return false;

		// 3. 타입 캐스팅 후 필드값 비교
		StrikeZoneResult that = (StrikeZoneResult)o;
		return type == that.type && Objects.equals(ballCount, that.ballCount);
	}

	@Override
	public int hashCode() {
		// equals에 사용된 필드들로 해시코드 생성
		return Objects.hash(type, ballCount);
	}

	// 내부 Enum 정의 (종류 구분용)
	public enum Type {NOTHING, PLAYING, COMPLETE}
}
