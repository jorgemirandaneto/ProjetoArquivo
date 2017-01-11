package br.com.projetoarquivo.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.projetoarquivo.domain.Setor;
import br.com.projetoarquivo.util.HibernateUtil;

public class SetorDAO extends GenericDAO<Setor>{

	@SuppressWarnings("unchecked")
	public List<Setor> BuscarOrgao(Long codigoOrgao){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Setor.class);
			consulta.add(Restrictions.eq("orgao.codigo", codigoOrgao));
			List<Setor> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException e) {
			throw e;
		}finally {
			sessao.close();
		}
		
		
	}
	


}
