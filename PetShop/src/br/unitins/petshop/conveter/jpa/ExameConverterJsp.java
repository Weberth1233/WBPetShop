package br.unitins.petshop.conveter.jpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.unitins.petshop.model.Exame;


@Converter(autoApply = true)
public class ExameConverterJsp implements AttributeConverter<Exame, Integer> {

	@Override
	public Integer convertToDatabaseColumn(Exame attribute) {
		return attribute == null? null : attribute.getId();
	}

	@Override
	public Exame convertToEntityAttribute(Integer dbData) {
		// TODO Auto-generated method stub
		return null;
	}


}
