package br.com.projetoarquivo.bean;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.projetoarquivo.dao.ProcessoDAO;
import br.com.projetoarquivo.domain.Processo;
import br.com.projetoarquivo.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

@ManagedBean
@ViewScoped
public class FechamentoBean {

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
			processos = processoDAO.buscarPorStatus();
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

			processos = processoDAO.buscarPorStatus();

			processo = new Processo();
			Messages.addGlobalInfo("Salvo com sucesso.");

		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao salvar processo");
			e.printStackTrace();
		}

	}

	
	
//Corrigir
	public void imprimir(ActionEvent event) {

		processo = (Processo) event.getComponent().getAttributes().get("impressaoSelecionado");
	
		try {
		
		Long filtroID = processo.getCodigo();
			
		String caminho = Faces.getRealPath("/reports/arquivo01.jasper");
		
		//String caminhoimagem = Faces.getRealPath("/resources/imagens/arquivo.jpg");
		
		Map<String, Object> parametros = new HashMap<>();
		
		parametros.put("id", filtroID );
		
		//parametros.put("caminho_imagem", caminhoimagem);
	
		System.out.println(parametros);
		
		Connection conexao = HibernateUtil.getConexao();
		
		JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
		
		JasperPrintManager.printReport(relatorio, true);

		} catch (JRException e) {
			e.printStackTrace();
		}

	}

}	
