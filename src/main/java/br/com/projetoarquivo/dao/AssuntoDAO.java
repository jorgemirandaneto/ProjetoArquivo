package br.com.projetoarquivo.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.projetoarquivo.domain.Assunto;
import br.com.projetoarquivo.util.HibernateUtil;

public class AssuntoDAO extends GenericDAO<Assunto> {
	@SuppressWarnings("unchecked")
	public List<Assunto> BuscarAssunto(Long codigoTipoAssunto){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Assunto.class);
			consulta.add(Restrictions.eq("tipoAssunto.codigo", codigoTipoAssunto));
			List<Assunto> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException e) {
			throw e;
		}finally {
			sessao.close();
		}
		
		
	}
}
