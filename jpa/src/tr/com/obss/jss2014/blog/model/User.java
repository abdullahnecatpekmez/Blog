package tr.com.obss.jss2014.blog.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_USER")
public class User extends BaseModel {

	@Column(unique=true, nullable=false)
	private String username;
	@Column(nullable=false)
	private String password;
	@OneToOne(cascade = { CascadeType.PERSIST })
	private UserDetail detail;
	@OneToMany(mappedBy = "author")
	private Set<BlogPost> blogPosts;

	public void addBlogPost(BlogPost blogPost) {
		blogPosts.add(blogPost);
		blogPost.setAuthor(this);
	}

	public Set<BlogPost> getBlogPosts() {
		return blogPosts;
	}

	public void setBlogPosts(Set<BlogPost> blogPosts) {
		this.blogPosts = blogPosts;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserDetail getDetail() {
		return detail;
	}

	public void setDetail(UserDetail detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [username=").append(username)
				.append(", password=").append(password).append(", detail=")
				.append(detail).append(", blogPosts=").append(blogPosts)
				.append(", id=").append(id).append(", version=")
				.append(version).append(", createDate=").append(createDate)
				.append(", updateDate=").append(updateDate).append("]");
		return builder.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (username == null) {
			if (other.username != null) {
				return false;
			}
		} else if (!username.equals(other.username)) {
			return false;
		}
		return true;
	}

}
