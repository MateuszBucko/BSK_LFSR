import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        File file = new File("test.bin");
        byte[] fileData = new byte[(int) file.length()];
        DataInputStream dis = new DataInputStream(new FileInputStream(file));
        dis.readFully(fileData);
        dis.close();


        LFSR lfsr = new LFSR("100001","110000");
        lfsr.generate();

    }




}
