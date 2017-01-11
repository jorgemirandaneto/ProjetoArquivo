package br.com.projetoarquivo.bean;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.projetoarquivo.dao.UsuarioDAO;
import br.com.projetoarquivo.domain.Usuario;

@ManagedBean
@SessionScoped
public class AutenticacaoBean {
	private Usuario usuario;
	private Usuario usuarioLogado;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	@PostConstruct
	private void iniciar() {
		usuario = new Usuario();
	}

	public void autenticar() {

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioLogado = usuarioDAO.autenticar(usuario.getCpf(), usuario.getSenha());

		if (usuarioLogado == null) {
			Messages.addGlobalError("Usuario não encontrado");
		} else {
			try {
				Faces.redirect("./pages/principal.xhtml");
				Messages.addGlobalInfo("Seja bem-vindo " + getUsuarioLogado());
			} catch (IOException e) {
				e.printStackTrace();
				Messages.addGlobalError("Erro ao redirecionar.");
			}
		}
		
	}
	
	public void encerrar(){
		
		try {
		usuarioLogado = null;
		Faces.refresh();
		} catch (IOException e) {
			Messages.addGlobalError("Erro ao deslogar!");
			e.printStackTrace();
		}
	}

}
