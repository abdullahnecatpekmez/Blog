package tr.com.obss.jss2014.blog.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@NamedQuery(name="BlogPost.getLatest", query="SELECT p FROM BlogPost p ORDER BY p.createDate DESC")
@Entity
public class BlogPost extends BaseModel {

	@Column(nullable=false)
	private String title;
	@Lob
	@Column(nullable=false)
	private String content;
	private Integer editCount;
	@OneToMany(mappedBy="blogPost")
	private Set<Comment> comments;
	@ManyToOne
	private User author;
	
	@PrePersist
	protected void onInsert(){
		setEditCount(0);
		super.onInsert();
	}
	
	@PreUpdate
	protected void onUpdate(){
		setEditCount(getEditCount()+1);
		super.onUpdate();
	}
	
	public void addComment(Comment comment){
		comments.add(comment);
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getEditCount() {
		return editCount;
	}

	public void setEditCount(Integer editCount) {
		this.editCount = editCount;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/* (non-Javadoc)
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
		if (!(obj instanceof BlogPost)) {
			return false;
		}
		BlogPost other = (BlogPost) obj;
		if (content == null) {
			if (other.content != null) {
				return false;
			}
		} else if (!content.equals(other.content)) {
			return false;
		}
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		return true;
	}

}
