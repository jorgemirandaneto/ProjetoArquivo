package br.com.projetoarquivo.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.projetoarquivo.domain.Processo;
import br.com.projetoarquivo.util.HibernateUtil;

public class ProcessoDAO extends GenericDAO<Processo>{
	
	@SuppressWarnings("unchecked")
	public List<Processo> buscarPorStatus(Boolean status){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria consulta = sessao.createCriteria(Processo.class);
			consulta.add(Restrictions.eq("situacao", status));
			List<Processo> resultado = consulta.list();
			return resultado;
		}catch (RuntimeException erro){
			throw erro;
		}finally {
			sessao.close();
		}
	}
	

}
