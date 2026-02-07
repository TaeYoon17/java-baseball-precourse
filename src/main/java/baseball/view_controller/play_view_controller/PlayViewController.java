package baseball.view_controller.play_view_controller;

import java.util.Date;
import java.util.List;

import baseball.domain.entity.StrikeZoneResult;
import baseball.domain.number_generator.INumberGenerator;
import baseball.domain.strike_zone_calculator.IStrikeZoneCalculator;
import baseball.view.PlayView;
import baseball.view_controller.common.ViewController;

public class PlayViewController implements ViewController {
	private final INumberGenerator<List<Integer>> numberGenerator;
	private final IStrikeZoneCalculator strikeZoneCalculator;
	private final PlayView playView = new PlayView();
	private final int generateNumber;
	public PlayViewControllerDelegate delegate;

	public PlayViewController(
		int generateNumber,
		INumberGenerator<List<Integer>> numberGenerator,
		IStrikeZoneCalculator strikeZoneCalculator
	) {
		this.generateNumber = generateNumber;
		this.numberGenerator = numberGenerator;
		this.strikeZoneCalculator = strikeZoneCalculator;
	}

	@Override
	public void render() {
		List<Integer> computerNumber = numberGenerator.execute(new Date(), generateNumber);
		while (true) {
			try {
				List<Integer> userNumber = playView.readPlayerNumber();
				if (userNumber.size() != generateNumber) {
					throw new IllegalArgumentException("유저 입력 형식이 맞지 않습니다!!");
				}
				StrikeZoneResult result = strikeZoneCalculator.execute(userNumber, computerNumber);
				playView.showUserMatchMessage(result);
				if (result.getType() == StrikeZoneResult.Type.COMPLETE) {
					playView.showGameCompletedMessage();
					break;
				}
			} catch (IllegalArgumentException e) { // ( ) 괄호 추가!
				playView.showErrorMessage(e.getMessage());
			}
		}
		delegate.playFinished();
	}
}
