package interfaces;

import java.util.List;
import entities.Produto;

public interface IProdutoRepository {

	void create(Produto produto) throws Exception;

	void update(Produto produto) throws Exception;

	void delete(Produto produto) throws Exception;

	List<Produto> findAll() throws Exception;

	Produto findById(Integer id) throws Exception;
	
	
	
	
	
	
	
}
