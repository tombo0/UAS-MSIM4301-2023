import java.util.Scanner;

class HitungNilai {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan nilai: ");
        int input = scanner.nextInt();
        if (input < 0) {
            System.out.println("Input invalid");
        }else if (input <= 60) {
            System.out.println("PT D");
        } else if (input <= 70) {
            System.out.println("PT C");
        } else if (input <= 80) {
            System.out.println("PT B");
        } else {
            System.out.println("PT A");
        }

        scanner.close();
    }
}