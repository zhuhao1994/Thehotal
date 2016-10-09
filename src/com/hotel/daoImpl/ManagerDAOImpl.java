package com.hotel.daoImpl;


import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.springframework.stereotype.Component;

import com.hotel.HButil.HButil;
import com.hotel.dao.ManagerDAO;
import com.hotel.pojo.Manager;
@Component(value="managerDao")
public class ManagerDAOImpl extends HButil implements ManagerDAO {

	@Override
	public Manager loginManager(Manager manager) {
		Criteria c=HButil.getSession().createCriteria(Manager.class);
		c.add(Expression.eq("mananame", manager.getMananame()));
		c.add(Expression.eq("manapassword", manager.getManapassword()));
		return (Manager) c.uniqueResult();
	}

}
