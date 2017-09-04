package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.models.Produto;

@Repository // Spring precisa "conhecer" os DAOs. Em outras palavras dizemos que devemos definir que o ProdutoDAO será gerenciado pelo Spring. Para isso devemos marcar o ProdutoDAO com a anotação @Repository.
@Transactional // Depois de habilitado a transacao nas configuracoes com @EnableTransactionManagement, habilitamos o DAO com a anottion @Transactional e assim os metodos do DAO serao transacionados
public class ProdutoDAO {
	
	@PersistenceContext
	private EntityManager manager; 
	
	public void gravar(Produto produto) {
		
		manager.persist(produto);
			
	}

	public List<Produto> listar() {
		return manager.createQuery("Select p from Produto p", Produto.class)
				.getResultList();
		
	}

	public Produto find(Integer id) {
		return manager.createQuery("select distinct(p) from Produto p join fetch p.precos precos where p.id = :id", 
				Produto.class).
				setParameter("id", id).
				getSingleResult();
	}



}
