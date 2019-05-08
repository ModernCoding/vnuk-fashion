package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql5200InsertIntoCustomers {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5200InsertIntoCustomers(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO customers (title_id , label , address , phone , email) "
				+ 	"values "
				+ 	"(1, 'Hoang Quan', '103 Nguyen Sang' , '0912334230' , 'quan.hoang170203@vnuk.edu.vn' ),"
				+ 	"(2, 'Nguyen Huynh Anh Thu' , '105 Dien Bien phu' , '0934333822' , 'thu.nguyen182003@vnuk.vn'), "
				+ 	"(1, 'Vu Huy Hieu' , '69 Hai Phong' , '0903445667' , 'hieu.vu123456@vnuk.edu.vn'), "
				+ 	"(1, 'Tran Ngoc Minh Quan', '105 Dien Bien Phu' , '0905632240' , 'quan.tran170202@vnuk.edu.vn' ),"
				+ 	"(2, 'Le Thi Thu Huong', '66 Trung Nu Vuong' , '0912533530' , 'huong.le172253@vnuk.edu.vn' ),"
				+ 	"(1, 'Nguyen Dang Trung Viet', '11 Le loi' , '0912334230' , 'viet.nguyen170503@vnuk.edu.vn' ),"
				+ 	"(3, 'Le Thi Tham', '105 Nguyen Toi' , '0904534230' , 'tham.le172403@vnuk.edu.vn' ),"
				+ 	"(3, 'Hoang Thi Ngoc', '22 Le Lai' , '0937894230' , 'ngoc.hoang189203@vnuk.edu.vn' ),"
				+ 	"(2, 'Le Luyn', '89 Viet Nguyen' , '0987654321' , 'luyn.viet170203@vnuk.edu.vn' ),"
				+ 	"(2, 'Le Ngoc Anh', '23 Le Ngoc' , '0987653214' , 'anh.le170203@vnuk.edu.vn' ),"
				+ 	"(1, 'Hoang Ky', '56 Nguyen Sang' , '0987123456' , 'ky.hoang174444@vnuk.edu.vn' ),"
				+ 	"(1, 'Le Hoang', '26 Le Huu Trac' , '0905768908' , 'hoang.le123456@vnuk.edu.vn' ),"
				+ 	"(3, 'Vo Bao Ngoc', '78 Le No' , '0989123456' , 'ngoc.vo123786@vnuk.edu.vn' ),"
				+ 	"(3, 'Le Bao Ngoc', '77 Le Ne' , '0123456789' , 'ngoc.le123567@vnuk.edu.vn' ),"
				+ 	"(2, 'Le Thi No', '77 Tran Binh Trong' , '0123456789' , 'no.le123567@vnuk.edu.vn' ),"
				+ 	"(2, 'Banh Thi No', '33 Dang Huy Ta' , '123456730' , 'no.banh233444@vnuk.edu.vn' ),"
				+ 	"(1, 'Hoang Phi Hong', '33 Trien Chieu' , '0123457689' , 'hong.hoang334321@vnuk.edu.vn' ),"
				+ 	"(2, 'Le Bum Bum', '27 Banh To' , '0987612345' , 'bum.bum234567@vnuk.edu.vn' ),"
				+ 	"(3, 'Tran Thi Le', '44 No Na' , '0987776543' , 'le.tran234578@vnuk.edu.vn' ),"
				+ 	"(1, 'Hoang Tran Khung', '103 To Thi' , '0122346667' , 'khung.hoang123456@vnuk.edu.vn' )"
				+ ";"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5200InsertIntoCustomers started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   DATA successfully loaded in \'customers\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5200InsertIntoCustomers ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
