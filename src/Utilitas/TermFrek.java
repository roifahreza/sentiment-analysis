package Utilitas;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import preprocessing.OlahDokumen;
import preprocessing.connect;

public class TermFrek {
   private  ArrayList<Dokumen> dokumen;
   private Vector allTerm;
   private HashMap<String, Bobot> h ;
   OlahDokumen olah =new OlahDokumen();
   boolean stopword=false,stemming=false,casefolding=false;
   
    
    public TermFrek() {
        h = new HashMap<String, Bobot>();
        dokumen = new ArrayList<>();
        allTerm =new Vector();
        
    }
    public void stopword( boolean stopword ){
    this.stopword=stopword;
    
    }
    public void casefolding(boolean casefolding ){
    this.casefolding=casefolding;
    }
    public void stemming( boolean stemming){
    this.stemming=stemming;
    }

    public void insertDokumen(ResultSet rs) throws SQLException, IOException {
       
        while(rs.next()){
             String kata=rs.getString("dokumen");
             if(casefolding==true){
               kata =olah.bersihkanStopword(rs.getString("dokumen"));
               
            }
            if (stopword==true) {
               
               kata =olah.bersihkanStopword(kata);
               
            }
            
            if(stemming==true){
            kata =olah.KataDasar(kata);
            }
            dokumen.add(new Dokumen(rs.getInt("id"), kata, rs.getString("kategori")));
       
        }
        allTerm();
        TermUnix();
        rs.close();
        
    }
        public void insertDokumenUji(ResultSet rs) throws SQLException, IOException {
            
        while(rs.next()){
             String kata=rs.getString("dokumen");
            if (stopword==true) {
               kata=olah.Negasi(kata);
                kata =olah.bersihkanStopword(kata);
               
            }
            if(casefolding==true){
               kata =olah.caseFolding(kata);
            }
            if(stemming==true){
            kata =olah.KataDasar(kata);
            }
            dokumen.add(new Dokumen(rs.getInt("id"), kata,rs.getString("kategori")));
             
        }
        allTerm();
        TermUnix();
        rs.close();}
    public void allTerm() {
        for (int i = 0; i < dokumen.size(); i++) {
            allTerm.addAll(dokumen.get(i).getAllTerm());
        }
    }

    public Vector getAlterm() {
        return allTerm;
    }

    public int JumTermAll() {
        return allTerm.size();
    }
    public int JumTermUnix(){
    return h.size();
    }
    public HashMap<String, Bobot> getFrek(){
        return h;
    }
    private HashMap<String, Bobot> TermUnix() {
        for (int i = 0; i < getAlterm().size(); i++) {
            String kunci =(String) getAlterm().get(i);
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
    public void cetakAllterm(){
        for (int i = 0; i < getAlterm().size(); i++) {
            System.out.println(""+getAlterm().get(i));
        }
    }
    public void cetakTermUnix() {
        System.out.println(" \ntext    ");
        for (Map.Entry<String, Bobot> entri : h.entrySet()) {
            System.out.print(entri.getKey() + " : ");
            System.out.println(entri.getValue().getFrek());
        }
    }
    public ArrayList <Dokumen> getDok(){
    return dokumen;
    }
    
    public static void main(String[] args) throws SQLException, IOException {
        connect koneksi= new connect();
        ResultSet Data_latih=koneksi.data("SELECT * FROM data_latih ;");
        
        TermFrek t = new TermFrek();
        t.insertDokumen(Data_latih);
        t.cetakAllterm();
        t.cetakTermUnix();
        
    }
}
