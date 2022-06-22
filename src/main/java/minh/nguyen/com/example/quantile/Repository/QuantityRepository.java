package minh.nguyen.com.example.quantile.Repository;

import minh.nguyen.com.example.quantile.Entity.QuantileEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuantityRepository extends CrudRepository<QuantileEntity, Long> {
    Optional<QuantileEntity> findByPoolId(Long id);
    List<QuantileEntity> findAll();

}
