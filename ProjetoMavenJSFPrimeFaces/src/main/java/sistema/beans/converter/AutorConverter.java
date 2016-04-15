package sistema.beans.converter;



import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sistema.modelos.Autor;
import sistema.service.AutorService;



@FacesConverter("converterAutor")
public class AutorConverter implements Converter {

	private AutorService servico = new AutorService();
	
	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {

		
		if (value != null && !value.isEmpty()) {
			
			  for(Autor f : servico.getAutores())
				 if(f.getNome().equals(value))
				  	return f;
					
		}

		return null;

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic,
			Object Autor) {
		if (Autor == null || Autor.equals("")) {
			return null;
		} else {
			return ((Autor) Autor).getNome();

		}
	}

}
