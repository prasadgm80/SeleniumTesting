import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.*;

public class JavaCodesPractice {

    public void reverseString(String str) {
        String reversed = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            char chrs = str.charAt(i);
            reversed = reversed + chrs;
        }
        System.out.println(("Reversed String is: " + reversed));
    }

    public void secondHighestNumber() {
        int[] arr = {4, 1, 3, 7, 9, 11, 10, 15, 15};
        int highest = 0;
        int secondHighest = 0;

        for (int num : arr) {
            if (num > highest) {
                secondHighest = highest;
                highest = num;
            } else if (num > secondHighest && num <= highest) {
                secondHighest = num;
            }
        }
        System.out.println("Highest number is: " + highest + " and second highest number is: " + secondHighest);
    }

    public void reverseInteger(int num) {
        int reversed = 0;
        while (num != 0) {
            int remainder = num % 10;
            reversed = (reversed * 10) + remainder;
            num = num / 10;
        }
        System.out.println("Reversed Number is: " + reversed);
    }

    public void specialCharacterCount(String str) {
        List<Character> hs = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            int count = 0;
            char chrs = str.charAt(i);
            boolean isSpecialCharacter = !Character.isLetterOrDigit(chrs);

            if (isSpecialCharacter) {
                if (!hs.contains(chrs)) {
                    hs.add(chrs);

                    for (int j = 0; j < str.length(); j++) {
                        char chrs1 = str.charAt(j);
                        if (chrs == chrs1) {
                            count++;
                        }
                    }
                    System.out.println("Special character " + chrs + " is repeated " + count + " times");
                }
            }
        }
    }

    public void sortingArray() {
        int[] arr = {-1, 5, -3, 1, -5, -7, 3, 7};
        int k = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    k = arr[i];
                    arr[i] = arr[j];
                    arr[j] = k;
                }
            }
        }
        System.out.println("Sorted array is : " + Arrays.toString(arr));
    }


    public void sortArrayWithCharacters() {
        String input = "12534";
        int[] arr = new int[input.length()];
        for (int i = 0; i < input.length(); i++) {
            arr[i] = Integer.parseInt((String.valueOf((input.charAt(i)))));
        }
        int k = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] < arr[j]) {
                    k = arr[i];
                    arr[i] = arr[j];
                    arr[j] = k;
                }
            }
        }
        StringBuilder sorted = new StringBuilder();
        for (int j : arr) {
            sorted.append(j);
        }
        System.out.println("Sorted string is :" + sorted);
    }

    public void hashMapPractice() {
        HashMap<Character, Integer> hs = new HashMap<>();
//        hs.put('P', 4);
//        hs.put('R', 6);
        String name = "prasad";
        int count = 0;
        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);
            try {
                count = hs.get(ch) + 1;
            } catch (NullPointerException e) {
                count = 1;
            }
            hs.put(ch, count);
        }

        for (Character ch : hs.keySet()) {
            System.out.println("Character is " + ch + "Key is " + hs.get(ch));
        }
    }

    public void listToSetTransfer() {
        List<String> ls = new ArrayList<>();
        ls.add("Prasad");
        ls.add("Aditi");
        ls.add("Akshay");
        ls.add("Aditi");
        ls.add("Prasad");

        HashSet<String> hs = new HashSet<>(ls);
        System.out.println(hs);
    }

    public void hcfAndLCM() {
        int a = 20;
        int b = 30;
        int hcf = 0;
        int lcm = 0;
        int min = Math.min(a, b);

        for (int i = min; i > 1; i--) {
            if (a % i == 0 && b % i == 0) {
                hcf = i;
                break;
            }
        }
        lcm = (a * b) / hcf;
        System.out.println("HCF is : " + hcf);
        System.out.println("LCM is : " + lcm);
    }

    public void maxValueMultiDimensionArray() {
        int arr[][] = {{1, 5, 8}, {4, 7, 11}, {2, 15, 6}};
        int max = 0;
        int column = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i][j] > max) {
                    max = arr[i][j];
                    column = j;
                }
            }
        }
        System.out.println("Max Number is: " + max + " in column :" + column);
    }

    public void fibonacci() {
        int start = 0;
        int second = 1;
        int count = 0;
        int fib = 0;

        // 0 1 1 2 3 5

        while (count <= 10) {
            System.out.println(fib);
            fib = start + second;
            second = start;
            start = fib;
            count++;
        }
    }

    public boolean primeNumber(int num) {
        int min = (int) Math.round(Math.sqrt(75));
        System.out.println("Min is " + min);

        if (num == 1) {
            return false;
        }

        if (num == 2) {
            return true;
        }
        for (int i = 3; i < min; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public void swapNodes() {
        int[] input = {1, 3, 5, 7, 9, 11, 13};
        int temp;
        for (int i = 0; i < input.length; i = i + 2) {
            if (i + 1 >= input.length) {
                break;
            } else {
                temp = input[i];
                input[i] = input[i + 1];
                input[i + 1] = temp;
            }
        }

        for (int num : input) {
            System.out.println(num);
        }
    }

    public void uniqueNumberInArray() {
        int[] arr = {1, 2, 3, 5, 7, 9, 11, 1, 2, 3, 7, 5, 11};
        HashMap<Integer, Integer> hs = new HashMap<>();
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            try {
                count = hs.get(arr[i]) + 1;
            } catch (NullPointerException e) {
                count = 1;
            }
            hs.put(arr[i], count);
        }

        for (int key : hs.keySet()) {
            if (hs.get(key) == 1) {
                System.out.println("Integer is unique: " + key);
            }
        }
    }

    public void uniqueNumberInArrayDifferentApproach() {
        int[] arr = {1, 2, 3, 5, 7, 9, 11, 1, 2, 3, 7, 5, 11};
        HashMap<Integer, Integer> hs = new HashMap<>();

        for (int num : arr) {
            hs.put(num, hs.getOrDefault(num, 0) + 1);
        }

        for (int key : hs.keySet()) {
            if (hs.get(key) == 1) {
                System.out.println("Integer is unique: " + key);
            }
        }
    }

    public void dateFormatValidator() {
        String[] dates = {"ae-12-2026", "99-12-2026", "12-12-2026"};

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for (String date : dates) {
            try {
                LocalDate.parse(date, formatter);
                System.out.println("Date is valid date: " + date);
            } catch (DateTimeParseException e) {
                System.out.println("Date is invalid date: " + date);
            }
        }
    }


    public static void main(String[] args) {
        JavaCodesPractice jc = new JavaCodesPractice();
//        jc.reverseString("prasad");
//        jc.secondHighestNumber();
//        jc.reverseInteger(256);
//        jc.specialCharacterCount("prasad@#$^&^%@#");
//        jc.sortingArray();
//        jc.sortArrayWithCharacters();
//        jc.hashMapPractice();
//        jc.listToSetTransfer();
//        jc.hcfAndLCM();
//        jc.maxValueMultiDimensionArray();
//        jc.fibonacci();
//        System.out.println("Number is prime: "+jc.primeNumber(13));
//        jc.swapNodes();
//        jc.uniqueNumberInArray();
//        jc.uniqueNumberInArrayDifferentApproach();
        jc.dateFormatValidator();
    }
}
