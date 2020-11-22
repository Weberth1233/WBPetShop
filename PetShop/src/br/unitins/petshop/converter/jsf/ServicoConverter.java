package br.unitins.petshop.converter.jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.unitins.petshop.model.Servico;
import br.unitins.petshop.repository.ServicoRepository;

@FacesConverter(forClass = Servico.class)
public class ServicoConverter implements Converter<Servico> {

	@Override
	public Servico getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty())
			return null;
		
		ServicoRepository repo = new ServicoRepository();
		Servico servico = repo.findById(Integer.valueOf(value.trim()));
		return servico;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Servico value) {
		if (value == null || value.getId() == null)
			return null;
		return value.getId().toString();
	}

}
