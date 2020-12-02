package br.unitins.petshop.converter.jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.unitins.petshop.model.Veterinario;
import br.unitins.petshop.repository.VeterinarioRepository;

@FacesConverter(forClass = Veterinario.class)
public class VeterinarioConverter implements Converter<Veterinario>{

	@Override
	public Veterinario getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty())
			return null;

		VeterinarioRepository repo = new VeterinarioRepository();
		Veterinario vet = repo.findById(Integer.valueOf(value.trim()));
		return vet;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Veterinario value) {
		if (value == null || value.getId() == null)
			return null;
		return value.getId().toString();
	}
}
