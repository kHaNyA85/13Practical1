import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class timeMethods {

    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static int linearSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }


public static int binarySearch(int[] arr, int key) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == key) {
                return mid;
            }
            if (arr[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static int[] generateRandomKeys(int count, int upperBound) {
        Random rand = new Random();
        int[] keys = new int[count];
        for (int i = 0; i < count; i++) {
            keys[i] = rand.nextInt(upperBound);
        }
        return keys;
    }


public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please provide the filename as an argument.");
            return;
        }

        String filename = args[0];
        int[] data = null;

        try (Scanner scanner = new Scanner(new File(filename))) {
            data = new int[100];
            int index = 0;
            while (scanner.hasNextInt()) {
                if (index < data.length) {
                    data[index++] = scanner.nextInt();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            return;
        }



if (data == null || data.length == 0) {
            System.out.println("No data read from file.");
            return;
        }

        Arrays.sort(data);

        int key = generateRandomKeys(1, 100)[0];

        long startTime = System.nanoTime();
        int linearResult = linearSearch(data, key);
        long durationLinear = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        int binaryResult = binarySearch(data, key);
        long durationBinary = System.nanoTime() - startTime;

        System.out.println("Key: " + key);
        System.out.println("Linear Search Index: " + linearResult + ", Time: " + durationLinear + " ns");
        System.out.println("Binary Search Index: " + binaryResult + ", Time: " + durationBinary + " ns");
    }
}
