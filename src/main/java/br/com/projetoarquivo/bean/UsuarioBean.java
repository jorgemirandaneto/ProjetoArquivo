package br.com.projetoarquivo.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.projetoarquivo.dao.UsuarioDAO;
import br.com.projetoarquivo.domain.Usuario;

@ManagedBean
@ViewScoped
public class UsuarioBean {

	private Usuario usuario;

	private List<Usuario> usuarios;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@PostConstruct
	public void listar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.listar();
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar listar usuários");
		}
	}

	public void novo() {
		try {
			usuario = new Usuario();

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.listar();
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar criar um novo usuário");
		}
	}

	public void salvar() {
		try {

			usuario.senhaCriptografada();

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.merge(usuario);

			novo();

			Messages.addGlobalInfo("Usuário cadastrado com sucesso.");

		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao salvar");
		}
	}

	public void excluir(ActionEvent event) {
		try {
			usuario = (Usuario) event.getComponent().getAttributes().get("usuarioSelecionado");
			
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.excluir(usuario);
			
			usuarios = usuarioDAO.listar();
			usuarioDAO.excluir(usuario);
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Erro ao excluir usuário");
		}
	}
	
	public void editar(ActionEvent evento) {
		try {
			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
					
		} catch (RuntimeException e) {
			e.printStackTrace();
			Messages.addGlobalError("Usuário editado");
		}
		
	}

}
