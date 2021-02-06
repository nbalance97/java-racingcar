package racing.controller;

import racing.domain.Cars;
import racing.domain.RacingGameMachine;
import racing.domain.dto.CarDto;
import racing.view.InputView;
import racing.view.OutputView;

import java.util.List;

public class RacingController {

    public void run() {
        RacingGameMachine racingGameMachine = initializeRacingGame();
        OutputView.printGameResultHeader();
        while (racingGameMachine.canPlay()) {
            racingGameMachine.play();
            List<CarDto> carDtos = racingGameMachine.getCarDtos();
            OutputView.printRacingTryResult(carDtos);
        }
        List<String> winnerNames = racingGameMachine.findWinnerNames();
        OutputView.printWinnerNames(winnerNames);
    }

    private RacingGameMachine initializeRacingGame() {
        String carNames = InputView.getCarNames();
        Cars cars = Cars.generate(carNames);
        int tryCounts = InputView.getTryCounts();
        return new RacingGameMachine(cars, tryCounts);
    }
}