package baseball.view_controller.home_view_controller;

import baseball.domain.entity.UserPlayMode;
import baseball.view.HomeView;
import baseball.view_controller.common.ViewController;

public class HomeViewController implements ViewController {
	private final HomeView homeView = new HomeView();
	public HomeViewControllerDelegate delegate;

	@Override
	public void render() {
		try {
			UserPlayMode mode = homeView.readUserPlayGame();
			if (mode == UserPlayMode.Start) {
				delegate.userPlaySelected();
				return;
			}

			if (mode == UserPlayMode.Finish) {
				homeView.showUserEndGameMessage();
				delegate.userExitSelected();
			}
		} catch (IllegalArgumentException e) {
			homeView.showErrorMessage(e.getMessage());
			render();
		}
	}
}


