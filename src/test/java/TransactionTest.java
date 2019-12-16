
import com.github.hattamaulana.controller.TransactionController;
import com.github.hattamaulana.project.uas.entities.Angsuran;
import com.github.hattamaulana.project.uas.entities.Customer;
import com.github.hattamaulana.project.uas.entities.Transaction;
import junit.framework.Assert;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ngub
 */
public class TransactionTest {
    private TransactionController controller = new TransactionController();
    
    @Test
    public void testAddNewTransaction() {
        System.out.println("Test Add New Transaction : ");
        
        Customer customer = new Customer();
        customer.setId("20");
        
        Transaction param = new Transaction();
        param.setTipeAngsuran("Per Minggu");
        param.setCustomer(customer);
        
        boolean expected = true;
        boolean actual   = controller.create(param);
        
        Assert.assertEquals(expected, actual);
        System.out.println("* OK");
    }
    
    @Test
    public void testSetorAngsuran() {
        System.out.println("Test Add New Transaction : ");
        
        Transaction transaksi = new Transaction();
        transaksi.setId(2);
        
        Angsuran angsuran = new Angsuran();
        angsuran.setTransaction(transaksi);
        angsuran.setAngsuran(500000);
        
        boolean expected = true;
        boolean actual   = controller.setorAngsuran(angsuran);
        
        System.out.println("* OK");
    }
    
}
