package baseball.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import baseball.domain.entity.StrikeZoneResult;

public class PlayView {
	private final Scanner scanner = new Scanner(System.in);

	public List<Integer> readPlayerNumber() {
		System.out.print("숫자를 입력해주세요: ");
		String input = scanner.next();

		List<Integer> numbers = new ArrayList<>();

		for (char c : input.toCharArray()) {
			if (!Character.isDigit(c)) {
				throw new IllegalArgumentException("숫자만 입력해야 합니다.");
			}
			int value = Character.getNumericValue(c);
			if (numbers.contains(value)) {
				throw new IllegalArgumentException("중복된 숫자가 있습니다.");
			}
			numbers.add(value);
		}
		return numbers;
	}

	public void showErrorMessage(String message) {
		System.out.println("[ERROR] " + message);
	}

	public void showUserMatchMessage(StrikeZoneResult result) {
		System.out.print(userMatchRouter(result));
	}

	public void showGameCompletedMessage() {
		System.out.println("게임 끝!");
	}

	private String userMatchRouter(StrikeZoneResult result) {
		StrikeZoneResult.Type resultType = result.getType();
		if (resultType == StrikeZoneResult.Type.COMPLETE) {
			return result.getStrike() + "개의 숫자를 모두 맞히셨습니다! ";
		}
		if (resultType == StrikeZoneResult.Type.NOTHING) {
			return "낫싱\n";
		}
		int ballCnt = result.getBall();
		int strikeCnt = result.getStrike();
		List<String> resList = new ArrayList<>();

		if (strikeCnt > 0)
			resList.add(strikeCnt + "스트라이크");
		if (ballCnt > 0)
			resList.add(ballCnt + "볼");
		return String.join(" ", resList) + "\n";
	}
}

