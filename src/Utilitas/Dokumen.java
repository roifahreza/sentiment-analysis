
package Utilitas;
import java.util.*;
import Utilitas.*;

public class Dokumen {
    private int DokKe;
    private String judul;
    private String teks;
    private String katagori;
    public HashMap<String, Bobot> infoText;
    private Vector allTerm;

    public Dokumen(int idDok, String text, String kat) {
        //  System.out.println(text);
        this.DokKe = idDok;
        this.teks = text;
        this.katagori = kat;
        this.allTerm = new Vector();
        infoText = HitungInfo(this.teks);
    }

    public Dokumen(int idDok, String text) {
        this.DokKe = idDok;
        this.teks = text;
        this.allTerm = new Vector();
        infoText = HitungInfo(this.teks);
    }

    public Dokumen() {
    }

    // Struktur data untuk menyinpan term dan frekuensinya
    public HashMap<String, Bobot> HitungInfo(String text) {
        StringTokenizer tjl = new StringTokenizer(text);
        int n = tjl.countTokens();

        HashMap<String, Bobot> h = new HashMap<String, Bobot>();
        for (int i = 0; i < n; i++) {
            String kunci = tjl.nextToken();
            this.allTerm.addElement(kunci);
            
            if (!h.containsKey(kunci)) {
                h.put(kunci, new Bobot());
            } else {
                Bobot b = h.get(kunci);
                b.Naikkan();
                h.put(kunci, b);
            }
        }
        return h;
    }

    // untuk mendapatkan semua term unik dari judul dan teks
    public Vector getAllTerm() {
        return this.allTerm;
    }

    public HashMap<String, Bobot> getInfoText() {
        return this.infoText;
    }

    public int getIdDok() {
        return this.DokKe;
    }
    public String getText(){
    return this.teks;}

    public String getKat() {
        return this.katagori;
    }

    public void cetakTermUnix() {
        System.out.println(" \ntext    ");
        for (Map.Entry<String, Bobot> entri : infoText.entrySet()) {
            System.out.print(entri.getKey() + " : ");
            System.out.println(entri.getValue().getFrek());
        }
    }
    public void cetakAlterm(){
        for (int i = 0; i < allTerm.size(); i++) {
            System.out.println(""+allTerm.get(i));
        }
    }
    public static void main(String[] args) {
        ArrayList<Dokumen> lat = new  ArrayList<Dokumen>();
                lat.add(new Dokumen(0,"makanan  ini ini enak ","baik"));
                lat.add(new Dokumen(1,"sehat selalu","baik"));
        lat.get(0).cetakTermUnix();
        lat.get(1).cetakTermUnix();
                
    }
    
}
