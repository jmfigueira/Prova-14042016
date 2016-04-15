package sistema.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import sistema.modelos.Autor;
import sistema.modelos.Livro;

public class AutorService extends Service{
	
	public void salvar(Autor autor)
	{
	    
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();	
			em.persist(autor);
		em.getTransaction().commit();	
	    em.close();
		
	}

	

	@SuppressWarnings("unchecked")
	public List <Autor> getAutores()
	{
		
		List <Autor > autores;
		
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("Select f From Autor f");
		autores = q.getResultList();
		em.close();
		
		return autores;
	}
	
	
	public void alterar(Autor autor) {

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();	
			em.merge(autor);
		em.getTransaction().commit();	
	    em.close();
	}
	
	
	public void remover(Autor autor) {

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();	
			autor = em.find(Autor.class,autor.getCodigo());
			em.remove(autor);
		em.getTransaction().commit();	
	    em.close();
	}



	public Autor pesquisar(Autor autor) {

		EntityManager em = emf.createEntityManager();
		autor = em.find(Autor.class,autor.getCodigo());
		em.close();
	    
	    return autor;
	}



	@SuppressWarnings("unchecked")
	public List<Livro> pesquisarLivrosAutor(Autor autor) {
		
		List <Livro> livros;
		
		EntityManager em = emf.createEntityManager();
		    
		    //Torna gerenciavel a entidade
		    autor = em.merge(autor);
		    
		    //Atualiza a entidade para não usar dados antigos no cache do JPA
		    //Ver https://wiki.eclipse.org/EclipseLink/Examples/JPA/Caching
			em.refresh(autor);
			
			
			//Recupera a lista de livros
			livros = autor.getLivros();
			
		em.close();
	    
	    return livros;	
	}
	
	
	
	
	
	
	
	
	
}
