package repositories;

import models.Img;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImgRepo extends JpaRepository<Img,Long> {

}
