
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner s = new Scanner(System.in);
        System.out.println("Input:");
        String output = calc(s.nextLine());
        System.out.println("Output:\n" + output);

    }

    public static String calc(String input) throws Exception {

        boolean roman = false;
        char[] operands = {'+', '-', '*', '/'};//acceptable operands


        for (char operand : operands) {
            int count = 0;
            for (char c : operands) {
                count += input.length() - input.replace(String.valueOf(c), "").length();//calculate amount of acceptable operands
            }

            if (count == 1) {
                String splitter = String.format("\\%s", operand);
                String[] numsS = input.split(splitter);
                int result = 0;

                if (numsS.length == 2) {
                    int[] nums = new int[2];
                    try {
                        Integer.parseInt(numsS[0]);
                        Integer.parseInt(numsS[1]);
                        nums[0] = Integer.parseInt(numsS[0]);
                        nums[1] = Integer.parseInt(numsS[1]);
                    } catch (NumberFormatException e) {
                        nums[0] = Roman.romanToInteger(numsS[0]);
                        nums[1] = Roman.romanToInteger(numsS[1]);
                        roman = true;
                    }
                    for (int num : nums) {
                        if (num < 1 || num > 10) {
                            throw new Exception("throws Exception");
                        }
                    }
                    result = switch (splitter) {
                        case "\\+" -> nums[0] + nums[1];
                        case "\\-" -> nums[0] - nums[1];
                        case "\\*" -> nums[0] * nums[1];
                        case "\\/" -> nums[0] * nums[1];
                        default -> throw new IllegalStateException("Unexpected value: " + splitter);
                    };
                    if (roman) {
                        if(result<1){
                            throw new Exception("throws Exception");
                        }
                        return Roman.integerToRoman(result);
                    }
                    return String.valueOf(result);

                } else {
                    throw new Exception("throws Exception");
                }
            }
            else throw new Exception("throws Exception");


        }
        throw new Exception("throws Exception");
    }

}



class Roman {

    static private int converse(char ch) throws Exception {
        /**
         *  if was fail during the converse => it isn't a roman numbers
         */
        return switch (ch) {
            case 'I' -> 1;
            case 'V' -> 5;
            case 'X' -> 10;
            case 'L' -> 50;
            case 'C' -> 100;
            default -> throw new Exception("throws Exception");
        };
    }

    boolean isRoman(String[] str) {

        return true;
    }

    static int romanToInteger(String roman) throws Exception {
        int sum = 0;
        int current = converse(roman.charAt(0));

        for (int i = 1; i < roman.length(); i++) {
            int next = converse(roman.charAt(i));
            if (current < next)
                sum -= current;
            else
                sum += current;

            current = next;

        }
        sum += current;

        return sum;
    }

    static String integerToRoman(int number) {

        String numRoman = "";
        String[] roman = new String[]{"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] value = new int[]{100, 90, 50, 40, 10, 9, 5, 4, 1};

        for (int pos = 0; number > 0; ++pos) {
            while (number >= value[pos]) {
                numRoman += roman[pos];
                number -= value[pos];
            }
        }

        return numRoman;
    }
}