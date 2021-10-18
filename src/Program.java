import java.time.LocalDate;

import database.DAO;

public class Program {
	
	public static void main(String[] args) {
		
		DAO dao = new DAO();
		
		String sql = "INSERT INTO tb_post(autor, data, texto, titulo) VALUES(?, ?, ?, ?)";
		
		dao.query(sql, "Andressa", String.format("%s", LocalDate.now()), "Olá mundo", "Hello World");
		
	}

}
