package br.com.projetoarquivo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class TipoAssunto extends GenericDomain {
	
	@Column
	private String nomeTipoAssunto;

	public String getNomeTipoAssunto() {
		return nomeTipoAssunto;
	}

	public void setNomeTipoAssunto(String nomeTipoAssunto) {
		this.nomeTipoAssunto = nomeTipoAssunto;
	}
	
	

}
