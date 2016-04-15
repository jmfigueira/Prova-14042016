package sistema.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import sistema.modelos.Livro;

public class LivroService extends Service{
	
	public void salvar(Livro livro)
	{
	    
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();	
			em.persist(livro);
		em.getTransaction().commit();	
	    em.close();
		
	}

	

	@SuppressWarnings("unchecked")
	public List <Livro> getLivros()
	{
		
		List <Livro > livros;
		
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("Select f From Livro f");
		livros = q.getResultList();
		em.close();
		
		return livros;
	}
	
	
	public void alterar(Livro livro) {

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();	
			em.merge(livro);
		em.getTransaction().commit();	
	    em.close();
	}
	
	
	public void remover(Livro livro) {

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();	
			livro = em.find(Livro.class,livro.getCodigo());
			em.remove(livro);
		em.getTransaction().commit();	
	    em.close();
	}
	
	
	
	
	
	
	
	
	
}
