import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static boolean isTrueSequence(String s) {
        if (s.equals("") || ((s.length() % 2) != 0)) { //проверка, что строка не пустая и количество скобок в строке четное
            return false;
        } else {
            char[] array = s.toCharArray(); //запись строки в символьный массив
            for (char c : array) {  //проверка, что в строке нет других символов, кроме скобок
                if (c != '{' && c != '[' && c != '(' && c != '}' && c != ']' && c != ')') {
                    return false;
                }
            }
        }
        while (s.contains("()") || s.contains("[]") || s.contains("{}")) { //
            s = s.replaceAll("\\(\\)", "")
                    .replaceAll("\\[\\]", "")
                    .replaceAll("\\{\\}", "");
        }
        return (s.length() == 0);
    }

    public static void task1() {
        System.out.println("Задание 1:");
        System.out.print("Введите число> ");
        int a = new Scanner(System.in).nextInt();
        if (a > 7) {
            System.out.println("Привет");
        }
        else{
            System.out.println("Введенное число меньше или равно 7");
        }
    }

    public static void task2() {
        System.out.println("Задание 2:");
        System.out.print("Введите имя> ");
        String name = new Scanner(System.in).nextLine();
        System.out.println((name.equals("Вячеслав") ? "Привет, " + name : "Нет такого имени"));

    }

    public static void task3() {
        System.out.println("Задание 3:");

        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("Дан массив" + Arrays.toString(array));
        System.out.println("Числа кратные 3:");
        for (int j : array) {
            if (j % 3 == 0) System.out.print(j + " ");
        }
        System.out.println();
    }

    public static void task4() {
        System.out.println("Задание 4:");
        System.out.print("Введите скобочную последовательность> ");

        String sequence = new Scanner(System.in).nextLine();
        System.out.println("Скобочная последовательность " + (isTrueSequence(sequence) ? "верна" : "не верна"));

    }

    public static void main(String[] args) {
        task1();
        System.out.println();

        task2();
        System.out.println();

        task3();
        System.out.println();

        task4();
        System.out.println();
    }


    @DataProvider(name = "sequenceData")
    public Object[][] sequenceData() {
        return new Object[][]{
                {"{()[]}", true},
                {"", false},
                {"()[", false},
                {"()[]1}", false},
                {"[((())()(())]]", false}
        };
    }

    @Test(description = "Проверка скобочной последовательности",
            dataProvider = "sequenceData")
    public void checkSequenceTest(String data, boolean isCorrect) {
        Assert.assertEquals(isCorrect, isTrueSequence(data), "Скобочная последовательность не верна");
    }
}