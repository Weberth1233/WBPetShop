package br.unitins.petshop.converter.jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.unitins.petshop.model.Funcionario;
import br.unitins.petshop.repository.FuncionarioRepository;

@FacesConverter(forClass = Funcionario.class)
public class FuncionarioConverter implements Converter<Funcionario>{

	@Override
	public Funcionario getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty())
			return null;

		FuncionarioRepository repo = new FuncionarioRepository();
		Funcionario funcionario = repo.findById(Integer.valueOf(value.trim()));
		return funcionario;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Funcionario value) {
		if (value == null || value.getId() == null)
			return null;
		return value.getId().toString();
	}


}
