import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static void main(String[] args) throws IOException {



/*        File file = new File("test.bin");
        byte[] fileData = new byte[(int) file.length()];
        DataInputStream dis = new DataInputStream(new FileInputStream(file));
        dis.readFully(fileData);
        dis.close();*/

        Scanner sc = new Scanner(System.in);
        int choose = -1;

        while (choose != 0) {
            createMenu();
            System.out.print(ANSI_GREEN + "Choose option: " + ANSI_RESET);
            try {
                choose = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Choose number !");
                sc.nextLine();
                choose = -1;

            }

            switch (choose) {

                case 1:
                    try {
                        LFSR lfsr = new LFSR();
                        System.out.print("Choose polynomial: ");
                        String polynomial = sc.nextLine();
                        lfsr.setPolynomial(polynomial);
                        System.out.print("Choose number: ");
                        String number = sc.nextLine();
                        lfsr.setStart(number);
                        if (polynomial.length() == number.length()) {
                            lfsr.generate();
                        } else {
                            System.out.println("Polynomial and number should have the same length !");
                            break;
                        }
                        break;
                    } catch (Exception e) {
                        System.out.println("Wrong data !");
                        break;
                    }
                case 2:
                    break;
                case 3:
                    break;

            }
        }


    }

    private static void createMenu() {
        System.out.println(ANSI_RED + "1. Generate LFSR ");
        System.out.println("2. TODO");
        System.out.println("3. TODO" + ANSI_RESET);
    }


}
