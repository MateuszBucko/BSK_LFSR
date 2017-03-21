/**
 * Created by X on 21.03.2017.
 */
public class CA {
    private String polynomial = "";
    private String start = "";
    private String ciphertext="";
    private String seedvector =  "";
    private int len = 0;

    public CA(){
    }

    public void generate(){
        
    }


    public String getPolynomial() {
        return polynomial;
    }

    public void setPolynomial(String polynomial) {
        this.polynomial = polynomial;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getCiphertext() {
        return ciphertext;
    }

    public void setCiphertext(String ciphertext) {
        this.ciphertext = ciphertext;
    }

    public String getSeedvector() {
        return seedvector;
    }

    public void setSeedvector(String seedvector) {
        this.seedvector = seedvector;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }
}
