package com.hotel.daoImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;
import org.springframework.stereotype.Component;

import com.hotel.HButil.HButil;
import com.hotel.dao.GoodsDAO;
import com.hotel.pojo.Goods;

@Component(value="goodsDao") 
public class GoodsDAOImpl implements GoodsDAO {
	private Session sess=null;
	
	//添加商品
	public boolean addGoods(Goods goods) {
		try {
			sess = HButil.getSession();
			Transaction ts = sess.beginTransaction();//开启事物
			sess.save(goods);//添加数据并保存到数据库中
			//rs = (Integer) rs1 ;
			ts.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("addGoods is error!");
		}finally {
			HButil.closeSession(sess);
		}
		return false;
	}

//遍历商品（所有）
	public List<Goods> listGoods() {
		List<Goods> good = new ArrayList<Goods>();
		try {
			sess = HButil.getSession();
			Query query = sess.createQuery("from Goods");//创建sql语句执行
			good=query.list();//调用list方法遍历
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("listGoods is error!");
		}finally {
			HButil.closeSession(sess);
		}
		return good;

	}
	//分页查询
	/*public List findByPage(int pageNo,int pageSize){
		sess = HButil.getSession();
		Query query = sess.createQuery("from Goods");
		query.setFirstResult((pageNo-1)*pageSize);
		//设置从哪一行记录开始读取
		query.setMaxResults(pageSize);
		//设置一次读取多少条记录 pageNo表示第几页，pagesize表示每页显示多少条记录
		return query.list();
	}*/
	

//修改商品
public void updateGoods(Goods goods) {
	try {
		//先查找商品
		Goods gd=this.findGoods(goods);
		Integer id=gd.getGoodsid();
		sess =HButil.getSession();
		Transaction ts = sess.beginTransaction();
		System.out.println(goods);
		if(gd != null){
			gd=goods;
			//找到商品进行修改
			gd.setGoodsid(id);
			sess.update(gd);
			ts.commit();
			}
		else
			System.out.println("数据不存在！");
		
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("数据跟新失败！");
		System.out.println("updateGoods is error!");
	}finally{
		HButil.closeSession(sess);
	}
}
//查询商品（根据ID查询）
	public List<Goods> findGoodsbyid(int goodsid) {
			sess = HButil.getSession();
			Criteria c=sess.createCriteria(Goods.class);
			c.add(Expression.eq("goodsid", goodsid));
			return c.list();
	}
	
	//查询商品（根据名称查询）
	public List<Goods> findGoodByName(String goodsname) {
		sess = HButil.getSession();
		Criteria c=sess.createCriteria(Goods.class);
		c.add(Expression.eq("goodsname", goodsname));
		return c.list();
	}

//查询商品（根据价格查询）多个商品
/*@Override
public List<Goods> findGoodByPrice(String goodsprice) {
	sess = HButil.getSession();
	Criteria c=sess.createCriteria(Goods.class);
	c.add(Expression.eq("goodsname", goodsprice));
	return c.list();
	
	
}*/


//删除商品
@Override
public boolean deletGoods(Goods goodsid) {
		try {
			sess = HButil.getSession();
			Goods thegood = this.findGoods(goodsid);
			Transaction ts = sess.beginTransaction();
			sess.delete(thegood);
			ts.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("deletGoods is error!");
		}finally {
			HButil.closeSession(sess);
		}
		return false;
}

//根据商品名称查询出商品
@Override
public Goods findGoodByNamecid(String goodsname) {
	Goods pic = new Goods();
	try {
		sess = HButil.getSession();
		Transaction ts = sess.beginTransaction();
		String hql = "from Goods where goodsname =?";
		Query query = (Query) sess.createQuery(hql);
	    query.setString(0, goodsname);
	    pic = (Goods) query.uniqueResult();
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("findGoods is error!");
	}finally {
		HButil.closeSession(sess);
	}
	return pic;
}

//根据商品id查找
@Override
public Goods findGoods(Goods goodsid) {
	Goods pic = null;
	try {
		sess = HButil.getSession();
		Transaction ts = sess.beginTransaction();
		String hql = "from Goods where goodsid =?";
		Query query = (Query) sess.createQuery(hql);
	    query.setInteger(0,goodsid.getGoodsid());
	    pic = (Goods) query.uniqueResult();
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("findGoods is error!");
	}finally {
		HButil.closeSession(sess);
	}
	return pic;
}





}