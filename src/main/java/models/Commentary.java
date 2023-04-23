package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "commentaries")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Commentary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String commentary;

    @ManyToOne
    private User author;

    private Date date = new Date();

    @JsonIgnore
    @ManyToOne
    private Post post;

    @OneToMany(mappedBy = "commentary", cascade = CascadeType.ALL)
    @OrderBy("date")
    @ToString.Exclude
    private List<Response> responses;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Commentary that = (Commentary) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}