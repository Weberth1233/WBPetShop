package br.unitins.petshop.converter.jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.unitins.petshop.model.Cliente;
import br.unitins.petshop.repository.ClienteRepository;

@FacesConverter(forClass = Cliente.class)
public class ClienteConverter implements Converter<Cliente> {
	
	@Override
	public Cliente getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty())
			return null;
		
		ClienteRepository repo = new ClienteRepository();
		Cliente cliente = repo.findById(Integer.valueOf(value.trim()));
		return cliente;
	}
	@Override
	public String getAsString(FacesContext context, UIComponent component, Cliente value) {
		if (value == null || value.getId() == null)
			return null;
		return value.getId().toString();
	}
}
