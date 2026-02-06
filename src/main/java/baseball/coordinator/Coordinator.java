package baseball.coordinator;

import java.util.List;

public interface Coordinator {
	List<Coordinator> childCoordinators = List.of();

	void start();
}
