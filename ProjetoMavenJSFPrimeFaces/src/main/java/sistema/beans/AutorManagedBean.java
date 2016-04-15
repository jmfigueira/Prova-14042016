package sistema.beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import sistema.beans.datamodel.AutorDataModel;
import sistema.modelos.Autor;
import sistema.modelos.Livro;
import sistema.service.AutorService;

@ManagedBean
@ViewScoped
public class AutorManagedBean {

	private Autor autor = new Autor();
	private Autor autorSelecionado;
    private AutorService servico = new AutorService();
    private List <Autor> autores;
	

	/**
	 * @return the autorSelecionado
	 */
	public Autor getAutorSelecionado() {
		return autorSelecionado;
	}

	/**
	 * @param autorSelecionado the autorSelecionado to set
	 */
	public void setAutorSelecionado(Autor autorSelecionado) {
		this.autorSelecionado = autorSelecionado;
	}

	public void salvar()
	{
		servico.salvar(autor);
		
		if(autores != null)
			autores.add(autor);
		
		
		autor = new Autor();
		
	}
	
	public DataModel<Autor> getAutores()
	{
		if(autores == null)
			autores = servico.getAutores();
		
		return new AutorDataModel(autores);
	}
	
	
	public Autor getAutor() {
		return autor;
	}


	public void remove(Autor autor)
	{
		if(servico.pesquisarLivrosAutor(autor).size() > 0)
		{
		   FacesContext context = FacesContext.getCurrentInstance();
           context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Não é possível remover autor","Autor possui livros!"));
		}
		else
		{
			servico.remover(autor);
			autores.remove(autor);
		}
	}
	
	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public List <Livro> getLivrosAutor()
	{
		if(autorSelecionado != null)
		{
			return servico.pesquisarLivrosAutor(autorSelecionado);
		}
		else
			return null;
	}
	
	
	public void onRowEdit(RowEditEvent event) {

		Autor f = ((Autor) event.getObject());
		servico.alterar(f);
	}

	
	
}
