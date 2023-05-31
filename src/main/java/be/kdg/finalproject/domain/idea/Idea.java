package be.kdg.finalproject.domain.idea;

import be.kdg.finalproject.domain.interaction.like.UserIdeaLike;
import be.kdg.finalproject.domain.report.Report;
import be.kdg.finalproject.domain.theme.SubTheme;
import be.kdg.finalproject.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity (name = "IDEAS")
@Getter
@Setter
public class Idea {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "idea_id", nullable = false)
	private Long id;

	@Column (name = "title", nullable = false)
	private String title;

	@Column (name = "content")
	private String content;

	@Column (name = "image", nullable = false)
	private String image;

	@Column (name = "is_flagged", nullable = false)
	private boolean isFlagged;

	@ManyToOne (cascade = {CascadeType.MERGE})
	@JoinColumn (name = "sub_theme_id", nullable = false)
	private SubTheme subTheme;

	@OneToMany (mappedBy = "idea", cascade = CascadeType.ALL)
	private Set<UserIdeaLike> likers = new HashSet<>();

	@Column (name = "like_count", nullable = false, columnDefinition = "int default 0")
	private int likeCount;

	@Transient
	private boolean liked;

	@ManyToOne (cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn (name = "user_id", nullable = false)
	private User creator;


	@JoinColumn (name = "call_for_idea_id", nullable = false)
	private Long callForIdeasId;

	@OneToMany (mappedBy = "ideaId", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Report> reports = new HashSet<>();

	@Column (name = "date_created", nullable = false)
	private Timestamp dateCreated;

	@Column (name = "deleted", nullable = false, columnDefinition = "boolean default false")
	private boolean deleted;

	public Idea(String content, String image, SubTheme subTheme) {
		this.content = content;
		this.image = image;
		this.subTheme = subTheme;
		this.dateCreated = Timestamp.valueOf(LocalDateTime.now());
		this.isFlagged = false;
		this.title = content.substring(0, content.length() / 2);
	}

	public Idea(String content, String image, boolean isFlagged, SubTheme subTheme) {
		this(content, image, subTheme);
		this.isFlagged = isFlagged;
		this.dateCreated = Timestamp.valueOf(LocalDateTime.now());
		this.title = content.substring(0, content.length() / 2);
	}

	public Idea() {
		this.dateCreated = Timestamp.valueOf(LocalDateTime.now());
	}

	public Idea(String content, String image) {
		this.content = content;
		this.image = image;
		this.dateCreated = Timestamp.valueOf(LocalDateTime.now());
		this.isFlagged = false;
		this.title = content.substring(0, content.length() / 2);
	}

	public void setContent(String content) {
		this.content = content;
		this.title = content.substring(0, content.length() / 2);
	}

	public void addLiker() {
		likeCount++;
	}

	public void setLiked() {
		this.liked = true;
	}

	public void removeLiker() {
		likeCount--;
	}
}
