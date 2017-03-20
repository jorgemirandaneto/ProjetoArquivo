package br.com.projetoarquivo.bean;

import br.com.projetoarquivo.interfaces.IImpressaoProcesso;

public class ImpressaoFactory {
	
	static final int PROCESSO_NORMAL = 1;
	static final int PROCESSO_CONSTRUCAO = 2;
	static final int PROCESSO_CONSTRUCAO_ATENDIDO = 3;
	static final int PROCESSO_CONSTRUCAO_PARCIALMENTE_ATENDIDO = 4;
	
	public static IImpressaoProcesso getInstanceImpressao(Long tipoProcesso){
		
		if (tipoProcesso == PROCESSO_NORMAL) {
			return new ImprimirBean();
		}else if(tipoProcesso == PROCESSO_CONSTRUCAO){
			return new ImprimirConstrucaoBean();
		}else if(tipoProcesso == PROCESSO_CONSTRUCAO_ATENDIDO){
			return new ImprimirConstrucaoAtendidoBean();
		}else{
			return new ImprimirConstrucaoParcialmenteAtendidoBean();
		}
	}

}
