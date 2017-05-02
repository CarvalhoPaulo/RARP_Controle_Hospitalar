package br.com.rarp.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.thoughtworks.qdox.parser.structs.FieldDef;

import br.com.rarp.annotations.Coluna;
import br.com.rarp.annotations.Descricao;

public class Utilitarios {
	public static List<Field> getColunas(Class classe) {
		List<Field> colunas = new ArrayList<>();
		if(classe.getSuperclass() != Object.class)
			colunas = getColunas(classe.getSuperclass());
		for(Field f: classe.getDeclaredFields()) {
			if(f.getDeclaredAnnotation(Coluna.class) != null) {
				if(f.getType().isPrimitive() || f.getType() == String.class || f.getType() == Date.class) {
					colunas.add(f);
				} else {
					colunas.add(getDescricao(f.getType()));
				}
			}
		}
		return colunas;
	}
	
	private static Field getDescricao(Class classe) {
		if(classe.getSuperclass() != Object.class)
			return getDescricao(classe.getSuperclass());
		for(Field f: classe.getDeclaredFields()) {
			if(f.getType().isPrimitive() || f.getType() == String.class || f.getType() == Date.class) {
				if(f.getDeclaredAnnotation(Descricao.class) != null)
					return f;
			} else {
				return getDescricao(f.getType());
			}
		}
		return null;
	}
}
