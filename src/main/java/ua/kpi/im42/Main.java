package ua.kpi.im42;

public class Main {
    public static void main(String[] args) {
        String input = "1 2 3 + 4 5 6 =";

        String[] keys = Calculator.Parse(input);

        int result = Calculator.Calculate(keys);

        System.out.println(result);
    }
}
