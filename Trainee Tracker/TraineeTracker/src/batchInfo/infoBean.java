package batchInfo;

public class infoBean {

	int sid;
	String rsname;
	String rsphone;
	String rcollege;
	String rcourse;
	String rsem;
	
	public infoBean(int sid, String rsname, String rsphone, String rcollege, String rcourse, String rsem )
	{
		super();
		this.sid = sid;
		this.rsname=rsname;
		this.rsphone=rsphone;
		this.rcollege = rcollege;
		this.rcourse= rcourse;
		this.rsem=rsem;
		
		
	}
	
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	
	public String getName() {
		return rsname;
	}
	public void setName(String rsname) {
		this.rsname = rsname;
	}
	
	public String getPhone() {
		return rsphone;
	}
	public void setPhone(String rsphone) {
		this.rsphone = rsphone;
	}
	
	public String getCollege() {
		return rcollege;
	}
	public void setCollege(String rcollege) {
		this.rcollege = rcollege;
	}
	
	public String getCourse() {
		return rcourse;
	}
	public void setCourse(String rcourse) {
		this.rcourse = rcourse;
	}
	
	
	public String getSem() {
		return rsem;
	}
	public void setSem(String rsem) {
		this.rsem = rsem;
	}
	
	
	
}
