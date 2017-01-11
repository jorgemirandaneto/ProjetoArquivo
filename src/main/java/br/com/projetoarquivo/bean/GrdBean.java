package br.com.projetoarquivo.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.projetoarquivo.dao.GrdDAO;
import br.com.projetoarquivo.domain.Processo;

@ManagedBean
@ViewScoped
public class GrdBean {
	private Processo processo;
	private List<Processo> processos;
	
	
		
	public Processo getProcesso() {
		return processo;
	}



	public void setProcesso(Processo processo) {
		this.processo = processo;
	}



	public List<Processo> getProcessos() {
		return processos;
	}



	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}



	@PostConstruct
	public void listar(){
		try {
			GrdDAO grdDAO = new GrdDAO();
			processos = grdDAO.Finalizados();	
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		
	}
	
}
