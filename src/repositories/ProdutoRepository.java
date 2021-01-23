package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entities.Produto;
import interfaces.IProdutoRepository;

public class ProdutoRepository implements IProdutoRepository {

	private Connection connection;

	public ProdutoRepository(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void create(Produto produto) throws Exception {

		String query = "insert into produto values(null, ?, ?, ?)";

		PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
		statement.setString(1, produto.getNome());
		statement.setDouble(2, produto.getPreco());
		statement.setInt(3, produto.getQuantidade());
		statement.execute();

		ResultSet result = statement.getGeneratedKeys();
		if (result.next()) {
			produto.setIdProduto(result.getInt(1));
		}

		statement.close();

	}

	@Override
	public void update(Produto produto) throws Exception {

		String query = "update produto set nome = ?, preco = ?, quantidade = ? where idproduto = ?";

		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, produto.getNome());
		statement.setDouble(2, produto.getPreco());
		statement.setInt(3, produto.getQuantidade());
		statement.setInt(4, produto.getIdProduto());

		statement.execute();
		statement.close();

	}

	@Override
	public void delete(Produto produto) throws Exception {

		String query = "delete from produto where idproduto = ?";

		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, produto.getIdProduto());

		statement.execute();
		statement.close();

	}

	@Override
	public List<Produto> findAll() throws Exception {

		String query = "select * from produto";

		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet result = statement.executeQuery();

		List<Produto> lista = new ArrayList<Produto>();

		while (result.next()) { // lendo cada registro obtido pelo ResultSet

			Produto produto = new Produto();

			produto.setIdProduto(result.getInt("idproduto"));
			produto.setNome(result.getString("nome"));
			produto.setPreco(result.getDouble("preco"));
			produto.setQuantidade(result.getInt("quantidade"));

			lista.add(produto); // adicionar na lista..
		}

		return lista;
	}

	@Override
	public Produto findById(Integer id) throws Exception {

		String query = "select * from produto where idproduto = ?";

		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, id);

		ResultSet result = statement.executeQuery();

		if (result.next()) { // lendo o registro obtido pelo ResultSet

			Produto produto = new Produto();

			produto.setIdProduto(result.getInt("idproduto"));
			produto.setNome(result.getString("nome"));
			produto.setPreco(result.getDouble("preco"));
			produto.setQuantidade(result.getInt("quantidade"));

			return produto;
		}

		else {
			return null;
		}

	}

}
