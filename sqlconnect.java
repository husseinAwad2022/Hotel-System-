package application;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import java.sql.*;

public class sqlconnect {
	Connection conn = null;

	public static Connection ConnectDb() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/hotelsystem", "root","abdallah1699");
			JOptionPane.showMessageDialog(null, "Connection Established");
			return conn;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}

	}

	public static ObservableList<employee> getDatausers() {
		Connection conn = ConnectDb();
		ObservableList<employee> list = FXCollections.observableArrayList();
		
		try {
			Statement stmt=conn.createStatement();
			//PreparedStatement ps = conn.prepareStatement("select * from employee");
			ResultSet rs = stmt.executeQuery("select * from employee");
			
			while (rs.next()) {
				
				list.add(new employee(Integer.parseInt(rs.getString(1)), Integer.parseInt(rs.getString(2)),rs.getString(3),
						              rs.getString(4), Integer.parseInt(rs.getString(5)),Integer.parseInt(rs.getString(6))));	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
}
