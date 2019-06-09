package feeInfo;

public class feeBean {

	int sid;
	String rsname;
	String rdate;
	String rtime;
	int rbalance;
	
	public feeBean(int sid,String rsname,String rdate,String rtime, int rbalance)
	{
		super();
		this.sid =sid;
		this.rsname =rsname;
		this.rdate=rdate;
		this.rtime=rtime;
		this.rbalance=rbalance;
		
	}
	
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	
	public String getRsname() {
		return rsname;
	}
   public void setRsname(String rsname) {  
	this.rsname = rsname;
    }
   
   public String getRdate() {
	return rdate;
   }
   public void setRdate(String rdate) {
	this.rdate = rdate;
   }
   
   public String getRtime() {
	return rtime;
   }
   public void setRtime(String rtime) {
	this.rtime = rtime;
   }
   
   public int getRbalance() {
	return rbalance;
   }
   public void setRbalance(int rbalance) {
	this.rbalance = rbalance;
   }
   
	
	
}
