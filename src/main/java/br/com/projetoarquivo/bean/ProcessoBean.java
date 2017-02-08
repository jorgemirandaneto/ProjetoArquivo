package br.com.projetoarquivo.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.projetoarquivo.dao.AssuntoDAO;
import br.com.projetoarquivo.dao.OrgaoDAO;
import br.com.projetoarquivo.dao.ProcessoDAO;
import br.com.projetoarquivo.dao.SetorDAO;
import br.com.projetoarquivo.dao.TipoAssuntoDAO;
import br.com.projetoarquivo.domain.Assunto;
import br.com.projetoarquivo.domain.Orgao;
import br.com.projetoarquivo.domain.Processo;
import br.com.projetoarquivo.domain.Setor;
import br.com.projetoarquivo.domain.TipoAssunto;

@ManagedBean
@ViewScoped
public class ProcessoBean {

	private Processo processo;
	private List<Processo> processos;

	private List<Orgao> orgaos;
	private Orgao orgao;

	private List<Setor> setores;

	private TipoAssunto tipoAssunto;
	private List<TipoAssunto> tipos;


	private List<Assunto> assuntos;
	

	public List<Assunto> getAssuntos() {
		return assuntos;
	}

	public void setAssuntos(List<Assunto> assuntos) {
		this.assuntos = assuntos;
	}

	public List<TipoAssunto> getTipos() {
		return tipos;
	}

	public void setTipos(List<TipoAssunto> tipos) {
		this.tipos = tipos;
	}

	public TipoAssunto getTipoAssunto() {
		return tipoAssunto;
	}

	public void setTipoAssunto(TipoAssunto tipoAssunto) {
		this.tipoAssunto = tipoAssunto;
	}

	public List<Processo> getProcessos() {
		return processos;
	}

	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public List<Orgao> getOrgaos() {
		return orgaos;
	}

	public void setOrgaos(List<Orgao> orgaos) {
		this.orgaos = orgaos;
	}

	public Orgao getOrgao() {
		return orgao;
	}

	public void setOrgao(Orgao orgao) {
		this.orgao = orgao;
	}

	public List<Setor> getSetores() {
		return setores;
	}

	public void setSetores(List<Setor> setores) {
		this.setores = setores;
	}

	public void novo() {
		try {
			processo = new Processo();

			ProcessoDAO processoDAO = new ProcessoDAO();
			processos = processoDAO.buscarPorStatus();

			orgao = new Orgao();
			OrgaoDAO orgaoDAO = new OrgaoDAO();
			orgaos = orgaoDAO.listar();

			setores = new ArrayList<>();

			tipoAssunto = new TipoAssunto();
			TipoAssuntoDAO tipoAssuntoDAO = new TipoAssuntoDAO();
			tipos = tipoAssuntoDAO.listar();

			assuntos = new ArrayList<>();

		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao gerar um novo processo");
		}

	}

	@PostConstruct
	public void listar() {
		try {
			ProcessoDAO processoDAO = new ProcessoDAO();
			processos = processoDAO.buscarPorStatus();
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao listar");
		}

	}

	public void salvar() {

		try {
			ProcessoDAO processoDAO = new ProcessoDAO();
			processoDAO.merge(processo);

			novo();

			Messages.addGlobalError("Salvo com sucesso.");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao salvar processo");
			e.printStackTrace();
		}

	}

	public void editar(ActionEvent evento) {
		try {

			processo = (Processo) evento.getComponent().getAttributes().get("processoSelecionado");

			orgao = processo.getSetor().getOrgao();

			OrgaoDAO orgaoDAO = new OrgaoDAO();
			orgaos = orgaoDAO.listar();

			SetorDAO setorDAO = new SetorDAO();
			setores = setorDAO.BuscarOrgao(orgao.getCodigo());
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao editar processo.");
		}

	}

	public void comboOrgao() {
		try {
			if (orgao != null) {
				SetorDAO setorDAO = new SetorDAO();
				setores = setorDAO.BuscarOrgao(orgao.getCodigo());
			} else {
				setores = new ArrayList<>();
			}

		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao filtrar setor");
		}

	}

	public void comboAssunto() {
		if (tipoAssunto != null) {
			AssuntoDAO assuntoDAO = new AssuntoDAO();
			assuntos = assuntoDAO.BuscarAssunto(tipoAssunto.getCodigo());
		} else {
			assuntos = new ArrayList<>();
		}

	}

}
