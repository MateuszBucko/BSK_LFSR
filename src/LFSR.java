import java.util.ArrayList;
import java.util.List;

/**
 * Created by X on 16.03.2017.
 */
public class LFSR {
    private String polynomial = "";
    private String seed = "";
    private int len = 0;
    private int wsk=0;
    private List<Integer> key = new ArrayList<Integer>();

    public LFSR(String polynomial, String seed){
        this.polynomial = polynomial;
        this.seed = seed;
    }

    public LFSR() {
    }

    public void generate(){
        len = (int) (Math.pow(2, seed.length())-1);
      //  System.out.println(seed);
        for(int i=0;i<len;i++){
            Integer b = 0;
            for(int j=0;j<polynomial.length();j++){
                if(Integer.parseInt(polynomial.substring(j,j+1)) == 1 ){
                   b +=Integer.parseInt(seed.substring(j,j+1));
                }
            }
            b = b%2;
            key.add(b);
            seed = b.toString() + seed.substring(0, seed.length()-1);
          //  System.out.println(seed);
        }

    }

    public Integer generateOne(){
        Integer b = 0;
        for(int j=0;j<polynomial.length();j++){
            if(Integer.parseInt(polynomial.substring(j,j+1)) == 1 ){
                b +=Integer.parseInt(seed.substring(j,j+1));
            }
        }
        b = b%2;

        return b;
    }

    public String getPolynomial() {
        return polynomial;
    }

    public void setPolynomial(String polynomial) {
        this.polynomial = polynomial;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public List<Integer> getKeyStream() {
        return key;
    }

    public Byte getByteLFSR(){
        List<Integer> key = getKeyStream();
        String t = "";

        for(int i=0;i<8;i++){
            t+=key.get(wsk).toString();
            wsk++;
            wsk=wsk%key.size();
        }
        int bInt = Integer.parseInt(t,2);
        return (byte) bInt;
    }

    public int getLen() {
        return len;
    }
}
