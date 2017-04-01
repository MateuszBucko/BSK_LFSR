import java.util.List;

/**
 * Created by X on 16.03.2017.
 */
public class SSC {
    private String polynomial = "";
    private String start = "";
    private String ciphertext="";
    private String seedvector =  "";
    private int len = 0;

    public SSC(String polynomial, String start){
        this.polynomial = polynomial;
        this.start = start;
    }

    public SSC() {
    }

    public void generate(){

        System.out.println("Dziala");

        LFSR lfsr = new LFSR();

        lfsr.setPolynomial(polynomial);

        lfsr.setSeed(seedvector);

        lfsr.generate();


        List<Integer> key;

        key = lfsr.getKeyStream();

        System.out.println(key);

        for(int i=0;i<start.length();i++)
        {

            Integer ciphertextnumber=0;

            ciphertextnumber = (key.get(i%lfsr.getLen()) + Integer.parseInt(start.substring(i,i+1)))%2;

            ciphertext= ciphertextnumber.toString() + ciphertext;

            String reverse = new StringBuffer(ciphertext).reverse().toString();

            System.out.println(reverse);


        }

    }

    public String getPolynomial() {
        return polynomial;
    }

    public void setPolynomial(String polynomial) {
        this.polynomial = polynomial;
    }

    public String getSeedvector() {
        return seedvector;
    }

    public void setSeedvector(String seedvector) {
        this.seedvector = seedvector;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }
}
