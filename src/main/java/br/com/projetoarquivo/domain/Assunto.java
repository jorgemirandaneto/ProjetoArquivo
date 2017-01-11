package br.com.projetoarquivo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@SuppressWarnings("serial")
@Entity
public class Assunto extends GenericDomain {
	
	@Column(length = 150)
	private String nomeAssunto;
	
	@JoinColumn
	@ManyToOne
	private TipoAssunto tipoAssunto;
	
		
	public TipoAssunto getTipoAssunto() {
		return tipoAssunto;
	}

	public void setTipoAssunto(TipoAssunto tipoAssunto) {
		this.tipoAssunto = tipoAssunto;
	}

	public String getNomeAssunto() {
		return nomeAssunto;
	}

	public void setNomeAssunto(String nomeAssunto) {
		this.nomeAssunto = nomeAssunto;
	}
	

}
