package mn.edu.must.sqat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GradeCalculatorTest {

    private final GradeCalculator calculator = new GradeCalculator();

    @Test
    void totalScoreShouldReturnCorrectTotal() {
        assertEquals(85, calculator.totalScore(30, 25, 30));
    }

    @Test
    void totalScoreShouldThrowExceptionWhenTotalIsNegative() {
        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.totalScore(-10, -20, 20)
        );
    }

    @Test
    void totalScoreShouldThrowExceptionWhenTotalExceeds100() {
        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.totalScore(50, 30, 30)
        );
    }

    @Test
    void letterGradeShouldReturnAFor90() {
        assertEquals("A", calculator.letterGrade(90));
    }

    @Test
    void letterGradeShouldReturnBFor80() {
        assertEquals("B", calculator.letterGrade(80));
    }

    @Test
    void letterGradeShouldReturnCFor70() {
        assertEquals("C", calculator.letterGrade(70));
    }

    @Test
    void letterGradeShouldReturnDFor60() {
        assertEquals("D", calculator.letterGrade(60));
    }

    @Test
    void letterGradeShouldReturnFFor59() {
        assertEquals("F", calculator.letterGrade(59));
    }
}
