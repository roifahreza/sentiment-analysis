package preprocessing;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class connect {
        Connection c = ConnectDB();
        Statement stmt = null;
        ResultSet rs = null;
     static Connection conn;
public  static Connection ConnectDB(){
    try {
        Class.forName("org.sqlite.JDBC");
        conn= DriverManager.getConnection("jdbc:sqlite:sentiment_analysis.sqlite");
    //    JOptionPane.showMessageDialog(null,"Connect");
        return conn;
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null,"Disconnect");
        return null;
    }}
public  void CloseDB() throws SQLException{
conn.close();
}
    public static void main(String[] args) throws SQLException {
        connect k = new connect();
         k.insert("INSERT INTO sinonim  VALUES ('baik','nyaman')");
         k.insert("INSERT INTO sinonim  VALUES ('baik','nyaman')");
         k.insert("INSERT INTO sinonim  VALUES ('sehat','nyaman')");
       //  k.insert("INSERT INTO query_expansion (dokumen) VALUES ('saya makan')");
//        ResultSet data = k.data("SELECT * FROM data_latih;");
//        
//    try {
//        while (data.next()) {
//         int id = data.getInt("id");
//         String  dokumen = data.getString("dokumen");
//         String kategori  = data.getString("kategori");
//         System.out.println( "ID = " + id );
//         System.out.println( "DOKUMEN = " + dokumen );
//         System.out.println( "KATEGORI = " + kategori );
//         System.out.println();
//        }
//    } catch (SQLException ex) {
//        Logger.getLogger(connect.class.getName()).log(Level.SEVERE, null, ex);
//    }
        }
    public ResultSet data(String Query) {
        try {
            stmt = c.createStatement();
            rs = stmt.executeQuery(Query);
            
            return rs;

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            return rs;
        }
    }
    
    public void insert (String Query) throws SQLException{
           try {
            stmt = c.createStatement();
            stmt.executeUpdate(Query);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    }

