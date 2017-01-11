package br.com.projetoarquivo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.apache.shiro.crypto.hash.SimpleHash;

@SuppressWarnings("serial")
@Entity
public class Usuario extends GenericDomain {

	@Column(length = 50, nullable = false)
	private String nome;

	@Column(length = 14, nullable = false)
	private String cpf;

	@Column(length = 32, nullable = false)
	private String senha;

	@Transient
	private String senhaSemCriptografia;

	@Column(nullable = false)
	private Character tipo;

	@Column(nullable = false)
	private Boolean ativo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Character getTipo() {
		return tipo;
	}

	public void setTipo(Character tipo) {
		this.tipo = tipo;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getSenhaSemCriptografia() {
		return senhaSemCriptografia;
	}

	public void setSenhaSemCriptografia(String senhaSemCriptografia) {
		this.senhaSemCriptografia = senhaSemCriptografia;
	}

	@Transient
	public String getTipoFormatado() {
		String tipoFormatado = null;

		if (tipo == 'A') {
			tipoFormatado = "Administrador";
		} else if (tipo == 'B') {
			tipoFormatado = "Apoio";
		}
		return tipoFormatado;

	}

	@Transient
	public String getAtivoFormatado() {
		String ativoFormatado = null;

		if (ativo == true) {
			ativoFormatado = "Ativo";
		} else if (ativo == false) {
			ativoFormatado = "Inativo";
		}	

		return ativoFormatado;

	}

	@Transient 
	public void senhaCriptografada(){
	SimpleHash hash = new SimpleHash("md5" , getSenhaSemCriptografia());
	setSenha(hash.toHex());
		
	}

}