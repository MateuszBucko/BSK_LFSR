/**
 * Created by X on 16.03.2017.
 */
public class LFSR {
    private String polynomial = "";
    private String start = "";
    private int len = 0;

    public LFSR(String polynomial, String start){
        this.polynomial = polynomial;
        this.start = start;
    }

    public LFSR() {
    }

    public void generate(){
        len = (int) (Math.pow(2,start.length())-1);
        System.out.println(start);
        for(int i=0;i<len;i++){
            Integer b = 0;
            for(int j=0;j<polynomial.length();j++){
                if(Integer.parseInt(polynomial.substring(j,j+1)) == 1 ){
                   b +=Integer.parseInt(start.substring(j,j+1));
                }
            }
            b = b%2;
            start = b.toString() + start.substring(0,start.length()-1);
            System.out.println(start);
        }

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
}
