import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {
    @Test
    void constructorArgumentNullThrowExceptionCorrectMessage() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void constructorArgumentEmptyListThrowExceptionCorrectMessage() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(Collections.emptyList()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }
    @Test
    void getHorses(){
        List<Horse> horsesList = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            horsesList.add(new Horse("Horse"+i,i * 1.0, i * 2.0));
        }
        Hippodrome hippodrome = new Hippodrome(horsesList);
        assertEquals(horsesList,hippodrome.getHorses());
    }
    @Test
    void move(){
        List<Horse> horsesList = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            horsesList.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horsesList);
        hippodrome.move();
        for (Horse horse : horsesList) {
            Mockito.verify(horse).move();
        }
    }
    @Test
    void getWinner(){
        List<Horse> horsesList = new ArrayList<>();
        horsesList.add(new Horse("name",1, 20));
        horsesList.add(new Horse("name",1, 10));
        horsesList.add(new Horse("name",1, 50));
        Hippodrome hippodrome = new Hippodrome(horsesList);
        assertEquals(50,hippodrome.getWinner().getDistance());
    }

}