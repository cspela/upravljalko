package upravljalko;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Povezovanje {
    
    public void dodajOdstraniPovezavo(char operation, Integer delavec_id, Integer skupina_id, Boolean clan) throws ClassNotFoundException, SQLException{
        Connection con; 
        con = MyConnector.getConnection();      
        PreparedStatement ps; 
        
        if(operation == 'i'){
            ps = con.prepareStatement("INSERT INTO `clanstvo` (`delavec_id`, `skupina_id`, `clan`) VALUES (?, ?, ?);");
            ps.setInt(1, delavec_id);
            ps.setInt(2, skupina_id);
            ps.setBoolean(3, true);
            if(ps.executeUpdate() > 0){
                JOptionPane.showMessageDialog(null, "Delavec dodan v skupino.");
            }
        }
        
        if(operation == 'd'){
            ps = con.prepareStatement("UPDATE `clanstvo` SET `clan` = ? WHERE `clanstvo` . `delavec_id` = ? AND `clanstvo` . `skupina_id` = ?;");
            ps.setBoolean(1, false);
            ps.setInt(2, delavec_id);  
            ps.setInt(3, skupina_id);
           
            if(ps.executeUpdate() > 0){
                JOptionPane.showMessageDialog(null, "Delavec izbrisan iz skupine.");
            }
        }
    }

    public boolean clanstvo(char operation, Integer delavec_id, Integer skupina_id, Boolean clan) throws ClassNotFoundException, SQLException{
        boolean odobreno = false; 
        Connection con = MyConnector.getConnection();      
        PreparedStatement ps; 
        
        ps = con.prepareStatement("SELECT * FROM `clanstvo` WHERE `delavec_id` = ? AND `skupina_id` = ? AND `clan` = ?;");
        ps.setInt(1, delavec_id);
        ps.setInt(2, skupina_id);

        if(operation == 'c'){ //clan
            ps.setBoolean(3, true); //1 zdaj je član
        }
        if(operation == 'e'){ //ex 
            ps.setBoolean(3, false); //0 enkrat že bil član
        }
            
        ResultSet rs = ps.executeQuery();
            
        if(rs.next()){
            odobreno = true; 
        }
        
        return odobreno; 
    }
    
    
    public void izpisClanstva(JTable table, String valueToSearch) throws ClassNotFoundException, SQLException{
        Connection con = MyConnector.getConnection(); 
        PreparedStatement ps; 
        ps = con.prepareStatement("SELECT d.ime, d.priimek, s.naziv FROM delavec d INNER JOIN clanstvo c ON d.id = c.delavec_id INNER JOIN skupina s ON s.id = c.skupina_id WHERE c.clan = true AND (CONCAT (d.ime, d.priimek, s.naziv) LIKE ?) GROUP BY d.ime, d.priimek, s.naziv ORDER BY d.ime;");
        ps.setString(1, "%"+valueToSearch+"%"); //
        
        ResultSet rs = ps.executeQuery(); 
        DefaultTableModel model = (DefaultTableModel)table.getModel(); 
        Object[] row;
        
        while(rs.next()){
            row = new Object[3]; 
            row[0] = rs.getString(1);
            row[1] = rs.getString(2);
            row[2] = rs.getString(3);
                
            model.addRow(row);
        }
    }
    
    public void izpisDelavcevSkupin(JTable table, String valueToSearch) throws ClassNotFoundException{
        Connection con = MyConnector.getConnection(); 
        PreparedStatement ps; 
        try{ 
            //ps = con.prepareStatement("SELECT d.id, d.ime, d.priimek, s.naziv FROM delavec d LEFT JOIN clanstvo c ON d.id = c.delavec_id LEFT JOIN skupina s ON s.id = c.skupina_id WHERE c.clan = true GROUP BY d.ime, d.priimek, s.naziv ORDER BY d.ime;");  
            ps = con.prepareStatement("SELECT d.id, d.ime, d.priimek, s.naziv, CASE WHEN c.clan = true THEN 'Clan' WHEN c.clan = false THEN 'Izclanjen' ELSE 'Ni clan nobene skupine' END FROM delavec d LEFT OUTER JOIN clanstvo c ON d.id = c.delavec_id LEFT OUTER JOIN skupina s ON s.id = c.skupina_id WHERE CONCAT (`ime`, `priimek`) LIKE ? GROUP BY d.ime, d.priimek, s.naziv ORDER BY d.ime;");
            ps.setString(1, "%"+valueToSearch+"%");

            ResultSet rs = ps.executeQuery(); 
            DefaultTableModel model = (DefaultTableModel)table.getModel(); 
            Object[] row;
            
            while(rs.next()){
                row = new Object[5]; 
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                
                model.addRow(row);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Delavec.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
}            

          
            