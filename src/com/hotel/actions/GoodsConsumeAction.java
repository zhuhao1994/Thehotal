package com.hotel.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Component;

import com.hotel.pojo.Goods;
import com.hotel.pojo.Goodsconsume;
import com.hotel.pojo.Guestroom;
import com.hotel.pojo.Hotelaccount;
import com.hotel.pojo.Pager;
import com.hotel.services.GoodsConsumeService;
import com.hotel.services.GoodsService;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

@Component(value = "goodsConsumeAction")
@ParentPackage(value = "struts-default")
@Namespace(value = "/")
@Action(value = "GoodsConsumeAction")
@Results({ 
		@Result(name = "testGoodsConsumess", location = "/page/goodsConsume/testGoodsConsumess.jsp"),
		@Result(name = "listGoodsConsumess", location = "/page/goods/goodsconsume.jsp"),
		@Result(name = "showPageGoodsConsume", location = "/page/goods/goodsconsume.jsp"),
		@Result(name = "addGoodsConsume", location = "/page/goods/addgoodsconsume.jsp"),
		@Result(name = "findGoodsConsume", location = "/page/goods/goodsconsume.jsp"),
		@Result(name = "index", location = "/index.jsp"), })
public class GoodsConsumeAction extends ActionSupport  {
	
	private GoodsConsumeService GoodsConsumeService;
	private GoodsService goodsService;
	private String gds;
	
