package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {
	
	private Connection conexao;
	
	public int query(String sql, Object... attributes) {
		try {
			
			PreparedStatement stmt = getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			addAttributes(stmt, attributes);
			
			if(stmt.executeUpdate() > 0) {
				ResultSet result = stmt.getGeneratedKeys();
				
				if(result.next()) {
					return result.getInt(1);
				}
			}
			
			return -1;
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void addAttributes(PreparedStatement stmt, Object[] attributes) throws SQLException {
				
		int index = 1;
		for(Object attribute: attributes) {
			
			if(attribute instanceof String) {
				stmt.setString(index, (String) attribute);
			}else if(attribute instanceof Integer) {
				stmt.setInt(index, (Integer) attribute);
			}
			
			index++;
		}
		
	}
	
	private Connection getConnection() {
		try {
			if(conexao != null && !conexao.isClosed()) {
				return conexao;
			}
		} catch (SQLException e) {
		}
		
		conexao = Connect.getConnection();
		return conexao;
	}

}
