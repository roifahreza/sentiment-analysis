
package preprocessing;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Data {
    connect koneksi = new connect();
    
    public Object[][] getDataLatih() throws SQLException{
        
        ResultSet data =koneksi.data("SELECT * FROM data_latih ;");
        Object data_latih[][]=new Object[1000][3];
        int i =0;
        while (data.next()) {
            data_latih[i][0]=data.getInt("id");
            data_latih[i][1]=data.getString("dokumen");
            data_latih[i][2]=data.getString("kategori");
           // System.out.println(""+data.getString("dokumen"));
            i++;
        }
        return data_latih;
    }
    public static void main(String[] args) throws SQLException {
        Data k = new Data();
        k.getDataLatih();
    }
}
