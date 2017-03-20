package br.com.projetoarquivo.bean;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.omnifaces.util.Faces;

import br.com.projetoarquivo.domain.Processo;
import br.com.projetoarquivo.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

public class ImprimirBean {
	Processo processo = new Processo();

	public void imprimir(Long idLong) {

		try {

			Long filtroID = idLong;

			String caminho = Faces.getRealPath("/reports/reportServidor.jasper");

			//String caminhoimagem = Faces.getRealPath("/resources/imagens/despacho.png");

			Map<String, Object> parametros = new HashMap<>();

			parametros.put("id", filtroID);

			//parametros.put("caminho_imagem", caminhoimagem);

			Connection conexao = HibernateUtil.getConexao();

			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);

			JasperPrintManager.printReport(relatorio, true);

		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	public void imprimirConstrucao(Long idLong) {


		try {

			Long filtroID = idLong;

			String caminho = Faces.getRealPath("/reports/reportConstrucao.jasper");

			//String caminhoimagem = Faces.getRealPath("/resources/imagens/despacho.png");

			Map<String, Object> parametros = new HashMap<>();

			parametros.put("id", filtroID);

			//parametros.put("caminho_imagem", caminhoimagem);

			Connection conexao = HibernateUtil.getConexao();

			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);

			JasperPrintManager.printReport(relatorio, true);

		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}
