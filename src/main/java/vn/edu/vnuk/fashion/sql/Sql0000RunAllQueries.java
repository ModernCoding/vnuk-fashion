package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.SQLException;

import vn.edu.vnuk.fashion.jdbc.ConnectionFactory;

public class Sql0000RunAllQueries {

	public static void main(String[] args) throws SQLException {
		
		Connection connectionDb = new ConnectionFactory()
				.getConnection("jdbc:mysql://localhost/");
		
		// Create database
		
		new Sql1000DropDatabase(connectionDb).run();
		new Sql1010CreateDatabase(connectionDb).run();

		connectionDb.close();
		
		//Create tables 
		
		Connection connectionTable = new ConnectionFactory()
				.getConnection("jdbc:mysql://localhost/vnuk_e_commerce_fashion");
		
		new Sql2010CreateCategories(connectionTable).run();
		new Sql2020CreateSubcategories(connectionTable).run();
		new Sql2030CreateGenders(connectionTable).run();
		new Sql2040CreateBodyParts(connectionTable).run();
		new Sql2050CreateSleeves(connectionTable).run();
		new Sql2060CreatePatterns(connectionTable).run();
		new Sql2070CreateLengths(connectionTable).run();
		new Sql2080CreateShapes(connectionTable).run();
		new Sql2090CreateCollars(connectionTable).run();
		new Sql2100CreateColors(connectionTable).run();
		new Sql2110CreateSizes(connectionTable).run();
		new Sql2120CreateTitles(connectionTable).run();
		new Sql2130CreateSellers(connectionTable).run();
		new Sql2140CreateMakers(connectionTable).run();
		new Sql2150CreatePriceTypes(connectionTable).run();
		new Sql2160CreateSubcategoriesBodyParts(connectionTable).run();
		new Sql2230CreateHeights(connectionTable).run();
		new Sql2240CreateMaterials(connectionTable).run();
		new Sql2170CreateProducts(connectionTable).run();
		new Sql2171CreateProductsSizes(connectionTable).run();
		new Sql2172CreateProductsColors(connectionTable).run();
		new Sql2173CreateProductsPatterns(connectionTable).run();
		new Sql2174CreateProductsLengths(connectionTable).run();
		new Sql2180CreateProductsGenders(connectionTable).run();
		new Sql2190CreatePrices(connectionTable).run();
		new Sql2200CreateCustomers(connectionTable).run();
		new Sql2210CreateOrders(connectionTable).run();
		new Sql2220CreateReviews(connectionTable).run();
		
		
		//Insert data into tables
		
		new Sql5010InsertIntoCategories(connectionTable).run();
		new Sql5020InsertIntoSubcategories(connectionTable).run();
		new Sql5030InsertIntoGenders(connectionTable).run();
		new Sql5040InsertIntoBodyParts(connectionTable).run();
		new Sql5050InsertIntoSleeves(connectionTable).run();
		new Sql5060InsertIntoPatterns(connectionTable).run();
		new Sql5070InsertIntoLengths(connectionTable).run();
		new Sql5080InsertIntoShapes(connectionTable).run();
		new Sql5090InsertIntoCollars(connectionTable).run();
		new Sql5100InsertIntoColors(connectionTable).run();
		new Sql5110InsertIntoSizes(connectionTable).run();
		new Sql5120InsertIntoTitles(connectionTable).run();
		new Sql5130InsertIntoSellers(connectionTable).run();
		new Sql5140InsertIntoMakers(connectionTable).run();
		new Sql5150InsertIntoPriceTypes(connectionTable).run();
		new Sql5160InsertIntoSubcategoriesBodyParts(connectionTable).run();
		new Sql5230InsertIntoHeights(connectionTable).run();
		new Sql5240InsertIntoMaterials(connectionTable).run();
		new Sql5170InsertIntoProducts(connectionTable).run();
		new Sql5171InsertIntoProductsSizes(connectionTable).run();
		new Sql5172InsertIntoProductsColors(connectionTable).run();
		new Sql5173InsertIntoProductsPatterns(connectionTable).run();
		new Sql5174InsertIntoProductsLengths(connectionTable).run();
		new Sql5180InsertIntoProductsGenders(connectionTable).run();
		new Sql5190InsertIntoPrices(connectionTable).run();
		new Sql5200InsertIntoCustomers(connectionTable).run();
		new Sql5210InsertIntoOrders(connectionTable).run();
		new Sql5220InsertIntoReviews(connectionTable).run();
		
		connectionTable.close();
		
	}

}
