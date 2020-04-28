import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.String;
import java.util.Arrays;

public class Solution {
    public static int fDigit2;
    public static int sDigit2;
    public static boolean flag1;
    public static boolean flag2;
    public static int answer;
    public static int operation = -1;
    public static int indexOper;
    public static String [] rome = new String[]{"","I","II","III","IV","V","VI","VII","VIII","IX","X"};

    public static void main (String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String example = reader.readLine();
        String example2 = example.replace(" ","");  //удаляем лишние пробелы
        defOperation(example2);                 //получаем действие
        defFirstDigit(example2);                //получаем 1 цифру
        defSecondDigit(example2);               //получаем 2 цифру
        calc(indexOper, fDigit2, sDigit2);      //считаем
        printAnswer(flag1, flag2, answer);      //выводим ответ
    }

    private static void defOperation(String example2) throws NumberOutOfRangeException{
        //создание массива операций
        String oper = "+-*/";
        char asd[] = oper.toCharArray();
        char qwe[] = example2.toCharArray();

        //поиск в ведённой строке знака с действием
        for (int i = 1; i < qwe.length; i++) {
            for (int j = 0; j < asd.length; j++) {
                if (qwe[i] == asd[j]) {
                    operation = i;
                    indexOper = j;
                    break;
                }
            }
        }
        if (operation == -1)
            throw new NumberOutOfRangeException("Указана неподходящая операция");
    }

    private static void defFirstDigit(String example2) throws NumberOutOfRangeException {
        String fDigit1 = example2.substring(0, operation);
        fDigit2 = Arrays.asList(rome).indexOf(fDigit1);

        if (fDigit2 > 0)
            flag1 = true;
        else
            fDigit2 = Integer.parseInt(fDigit1);

        if (fDigit2 < 1 || fDigit2 > 10)
            throw new NumberOutOfRangeException("Первое слагаемое не входит в диапазон значений калькулятора");
    }

    private static void defSecondDigit(String example2) throws NumberOutOfRangeException, IncorrectNumberRecordingSystemException {
        String sDigit1 = example2.substring(operation + 1);
        sDigit2 = Arrays.asList(rome).indexOf(sDigit1);
        if (sDigit2 > 0)
            flag2 = true;
        else
            sDigit2 = Integer.parseInt(sDigit1);

        if (sDigit2 < 1 || sDigit2 > 10)
            throw new NumberOutOfRangeException("Второе слагаемое не входит в диапазон значений калькулятора");
        if (flag1 != flag2)
            throw new IncorrectNumberRecordingSystemException("Введены числа разных числовых систем");
    }

    private static int calc (int indexOper, int a, int b) {
        switch (indexOper) {
            case 0:                 //  +
                answer = a + b;
                break;
            case 1:                 //  -
                answer = a - b;
                break;
            case 2:                 //  *
                answer = a * b;
                break;
            case 3:                 //  /
                answer = a / b;
                break;
            default:
                throw new IllegalStateException("Неподходящее действие");
        }
        return answer;
    }

    private static String transform_number_to_roman_numeral(int number) {
        int[] roman_value_list = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] roman_char_list = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < roman_value_list.length; i++) {
            while (number >= roman_value_list[i]){
                number -= roman_value_list[i];
                res.append(roman_char_list[i]);
            }
        }
        return res.toString();
    }

    private static void printAnswer(boolean flag1, boolean flag2, int answer) {
        if (flag1 && flag2)
            System.out.println(transform_number_to_roman_numeral(answer));
        else
            System.out.println(answer);
    }
}
