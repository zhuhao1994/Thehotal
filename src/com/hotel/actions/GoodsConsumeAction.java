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
	//�������е����Ѽ�¼
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
		
		//��ȡ��ѯ�Ĳ���
//		String theKey = request.getParameter("theKey").trim().toString();
//		String keyContent = request.getParameter("keyContent").trim().toString();
//		if(!theKey.equals("no"))
//		{	System.out.println("ִ����");
//			pager = findHAbyCusName(theKey,keyContent,pager);
//		}
		List<Goodsconsume> has = (List<Goodsconsume>)pager.getList();
		if (has.size() > 0) {
			//���ݲ�ͬ����������ѯ
			request.setAttribute("allGoodsconsume", has);
			request.setAttribute("haPage", pager);
			return "showPageGoodsConsume";
		} else
			return "index";
	}
	
	
	//������Ѽ�¼
	public String addgoodsume(){
		/**
		 * 1.�õ���jspҳ���������Ʒ����
		 * 2.�ٸ�����Ʒ���Ʋ�ѯ����Ʒid
		 * 3.�Ѹ���Ʒ��ӵ����Ѽ�¼����
		 */
		request = ServletActionContext.getRequest();
		System.out.println("����action�ˣ�");
		String room=request.getParameter("goodsconxume.guestroom.roomno");//ȡ��ҳ�淿���
		Guestroom guest=new Guestroom();
		guest=goodsService.findRoom(room);//���ݷ���Ų������id
		//����ҳ������Ʋ�ѯ����Ʒ��
		String name=request.getParameter("goodsconxume.goods.goodsname");//ȡ��ҳ�����Ʒ����
		System.out.println(name);
		Goods good=new Goods();
		good=goodsService.findByGoodNamecid(name);//������Ʒ���Ʋ�ѯ��Ʒ
		System.out.println(good);
		System.out.println(goodsService.findByGoodNamecid(name).getGoodsid());
		goodsconxume.setGuestroom(guest);
		goodsconxume.setGoods(good);
		boolean gd=GoodsConsumeService.addGoodsconsume(goodsconxume);
		System.out.println(gd);
		if(gd){
			System.out.println("�����ˣ�");
			return "addGoodsConsume";
		}
		else
			return "addGoodsConsume";
	}
	
	//��ѯ���Ѽ�¼ҳ��������˵�
	/*public String findbygoodsconsume(){
		request = ServletActionContext.getRequest();
		List<Goodsconsume> goods=new ArrayList<Goodsconsume>();
		String term = request.getParameter("ggd");
		if(term.equals("goodsname")){
			String name=request.getParameter("keygoodsc");
			goods=GoodsConsumeService.findGoodsconsumeByname(name);
			//��id��ѯ
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
			String term = request.getParameter("ggd");// ��ѯ�ķ���
			// ����ѯ�ķ���
			String keygoodsc = request.getParameter("keygoodsc").trim();// ��ѯ�Ĳ���
			request.getSession().setAttribute("term", term);// ����ѯ�ķ���������
			if(keygoodsc=="")//���Ϊ�ղ�ѯ��������
			{
				System.out.println("��ѯ����Ϊ�գ�");
				showpageGoodsConsume();
				return "showPageGoodsConsume";
			}
			request.getSession().setAttribute("term", term);// ����ѯ�ķ���������
			keygoodsc = URLDecoder.decode(keygoodsc, "utf-8");
			request.setAttribute("keygoodsc", keygoodsc);// ����ѯ�Ĳ���������

			int page = Integer.parseInt(request.getParameter("nowpage"));
			System.out.println("��ȡ����page " + page);
			Pager pager=new Pager();

			if (term.equals("goodsname")) {
				String hql = "from  Goodsconsume where goods.goodsname=:goodsname";
				pager = GoodsConsumeService.findPager(1, hql, keygoodsc,page);

			} else if (term.equals("roomno")) {
				String hql = "from  Goodsconsume where guestroom.roomno=:roomno";
				pager = GoodsConsumeService.findPager(2, hql, keygoodsc,page);
			}
			// ���÷�ҳ
			goods = (List<Goodsconsume>) pager.getList();
			if (goods != null) {
				//�������ҳ����ʾ
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
		System.out.println("thefirst :" +thefirst+ "  theEnd�� "+theEnd);
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
			//������Ʒ���ֲ�ѯ��Ʒ�Ƿ����
			Goods goods=goodsService.findByGoodNamecid(gds);
			PrintWriter out=response.getWriter();
			if(goods==null){
				out.print("��Ʒ������!");	
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
				out.print("���䲻����Ŷ!");	
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
