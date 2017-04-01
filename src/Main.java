import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static void main(String[] args) throws IOException {

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
                        lfsr.setSeed(number);
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
                    SSC ssc = new SSC();

                    System.out.print("Choose polynomial: ");
                    String polynomial = sc.nextLine();
                    ssc.setPolynomial(polynomial);

                    System.out.print("Choose seed vector: ");
                    String seed = sc.nextLine();
                    ssc.setSeedvector(seed);

                    System.out.print("Choose number: ");
                    String number = sc.nextLine();
                    ssc.setStart(number);

                    ssc.generate();

                    break;


                case 3:
                    System.out.print("Choose polynomial: ");
                    String polynomialSSC = sc.nextLine();


                    System.out.print("Choose seed vector: ");
                    String seedSSC = sc.nextLine();

                    System.out.print("File name to encode: ");
                    String fileName = sc.nextLine();

                    System.out.print("Result file name: ");
                    String resultFileName = sc.nextLine();


                    LFSR lfsr = new LFSR();
                    lfsr.setPolynomial(polynomialSSC);
                    lfsr.setSeed(seedSSC);
                    lfsr.generate();


                    File file = new File(fileName);
                    byte[] fileData = new byte[(int) file.length()];
                    DataInputStream dis = new DataInputStream(new FileInputStream(file));
                    dis.readFully(fileData);


                    byte[] result = new byte[fileData.length];

                    for (int i = 0; i < fileData.length; i++) {
                        System.out.println("BYTE : " + lfsr.getByteLFSR());
                        byte xor = (byte) (lfsr.getByteLFSR() ^ fileData[i]);
                        result[i] = xor;

                    }
                    Files.write(Paths.get(resultFileName), result);
                    dis.close();
                    break;
                case 4:
                    System.out.print("Choose polynomial: ");
                    String autokeyPolynomial = sc.nextLine();


                    System.out.print("Choose seed vector: ");
                    String autokeySeed = sc.nextLine();

                    System.out.print("File name to encode: ");
                    String autoKeyFileNameToEncode = sc.nextLine();

                    System.out.print("Result file name: ");
                    String autoKeyFileNameResult = sc.nextLine();

                    LFSR autoKeyLfsr = new LFSR();
                    autoKeyLfsr.setPolynomial(autokeyPolynomial);
                    autoKeyLfsr.setSeed(autokeySeed);
                    autoKeyLfsr.generate();


                    File fileToEncode = new File(autoKeyFileNameToEncode);
                    byte[] data = new byte[(int) fileToEncode.length()];
                    DataInputStream diss = new DataInputStream(new FileInputStream(fileToEncode));
                    diss.readFully(data);


                    int i = 0;
                    byte[] resultArray = new byte[(int) fileToEncode.length()];

                    for (byte b : data) {

                        // System.out.println(i/data.length * 100 + "%");
                        String bits = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
                        String res = "";
                        for (char bb : bits.toCharArray()) {
                            Integer xored = ((int) bb + autoKeyLfsr.generateOne()) % 2;
                            autoKeyLfsr.setSeed(xored.toString() + autoKeyLfsr.getSeed().substring(0, autoKeyLfsr.getSeed().length() - 1));

                            res += xored.toString();

                        }
                        Byte resByte = (byte) (int) Integer.valueOf(res, 2);

                        resultArray[i] = resByte;
                        i++;
                    }

                    Files.write(Paths.get(autoKeyFileNameResult), resultArray);
                    diss.close();
                    break;

                case 5:
                    System.out.print("Choose polynomial: ");
                    String autokeyPolynomialDec = sc.nextLine();


                    System.out.print("Choose seed vector: ");
                    String autokeySeedDec = sc.nextLine();

                    System.out.print("File name to encode: ");
                    String autoKeyFileNameToDecode = sc.nextLine();

                    System.out.print("Result file name: ");
                    String autoKeyFileNameDecoded = sc.nextLine();

                    LFSR autoKeyLfsrDecode = new LFSR();
                    autoKeyLfsrDecode.setPolynomial(autokeyPolynomialDec);
                    autoKeyLfsrDecode.setSeed(autokeySeedDec);
                    autoKeyLfsrDecode.generate();


                    File fileToDecode = new File(autoKeyFileNameToDecode);
                    byte[] dataToDecode = new byte[(int) fileToDecode.length()];
                    DataInputStream disss = new DataInputStream(new FileInputStream(fileToDecode));
                    disss.readFully(dataToDecode);


                    int j = 0;
                    byte[] resArray = new byte[(int) fileToDecode.length()];

                    for (byte dataBytes : dataToDecode) {
                        String bits = String.format("%8s", Integer.toBinaryString(dataBytes & 0xFF)).replace(' ', '0');
                        String res = "";
                        for (char bb : bits.toCharArray()) {
                            Integer xx = ((int) bb + autoKeyLfsrDecode.generateOne()) % 2;
                            autoKeyLfsrDecode.setSeed(bb + (autoKeyLfsrDecode.getSeed().substring(0, autoKeyLfsrDecode.getSeed().length() - 1)));

                            res += xx.toString();
                        }


                        Byte resByte = (byte) (int) Integer.valueOf(res, 2);

                        resArray[j] = resByte;
                        j++;
                    }
                    Files.write(Paths.get(autoKeyFileNameDecoded), resArray);
                    disss.close();
                    break;

            }

        }


    }

    private static void createMenu() {
        System.out.println(ANSI_RED + "1. Generate LFSR ");
        System.out.println("2. SSC_Encode");
        System.out.println("3. SSC form File test.bin");
        System.out.println("4. Cipher autokey encrypt");
        System.out.println("5. Cipher autokey decrypt" + ANSI_RESET);
    }


}
