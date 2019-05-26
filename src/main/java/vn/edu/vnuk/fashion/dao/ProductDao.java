package vn.edu.vnuk.fashion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import vn.edu.vnuk.fashion.jdbc.ConnectionFactory;
import vn.edu.vnuk.fashion.model.Collar;
import vn.edu.vnuk.fashion.model.Height;
import vn.edu.vnuk.fashion.model.Maker;
import vn.edu.vnuk.fashion.model.Material;
import vn.edu.vnuk.fashion.model.Product;
import vn.edu.vnuk.fashion.model.Shape;
import vn.edu.vnuk.fashion.model.Sleeve;

public class ProductDao {
	
	@Autowired
    private SleeveDao sleeveDao;

    @Autowired
    private ShapeDao shapeDao;
    
    @Autowired
    private CollarDao collarDao;

    @Autowired
    private HeightDao heightDao;

    @Autowired
    private MaterialDao materialDao;

    @Autowired
    private MakerDao makerDao;	
    
    private Connection connection;

    public ProductDao(){
        this.connection = new ConnectionFactory().getConnection();
    }

    public ProductDao(Connection connection){
        this.connection = connection;
    }


    //  CREATE
    public void create(Product product) throws SQLException{

        String sqlQuery = "insert into products (name , subcategory_id , sleeve_id , shape_id , collar_id , height_id , material_id , maker_id) "
                        +	"values (? , ? , ? , ? , ? , ? , ? , ?)";

        PreparedStatement statement;

        try {
                statement = connection.prepareStatement(sqlQuery);

                //	Replacing "?" through values
                statement.setString(1, product.getName());
                statement.setLong(2, product.getSubcategory().getId());
                statement.setLong(3, product.getSleeve().getId());
                statement.setLong(4, product.getShape().getId());
                statement.setLong(5, product.getCollar().getId());
                statement.setLong(6, product.getHeight().getId());
                statement.setLong(7, product.getMaterial().getId());
                statement.setLong(8, product.getMaker().getId());

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
    
    
    //  READ (List of Products)
    @SuppressWarnings("finally")
    public List<Product> read() throws SQLException {

        String sqlQuery = "select * from subcategories";
        PreparedStatement statement;
        List<Product> products = new ArrayList<Product>();

        try {

            statement = connection.prepareStatement(sqlQuery);

            // 	Executing statement
            ResultSet results = statement.executeQuery();
            
            while(results.next()){

            	Product product = new Product();
                
                product.setName(results.getString("name"));
                
                // Process foreign key
                Long subcategoryIdFromDB = results.getLong("subcategory_id");
                Long sleeveIdFromDB = results.getLong("sleeve_id");
                Long shapeIdFromDB = results.getLong("shape_id");
                Long collarIdFromDB = results.getLong("collar_id");
                Long heightIdFromDB = results.getLong("height_id");
                Long materialIdFromDB = results.getLong("material_id");
                Long makerIdFromDB = results.getLong("maker_id");

                
                
//                SubcategoryDao subcategoryDao = new SubcategoryDao();
                
                
//                Subcategory subcategory = subcategoryDao.read(subcategoryIdFromDB);
                Sleeve sleeve = sleeveDao.read(sleeveIdFromDB);
                Shape shape = shapeDao.read(shapeIdFromDB);
                Collar collar = collarDao.read(collarIdFromDB);
                Height height = heightDao.read(heightIdFromDB);
                Material material = materialDao.read(materialIdFromDB);
                Maker maker = makerDao.read(makerIdFromDB);
                
//                product.setSubcategory(subcategory);
                product.setSleeve(sleeve);
                product.setShape(shape);
                product.setCollar(collar);
                product.setHeight(height);
                product.setMaterial(material);
                product.setMaker(maker);

                products.add(product);

            }

            results.close();
            statement.close();


        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
                connection.close();
                return products;
        }


    }


    //  READ (Single Category)
    public Product read(Long id) throws SQLException{
        return this.read(id, true);
    }  

    
    //  UPDATE
    public void update(Product product) throws SQLException {
        String sqlQuery = "update products name=? subcategory_id=? sleeve_id=? shape_id=? collar_id=? height_id=? material_id=? maker_id=? where id=?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, product.getName());
            statement.setLong(2, product.getSubcategory().getId());
            statement.setLong(3, product.getSleeve().getId());
            statement.setLong(4, product.getShape().getId());
            statement.setLong(5, product.getCollar().getId());
            statement.setLong(6, product.getHeight().getId());
            statement.setLong(7, product.getMaterial().getId());
            statement.setLong(8, product.getMaker().getId());
            
            statement.execute();
            statement.close();
            
            System.out.println("Product successfully modified.");
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
        String sqlQuery = "delete from products where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, id);
            statement.execute();
            statement.close();
            
            System.out.println("Product successfully deleted.");

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
    private Product read(Long id, boolean closeAfterUse) throws SQLException{

        String sqlQuery = "select * from products where id=?";

        PreparedStatement statement;
        Product product = new Product();

        try {
            statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setLong(1, id);

            // 	Executing statement
            ResultSet results = statement.executeQuery();

            if(results.next()){

            	product.setName(results.getString("name"));
                
                // Process foreign key
                Long subcategoryIdFromDB = results.getLong("subcategory_id");
                Long sleeveIdFromDB = results.getLong("sleeve_id");
                Long shapeIdFromDB = results.getLong("shape_id");
                Long collarIdFromDB = results.getLong("collar_id");
                Long heightIdFromDB = results.getLong("height_id");
                Long materialIdFromDB = results.getLong("material_id");
                Long makerIdFromDB = results.getLong("maker_id");
                
//                Subcategory subcategory = subcategoryDao.read(subcategoryIdFromDB);
                Sleeve sleeve = sleeveDao.read(sleeveIdFromDB);
                Shape shape = shapeDao.read(shapeIdFromDB);
                Collar collar = collarDao.read(collarIdFromDB);
                Height height = heightDao.read(heightIdFromDB);
                Material material = materialDao.read(materialIdFromDB);
                Maker maker = makerDao.read(makerIdFromDB);
                
//                product.setSubcategory(subcategory);
                product.setSleeve(sleeve);
                product.setShape(shape);
                product.setCollar(collar);
                product.setHeight(height);
                product.setMaterial(material);
                product.setMaker(maker);

            }

            statement.close();

        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
            
            if (closeAfterUse) {
                connection.close();
    
            }
            
            return product;
        }

    }

}