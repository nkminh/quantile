package minh.nguyen.com.example.quantile.service;

import minh.nguyen.com.example.quantile.Entity.QuantileEntity;
import minh.nguyen.com.example.quantile.model.PoolCalculateDTO;
import minh.nguyen.com.example.quantile.model.PoolDTO;
import minh.nguyen.com.example.quantile.model.PoolRes;

import java.util.List;

public interface QuantileService {
    String insertOrUpdate(PoolDTO poolDTO);
    List<QuantileEntity> getAll();
    PoolRes calculateQuantile(PoolCalculateDTO poolCalculateDTO);
}
