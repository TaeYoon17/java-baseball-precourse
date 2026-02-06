package baseball.coordinator;

import baseball.DIContainer;
import baseball.view_controller.home_view_controller.HomeViewController;
import baseball.view_controller.home_view_controller.HomeViewControllerDelegate;
import baseball.view_controller.play_view_controller.PlayViewController;
import baseball.view_controller.play_view_controller.PlayViewControllerDelegate;

public class MainCoordinator implements Coordinator, PlayViewControllerDelegate, HomeViewControllerDelegate {
	private final DIContainer diContainer = DIContainer.shared;
	private HomeViewController homeViewController;
	private PlayViewController playViewController;

	// --- Coordinator 구현 ---
	@Override
	public void start() {
		showPlay();
	}

	// --- HomeViewControllerDelegate 구현 ---
	@Override
	public void userPlaySelected() {
		showPlay();
	}

	@Override
	public void userExitSelected() {
		homeViewController = null;
		playViewController = null;
	}

	// --- PlayViewControllerDelegate 구현 ---
	@Override
	public void playFinished() {
		showHome();
	}

	private void showHome() {
		this.homeViewController = new HomeViewController();
		this.homeViewController.delegate = this;
		this.homeViewController.render();
	}

	private void showPlay() {
		// PlayViewController에 필요한 의존성을 여기서 생성하고 주입합니다.
		this.playViewController = new PlayViewController(
			diContainer.domain.integerNumberGenerator,
			diContainer.domain.strikeZoneCalculator
		);
		this.playViewController.delegate = this;
		this.playViewController.render();
	}
}
