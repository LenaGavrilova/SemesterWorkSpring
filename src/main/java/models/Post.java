package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "posts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String icon = "no";

    @Column(columnDefinition = "text")
    private String description;

    @JsonIgnore
    @ManyToOne(optional = false)
    private User author;

    @Column(nullable = false)
    private Date date = new Date();

    @JsonIgnore
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @OrderBy("date")
    @ToString.Exclude
    private List<Commentary> commentaries;

    public Post(String title, String description, User author) {
        this.title = title;
        this.description = description;
        this.author = author;
    }

    @JsonIgnore
    @Transient
    public String getShortTitle(){
        if(title.length() > 37) {
            String summary = title.substring(0, 34);
            return summary + "...";
        }
        return title;
    }

    @JsonIgnore
    @Transient
    public String getShortDesc(){
        if(description.length() > 325){
            String summary = description.substring(0, 325);
            return summary + "...";
        }
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Post post = (Post) o;
        return id != null && Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}