package ua.kpi.im42;

public class Calculator {

    public static String[] Parse(String input) {
        if (input == null || input.isBlank()) {
            return new String[0];
        }

        return input.trim().split("\\s+");
    }

    public static void HandleKeyPress(
            CalculatorState state, String key) {

        if (state.finished) {
            throw new IllegalStateException(
                    "No keys allowed after '='");
        }

        if (key.matches("[0-9]")) {
            int digit = Integer.parseInt(key);

            if (state.startSecondNumber) {
                state.screen = digit;
            } else {
                state.screen = state.screen * 10 + digit;
            }

            state.startSecondNumber = false;
        }
        else if (key.matches("[+\\-*/]")) {
            state.firstNumber = state.screen;
            state.op = key.charAt(0);
            state.startSecondNumber = true;
        }
        else if (key.equals("=")) {
            switch (state.op) {
                case '+':
                    state.screen = state.firstNumber + state.screen;
                    break;

                case '-':
                    state.screen = state.firstNumber - state.screen;
                    break;

                case '*':
                    state.screen = state.firstNumber * state.screen;
                    break;

                case '/':
                    if (state.screen == 0) {
                        throw new ArithmeticException(
                                "Division by zero");
                    }

                    state.screen = Math.floorDiv(
                            state.firstNumber, state.screen);
                    break;

                default:
                    throw new IllegalStateException(
                            "No operation selected");
            }

            state.startSecondNumber = true;
            state.finished = true;
        }
        else {
            throw new IllegalArgumentException(
                    "Invalid key: " + key);
        }
    }

    public static int Calculate(String[] keys) {
        CalculatorState state = new CalculatorState();

        for (String key : keys) {
            HandleKeyPress(state, key);
        }

        return state.screen;
    }
}