package upravljalko;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Skupina {
    public void dodajPosodobiBrisiSkupino(char operation, String naziv, Integer id) throws ClassNotFoundException, SQLException{
        Connection con; 
        con = MyConnector.getConnection();      
        PreparedStatement ps; 
       
        //insert
        if(operation == 'i'){
            ps = con.prepareStatement("INSERT INTO `skupina` (`naziv`) VALUES (?);");
            ps.setString(1, naziv);

            if(ps.executeUpdate() > 0){
                JOptionPane.showMessageDialog(null, "Dodana nova skupina");
            }
        }
        
        //update
        if(operation == 'u'){
            ps = con.prepareStatement("UPDATE `skupina` SET `naziv` = ? WHERE `skupina` . `id` = ?;");
            ps.setString(1, naziv);
            ps.setInt(2, id);
               
            if(ps.executeUpdate() > 0){
                JOptionPane.showMessageDialog(null, "Podatki so bili posodobljeni");
            }
        }
        
        //delete
        if(operation == 'd'){
            ps = con.prepareStatement("DELETE FROM `skupina` WHERE `skupina` . `id` = ?;");
            ps.setInt(1, id);
            if(ps.executeUpdate() > 0){
                JOptionPane.showMessageDialog(null, "Skupina izbrisana");
            }
        }
    }
    
    public void izpisSkupin(JTable table, String valueToSearch) throws ClassNotFoundException, SQLException{
        Connection con = MyConnector.getConnection(); 
        PreparedStatement ps; 

        ps = con.prepareStatement("SELECT * FROM `skupina` WHERE CONCAT (`naziv`) LIKE ? ORDER BY `naziv`;");  
        ps.setString(1, "%"+valueToSearch+"%");
            
        ResultSet rs = ps.executeQuery(); 
        DefaultTableModel model = (DefaultTableModel)table.getModel(); 
        Object[] row;
            
        while(rs.next()){
            row = new Object[3]; 
            row[0] = rs.getInt(1);
            row[1] = rs.getString(2);
        
            model.addRow(row);
        }
    }

    public boolean obstojSkupine(String nazivSkupine) throws ClassNotFoundException, SQLException{
        boolean skupinaObstaja = false; 
        Connection con = MyConnector.getConnection();      
        PreparedStatement ps; 

        ps = con.prepareStatement("SELECT * FROM `skupina` WHERE `naziv` = ? ;");
        ps.setString(1, nazivSkupine); 
        ResultSet rs = ps.executeQuery();
            
        if(rs.next()){
            skupinaObstaja = true; 
        }
        return skupinaObstaja; 
    }
    
    public boolean spremembe(String nazivSkupine, Integer idSkupine) throws ClassNotFoundException, SQLException{
        boolean spremembe = false; 
        Connection con = MyConnector.getConnection();      
        PreparedStatement ps; 
            
        ps = con.prepareStatement("SELECT * FROM `skupina` WHERE `naziv` = ? AND `id` = ? ;");
        ps.setString(1, nazivSkupine); 
        ps.setInt(2, idSkupine); 
        ResultSet rs = ps.executeQuery();
            
        if(rs.next()){
            spremembe = true; 
        }
        
        return spremembe; 
    }
    
    public int getIdSkupine(String nazivSkupine) throws ClassNotFoundException, SQLException{
        int idSkupine = 0; 
                
        Connection con = MyConnector.getConnection();      
        PreparedStatement ps; 
        
        ps = con.prepareStatement("SELECT * FROM `skupina` WHERE `naziv` = ?;");
        ps.setString(1, nazivSkupine); 
        ResultSet rs = ps.executeQuery();
            
        if(rs.next()){
            idSkupine = rs.getInt("id"); 
        }
            
        return idSkupine;    
    }
   
    public void izpisSkupinComboBox(JComboBox combo) throws ClassNotFoundException, SQLException{
        Connection con = MyConnector.getConnection(); 
        PreparedStatement ps; 

        ps = con.prepareStatement("SELECT * FROM `skupina` ORDER BY `naziv`;");  
        ResultSet rs = ps.executeQuery(); 
        
        while(rs.next()){
            combo.addItem(rs.getString(2));
        }
    }
}