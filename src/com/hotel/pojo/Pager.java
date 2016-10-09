package com.hotel.pojo;

import java.util.List;

public class Pager {

	private int page;// ��ǰҳ��
	private int pageTotal;// ��ҳ��
	private int rowsTotal;// ������
	private int rows;// ÿҳ��ʾ����
	private String hql;// ��ҳ���
	private List<?> list;// ���ص����ݼ���

	public Pager() {
	     super();
	 }

	public String getHql() {
		return hql;
	}

	public void setHql(String hql) {
		this.hql = hql;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}

	public int getRowsTotal() {
		return rowsTotal;
	}

	public void setRowsTotal(int rowsTotal) {
		this.rowsTotal = rowsTotal;
		pageTotal = rowsTotal % rows == 0 ? rowsTotal / rows : rowsTotal / rows + 1;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "Pager [list=" + list + ", page=" + page + ", pageTotal=" + pageTotal + ", rows=" + rows + ", rowsTotal="
				+ rowsTotal + "]";
	}

}
