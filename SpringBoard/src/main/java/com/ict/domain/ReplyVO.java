package com.ict.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class ReplyVO {
	
	private Long rno;
	private Long bno;
	private String reply;
	private String replyer;
	private Date replyDate;
	private Date updateDate;
	private Long rownum;
	
	public Long getRownum() {
		return rownum;
	}
	public void setRownum(Long rownum) {
		this.rownum = rownum;
	}
	public Long getRno() {
		return rno;
	}
	public void setRno(Long rno) {
		this.rno = rno;
	}
	public Long getBno() {
		return bno;
	}
	public void setBno(Long bno) {
		this.bno = bno;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public String getReplyer() {
		return replyer;
	}
	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}
	public Date getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "ReplyVO [rno=" + rno + ", bno=" + bno + ", reply=" + reply + ", replyer=" + replyer + ", replyDate="
				+ replyDate + ", updateDate=" + updateDate + ", rownum=" + rownum + "]";
	}
	public ReplyVO(Long rno, Long bno, String reply, String replyer, Date replyDate, Date updateDate, Long rownum) {
		super();
		this.rno = rno;
		this.bno = bno;
		this.reply = reply;
		this.replyer = replyer;
		this.replyDate = replyDate;
		this.updateDate = updateDate;
		this.rownum = rownum;
	}
	public ReplyVO() {
		
	}
	
}
