package vn.edu.vnuk.fashion.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql5130InsertIntoSellers {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5130InsertIntoSellers(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO sellers (label, address, phone, email) "
				+ 	"values "
				
				+ 	"('Mee House', '327 Hung Vuong, Da Nang, Viet Nam', '0914162689', 'meehouse@gmail.com' ), "
				+ 	"('Guu Store', '169 Phan Chu Trinh, Da Nang, Viet Nam', '01669788679', 'guustore@gmail.com'), "
				+ 	"('Xon Xen Shop', '248 Le Duan, Da Nang, Viet Nam', '0978178764', 'xonxenshop@gmail.com'),"
				+ 	"('Davies Fashion Studio','162 Trieu Nu Vuong, Da Nang, Viet Nam','0979809204', 'daviesfashionstudio@gmail.com'),"
				+ 	"('Germe Shop', '258 Ba Trieu, Ha Noi, Viet Nam', '0905682529', 'germeshop@gmail.com' ),"
				+ 	"('More Than Basic', '4 Hoang Dieu, Ho Chi Minh, Viet Nam', '0937303282', 'morethanbasic@gmail.com'),"
				+ 	"('Factory Outlet', '117 Tran Te Xuong, Ho Chi Minh, Viet Nam', '0988753023', ' '),"
				+ 	"('The NySmile', '436 Le Duan, Ha Noi, Viet Nam', '0972907579', 'thenysmile@gmail'),"
				+ 	"('Chiclala Accessories', '164 Vo Thi Sau, Hai Phong, Viet Nam', '0904054732', ' '),"
				+ 	"('I.AM Shop', '38 CMT8, Vinh, Viet Nam', '0983043195', 'i.amshop@gmail.com' ),"
				+ 	"('Little Devil Shop', '15 Huynh Van Banh, Ho Chi Minh, Viet Nam', '0976096113', 'littledevilshop@gmail.com' ),"
				+ 	"('Yuna Shop', '32/12 Tran Ke Xuong, Vinh, Viet Nam', '0123123532', 'yunashop@gmail.com'),"
				+ 	"('Fumyz Shop', '330 Bac Hai, Hai Duong, Viet Nam', '0912992158', 'fumyzshop@gmail.com' ),"
				+ 	"('Natthy Style', '24 Dinh Bo Linh, Can Tho, Viet Nam', '0979389891', 'natthystyle@gmail.com'),"
				+ 	"('QF Boutique', '120 Tran Binh Trong, Quang Nam, Viet Nam', '0979680065', 'QFboutique@gmail.com'), "
				+	"('The Little Owl', '12/7 Le Duan, Hue, Viet Nam', '0976764388', 'thelittleowl@gmail.com' ),"
				+ 	"('Wildshop', '368 Phan Thanh, Hue, Viet Nam', '0975808375','wildshop@gmail.com'),"
				+ 	"('Whale Accessorize', '456 Tran Quoc Toan, Quang Binh, Viet Nam', '0918612410', ''),"
				+ 	"('ABC shop', '179 Truong Chinh, Nghe An, Viet Nam', '0914421796', 'abcshop@gmail.com'),"
				+ 	"('Gemini store', '459 Le Loi, Da Nang, Viet Nam', '0986109656', 'geministore@gmail.com')"
				+ ";"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5130InsertIntoSellers started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   DATA successfully loaded in \'sellers\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5130InsertIntoSellers ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
