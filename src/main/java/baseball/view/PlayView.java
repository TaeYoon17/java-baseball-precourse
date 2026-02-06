package baseball.view;

import java.util.List;
import java.util.Scanner;

public class PlayView {

	List<Integer> input() {
		Scanner sc = new Scanner(System.in);
		System.out.print("숫자를 입력해주세요 : ");
		String userRawNumber = sc.next();
		sc.close();
		for (int i = 0; i < userRawNumber.length(); i += 1) {
			
		}
		return List.of(1, 2, 3);
	}
}

