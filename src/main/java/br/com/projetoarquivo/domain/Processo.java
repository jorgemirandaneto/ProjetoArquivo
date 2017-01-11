package br.com.projetoarquivo.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Processo extends GenericDomain {

	@Column(length = 12)
	private String spu;

	@Column(length = 50)
	private String solicitante;

	@Column
	@Temporal(TemporalType.DATE)
	private Date dataEntrada;

	@Column(length = 50)
	private String servidorResponsavel;

	@Column
	@Temporal(TemporalType.DATE)
	private Date dataSaida;

	@Column(length = 150)
	private String observacao;

	@Column
	private boolean situacao;

	@Column
	private Character status;

	@Column(length = 8)
	private String nDespacho;

	@JoinColumn
	@ManyToOne
	private Setor setor;

	@JoinColumn
	@ManyToOne
	private TipoAssunto tipoAssunto;

	@JoinColumn
	@ManyToOne
	private Assunto assunto;
	
	

	public TipoAssunto getTipoAssunto() {
		return tipoAssunto;
	}

	public void setTipoAssunto(TipoAssunto tipoAssunto) {
		this.tipoAssunto = tipoAssunto;
	}

	public Assunto getAssunto() {
		return assunto;
	}

	public void setAssunto(Assunto assunto) {
		this.assunto = assunto;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public String getSpu() {
		return spu;
	}

	public void setSpu(String spu) {
		this.spu = spu;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public String getServidorResponsavel() {
		return servidorResponsavel;
	}

	public void setServidorResponsavel(String servidorResponsavel) {
		this.servidorResponsavel = servidorResponsavel;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getnDespacho() {
		return nDespacho;
	}

	public void setnDespacho(String nDespacho) {
		this.nDespacho = nDespacho;
	}

	public boolean isSituacao() {
		return situacao;
	}

	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

}
