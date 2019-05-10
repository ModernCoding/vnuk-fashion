package vn.edu.vnuk.fashion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.edu.vnuk.fashion.jdbc.ConnectionFactory;
import vn.edu.vnuk.fashion.model.Material;

public class MaterialDao {
	
    private Connection connection;

    public MaterialDao(){
        this.connection = new ConnectionFactory().getConnection();
    }

    public MaterialDao(Connection connection){
        this.connection = connection;
    }


    //  CREATE
    public void create(Material material) throws SQLException{

        String sqlQuery = "insert into materials (label) "
                        +	"values (?)";

        PreparedStatement statement;

        try {
                statement = connection.prepareStatement(sqlQuery);

                //	Replacing "?" through values
                statement.setString(1, material.getLabel());

                // 	Executing statement
                statement.execute();

                System.out.println("New record in DB !");

        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
                System.out.println("Done !");
                connection.close();
        }

    }
    
    
    //  READ (List of Tasks)
    @SuppressWarnings("finally")
    public List<Material> read() throws SQLException {

        String sqlQuery = "select * from materials";
        PreparedStatement statement;
        List<Material> materials = new ArrayList<Material>();

        try {

            statement = connection.prepareStatement(sqlQuery);

            // 	Executing statement
            ResultSet results = statement.executeQuery();
            
            while(results.next()){

                Material material = new Material();
                material.setId(results.getLong("id"));
                material.setLabel(results.getString("label"));
                
                materials.add(material);

            }

            results.close();
            statement.close();


        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
                connection.close();
                return materials;
        }


    }


    //  READ (Single Material)
    public Material read(Long id) throws SQLException{
        return this.read(id, true);
    }  

    
    //  UPDATE
    public void update(Material material) throws SQLException {
        String sqlQuery = "update materials label=? where id=?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, material.getLabel());
            
            statement.execute();
            statement.close();
            
            System.out.println("Material successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        finally {
            connection.close();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
        String sqlQuery = "delete from materials where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, id);
            statement.execute();
            statement.close();
            
            System.out.println("Material successfully deleted.");

        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        finally {
            connection.close();
        }

    }
  
    
    //  PRIVATE
    
    @SuppressWarnings("finally")
    private Material read(Long id, boolean closeAfterUse) throws SQLException{

        String sqlQuery = "select * from materials where id=?";

        PreparedStatement statement;
        Material material = new Material();

        try {
            statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setLong(1, id);

            // 	Executing statement
            ResultSet results = statement.executeQuery();

            if(results.next()){

                material.setId(results.getLong("id"));
                material.setLabel(results.getString("label"));

            }

            statement.close();

        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
            
            if (closeAfterUse) {
                connection.close();
    
            }
            
            return material;
        }

    }

}