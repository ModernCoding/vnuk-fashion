package vn.edu.vnuk.fashion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.edu.vnuk.fashion.jdbc.ConnectionFactory;
import vn.edu.vnuk.fashion.model.Size;

public class SizeDao {
	
    private Connection connection;

    public SizeDao(){
        this.connection = new ConnectionFactory().getConnection();
    }

    public SizeDao(Connection connection){
        this.connection = connection;
    }


    //  CREATE
    public void create(Size size) throws SQLException{

        String sqlQuery = "insert into sizes (universal , us , uk , france , italy , germany , australia , japan) "
                        +	"values (? , ? , ? , ? , ? , ? , ? , ?)";

        PreparedStatement statement;

        try {
                statement = connection.prepareStatement(sqlQuery);

                //	Replacing "?" through values
                statement.setString(1, size.getUniversal());
                statement.setString(2, size.getUs());
                statement.setString(3, size.getUk());
                statement.setString(4, size.getFrance());
                statement.setString(5, size.getItaly());
                statement.setString(6, size.getGermany());
                statement.setString(7, size.getAustralia());
                statement.setString(8, size.getJapan());


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
    
    
    //  READ (List of Sizes)
    @SuppressWarnings("finally")
    public List<Size> read() throws SQLException {

        String sqlQuery = "select * from sizes";
        PreparedStatement statement;
        List<Size> sizes = new ArrayList<Size>();

        try {

            statement = connection.prepareStatement(sqlQuery);

            // 	Executing statement
            ResultSet results = statement.executeQuery();
            
            while(results.next()){

                Size size = new Size();
                size.setId(results.getLong("id"));
                size.setUniversal(results.getString("universal"));
                size.setUs(results.getString("us"));
                size.setUk(results.getString("uk"));
                size.setFrance(results.getString("france"));
                size.setItaly(results.getString("italy"));
                size.setGermany(results.getString("germany"));
                size.setAustralia(results.getString("australia"));
                size.setJapan(results.getString("japan"));

                
                sizes.add(size);

            }

            results.close();
            statement.close();


        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
                connection.close();
                return sizes;
        }


    }


    //  READ (Single Size)
    public Size read(Long id) throws SQLException{
        return this.read(id, true);
    }  

    
    //  UPDATE
    public void update(Size size) throws SQLException {
        String sqlQuery = "update sizes universal=? us=? uk=? france=? italy=? germany=? australia=? japan=? where id=?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, size.getUniversal());
            statement.setString(2, size.getUs());
            statement.setString(3, size.getUk());
            statement.setString(4, size.getFrance());
            statement.setString(5, size.getItaly());
            statement.setString(6, size.getGermany());
            statement.setString(7, size.getAustralia());
            statement.setString(8, size.getJapan());
            
            
            
            statement.execute();
            statement.close();
            
            System.out.println("Size successfully modified.");
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
        String sqlQuery = "delete from sizes where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, id);
            statement.execute();
            statement.close();
            
            System.out.println("Size successfully deleted.");

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
    private Size read(Long id, boolean closeAfterUse) throws SQLException{

        String sqlQuery = "select * from sizes where id=?";

        PreparedStatement statement;
        Size size = new Size();

        try {
            statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setLong(1, id);

            // 	Executing statement
            ResultSet results = statement.executeQuery();

            if(results.next()){

                size.setId(results.getLong("id"));
                size.setUniversal(results.getString("universal"));
                size.setUs(results.getString("us"));
                size.setUk(results.getString("uk"));
                size.setFrance(results.getString("france"));
                size.setItaly(results.getString("italy"));
                size.setGermany(results.getString("germany"));
                size.setAustralia(results.getString("australia"));
                size.setJapan(results.getString("japan"));

            }

            statement.close();

        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
            
            if (closeAfterUse) {
                connection.close();
    
            }
            
            return size;
        }

    }

}