package kr.or.ddit.dto;

import java.util.Date;

public class ProductVO {
	private int pno;
	private String pname;
	private int price;
	private String writer;
	private String content;
	private String picture;
	private Date regdate;
	private Date updatedate;
	private int viewcnt;
	private String soldout;
	private String isdel;
	
	
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	public int getViewcnt() {
		return viewcnt;
	}
	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}
	public String getSoldout() {
		return soldout;
	}
	public void setSoldout(String soldout) {
		this.soldout = soldout;
	}
	public String getIsdel() {
		return isdel;
	}
	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}
	@Override
	public String toString() {
		return "ProductVO [content=" + content + ", isdel=" + isdel + ", picture=" + picture + ", pname=" + pname
				+ ", pno=" + pno + ", price=" + price + ", regdate=" + regdate + ", soldout=" + soldout
				+ ", updatedate=" + updatedate + ", viewcnt=" + viewcnt + ", writer=" + writer + "]";
	}
	
	
	
}
