package br.unitins.petshop.converter.jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.unitins.petshop.model.Exame;
import br.unitins.petshop.repository.ExameRepository;

@FacesConverter(forClass = Exame.class)

public class ExameConverter implements Converter<Exame> {

	@Override
	public Exame getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty())
			return null;

		ExameRepository repo = new ExameRepository();
		Exame exame = repo.findById(Integer.valueOf(value.trim()));
		return exame;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Exame value) {
		if (value == null || value.getId() == null)
			return null;
		return value.getId().toString();
	}

}
