package baseball.domain.number_generator;

import java.util.Date;

public interface INumberGenerator<T> {
	T execute(Date date, Integer generateNumberCount);
}
