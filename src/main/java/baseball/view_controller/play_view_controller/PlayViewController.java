package baseball.view_controller.play_view_controller;

import java.util.List;
import java.util.Scanner;

import baseball.domain.number_generator.INumberGenerator;
import baseball.view_controller.common.ViewController;

public class PlayViewController implements ViewController {
	private final INumberGenerator<List<Integer>> numberGenerator;
	public PlayViewControllerDelegate delegate;

	public PlayViewController(
		INumberGenerator<List<Integer>> numberGenerator
	) {
		this.numberGenerator = numberGenerator;
	}

	@Override
	public void render() {
		while (true) {
			System.out.println("아무 키나 누르세요");
			Scanner sc = new Scanner(System.in);
			String input = sc.next();
			break;
		}
		delegate.playFinished();
	}
}
