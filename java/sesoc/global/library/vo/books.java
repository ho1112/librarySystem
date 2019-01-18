package sesoc.global.library.vo;

public class books {

	private int num;
	private String title;
	private String publisher;
	private String author;
	private String content;
	private String imageurl;
	
	public books() {
	}

	public books(int num, String title, String publisher, String author, String content, String imageurl) {
		super();
		this.num = num;
		this.title = title;
		this.publisher = publisher;
		this.author = author;
		this.content = content;
		this.imageurl = imageurl;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	@Override
	public String toString() {
		return "books [num=" + num + ", title=" + title + ", publisher=" + publisher + ", author=" + author
				+ ", content=" + content + ", imageurl=" + imageurl + "]";
	}
	
}
