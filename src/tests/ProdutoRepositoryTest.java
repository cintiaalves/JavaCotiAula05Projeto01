package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.Produto;
import factories.ConnectionFactory;
import interfaces.IProdutoRepository;
import repositories.ProdutoRepository;

class ProdutoRepositoryTest {
	
	private IProdutoRepository produtoRepository;
	private Produto produto;
	
	

	@BeforeEach
	void setUp() throws Exception {
		
		ConnectionFactory factory = new ConnectionFactory();
		produtoRepository = new ProdutoRepository(factory.getConnection());		
		produto = new Produto(null, "Produto Teste", 1000.0, 10);
	}

	@Test
	void testCreate() throws Exception {
		
		produtoRepository.create(produto);
		
		assertNotNull(produto.getIdProduto());
		
		Produto produtoCriado = produtoRepository.findById(produto.getIdProduto());
		
		assertEquals(produto, produtoCriado);
		
	}

	@Test
	void testUpdate() throws Exception {
		
		produtoRepository.create(produto);
		
		produto.setNome("Produto Teste atualização");
		produto.setPreco(2000.0);
		produto.setQuantidade(20);
		
		produtoRepository.update(produto);
		
		Produto produtoAtualizado = produtoRepository.findById(produto.getIdProduto());
		
		assertEquals(produto, produtoAtualizado);
		
	}
		
	@Test
	void testDelete() throws Exception {
		
		produtoRepository.create(produto);
		
		produtoRepository.delete(produto);
		
		Produto produtoExcluido = produtoRepository.findById(produto.getIdProduto());
		
		assertNull(produtoExcluido);
		
	}
	
	
	@Test
	void testFindAll() throws Exception {
		
		produtoRepository.create(produto);
		
		List<Produto> lista = produtoRepository.findAll();
		
		Predicate<Produto> filtro = p -> p.getIdProduto().equals(produto.getIdProduto());
		
		Produto produtoConsulta = lista.stream()
									.filter(filtro)
									.findFirst()
									.orElse(null);
		
		assertNotNull(produtoConsulta);
		
		assertEquals(produto, produtoConsulta);
		
	}
	
	
	@Test
	void testFindById() throws Exception {
		
		produtoRepository.create(produto);
		
		Produto produtoConsulta = produtoRepository.findById(produto.getIdProduto());
		
		assertNotNull(produtoConsulta);
		
		assertEquals(produto, produtoConsulta);
		
		
	}
	
	
	
}
