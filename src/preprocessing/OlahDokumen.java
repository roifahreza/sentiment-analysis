package preprocessing;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *---------------------------------------------
 * @author Yuita Arum Sari 0710960009
 * Ilmu Komputer 2007 Universitas Brawijaya
 * --------------------------------------------
 * 
 */
public class OlahDokumen {
    private BufferedReader bufferBaca;
    private String barisData = "";
    private String bersih = "";
    private StringTokenizer token;
    private String words = "";
    private HashMap<String, Integer> map;
    private ArrayList<String> listKata;
    
    public String readDokumenTeks(String bacateks) throws FileNotFoundException, IOException { // D:dokumen.txt
        File bacafile = new File(bacateks);//mengubah inputan string mejadi sebuah file
        FileReader inputDokumen = new FileReader(bacafile);//membaca inputan sebuah dokumen
        bufferBaca = new BufferedReader(inputDokumen);//buffer dari dokumen ketika dibaca
        StringBuffer content = new StringBuffer();//untuk menampung string dalam bufer
        while ((barisData = bufferBaca.readLine()) != null) {//jika barisdata ada
          //  barisData = barisData.toLowerCase();
            content.append(barisData);//mencetak baris kata dalam dokumen
            content.append(System.getProperty("line.separator"));
        }
        return content.toString();
    }
    
    public String caseFolding(String inputFile) throws FileNotFoundException, IOException{
        return inputFile.toLowerCase();
    }

    public String bersihkanStopword(String inputdokumen) throws FileNotFoundException, IOException { //Fungsi untuk membuang kata yang tidak diperlukan
        inputdokumen=HapusAngkadanTandaBaca(inputdokumen);
        String daftarstopword = readDokumenTeks("Stopwords.txt"); // membaca kamus
        String stop = "\\b(" + daftarstopword + ")\\b\\s*";
        Pattern stopword = Pattern.compile(stop, Pattern.CASE_INSENSITIVE);// membandingkan kata dengan kamus
        Matcher sesuai = stopword.matcher(inputdokumen);
        bersih = sesuai.replaceAll(""); //pembuangan kata
        return bersih; 
    }
        public String Negasi(String inputdokumen) throws FileNotFoundException, IOException { //Fungsi untuk membuang kata yang tidak diperlukan
       inputdokumen=HapusAngkadanTandaBaca(inputdokumen);
        String negasi = readDokumenTeks("negasi.txt"); // membaca kamus
        String stop = "\\b(" + negasi + ")\\b\\s*";
        Pattern stopword = Pattern.compile(stop, Pattern.CASE_INSENSITIVE);
        Matcher matcher = stopword.matcher(inputdokumen);
        if(matcher.find()==true){
        String Positif = readDokumenTeks("positif.txt"); // membaca kamus
        String stop1 = "\\b(" + Positif + ")\\b\\s*";
        Pattern P = Pattern.compile(stop1, Pattern.CASE_INSENSITIVE);
        Matcher Mpositif = P.matcher(matcher.replaceAll(""));
        String Negatif = readDokumenTeks("negatif.txt"); // membaca kamus
        String stop2 = "\\b(" + Negatif + ")\\b\\s*";
        Pattern N = Pattern.compile(stop2, Pattern.CASE_INSENSITIVE);
        Matcher Mnegatif = N.matcher(matcher.replaceAll(""));
            if (Mpositif.find()==true) {
            return Mpositif.replaceAll(" negatif ") ;
                }
            else if (Mnegatif.find()==true){
            return Mnegatif.replaceAll(" positif ");
            }        
        }        
        return inputdokumen;
       
    }
    
    public static void main(String[] args) throws IOException {
        OlahDokumen k = new OlahDokumen();
        System.out.println(k.bersihkanStopword("tidak bisa untuk kesehatan masyarakat"));
        System.out.println(k.caseFolding("tidak bisa untuk kesehatan masyarakat"));
        System.out.println(k.Negasi("ga bisa untuk kesehatan masyarakat"));
        System.out.println(k.Negasi("tidak bisa untuk kesehatan masyarakat"));
    }
    

    public static String HapusAngkadanTandaBaca(String kalimat) { // Fungsi untuk menghapus angka dan tanda baca
        kalimat = kalimat.replaceAll("\\p{Punct}|\\d", " ");
        return kalimat;
    }
    
    public ArrayList<String> token (String kalimat) { // Fungsi dengan parameter String kalimat berfungsi untuk memecah kalimat menjadi kata tunggal 
        String hasilKalimat = HapusAngkadanTandaBaca(kalimat);// Memanggil Fungsi untuk menghapus angka dan tanda baca
        String hapusNewline = hasilKalimat.replace(System.getProperty("line.separator"), " "); //Pemisahan kata berdasarkan spasi
        listKata = new ArrayList<String>(); // inisisalisasi Array list untuk menyimpan kata hasil tokenization
        token = new StringTokenizer(hapusNewline, " ");
        while (token.hasMoreTokens()) { // proses perulangan untuk menambahkan kata kedalam ArrayList
            words = token.nextToken();
            listKata.add(words);
        }
        return listKata;
    }
    public ArrayList<String> getlistdata(){
        return listKata;
    }
    
    public ArrayList<String> stemming( ArrayList<String> kalimat) throws IOException{
        StemmingNaziefAndriani stem = new StemmingNaziefAndriani();
        for (int i = 0; i < listKata.size(); i++) {
            listKata.set(i, stem.KataDasar(listKata.get(i)));
        }
        return listKata;}
    public String KataDasar(String kalimat) throws IOException{
        String out="";
        stemming(token(kalimat));
        for (int i = 0; i < getlistdata().size(); i++) {
        out+=getlistdata().get(i)+" ";
        }
        return out;
    }
    public void cetak (){
        for (int i = 0; i < listKata.size(); i++) {
            System.out.println(""+listKata.get(i));
        }
    }
}