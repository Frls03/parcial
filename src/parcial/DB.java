/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial;
import java.sql.*;
import static javax.swing.JOptionPane.showMessageDialog;

public class DB {
    public Connection con;
    
    //metodo constructor
    public DB() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        String server = "jdbc:mysql://localhost:3307/";
        String database = "ventas";
        String user = "root";
        String password = "";
        this.con = DriverManager.getConnection(server + database, user, password);
    }

    public boolean isOpen(){
        return this.con != null;
    }
    
    
    public ResultSet getData() throws SQLException{
        if(this.isOpen()){
            String sql = "select * from productos";
            Statement st = this.con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            return rs;
        }
        else{
            return null;
        }
    }
    
    public void Insert(String codigo, String producto, String precio) throws SQLException{
        double PRECIO = Double.valueOf(precio);
        String sql = "insert into productos(Codigo,Nombre, Precio)values(?, ?, ?)";
        PreparedStatement cursor = this.con.prepareCall(sql);
        cursor.setString(1, codigo);
        cursor.setString(2, producto);
        cursor.setDouble(3, PRECIO);
        int result = cursor.executeUpdate();
        if(result > 0){
            System.out.println("Se ha insertado el producto correctamente :D");
        }else{
            System.out.println("Error al insertar producto en la base de datos :C");
        }
    }
    
    public void Actualizar(String codigo, String producto, String precio) throws SQLException{
        double PRECIO = Double.valueOf(precio);
        String sql = "update productos set Nombre=?, Precio=? where Codigo=?";
        PreparedStatement cursor = this.con.prepareCall(sql);
        cursor.setString(1, producto);
        cursor.setDouble(2, PRECIO);
        cursor.setString(3, codigo);
        int result = cursor.executeUpdate();
        if(result > 0){
            System.out.println("Se ha insertado el producto correctamente :D");
        }else{
            System.out.println("Error al insertar producto en la base de datos :C");
        }
    }
    
    public void Eliminar(String codigo) throws SQLException{
        try{
            String query = "delete from users where id = ?";
            PreparedStatement preparedStmt = this.con.prepareStatement(query);
            preparedStmt.setString(1, codigo);
            preparedStmt.execute();
        }catch(Exception e){
            showMessageDialog(null, e.getMessage());
        }
        
      
    }
    
    
    public void CloseConnection() throws SQLException{
        this.con.close();
    }


}

