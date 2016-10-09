package com.hotel.dao;

import java.util.List;

import com.hotel.pojo.Customer;
import com.hotel.pojo.Goods;

public interface GoodsDAO {
	/*
	 * 1.添加商品（输入商品所有属性：名称+价格+数量 进行添加（ID自动生成））
	 * 2.查找商品（可以根据商品的ID查找，也可以根据商品名称查找）
	 * 3.遍历商品（可以查询所有商品）
	 * 4.删除商品（先查询该商品是否存在，可以根据商品的ID删除该商品，但是之前产生的消费记录还在）
	 * 5.修改商品（先查询商品是否存在，存在才可以对商品进行修改）
	 */
	
	//上新商品（添加商品）
	public boolean addGoods(Goods goods);
	
	//查找商品（根据商品ID查找）
	public Goods findGoods(Goods goodsid);
	
	//查找商品(根据商品id)
	public List<Goods> findGoodsbyid(int goodsid);
	
	//根据商品名称查找
	public List<Goods> findGoodByName(String goodsname);
	
	//根据商品名称查出商品id
	public Goods findGoodByNamecid(String goodsname);
	
	/*//根据商品价格查找
	public List<Goods> findGoodByPrice(String goodsprice);*/
	
	//遍历商品
	public List<Goods> listGoods();
	
	//删除商品(根据商品id删除)
	public boolean deletGoods(Goods goodsid);
	
	//修改商品
	public void updateGoods(Goods goods);
	
	//public List findByPage(int pageNo,int pageSize);
}
