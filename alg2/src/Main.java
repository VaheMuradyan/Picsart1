import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        long startTime, endTime;

        int [] arr1 = new int[1000];
        for(int i = 0; i < arr1.length; i++){
            arr1[i] = random.nextInt(10000);
        }

        int[][] A = {{1, 2, 3, 4},
                {5, 6, 7, 8},
                {13, 14, 15, 16}};

        int[][] B = {{17, 18, 19, 20},
                {21, 22, 23, 24},
                {25, 26, 27, 28},
                {29, 30, 31, 32}};

        int [][] arr2;

        System.out.println("*****What you wont*****");
        System.out.println("Merge sort: 1");
        System.out.println("Strassens Algorithm: 2");
        System.out.println("Maximum subarray problem i cant :(");
        System.out.println();

        int choice = getUserInput(scanner,1,2);
        System.out.println();
        switch (choice){
            case 1:
                printArray(arr1);
                startTime = System.currentTimeMillis();
                mergeSort(arr1);
                endTime = System.currentTimeMillis();
                System.out.println();
                System.out.println("\n----- After merge sort -----");
                System.out.println();
                printArray(arr1);
                System.out.println();
                System.out.println("\nTook " + (endTime - startTime) + "ms");
                break;
            case 2:
                System.out.println("A = ");
                printArray(A);
                System.out.println();
                System.out.println("B = ");
                printArray(B);

                startTime = System.currentTimeMillis();
                arr2 = strassensAlgorithm(A,B);
                endTime = System.currentTimeMillis();

                System.out.println("\n----- After Strassens algorithm -----");
                System.out.println();
                printArray(arr2);
                System.out.println();
                System.out.println("\nTook " + (endTime - startTime) + "ms");
                break;
        }

    }

    private static void mergeSort(int [] arr){
        int inputLength = arr.length;
        if(inputLength < 2){
            return;
        }

        int midIndex = inputLength / 2;
        int [] leftHalf = new int[midIndex];
        int [] rightHalf = new int[inputLength - midIndex];

        for(int i = 0; i < midIndex; i++){
            leftHalf[i] = arr[i];
        }
        for(int i = midIndex; i < inputLength; i++){
            rightHalf[i - midIndex] = arr[i];
        }

        mergeSort(leftHalf);
        mergeSort(rightHalf);

        merge(arr,leftHalf,rightHalf);
    }

    private static int[][] strassensAlgorithm(int[][] A, int[][] B) {
        int rowsA = A.length;
        int colsA = A[0].length;
        int colsB = B[0].length;

        int[][] result = new int[rowsA][colsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return result;
    }

    private static void merge(int [] arr, int [] leftHalf, int [] rightHalf){
        int leftSize = leftHalf.length;
        int rightSize = rightHalf.length;

        int i = 0, j = 0, k = 0;

        while(i < leftSize && j < rightSize){
            if(leftHalf[i] <= rightHalf[j]){
                arr[k] = leftHalf[i];
                i++;
            }else{
                arr[k] = rightHalf[j];
                j++;
            }
            k++;
        }

        while(i < leftSize){
            arr[k] = leftHalf[i];
            i++;
            k++;
        }

        while(j < rightSize){
            arr[k] = rightHalf[j];
            j++;
            k++;
        }
    }

    private static void printArray(int [] arr){
        for(int x : arr){
            System.out.print(x + " ");
        }
    }

    private static void printArray(int [][] arr){
        for(int [] x : arr){
            for(int y : x){
                System.out.print(y + " ");
            }
            System.out.println();
        }
    }


    private static int getUserInput(Scanner scanner, int min, int max) {
        int choice;
        while (true) {
            System.out.print("Enter your choice: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice >= min && choice <= max) {
                    return choice;
                }
            } else {
                scanner.nextLine();
            }
            System.out.println("Invalid input. Please enter a valid number between " + min + " and " + max);
        }
    }
}