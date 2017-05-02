package br.com.rarp.utils;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.com.rarp.model.EntradaPaciente;


public class UtilitariosTest {

	@Test
	public void test() {
		List<Field> fields = new ArrayList<>();
		Class<EntradaPaciente> classe = EntradaPaciente.class;
		fields = Utilitarios.getColunas(classe);
		assertTrue("Quantidade de campos inválido", fields.size() == 3);
	}

}
