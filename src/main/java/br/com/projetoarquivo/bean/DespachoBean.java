package br.com.projetoarquivo.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.projetoarquivo.dao.ProcessoDAO;
import br.com.projetoarquivo.domain.Processo;

@ManagedBean
@ViewScoped
public class DespachoBean {

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
	public void listar() {
		try {
			ProcessoDAO processoDAO = new ProcessoDAO();
			processos = processoDAO.buscarPorStatus(true);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	public void finalizar(ActionEvent event) {

		processo = (Processo) event.getComponent().getAttributes().get("finalizadoSelecionado");

	}

	public void salvar() {

		try {

			ProcessoDAO processoDAO = new ProcessoDAO();
			processoDAO.merge(processo);

			processos = processoDAO.buscarPorStatus(true);

			processo = new Processo();
			Messages.addGlobalInfo("Salvo com sucesso.");

		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao salvar processo");
			e.printStackTrace();
		}

	}

	public void imprimir(ActionEvent event) {

		processo = (Processo) event.getComponent().getAttributes().get("impressaoSelecionado");

		try {
			if (processo.getAssunto().getTipoAssunto().getCodigo() == 2 && processo.isSituacao() == true) {
				ImprimirBean imprimirBean = new ImprimirBean();
				imprimirBean.imprimirConstrucao(processo.getCodigo());
			}else{
				ImprimirBean imprimirBean = new ImprimirBean();		
				imprimirBean.imprimir(processo.getCodigo());
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao gerar despacho.");
		}
		
	}

}