	public String getGds() {
		return gds;
	}
	public void setGds(String gds) {
		this.gds = gds;
	}
	public GoodsService getGoodsService() {
		return goodsService;
	}
	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}

	private Goodsconsume goodsconxume;
	private Guestroom guestroom;
	public Guestroom getGuestroom() {
		return guestroom;
	}
	public void setGuestroom(Guestroom guestroom) {
		this.guestroom = guestroom;
	}

	private Goods goods;
	
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public Goodsconsume getGoodsconxume() {
		return goodsconxume;
	}
	public void setGoodsconxume(Goodsconsume goodsconxume) {
		this.goodsconxume = goodsconxume;
	}
	//遍历所有的消费记录
	public String showGoodsConsume() {
		List<Goodsconsume> showGoodsConsume = GoodsConsumeService.listGoodsconsume();
		if (showGoodsConsume != null) {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("showGoodsConsume", showGoodsConsume);
			return "listGoodsConsumess";
		}else{
			return "index";
		}
	}
	
	
	
	public GoodsConsumeService getGoodsConsumeService() {
		return GoodsConsumeService;
	}

	public void setGoodsConsumeService(GoodsConsumeService goodsConsumeService) {
		GoodsConsumeService = goodsConsumeService;
	}

	private static HttpServletRequest request;
	
	public String showpageGoodsConsume()
	{
		request = ServletActionContext.getRequest();
		int nowPage = Integer.parseInt(request.getParameter("nowpage"));
		Pager pager= GoodsConsumeService.showPagegoddsconsume(nowPage);
		
		//获取查询的参数
//		String theKey = request.getParameter("theKey").trim().toString();
//		String keyContent = request.getParameter("keyContent").trim().toString();
//		if(!theKey.equals("no"))
//		{	System.out.println("执行了");
//			pager = findHAbyCusName(theKey,keyContent,pager);
//		}
		List<Goodsconsume> has = (List<Goodsconsume>)pager.getList();
		if (has.size() > 0) {
			//根据不同的内容做查询
			request.setAttribute("allGoodsconsume", has);
			request.setAttribute("haPage", pager);
			return "showPageGoodsConsume";
		} else
			return "index";
	}
	
	
	//添加消费记录
	public String addgoodsume(){
		/**
		 * 1.得到在jsp页面输入的商品名称
		 * 2.再根据商品名称查询到商品id
		 * 3.把该商品添加到消费记录里面
		 */
		request = ServletActionContext.getRequest();
		System.out.println("进来action了！");
		String room=request.getParameter("goodsconxume.guestroom.roomno");//取出页面房间号
		Guestroom guest=new Guestroom();
		guest=goodsService.findRoom(room);//根据房间号查出房间id
		//根据页面的名称查询到商品。
		String name=request.getParameter("goodsconxume.goods.goodsname");//取出页面的商品名称
		System.out.println(name);
		Goods good=new Goods();
		good=goodsService.findByGoodNamecid(name);//根据商品名称查询商品
		System.out.println(good);
		System.out.println(goodsService.findByGoodNamecid(name).getGoodsid());
		goodsconxume.setGuestroom(guest);
		goodsconxume.setGoods(good);
		boolean gd=GoodsConsumeService.addGoodsconsume(goodsconxume);
		System.out.println(gd);
		if(gd){
			System.out.println("进来了！");
			return "addGoodsConsume";
		}
		else
			return "addGoodsConsume";
	}
	
	//查询消费记录页面的下拉菜单
	/*public String findbygoodsconsume(){
		request = ServletActionContext.getRequest();
		List<Goodsconsume> goods=new ArrayList<Goodsconsume>();
		String term = request.getParameter("ggd");
		if(term.equals("goodsname")){
			String name=request.getParameter("keygoodsc");
			goods=GoodsConsumeService.findGoodsconsumeByname(name);
			//按id查询
		}
		else if(term.equals("roomno")){
			String name=request.getParameter("keygoodsc").trim();
			goods=GoodsConsumeService.findGoodsconsumeByroomno(name);
		} 
		if(goods!=null){
			request.setAttribute("allGoodsconsume", goods);
			return "showPageGoodsConsume";
		}
		else
			return "index";
	}*/
	
	
	
	public String findbygoodsconsume() {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Goodsconsume> goods = new ArrayList<Goodsconsume>();
		try {
			String term = request.getParameter("ggd");// 查询的方法
			// 将查询的方法
			String keygoodsc = request.getParameter("keygoodsc").trim();// 查询的参数
			request.getSession().setAttribute("term", term);// 将查询的方法存起来
			if(keygoodsc=="")//如果为空查询所有内容
			{
				System.out.println("查询内容为空！");
				showpageGoodsConsume();
				return "showPageGoodsConsume";
			}
			request.getSession().setAttribute("term", term);// 将查询的方法存起来
			keygoodsc = URLDecoder.decode(keygoodsc, "utf-8");
			request.setAttribute("keygoodsc", keygoodsc);// 将查询的参数存起来

			int page = Integer.parseInt(request.getParameter("nowpage"));
			System.out.println("获取到的page " + page);
			Pager pager=new Pager();

			if (term.equals("goodsname")) {
				String hql = "from  Goodsconsume where goods.goodsname=:goodsname";
				pager = GoodsConsumeService.findPager(1, hql, keygoodsc,page);

			} else if (term.equals("roomno")) {
				String hql = "from  Goodsconsume where guestroom.roomno=:roomno";
				pager = GoodsConsumeService.findPager(2, hql, keygoodsc,page);
			}
			// 设置分页
			goods = (List<Goodsconsume>) pager.getList();
			if (goods != null) {
				//将结果分页后显示
				request.setAttribute("haPage", pager);
				request.setAttribute("allGoodsconsume", goods);
				return "showPageGoodsConsume";
			} else {
				return "index";
			}

		} catch (Exception e) {

		}
		if (goods != null) {
			request.setAttribute("allGoodsconsume", goods);
			return "showPageGoodsConsume";
		} else {
			return "index";
		}
	}
	
	/*public Pager Mypage(Pager pager)
	{
		
		List<Object> lists = (List<Object>) pager.getList();
		List<Object> Mylists = new ArrayList<>();
		int thefirst = (pager.getPage() - 1) * pager.getRows();
		int theEnd = pager.getRows()*pager.getPage();
		if(pager.getPage() == pager.getPageTotal())
			theEnd = pager.getRowsTotal();
		System.out.println("thefirst :" +thefirst+ "  theEnd： "+theEnd);
		for(int i = thefirst;i < theEnd; i++)
		{
			Mylists.add(lists.get(i));
		}
		pager.setList(Mylists);
		return pager;
	}
	*/
	//ajax
	public void findbyid(){
		System.out.println(gds);
		try {
			HttpServletResponse response=ServletActionContext.getResponse();
			request = ServletActionContext.getRequest();
			response.setCharacterEncoding("utf-8");
			//根据商品名字查询商品是否存在
			Goods goods=goodsService.findByGoodNamecid(gds);
			PrintWriter out=response.getWriter();
			if(goods==null){
				out.print("商品不存在!");	
			}else{
				out.print("");	
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void findbyroom(){
		System.out.println(gds);
		try {
			HttpServletResponse response=ServletActionContext.getResponse();
			request = ServletActionContext.getRequest();
			response.setCharacterEncoding("utf-8");
			Guestroom room=goodsService.findRoom(gds);
			PrintWriter out=response.getWriter();
			if(room==null){
				out.print("房间不存在哦!");	
			}else{
				out.print("");	
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

}
