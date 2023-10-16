import java.util.Scanner;

class ArabicRomeCalc {

    public static void main(String[] args)  throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение, состоящее из двух переменных от 1 до 10: ");
        String expression = scanner.nextLine();
        System.out.println(parse(expression));
    }
    public static String parse(String expression) throws  Exception {
        int num1;
        int num2;
        String result;
        boolean isRoman;

        String preparedExp = expression.replace(" ", "");
        String[] operands = preparedExp.split("[+\\-*/]");

        if (operands.length != 2) throw new Exception("Введите два числа");

        if (RomanNumb.isRoman(operands[0]) && RomanNumb.isRoman(operands[1])) {
            num1 = RomanNumb.convertToArabic(operands[0]);
            num2 = RomanNumb.convertToArabic(operands[1]);
            isRoman = true;
        }
        else if (!RomanNumb.isRoman(operands[0]) && !RomanNumb.isRoman(operands[1])) {
            num1 = Integer.parseInt(operands[0]);
            num2 = Integer.parseInt(operands[1]);
            isRoman = false;
        }
        else {
            throw new Exception("Используются одновременно разные системы счисления. Введите числа в одном формате!");
        }
        if (num1 > 10 || num2 > 10) {
            throw new Exception("Введите числа от 1 до 10!");
        }
        String operator = preparedExp.replaceAll("[0-9IVX]", "");
        char[] op = operator.toCharArray();
        String oper = detectOperation(Character.toString(op[0]));

        int arabic = calc(num1, num2, oper);
        if (isRoman) {
            if (arabic <= 0) {
                throw new Exception("В римской системе счисления нет отрицательных чисел");
            }
            result = (RomanNumb.convertToRoman(arabic));
        }
        else {
            result = String.valueOf(arabic);
        }
        return preparedExp + "=" + result;
    }

    static String detectOperation(String expression) throws Exception {
        return switch (expression) {
            case "+", "-", "*", "/" -> expression;
            default -> throw new Exception("Строка не является математической операцией");
        };
    }
    static int calc(int a, int b, String oper) {
        return switch (oper) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            default -> a / b;
        };
    }
}
