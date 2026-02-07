package baseball.view;

import java.util.Scanner;

import baseball.domain.entity.UserPlayMode;

public class HomeView {
	private final Scanner scanner = new Scanner(System.in);

	public void askForGameOption() {
		System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
	}

	public UserPlayMode readUserPlayGame() {
		String input = scanner.nextLine().trim(); // 앞뒤 공백 제거 및 줄 단위 읽기
		try {
			int userMode = Integer.parseInt(input);

			if (userMode == 1) {
				return UserPlayMode.Start;
			}
			if (userMode == 2) {
				return UserPlayMode.Finish;
			}
			throw new IllegalArgumentException("1 또는 2만 입력 가능합니다..");
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("숫자가 아닌 잘못된 형식을 입력하셨습니다.");
		}
	}

	public void showErrorMessage(String message) {
		System.out.println("[ERROR] " + message);
	}

	public void showUserEndGameMessage() {
		System.out.println("게임을 종료하였습니다 바이 바이!!");
	}
}
