package Utilitas;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import preprocessing.connect;

public class NaiveBayes {

    ArrayList<Double> P_D = new ArrayList<>();
    ArrayList<Double> N_D = new ArrayList<>();
    ArrayList<String> Hasil = new ArrayList<>();
    TermFrek Latih_Positif;
    TermFrek Latih_Negatif;
    TermFrek Uji;
    ResultSet LatPositif, LatNegatif, Data_Uji;
    Vector Allterm;
    private HashMap<String, Bobot> h;

    public NaiveBayes(ResultSet LatPositif, ResultSet LatNegatif, ResultSet Data_Uji) throws SQLException, IOException {
        Latih_Positif = new TermFrek();
        Latih_Negatif = new TermFrek();
        Uji = new TermFrek();
        this.LatPositif = LatPositif;
        this.LatNegatif = LatNegatif;
        this.Data_Uji = Data_Uji;
        Allterm = new Vector();
        h = new HashMap<String, Bobot>();

    }

    public void Latih_Positif() throws SQLException, IOException {
        Latih_Positif.insertDokumen(LatPositif);

    }
    public  TermFrek getDok(){
    return Uji;}

    public void Latih_Negatif() throws SQLException, IOException {
        Latih_Negatif.insertDokumen(LatNegatif);
    }
    public void Dok_Uji() throws SQLException, IOException {
        Uji.insertDokumenUji(Data_Uji);
    }

    public int Jum_Positif() {
        return Latih_Positif.JumTermAll();
    }

    public int Jum_Negatif() {
        return Latih_Negatif.JumTermAll();
    }

    public int Jum_TermUnix() {
        return TermUnix().size();
    }

    public int findFrekPositif(String key) {
        if (Latih_Positif.getFrek().containsKey(key)) {
            return Latih_Positif.getFrek().get(key).getFrek();
        } else {
            return 0;
        }
    }

    public int findFrekNegatif(String key) {
        if (Latih_Negatif.getFrek().containsKey(key)) {
            return Latih_Negatif.getFrek().get(key).getFrek();
        } else {
            return 0;
        }
    }

    public Vector Allterm() {
        Allterm.clear();
        Allterm.addAll(Latih_Positif.getAlterm());
        Allterm.addAll(Latih_Negatif.getAlterm());
        return Allterm;
    }

    public HashMap<String, Bobot> TermUnix() {
        h.clear();
        for (int i = 0; i < Allterm().size(); i++) {
            String kunci = (String) Allterm().get(i);

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

    public void CetakAllTerm() {
        for (int i = 0; i < Allterm().size(); i++) {
            System.out.println(" " + Allterm().get(i));
        }
    }

    public void cetakTermUnix() {
        System.out.println(" \ntext    ");
        for (Map.Entry<String, Bobot> entri : h.entrySet()) {
            System.out.print(entri.getKey() + " : ");
            System.out.println(entri.getValue().getFrek());
        }
    }

    public double PriorPositif() { // fungsi untuk menghitung nilai prior positif
        return (double) (Latih_Positif.getDok().size() / (double) (Latih_Positif.getDok().size() + Latih_Negatif.getDok().size()));
    }

    public double PriorNegatif() { // fungsi untuk menghitung prior negatif
        return (double) (Latih_Negatif.getDok().size() / (double) (Latih_Positif.getDok().size() + Latih_Negatif.getDok().size()));
    }

 
    
    public void preprocessing(boolean stopword, boolean casefolding, boolean stemming) {
        Latih_Positif.stopword(stopword);
        Latih_Positif.casefolding(casefolding);
        Latih_Positif.stemming(stemming);
        Latih_Negatif.stopword(stopword);
        Latih_Negatif.casefolding(casefolding);
        Latih_Negatif.stemming(stemming);
        Uji.stopword(stopword);
        Uji.casefolding(casefolding);
        Uji.stemming(stemming);
    }

    public void Klasifikasi() throws SQLException, IOException {
        Latih_Positif(); //
        Latih_Negatif();
        Dok_Uji();
        Allterm();
        TermUnix();
      //  CetakAllTerm();
        for (int i = 0; i < Uji.getDok().size(); i++) {
            double Positif = 1;
            double Negatif = 1;
            for (Map.Entry<String, Bobot> entri : Uji.getDok().get(i).infoText.entrySet()) {
//                System.out.print(entri.getKey() + " : ");
//                System.out.println(entri.getValue().getFrek());
//         System.out.println(entri.getKey() + " N: " + (findFrekPositif(entri.getKey()) + " + " + 1) + " /" + (Jum_Positif()+ " + " + Jum_TermUnix()));
//         System.out.println(entri.getKey() + " N: " + (findFrekNegatif(entri.getKey()) + " + " + 1) + " /" + (Jum_Negatif() + " + " + Jum_TermUnix()));

                Positif = Positif * (Math.pow((double) (findFrekPositif(entri.getKey()) + 1) / (double) (Jum_Positif() + Jum_TermUnix()), entri.getValue().getFrek()));
                Negatif = Negatif * (Math.pow((double) (findFrekNegatif(entri.getKey()) + 1) / (double) (Jum_Negatif() + Jum_TermUnix()), entri.getValue().getFrek()));

            }
//            System.out.println("-------------");
//            System.out.println("Prior P : " + Latih_Positif.getDok().size() + "/" + (Latih_Positif.getDok().size() + Latih_Negatif.getDok().size()));
//            System.out.println("Prior N : " + Latih_Negatif.getDok().size() + "/" + (Latih_Positif.getDok().size() + Latih_Negatif.getDok().size()));

            P_D.add((double) (PriorPositif() * Positif));
            N_D.add((double) (PriorNegatif() * Negatif));
        }

        for (int i = 0; i < Uji.getDok().size(); i++) {
  //          System.out.println(" P_D : " + P_D.get(i));
  //          System.out.println(" N_D : " + N_D.get(i));
            if (P_D.get(i) >= N_D.get(i)) {
   //             System.out.println("Positif");
                Hasil.add("positif");
                
            } else {
   //             System.out.println("Negatif");
                Hasil.add("negatif");
            }
        }
    }

    public ArrayList<Double> getP_D() {
        return this.P_D;
    }

    public ArrayList<Double> getN_D() {
        return this.N_D;
    }

    public ArrayList<String> getHasil() {
        return this.Hasil;
    }

    public static void main(String[] args) throws SQLException, IOException {
        connect koneksi = new connect();
        ResultSet Data_latih_Positif = koneksi.data("SELECT * FROM data_latih where kategori='positif'  limit 10 ;");
        ResultSet Data_latih_Negatif = koneksi.data("SELECT * FROM data_latih where kategori='negatif'  limit 10;");
        ResultSet Data_Uji = koneksi.data("SELECT * FROM data_uji;");
        NaiveBayes n = new NaiveBayes(Data_latih_Positif, Data_latih_Negatif, Data_Uji);
        n.Klasifikasi();
    }

}





