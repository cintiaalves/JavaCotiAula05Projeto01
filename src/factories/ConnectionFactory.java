package factories;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	
		// parametros de conex�o..
		private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/aula05?useTimezone=true&serverTimezone=UTC&useSSL=false";
		private static final String USERNAME = "root";
		private static final String PASSWORD = "admin";

		// m�todo para gerar a conex�o..
		public Connection getConnection() throws Exception {
			return DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
		}
}

