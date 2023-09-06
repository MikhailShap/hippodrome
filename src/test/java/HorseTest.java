import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HorseTest {

    @Test
    void nullNameParameterConstructorExceptionCorrectMessage() {
        Throwable exception  = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 2, 2));
        assertEquals("Name cannot be null.", exception.getMessage());

    }


    @ParameterizedTest
    @ValueSource(strings = {"", " ", "    "})
    void emptyStringOrWhitespaceCharactersParameterNameConstructorExceptionCorrectMessage(String name) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 2, 2));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }


    @Test
    void constructorWhenSpeedParameterIsNegativeExceptionCorrectMessage() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("name", -2, 2));
        assertEquals("Speed cannot be negative.", exception.getMessage());

    }


    @Test
    void constructorWhenDistanceParameterIsNegativeExceptionCorrectMessage() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("name", 2, -2));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }


    @Test
    void getName() {
        Horse horse = new Horse("name", 2, 3);
        assertEquals("name", horse.getName());
    }

    @Test
    void getSpeed() {
        Horse horse = new Horse("name", 2, 3);
        assertEquals(2, horse.getSpeed());
    }

    @Test
    void getDistance() {
        Horse horse = new Horse("name", 2, 3);
        assertEquals(3, horse.getDistance());
    }

    @Test
    void getDistanceWhenObjectCreatedWithTwoParameters() {
        Horse horse = new Horse("name", 2);
        assertEquals(0, horse.getDistance());
    }

    @Test
    void moveCallGetRandomDouble() {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("name", 2, 3);
            horse.move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
    @ParameterizedTest
    @CsvSource({"2,3","4,5","6,7",})
    void move(double speed,double distance){
        Horse horse = new Horse("name", speed, distance);
        try(MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)){
            horseMockedStatic.when(()->Horse.getRandomDouble(0.2, 0.9)).thenReturn(3.0);
            double correctResult = distance + speed * Horse.getRandomDouble(0.2, 0.9);
            horse.move();
            assertEquals(correctResult,horse.getDistance());
            horseMockedStatic.verify(()->Horse.getRandomDouble(0.2,0.9),Mockito.times(2));
        }
    }
}