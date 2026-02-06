package baseball.view_controller.home_view_controller;

import java.util.Scanner;

import baseball.view_controller.common.ViewController;

public class HomeViewController implements ViewController {
	private final Scanner sc = new Scanner(System.in);
	public HomeViewControllerDelegate delegate;

	@Override
	public void render() {
		System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
		int userMode = sc.nextInt();
		System.out.println("홈화면 선택" + userMode);
		if (userMode == 1) {
			delegate.userPlaySelected();
			return;
		}
		System.out.println("게임을 종료하였습니다 바이 바이!!");
		delegate.userExitSelected();
	}
}

