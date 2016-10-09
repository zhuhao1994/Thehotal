package com.hotel.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hotel.pojo.Goods;
import com.hotel.pojo.Goodsconsume;
import com.hotel.pojo.Guestroom;
import com.hotel.pojo.Pager;
import com.hotel.services.GoodsConsumeService;
import com.hotel.services.GoodsService;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component(value = "goodsAction")
@ParentPackage(value = "struts-default")
@Namespace(value = "/")
@Action(value = "GoodsAction")
@Results({ 
		@Result(name = "findGoodsss", location = "/page/goods/findgoods.jsp"),
		@Result(name = "addGoodsss", location = "/page/goods/addgoods.jsp"),
		@Result(name = "updategoods", location = "/page/goods/updategoods.jsp"),
		@Result(name = "updatesc", location = "/page/goods/updatesc.jsp"),
		@Result(name = "updategoodssuccess", location = "/page/goods/updatesuccess.jsp"),
		@Result(name = "showPageGoods", location = "/page/goods/findgoods.jsp"),
		@Result(name = "deleteGoodsss", location = "/page/goods/detgoods.jsp"),
	//	@Result(name = "addGoodssserror", location = "/index.jsp"),
		@Result(name = "index", location = "/index.jsp") })
public class GoodsAction extends ActionSupport  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private GoodsService goodsService;
//	private Map<String, Object> session;
	private Goods goods;
	private String gds;
	private GoodsConsumeService GoodsConsumeService;
	
	public GoodsConsumeService getGoodsConsumeService() {
		return GoodsConsumeService;
	}
	public void setGoodsConsumeService(GoodsConsumeService goodsConsumeService) {
		GoodsConsumeService = goodsConsumeService;
	}
	public String getGds() {
		return gds;
	}
	public void setGds(String gds) {
		this.gds = gds;
	}
	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}
	public GoodsService getGoodsService() {
		return goodsService;
	}
	
	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	
	private static HttpServletRequest request;
	private static HttpServletResponse response;
	//ajax的方法
	public void findbyid(){
		response=ServletActionContext.getResponse();
		request=ServletActionContext.getRequest();
		try {
			response.setCharacterEncoding("utf-8");
			request.getParameter("gds");//前台传过来的数据
			Goods goods=goodsService.findByGoodNamecid(gds);
			PrintWriter out=response.getWriter();
			if(goods!=null){
				out.print("商品已存在");
			}
			else{
				out.print("");
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//查询所有商品
	public String showGood() {
		System.out.println("00");
		request = ServletActionContext.getRequest();
		List<Goods> showGoods = goodsService.listAllGoods();
		System.out.println(showGoods);
		if (showGoods.size()>0) {
			request.setAttribute("showallGoods", showGoods);
			return "showPageGoods";
		}else{
			return "index";
		}
	}
	
	//分页查询---所有商品
	public String showpageGoods()
	{
		request = ServletActionContext.getRequest();
		int nowPage = Integer.parseInt(request.getParameter("nowpage"));
		Pager pager= goodsService.showPageHa(nowPage);
		
		try {
			String thepath = request.getParameter("thepath");
				request.getSession().setAttribute("thepath", thepath);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("showpageGoods is null point!");
		}
		List<Goods> has = (List<Goods>)pager.getList();
		if (has.size() > 0) {
			//根据不同的内容做查询
			request.setAttribute("allGoods", has);
			request.setAttribute("haPage", pager);
			return "showPageGoods";
		} else
			return "index";
	}
	
	//添加商品
	public String addGood(){
			boolean gd=goodsService.addtoGoods(goods);
			//request = ServletActionContext.getRequest();
			if(gd){
				//request.setAttribute("gd", gd);
				System.out.println("进来了！");
				return "addGoodsss";
			}
			else
				return "index";
	}

	//查找商品(修改需要先查询商品)
	public String findGoodsbyid(){
		try {
			request = ServletActionContext.getRequest();
			String id=request.getParameter("goodsid");
			Goods goods=new Goods();
			goods.setGoodsid(Integer.parseInt(id));
			Goods good=goodsService.findGoods(goods);
			System.out.println(goods);
			request.setAttribute("updategds", good);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "updategoods";
	}
	
	//修改商品
	public String updateGoods(){
		try {
			request = ServletActionContext.getRequest();
			goodsService.updateGoods(goods);
			request.setAttribute("up", goods);
			System.out.println("修改成功！");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return "showPageGoods";
		return "updatesc";
		
	}
	
	//查询商品
	/*public String findbygoods(){
		request = ServletActionContext.getRequest();
		List<Goods> good=new ArrayList<Goods>();
		String t=request.getParameter("ggd");
		
		if(t.equals("goodsid")){
			String trim=request.getParameter("keygoods");
			good=goodsService.findGoodsbyid(Integer.parseInt(trim));
			//按id查询
		}
		else if(t.equals("goodsname")){
			String name=request.getParameter("keygoods").trim();
			good=goodsService.findGoodsbyname(name);
		}else if(t.equals("goodsprice")){
			String name=request.getParameter("keygoods");
			good=goodsService.findByGoodPrice(name);
		} 
		if(good!=null){
			request.setAttribute("allGoods", good);
			return "findGoodsss";
		}
		else
			return "index";
		
	}
	*/
	
	
	public String findbygoods() {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Goods> goods = new ArrayList<Goods>();
		try {
			String term = request.getParameter("ggd");// 查询的方法
			// 将查询的方法
			String keygoods = request.getParameter("keygoods").trim();// 查询的参数
			request.getSession().setAttribute("term", term);// 将查询的方法存起来
			if(keygoods=="")//如果为空查询所有内容
			{
				System.out.println("查询内容为空！");
				showpageGoods();
				return "showPageGoods";
			}
			request.getSession().setAttribute("term", term);// 将查询的方法存起来
			keygoods = URLDecoder.decode(keygoods, "utf-8");
			request.setAttribute("keygoods", keygoods);// 将查询的参数存起来
			int page = Integer.parseInt(request.getParameter("nowpage"));
			System.out.println("获取到的page " + page);
			Pager pager=new Pager();
			
			if (term.equals("goodsid")) {
				String hql = "from  Goods where goodsid=:goodsid";
				pager = goodsService.findPager(1, hql, keygoods,page);

			} else if (term.equals("goodsname")) {
				String hql = "from  Goods where goodsname=:goodsname";
				pager = goodsService.findPager(2, hql, keygoods,page);

			}
			goods = (List<Goods>) pager.getList();
			if (goods != null) {
				//将结果分页后显示
				request.setAttribute("haPage", pager);
				request.setAttribute("allGoods", goods);
				return "showPageGoods";
			} else {
				return "index";
			}

		} catch (Exception e) {

		}
		if (goods != null) {
			request.setAttribute("allGoods", goods);
			return "showPageGoods";
		} else {
			return "index";
		}
	}
	//===========ajax:批量添加商品消费记录===
	public void addgdconsumes(){
		response=ServletActionContext.getResponse();
		request=ServletActionContext.getRequest();
		Map mygoodac = new HashMap<String,String>();
		PrintWriter pw =null;
		try {
			response.setContentType("text/json;charset=utf-8");
			  pw = response.getWriter();
			String goods = request.getParameter("goodss");
			/*json对象*/
			JSONArray json = JSONArray.fromObject(goods); // 首先把字符串转成 JSONArray  对象
			//获取到消费房间号roomid
			int theroomid =Integer.parseInt(request.getSession().getAttribute("myHaRoomId").toString()) ;
			Guestroom theroom =  new Guestroom();
			theroom.setRoomid(theroomid);
			Goodsconsume gds = new Goodsconsume();
			gds.setGuestroom(theroom);
			if(json.size()>0){
			   for(int i=0;i<json.size();i++){
			     JSONObject job = json.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
			     System.out.println("商品id  :"+job.get("goodsid")) ;  // 得到 每个对象中的属性值
			     System.out.println("商品数量  :"+job.get("goodsnum")) ;  // 得到 每个对象中的属性值
			     //====批量添加商品消费===
			     int thegoodsid = Integer.parseInt(job.get("goodsid").toString());
			     int thegoodsnum = Integer.parseInt(job.get("goodsnum").toString());
			     Goods thegoods = new Goods();
			     thegoods.setGoodsid(thegoodsid);
			     gds.setGoods(thegoods);
			     gds.setGoodsnum(thegoodsnum);
			     boolean gd = GoodsConsumeService.addGoodsconsume(gds);
			     System.out.println("结算 ：" + gd);
			   }
			}
			/*******/
			String sumPrice = request.getParameter("sumPrice");
			pw.write("success");
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ajax:批量添加商品消费记录 is errer!");
		}
		
	}

	
}
