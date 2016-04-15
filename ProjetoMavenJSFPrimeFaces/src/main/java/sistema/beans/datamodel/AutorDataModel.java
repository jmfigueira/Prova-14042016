package sistema.beans.datamodel;

import java.util.List;

import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;
import sistema.modelos.Autor;
import sistema.service.AutorService;


public class AutorDataModel extends ListDataModel<Autor> implements SelectableDataModel<Autor> 
{
	private AutorService servico = new AutorService();
	
	public AutorDataModel()
	{
		
	}

	public AutorDataModel(List <Autor> list)
	{
	   super(list);	
	}

	
	@Override
	public Autor getRowData(String rowKey) {
		
		for(Autor f: servico.getAutores())
		   if(Integer.parseInt(rowKey) ==  f.getCodigo())
			   return f;
		
		return null;
	}

	@Override
	public Object getRowKey(Autor Autor) {
		return Autor.getCodigo();
	}
	
}