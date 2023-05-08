package ir.mapsa.autochargemodule.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

//public interface BaseRepository<E, ID extends Serializable> extends JpaRepository<E, ID>

@NoRepositoryBean
public interface BaseRepository<E,ID extends Serializable>  extends JpaRepository<E,ID>{

}
