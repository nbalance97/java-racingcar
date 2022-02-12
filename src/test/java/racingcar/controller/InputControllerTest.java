package racingcar.controller;

import org.junit.jupiter.api.Test;
import racingcar.controller.InputController;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class InputControllerTest {
    final InputController inputController = new InputController();

    @Test
    public void 자동차_이름_나누기() {
        String cars = "pobi,crong,honux";
        assertThat(inputController.carSplit(cars)).contains("pobi", "crong", "honux");
    }

    @Test
    public void 숫자_입력() {
        String scanNumber = "3";
        int actual = inputController.toInt(scanNumber);
        assertThat(Integer.parseInt(scanNumber)).isEqualTo(actual);
    }
}
