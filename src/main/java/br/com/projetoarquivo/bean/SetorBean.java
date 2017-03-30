package br.com.projetoarquivo.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.projetoarquivo.dao.OrgaoDAO;
import br.com.projetoarquivo.dao.SetorDAO;
import br.com.projetoarquivo.domain.Orgao;
import br.com.projetoarquivo.domain.Setor;

@ManagedBean
@ViewScoped
public class SetorBean {
	private Setor setor;
	private List<Orgao> orgaos;
	private List<Setor> setores;
	
	
	
	public List<Setor> getSetores() {
		return setores;
	}
	public void setSetores(List<Setor> setores) {
		this.setores = setores;
	}
	public Setor getSetor() {
		return setor;
	}
	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	public List<Orgao> getOrgaos() {
		return orgaos;
	}
	public void setOrgaos(List<Orgao> orgaos) {
		this.orgaos = orgaos;
	}
	
	public void novo() {
		
		try {
			setor = new Setor();
			
			OrgaoDAO orgaoDAO = new OrgaoDAO();
			orgaos = orgaoDAO.listar();
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao criar um novo");
		}
	
	}
	
	@PostConstruct
	public void listar() {
		try {
			SetorDAO setorDAO = new SetorDAO();
			setores = setorDAO.listar();
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalFatal("Erro ao listar setores");
		}

	}
}
