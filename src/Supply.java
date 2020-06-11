import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Supply{
	private ArrayList<Product> products;
	/**
	 * Create the panel.
	 */
	public Supply(MainFrame mainFrame) {
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDateTime now = LocalDateTime.now(); 
			int month = Integer.valueOf(String.valueOf(dtf.format(now).charAt(3))+String.valueOf(dtf.format(now).charAt(4)))+1;
			int year = Integer.valueOf((String.valueOf(dtf.format(now).charAt(6))+String.valueOf(dtf.format(now).charAt(7))+String.valueOf(dtf.format(now).charAt(8))+String.valueOf(dtf.format(now).charAt(9))))-1;
		    
			JFileChooser chooser = new JFileChooser(); 
		    chooser.setCurrentDirectory(new java.io.File("."));
		    chooser.setDialogTitle("Export Supply Report");
		    chooser.setAcceptAllFileFilterUsed(false);
		    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    
		    if (chooser.showOpenDialog(mainFrame) == JFileChooser.APPROVE_OPTION) { 
				File file = new File(chooser.getSelectedFile(), "SupplyReport.txt");
				FileWriter writer = new FileWriter(file);
				writer.write("|                   " + dtf.format(now) + "                  |");
				writer.write(System.lineSeparator());
				writer.write("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");
				writer.write(System.lineSeparator());
				writer.write("|      Product      |     Barcode     |   QTY   |");
				writer.write(System.lineSeparator());
				writer.write("|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|");
				writer.write(System.lineSeparator());
				fetchProducts();
				for(Product p:products) {					
					if(p.lastYearSales(month,year)-p.getQty() > 0) {
						writer.write("|"+p.getName());
						for(int i=0; i<=18-p.getName().length();i++)
							writer.write(" ");
						writer.write("|"+p.getId());
						for(int i=0; i<=16-String.valueOf(p.getId()).length();i++)
							writer.write(" ");
						writer.write("|"+String.valueOf(p.lastYearSales(month,year)-p.getQty()));
						for(int i=0; i<=8-String.valueOf(p.getQty()).length();i++)
							writer.write(" ");
						writer.write("|");
						writer.write(System.lineSeparator());
						writer.write("|-----------------------------------------------|");
						writer.write(System.lineSeparator());
					}
				}
				writer.close();
				JOptionPane.showMessageDialog(mainFrame, "Supply Report exported succesfuly", "Succesful", JOptionPane.INFORMATION_MESSAGE);
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void fetchProducts() {
		DB_Connection objDB = new DB_Connection();
		Connection connection = objDB.get_connection();
		PreparedStatement ps = null;
		
		String query = "SELECT * FROM products";
		
		try {
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			products = new ArrayList<>();
			Product p = null;
			while (rs.next()){
				p = new Product(rs.getInt("id"));
				products.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
