package sesoc.global.library.vo;

public class lend {
	private int num;
	private String usernum;
	private int booknum;
	private String startdate;
	private String enddate;
	private String returndate;
	private String status;
	
	public lend() {
		// TODO Auto-generated constructor stub
	}

	public lend(int num, String nusernum, int booknum, String startdate, String enddate, String returndate,
			String status) {
		super();
		this.num = num;
		this.usernum = nusernum;
		this.booknum = booknum;
		this.startdate = startdate;
		this.enddate = enddate;
		this.returndate = returndate;
		this.status = status;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getUsernum() {
		return usernum;
	}

	public void setUsernum(String nusernum) {
		this.usernum = nusernum;
	}

	public int getBooknum() {
		return booknum;
	}

	public void setBooknum(int booknum) {
		this.booknum = booknum;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getReturndate() {
		return returndate;
	}

	public void setReturndate(String returndate) {
		this.returndate = returndate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "lend [num=" + num + ", nusernum=" + usernum + ", booknum=" + booknum + ", startdate=" + startdate
				+ ", enddate=" + enddate + ", returndate=" + returndate + ", status=" + status + "]";
	}
	
	
}
