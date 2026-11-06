package ua.kpi.im42;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    private static int calc(String input) {
        return Calculator.Calculate(Calculator.Parse(input));
    }

    @Test
    void AdditionPositiveTest () {
        Assertions.assertEquals( 4, calc("2 + 2 ="), "2 + 2 = must be 4");
    }

    @Test
    void  SubtractionPositiveTest() {
        Assertions.assertEquals( 4, calc("8 - 4 ="), "8 - 4 = must be 4");
    }

    @Test
    void  DivisionPositiveTest() {
        Assertions.assertEquals( 2, calc("8 / 4 ="), "8 / 4 = must be 2");
    }

    @Test
    void  MultiplicationPositiveTest() {
        Assertions.assertEquals( 4, calc("2 * 2 ="), "2 * 2 = must be 4");
    }

    @Test
    void DivisionByZeroTest (){
        Assertions.assertThrows(ArithmeticException.class,
                () -> Calculator.Calculate(Calculator.Parse("1 0 / 0 =")), "Division by zero must throw ArithmeticException" );
    }

    @Test
    void  BeginningOnZeroTest (){
        Assertions.assertEquals( 6, calc("0 0 3 + 3 ="), "0 0 3 + 3 = must be 6");
    }

    @Test
    void SubtractionNegativeTest () {
        Assertions.assertEquals( -4, calc(" 4 - 8 ="), "4 - 8  = must be -4 ");
    }

    @Test
    void DivisionRoundsDownTest() {
        Assertions.assertEquals( 33, calc("1 0 0 / 3 ="), "100 / 3 = must be 33");
    }


    @Test
    void MultiplicationByZeroTest () {
        Assertions.assertEquals( 0, calc("0 * 1 0 = "), "0 * 1 0 = must be 0");
    }

    @Test
    void MultiDigitOperandsAdditionTest() {
        Assertions.assertEquals(579, calc("1 2 3 + 4 5 6 ="), "123 + 456 must be 579");
    }

    @Test
    void MultiDigitSubtractionTest() {
        Assertions.assertEquals(100, calc("1 2 3 - 2 3 ="), "123 - 23 must be 100");
    }

    @Test
    void SubtractionBiggerSecondOperandTest() {
        Assertions.assertEquals(-90, calc("1 0 - 1 0 0 ="), "10 - 100 must be -90");
    }

    @Test
    void MultiDigitMultiplicationTest() {
        Assertions.assertEquals(220, calc("1 0 * 2 2 ="), "10 * 22 must be 220");
    }

    @Test
    void DivisionSmallerByBiggerIsZeroTest() {
        Assertions.assertEquals(0, calc("9 / 1 0 ="), "9 / 10 must be 0");
    }

    @Test
    void TwoDigitNumberOnScreenTest() {
        Assertions.assertEquals(12, calc("1 2"), "1 2 must show 12");
    }

    @Test
    void ScreenAfterOperatorShowsFirstOperandTest() {
        Assertions.assertEquals(123, calc("1 2 3 +"), "after operator screen must show 123");
    }

    @Test
    void ScreenWhileTypingSecondOperandTest() {
        Assertions.assertEquals(456, calc("1 2 3 + 4 5 6"), "while typing second operand screen must show 456");
    }

    @Test
    void NullInputTest () {
        Assertions.assertEquals(0, calc(null), "if input is null must be 0");
    }

    @Test
    void InputIsBlankTest () {
        Assertions.assertEquals(0, calc("  "), "if input is  blank  must be 0");
    }

    @Test
    void InputIsEmptyTest () {
        Assertions.assertEquals(0, calc(""), "if input is empty must be 0");
    }

    @Test
    void FirstNumberOnlyTest () {
        Assertions.assertEquals(4, calc("4"), "first number must be 4");
    }

    @Test
    void WithoutEqualsTest () {
        Assertions.assertEquals(4, calc("2 + 4"), "Result without Equal number must be 4");
    }

    @Test
    void InvalidLetterKeyTest() {
        Assertions.assertThrows(
                IllegalArgumentException.class, () -> Calculator.Calculate(Calculator.Parse("а")),
                "Letter  must throw IllegalArgumentException" );
    }

    @Test
    void InvalidMultiDigitKeyTest() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> Calculator.Calculate(Calculator.Parse("123")
                ), "Multi Digit Key must throw IllegalArgumentException" );
    }

    @Test
    void InvalidOperatorTest() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> Calculator.Calculate(Calculator.Parse("1 2 3 ++ 4 5 6 =")
                ), "Operator ++ must throw IllegalArgumentException" );
    }

    @Test
    void EqualsWithoutOperationThrowsTest() {
        Assertions.assertThrows(IllegalStateException.class,
                () -> Calculator.Calculate(Calculator.Parse("5 =")
                ), "’=' without operation must throw IllegalStateException" );
    }

    @Test
    void ParseSplitsKeysBySpacesTest() {
        Assertions.assertArrayEquals(
                new String[]{"1", "2", "+", "3"},
                Calculator.Parse("1  2 + 3"));
    }
    @Test
    void KeyAfterEqualsThrowsTest() {
        Assertions.assertThrows(IllegalStateException.class,
                () -> Calculator.Calculate(Calculator.Parse("2 + 2 = + 3 =")));
    }
}