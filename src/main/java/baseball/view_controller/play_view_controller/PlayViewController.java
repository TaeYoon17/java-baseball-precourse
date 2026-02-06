package baseball.view_controller.play_view_controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import baseball.domain.entity.StrikeZoneResult;
import baseball.domain.number_generator.INumberGenerator;
import baseball.domain.strike_zone_calculator.IStrikeZoneCalculator;
import baseball.view_controller.common.ViewController;

public class PlayViewController implements ViewController {
	private final INumberGenerator<List<Integer>> numberGenerator;
	private final IStrikeZoneCalculator strikeZoneCalculator;
	public PlayViewControllerDelegate delegate;

	public PlayViewController(
		INumberGenerator<List<Integer>> numberGenerator,
		IStrikeZoneCalculator strikeZoneCalculator
	) {
		this.numberGenerator = numberGenerator;
		this.strikeZoneCalculator = strikeZoneCalculator;
	}

	@Override
	public void render() {
		List<Integer> computerNumber = numberGenerator.execute(new Date(), 3);
		while (true) {
			List<Integer> userNumber = tempInputParser();
			StrikeZoneResult result = strikeZoneCalculator.execute(userNumber, computerNumber);
			var output = tempOutputRouter(result);
			System.out.print(output);
			if (result.getType() == StrikeZoneResult.Type.COMPLETE) {
				System.out.println("게임 끝!");
				break;
			}
		}
		delegate.playFinished();
	}

	private List<Integer> tempInputParser() {
		System.out.print("숫자를 입력해주세요: ");
		Scanner sc = new Scanner(System.in);
		String input = sc.next();

		List<Integer> numbers = new ArrayList<>();

		// 문자열을 문자 배열로 변환하여 반복
		for (char c : input.toCharArray()) {
			// Character.getNumericValue(c)는 문자 '3'을 숫자 3으로 바꿔줍니다.
			// (단순 형변환 (int)c를 하면 아스키코드 값 51이 나오므로 주의!)
			numbers.add(Character.getNumericValue(c));
		}

		return numbers;
	}

	private String tempOutputRouter(StrikeZoneResult result) {
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
