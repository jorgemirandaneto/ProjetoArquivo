package br.com.projetoarquivo.util;

import org.hibernate.Session;
import org.junit.Test;

import br.com.projetoarquivo.util.HibernateUtil;

public class HibernateUtilTest {
	@Test
	public void conectar(){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		sessao.close();
		HibernateUtil.getFabricaDeSessoes().close();
	}
}
