package br.com.projetoarquivo.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

import br.com.projetoarquivo.bean.AutenticacaoBean;
import br.com.projetoarquivo.domain.Usuario;


@SuppressWarnings("serial")
public class AutenticacaoListener implements PhaseListener{

	@Override
	public void afterPhase(PhaseEvent event) {
		String paginaAtual = Faces.getViewId();
	
		boolean ehPaginaDeAutenticacao = paginaAtual.contains("home.xhtml");
	
		if(!ehPaginaDeAutenticacao){
			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");

			if(autenticacaoBean == null){
				Faces.navigate("/pages/home.xhtml");
				return;
			}
			
			Usuario usuario = autenticacaoBean.getUsuarioLogado();
			if(usuario == null){
				Faces.navigate("/pages/home.xhtml");
				return;
			}
		}		
	}

	@Override
	public void beforePhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
