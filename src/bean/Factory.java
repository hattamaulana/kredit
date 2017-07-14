package bean;

import data.Parameter;

import java.sql.SQLException;
import java.util.Vector;

public class Factory {
    
    public static Vector generateCollections (int id, String barang, String nama, String noHp, String alamat, int angsuran, int tipe) throws SQLException {
        // initial collection for save data
        java.util.Vector collections = new java.util.Vector();
        
        int     x     = 90;
        int     exe   = 0;
        int[]   array = new int [tipe/3];

        for (int i = 0 ; i <= tipe ; i++) {
            if (i % 3 == 1) {
                array[exe] = i;
                exe++;
            }
        }
        
        // Add data values to collections
        for (int i = 0 ; i < array.length ; i++) {
            collections.add(new Parameter (id, barang, nama, noHp, alamat, angsuran, tipe, array[i]));
        }
        
        // return collections to beans
        return collections;
    }
}
