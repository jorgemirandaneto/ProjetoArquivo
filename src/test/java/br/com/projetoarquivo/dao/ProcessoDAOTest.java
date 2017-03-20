package br.com.projetoarquivo.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.projetoarquivo.domain.Processo;

public class ProcessoDAOTest {

	@Test
	@Ignore
	public void listar(){
		ProcessoDAO processoDAO = new ProcessoDAO();
		List<Processo> resultado = processoDAO.buscarPorStatus(true);
		for(Processo processo : resultado){
			System.out.println(processo.getSolicitante());
		}
	}
}
