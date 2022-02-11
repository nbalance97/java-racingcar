package racingcar.controller;

import racingcar.model.Car;
import racingcar.util.RandomNumberGenerator;
import racingcar.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class RaceController {
    private static final int DEFAULT_POSITION = 0;
    private List<Car> cars = new ArrayList<>();
    private RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

    public void insertCar(Car car) {
        cars.add(car);
    }

    public void insertCarFromCarNames(String[] carNames) {
        for (String carName : carNames) {
            insertCar(new Car(carName, DEFAULT_POSITION, randomNumberGenerator));
        }
    }

    public int carCount() {
        return cars.size();
    }

    public void moveRound() {
        for (Car car : cars) {
            car.move();
        }
    }


    public String[] getWinner() {
        int maxPosition = getMaxPosition();

        return cars.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .map(Car::getName)
                .toArray(String[]::new);
    }

    private int getMaxPosition() {
        return cars.stream()
                .mapToInt(Car::getPosition)
                .max()
                .getAsInt();
    }

    public void printPosition() {
        OutputView.printResult(cars);
    }
    public void printWinner() {
        OutputView.printWinner(getWinner());
    }
}
