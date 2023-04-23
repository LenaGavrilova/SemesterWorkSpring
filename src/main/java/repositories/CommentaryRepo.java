package repositories;

import models.Commentary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentaryRepo extends JpaRepository<Commentary,Long> {
}
