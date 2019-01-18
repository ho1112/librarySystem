package sesoc.global.library.vo;

public class users {
	
	private String num;
	private String userid;
	private String password;
	private String birthdate;
	private String username;
	private String tel1;
	private String tel2;
	private String tel3;
	private String image;

	public users() {
		// TODO Auto-generated constructor stub
	}

	public users(String num, String userid, String password, String birthdate, String username, String tel1,
			String tel2, String tel3, String image) {
		super();
		this.num = num;
		this.userid = userid;
		this.password = password;
		this.birthdate = birthdate;
		this.username = username;
		this.tel1 = tel1;
		this.tel2 = tel2;
		this.tel3 = tel3;
		this.image = image;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTel1() {
		return tel1;
	}

	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}

	public String getTel2() {
		return tel2;
	}

	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}

	public String getTel3() {
		return tel3;
	}

	public void setTel3(String tel3) {
		this.tel3 = tel3;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "users [num=" + num + ", userid=" + userid + ", password=" + password + ", birthdate=" + birthdate
				+ ", username=" + username + ", tel1=" + tel1 + ", tel2=" + tel2 + ", tel3=" + tel3 + ", image=" + image
				+ "]";
	}
	
}
