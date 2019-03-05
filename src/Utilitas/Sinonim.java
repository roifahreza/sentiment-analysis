package Utilitas;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import org.json.JSONObject;
import preprocessing.connect;

public class Sinonim {
    
    HashMap<String, String> kamus = new HashMap<String, String>();
    public void MainUnix(connect koneksi,ResultSet Data_Uji ) throws SQLException, IOException, InterruptedException{
    TermFrek t = new TermFrek();
    t.casefolding(true);
    t.stopword(true);
    t.stemming(true);
    t.insertDokumenUji(Data_Uji);
        for (int i = 0; i <t.getDok().size(); i++) {
             for (int j = 0; j <t.getDok().get(i).getAllTerm().size(); j++) {
                 InsertKamus(koneksi, String.valueOf(t.getDok().get(i).getAllTerm().get(j)));
                 
                 System.out.println("----- : "+j);
            }   
        }
        //Thread.sleep(10000);
    }
    public  void InsertKamus(connect koneksi,String kata) throws SQLException, InterruptedException{
    
     if (!kamus.containsKey(kata)) {
         kamus.put(kata, kateglo(kata));
         
         koneksi.insert("INSERT INTO sinonim VALUES ('"+kata+"','"+kateglo(kata)+"')");
         Thread.sleep(3000);
         }
    }
    public static String kateglo(String kata) {
        String persamaan="";
        try {
            String str = httpGet("http://kateglo.com/api.php?format=json&phrase="+kata);
            JSONObject obj = new JSONObject(str);
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject kateglo = (JSONObject) jsonObject.get("kateglo");
            //String kelas = (String) kateglo.get("lex_class");
            JSONObject relation = (JSONObject) kateglo.get("relation");
            JSONObject sinonim = (JSONObject) relation.get("s");
            for (int i = 0; i <10; i++) {
                JSONObject loop = (JSONObject) sinonim.get(String.valueOf(i));
                String related_phrase = (String) loop.get("related_phrase");
                persamaan +=related_phrase+" ";
            }
        } catch (Exception iox) {
            return persamaan;
        }
        return persamaan;
    }
    //================================================================================
    //----------------------------------------------------------------------------------
    //=================================================================================
    
   public void termUnix(connect koneksi,ResultSet Data_Uji ) throws SQLException, IOException, InterruptedException{
    TermFrek t = new TermFrek();
   koneksi.insert("DELETE FROM qe_nonpre");
    t.casefolding(false);
    t.stopword(false);
    t.stemming(false);
    t.insertDokumenUji(Data_Uji);
        for (int i = 0; i <t.getDok().size(); i++) {
            String Dokument="";
            for (int j = 0; j <t.getDok().get(i).getAllTerm().size(); j++) {
//               System.out.println(kalimat(String.valueOf(t.getDok().get(i).getAllTerm().get(j))));
                Dokument=Dokument+" "+kalimat(String.valueOf(t.getDok().get(i).getAllTerm().get(j)));
                Thread.sleep(500);
            }
           System.out.println("-----------------");
            koneksi.insert("INSERT INTO qe_nonpre (dokumen,kategori) VALUES ('"+Dokument+"','"+t.getDok().get(i).getKat()+"')");
        }
    }
     public static String kalimat(String kata) {
        try {
            String str = httpGet("http://kateglo.com/api.php?format=json&phrase="+kata);
            JSONObject obj = new JSONObject(str);
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject kateglo = (JSONObject) jsonObject.get("kateglo");
            String kelas = (String) kateglo.get("lex_class");
            JSONObject relation = (JSONObject) kateglo.get("relation");
            JSONObject sinonim = (JSONObject) relation.get("s");
            if (kelas.equalsIgnoreCase("adj")) {
                System.out.print("--> "+kata+"    :  ");
                for (int i = 0; i <3; i++) {
                JSONObject loop = (JSONObject) sinonim.get(String.valueOf(i));
                String related_phrase = (String) loop.get("related_phrase");
                kata = kata + " " + related_phrase;
   //                 System.out.print("  "+related_phrase);
            }
            }//System.out.println("");
            
        } catch (Exception iox) {
            return kata;
        }
        return kata;
    }
     
    public static String httpGet(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        if (conn.getResponseCode() != 200) {
            throw new IOException(conn.getResponseMessage());
        }
        // Buffer the result into a string
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        return sb.toString();
    }

    
    public static void main(String[] args) throws SQLException, IOException, InterruptedException {
        Sinonim n = new Sinonim();
        connect koneksi =new connect();
        n.termUnix(koneksi ,koneksi.data("SELECT * FROM data_uji"));
  //          n.MainUnix(koneksi, koneksi.data("SELECT * FROM data_uji"));
  //      System.out.println(n.kateglo("mantap"));
  }
}
