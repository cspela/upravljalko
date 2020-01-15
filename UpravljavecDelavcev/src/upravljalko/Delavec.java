package upravljalko;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Delavec {
    public void dodajPosodobiBrisiDelavca(char operation, String ime, String priimek, Integer id) throws ClassNotFoundException, SQLException{
       Connection con; 
       con = MyConnector.getConnection();      
       PreparedStatement ps; 
       
       //insert
       if(operation == 'i'){
            ps = con.prepareStatement("INSERT INTO `delavec` (`ime`, `priimek`) VALUES (?, ?);");
            ps.setString(1, ime);
            ps.setString(2, priimek);
               
            if(ps.executeUpdate() > 0){
                JOptionPane.showMessageDialog(null, "Dodan nov delavec");
            }
       }
       //update
        if(operation == 'u'){
            ps = con.prepareStatement("UPDATE `delavec` SET `ime` = ? , `priimek` = ? WHERE `delavec` . `id` = ? ;");
            ps.setString(1, ime);
            ps.setString(2, priimek);
            ps.setInt(3, id);
               
            if(ps.executeUpdate() > 0){
                JOptionPane.showMessageDialog(null, "Podatki so bili posodobljeni");
            }
       }
       //delete
       if(operation == 'd'){
            ps = con.prepareStatement("DELETE FROM `delavec` WHERE `delavec` . `id` = ?;");
            ps.setInt(1, id);
            
            if(ps.executeUpdate() > 0){
                JOptionPane.showMessageDialog(null, "Delavec izbrisan");
            }
       } 
    }
    
    public void izpisDelavcev(JTable table, String valueToSearch) throws ClassNotFoundException, SQLException{
        Connection con = MyConnector.getConnection(); 
        PreparedStatement ps; 

        ps = con.prepareStatement("SELECT * FROM `delavec` WHERE CONCAT (`ime`, `priimek`) LIKE ? ORDER BY `ime`;");  
        ps.setString(1, "%"+valueToSearch+"%");
            
        ResultSet rs = ps.executeQuery(); 
        DefaultTableModel model = (DefaultTableModel)table.getModel(); 
        Object[] row;
            
        while(rs.next()){
            row = new Object[3]; 
            row[0] = rs.getInt(1);
            row[1] = rs.getString(2);
            row[2] = rs.getString(3);
                
            model.addRow(row);
        }  
    }

    void dodajDelavca(char c, String id, String ime, String priimek) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean obstojDelavca(String ime, String priimek) throws ClassNotFoundException, SQLException{
        boolean delavecObstaja = false; 
        Connection con = MyConnector.getConnection();      
        PreparedStatement ps; 
        
        ps = con.prepareStatement("SELECT * FROM `delavec` WHERE `ime` = ? AND `priimek` = ? ;");
        ps.setString(1, ime); 
        ps.setString(2, priimek);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            delavecObstaja = true; 
        }    
        return delavecObstaja; 
    }

    public boolean spremembe(String ime, String priimek, Integer id) throws ClassNotFoundException, SQLException{
        boolean spremembe = false; 
        Connection con = MyConnector.getConnection();      
        PreparedStatement ps; 
    
        ps = con.prepareStatement("SELECT * FROM `delavec` WHERE `ime` = ? AND `priimek` = ? AND `id` = ?");
        ps.setString(1, ime); 
        ps.setString(2, priimek); 
        ps.setInt(3, id); 
        ResultSet rs = ps.executeQuery();
            
        if(rs.next()){
            spremembe = true; 
        }
   
        return spremembe; 
    }
    
    public int getIdDelavca(String ime, String priimek) throws ClassNotFoundException, SQLException{
        int idDelavca = 0; 
  
        Connection con = MyConnector.getConnection();      
        PreparedStatement ps; 
   
        ps = con.prepareStatement("SELECT * FROM `delavec` WHERE `ime` = ? AND `priimek` = ? ;");
        ps.setString(1, ime); 
        ps.setString(2, priimek); 
        ResultSet rs = ps.executeQuery();
            
        if(rs.next()){
            idDelavca = rs.getInt("id"); 
        }

        return idDelavca;    
    }
    
    public void izpisDelavcevComboBox(JComboBox combo) throws ClassNotFoundException, SQLException{
        Connection con = MyConnector.getConnection(); 
        PreparedStatement ps; 
        
        ps = con.prepareStatement("SELECT * FROM `delavec` ORDER BY `ime`;");  
        ResultSet rs = ps.executeQuery(); 
        while(rs.next()){
            combo.addItem(rs.getString(2) + " " + rs.getString(3));
        }
    }
       
}

