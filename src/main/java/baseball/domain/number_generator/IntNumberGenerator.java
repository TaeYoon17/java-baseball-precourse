package baseball.domain.number_generator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class IntNumberGenerator implements INumberGenerator<List<Integer>> {
	
	@Override
	public List<Integer> execute(Date date) {
		Random random = new Random(date.getTime());
		List<Integer> numbers = new ArrayList<>();
		while (numbers.size() < 3) {
			int number = random.nextInt(9) + 1;
			if (numbers.contains(number))
				continue;
			numbers.add(number);
		}
		return numbers;
	}
}
