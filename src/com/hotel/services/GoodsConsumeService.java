package com.hotel.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.hotel.dao.GoodsConsumeDAO;
import com.hotel.dao.GoodsDAO;
import com.hotel.dao.PageinationDAO;
import com.hotel.daoImpl.GoodsConsumeDAOImpl;
import com.hotel.pojo.Goodsconsume;
import com.hotel.pojo.Pager;


@Component(value="goodsConsumeService")
public class GoodsConsumeService {
	private GoodsConsumeDAO goodsConsumeDao;
	
	public GoodsConsumeDAO getGoodsConsumeDao() {
		return goodsConsumeDao;
	}
	public void setGoodsConsumeDao(GoodsConsumeDAO goodsConsumeDao) {
		this.goodsConsumeDao = goodsConsumeDao;
	}

	private GoodsDAO goodsDao;
	
	public GoodsDAO getGoodsDao() {
		return goodsDao;
	}
	public void setGoodsDao(GoodsDAO goodsDao) {
		this.goodsDao = goodsDao;
	}

	private PageinationDAO pageNext;
	
	public PageinationDAO getPageNext() {
		return pageNext;
	}
	public void setPageNext(PageinationDAO pageNext) {
		System.out.println("set pageNext");
		this.pageNext = pageNext;
	}
	
	public GoodsConsumeService(){
		super();
		//goodsConsumeDao=new GoodsConsumeDAOImpl();
	}
	
	
	//查看消费记录
	public List<Goodsconsume> listGoodsconsume(){
		return goodsConsumeDao.listGoodsconsume();
	}
	
	//添加消费记录
	public boolean addGoodsconsume(Goodsconsume gsc)
	{	
		System.out.println("进入GoodsconsumeService了！");
		gsc.setConsumetime(new Date());
		return goodsConsumeDao.addGoodsconsume(gsc);
		
	}
	//查询消费记录
	public List<Goodsconsume> findGoodsconsumeByname(String goodsname){
		return goodsConsumeDao.findGoodsconsumeByname(goodsname);
	}
	public List<Goodsconsume> findGoodsconsumeByroomno(String roomno){
		return goodsConsumeDao.findGoodsconsumeByroomno(roomno);
	}
	
	//分页:查询===from  Hotelaccount
	public Pager showPagegoddsconsume(int nowPage)
	{
		Pager page = new Pager();
		page.setPage(nowPage);
		page.setRows(10);
		page.setHql("from  Goodsconsume");
		Pager newPage = pageNext.pagerff(page, null);
		return newPage;
	}
	
	public Pager findPager(int key ,String hql,String keyContent,int tpage)
	{
		Pager page = new Pager();
		page.setPage(tpage);
		page.setRows(10);
		page.setHql(hql);
		Map<String, Object> param= new HashMap<String, Object>();
		if(key == 1)//通过商品编号
		 param.put("goodsname",keyContent);
		else if(key == 2)//通过商品名称 
			param.put("roomno",Integer.parseInt(keyContent));
		Pager newPage = pageNext.pagerff(page, param);
		return newPage;
	}
}
