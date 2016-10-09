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
	//ajax�ķ���
	public void findbyid(){
		response=ServletActionContext.getResponse();
		request=ServletActionContext.getRequest();
		try {
			response.setCharacterEncoding("utf-8");
			request.getParameter("gds");//ǰ̨������������
			Goods goods=goodsService.findByGoodNamecid(gds);
			PrintWriter out=response.getWriter();
			if(goods!=null){
				out.print("��Ʒ�Ѵ���");
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
	
	//��ѯ������Ʒ
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
	
	//��ҳ��ѯ---������Ʒ
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
			//���ݲ�ͬ����������ѯ
			request.setAttribute("allGoods", has);
			request.setAttribute("haPage", pager);
			return "showPageGoods";
		} else
			return "index";
	}
	
	//�����Ʒ
	public String addGood(){
			boolean gd=goodsService.addtoGoods(goods);
			//request = ServletActionContext.getRequest();
			if(gd){
				//request.setAttribute("gd", gd);
				System.out.println("�����ˣ�");
				return "addGoodsss";
			}
			else
				return "index";
	}

	//������Ʒ(�޸���Ҫ�Ȳ�ѯ��Ʒ)
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
	
	//�޸���Ʒ
	public String updateGoods(){
		try {
			request = ServletActionContext.getRequest();
			goodsService.updateGoods(goods);
			request.setAttribute("up", goods);
			System.out.println("�޸ĳɹ���");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return "showPageGoods";
		return "updatesc";
		
	}
	
	//��ѯ��Ʒ
	/*public String findbygoods(){
		request = ServletActionContext.getRequest();
		List<Goods> good=new ArrayList<Goods>();
		String t=request.getParameter("ggd");
		
		if(t.equals("goodsid")){
			String trim=request.getParameter("keygoods");
			good=goodsService.findGoodsbyid(Integer.parseInt(trim));
			//��id��ѯ
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
			String term = request.getParameter("ggd");// ��ѯ�ķ���
			// ����ѯ�ķ���
			String keygoods = request.getParameter("keygoods").trim();// ��ѯ�Ĳ���
			request.getSession().setAttribute("term", term);// ����ѯ�ķ���������
			if(keygoods=="")//���Ϊ�ղ�ѯ��������
			{
				System.out.println("��ѯ����Ϊ�գ�");
				showpageGoods();
				return "showPageGoods";
			}
			request.getSession().setAttribute("term", term);// ����ѯ�ķ���������
			keygoods = URLDecoder.decode(keygoods, "utf-8");
			request.setAttribute("keygoods", keygoods);// ����ѯ�Ĳ���������
			int page = Integer.parseInt(request.getParameter("nowpage"));
			System.out.println("��ȡ����page " + page);
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
				//�������ҳ����ʾ
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
	//===========ajax:���������Ʒ���Ѽ�¼===
	public void addgdconsumes(){
		response=ServletActionContext.getResponse();
		request=ServletActionContext.getRequest();
		Map mygoodac = new HashMap<String,String>();
		PrintWriter pw =null;
		try {
			response.setContentType("text/json;charset=utf-8");
			  pw = response.getWriter();
			String goods = request.getParameter("goodss");
			/*json����*/
			JSONArray json = JSONArray.fromObject(goods); // ���Ȱ��ַ���ת�� JSONArray  ����
			//��ȡ�����ѷ����roomid
			int theroomid =Integer.parseInt(request.getSession().getAttribute("myHaRoomId").toString()) ;
			Guestroom theroom =  new Guestroom();
			theroom.setRoomid(theroomid);
			Goodsconsume gds = new Goodsconsume();
			gds.setGuestroom(theroom);
			if(json.size()>0){
			   for(int i=0;i<json.size();i++){
			     JSONObject job = json.getJSONObject(i);  // ���� jsonarray ���飬��ÿһ������ת�� json ����
			     System.out.println("��Ʒid  :"+job.get("goodsid")) ;  // �õ� ÿ�������е�����ֵ
			     System.out.println("��Ʒ����  :"+job.get("goodsnum")) ;  // �õ� ÿ�������е�����ֵ
			     //====���������Ʒ����===
			     int thegoodsid = Integer.parseInt(job.get("goodsid").toString());
			     int thegoodsnum = Integer.parseInt(job.get("goodsnum").toString());
			     Goods thegoods = new Goods();
			     thegoods.setGoodsid(thegoodsid);
			     gds.setGoods(thegoods);
			     gds.setGoodsnum(thegoodsnum);
			     boolean gd = GoodsConsumeService.addGoodsconsume(gds);
			     System.out.println("���� ��" + gd);
			   }
			}
			/*******/
			String sumPrice = request.getParameter("sumPrice");
			pw.write("success");
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ajax:���������Ʒ���Ѽ�¼ is errer!");
		}
		
	}

	
}
