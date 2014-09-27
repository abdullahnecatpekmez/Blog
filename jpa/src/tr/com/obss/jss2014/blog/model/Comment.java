package tr.com.obss.jss2014.blog.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import tr.com.obss.jss2014.blog.util.MD5Util;

@Entity
public class Comment extends BaseModel {

	private String title;
	@Lob
	@Column(nullable=false)
	private String comment;
	@Column(nullable=false)
	private String email;
	@ManyToOne
	private Comment parent;
	@OneToMany(mappedBy = "parent")
	private Set<Comment> children;
	@ManyToOne
	private BlogPost blogPost;

	public void addChild(Comment comment) {
		children.add(comment);
		comment.setParent(this);
	}

	public BlogPost getBlogPost() {
		return blogPost;
	}

	public void setBlogPost(BlogPost blogPost) {
		this.blogPost = blogPost;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Comment getParent() {
		return parent;
	}

	public void setParent(Comment parent) {
		this.parent = parent;
	}

	public Set<Comment> getChildren() {
		return children;
	}

	public void setChildren(Set<Comment> children) {
		this.children = children;
	}
	
	public String getAvatar(){
		String formattedEmail = getEmail().trim().toLowerCase();
		String hash = MD5Util.md5Hex(formattedEmail);
		return "http://gravatar.com/avatar/"+hash;
	}

}
