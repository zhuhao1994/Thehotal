package com.hotel.dao;

import java.util.List;

import com.hotel.pojo.Goods;
import com.hotel.pojo.Goodsconsume;
/**
 * 1.添加消费记录（消费记录里面有  房间ID+商品ID+商品数量）
 * 2.查看消费记录(所有的消费记录)
 * 3.查找商品消费记录
 * -----根据商品名称查询
 * -----根据商房间号查询
 * @param gsc
 * @return
 */
public interface GoodsConsumeDAO {
	
	public boolean addGoodsconsume(Goodsconsume gsc);//添加消费记录
	public List<Goodsconsume> listGoodsconsume();//查看消费记录
	//查找商品消费记录(根据商品名称)
	public List<Goodsconsume> findGoodsconsumeByname(String goodsname);
			
	//根据房间号查找
	public List<Goodsconsume> findGoodsconsumeByroomno(String roomno);
		

	
}
