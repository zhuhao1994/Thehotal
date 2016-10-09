package com.hotel.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.hotel.dao.GoodsDAO;
import com.hotel.dao.GuestroomDAO;
import com.hotel.dao.PageinationDAO;
import com.hotel.pojo.Goods;
import com.hotel.pojo.Guestroom;
import com.hotel.pojo.Pager;


@Component(value="goodsService")
public class GoodsService  {
	
	private GoodsDAO goodsDao;
	private  GuestroomDAO guestroomDao;
	public GuestroomDAO getGuestroomDao() {
		return guestroomDao;
	}
	public void setGuestroomDao(GuestroomDAO guestroomDao) {
		this.guestroomDao = guestroomDao;
	}

	private PageinationDAO pageNext;
	
	public PageinationDAO getPageNext() {
		return pageNext;
	}
	public void setPageNext(PageinationDAO pageNext) {
		this.pageNext = pageNext;
	}

	public GoodsDAO getGoodsDao() {
		return goodsDao;
	}

	public void setGoodsDao(GoodsDAO goodsDao) {
		this.goodsDao = goodsDao;
	}

	public GoodsService(){
		super();
		//goodsDao =new GoodsDAOImpl();
	}
	
	/*---------service的方法----------*/
	
	//遍历所有商品
	public List<Goods> listAllGoods(){
		List<Goods> has =goodsDao.listGoods();
		for(Goods ha:has)
			System.out.println(ha);
		return has;
		
	}
	
	//添加商品
	public boolean addtoGoods(Goods goods){
		return goodsDao.addGoods(goods);
		
	}
	
	//根据id查找商品
	public Goods findGoods(Goods goodsid){
		return goodsDao.findGoods(goodsid);
	}
	
	//查找商品（根据id查询）
	public List<Goods> findGoodsbyid(int goodsid){
		return goodsDao.findGoodsbyid(goodsid);
	}
	
	//查找商品（根据名称查询）
	public List<Goods> findGoodsbyname(String goodsname)
	{
		return goodsDao.findGoodByName(goodsname);
	}
	//根据商品名称查找商品
	public Goods findByGoodNamecid(String goodsname) {
		return goodsDao.findGoodByNamecid(goodsname);
	}
	
/*	//根据商品价格查询
	public List<Goods> findByGoodPrice(String goodsprice){
		return goodsDao.findGoodByPrice(goodsprice);
	}
*/	
	//根据房间号查询房间id
	public Guestroom findRoom(String roomno){
		return guestroomDao.findRoom(roomno);
	}
	
	
	//删除商品
	public  boolean deletGoods(Goods goodsid)
	{
		return goodsDao.deletGoods(goodsid);
	}
	
	//修改商品
	public void updateGoods(Goods goods)
	{	
		System.out.println("修改商品");
		 goodsDao.updateGoods(goods);
	}
	
	//分页:查询===from  Goods===
	public Pager showPageHa(int nowPage)
	{
		Pager page = new Pager();
		page.setPage(nowPage);
		page.setRows(10);
		page.setHql("from  Goods");
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
		 param.put("goodsid",Integer.parseInt(keyContent));
		else if(key == 2)//通过商品名称 
			param.put("goodsname",keyContent);
		Pager newPage = pageNext.pagerff(page, param);
		return newPage;
	}

	
}
