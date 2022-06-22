package minh.nguyen.com.example.quantile.service;

import minh.nguyen.com.example.quantile.Entity.QuantileEntity;
import minh.nguyen.com.example.quantile.Repository.QuantityRepository;
import minh.nguyen.com.example.quantile.model.PoolCalculateDTO;
import minh.nguyen.com.example.quantile.model.PoolDTO;
import minh.nguyen.com.example.quantile.model.PoolRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Service
public class QuantileServiceImp implements QuantileService {

    @Autowired
    QuantityRepository quantityRepository;

    @Override
    public String insertOrUpdate(PoolDTO poolDTO) {
        QuantileEntity quantileEntity = quantityRepository.findByPoolId(poolDTO.getPoolId()).orElse(null);
        if (quantileEntity == null) {
            quantityRepository.save(new QuantileEntity(poolDTO.getPoolId(), poolDTO.getPoolValues()));
            return "inserted";

        } else {
            List<Double> poolValues = quantileEntity.getPoolValues();
            poolValues.addAll(poolDTO.getPoolValues());

            quantityRepository.save(quantileEntity);
            return "appended";
        }
    }

    @Override
    public List<QuantileEntity> getAll() {
        return quantityRepository.findAll();
//        return null;
    }

    @Override
    public PoolRes calculateQuantile(PoolCalculateDTO poolCalculateDTO) {
        if (poolCalculateDTO.getPercentile() < 0 || poolCalculateDTO.getPercentile() > 100) {
            // invalid percentile
            return null;
        }

        // get pool by pool-id
        QuantileEntity quantileEntity = quantityRepository.findByPoolId(poolCalculateDTO.getPoolId()).orElse(null);
        // size of pool
        int n = quantileEntity.getPoolValues().size();

        if (quantileEntity == null || n < 1) {
            return null;
        }

        // sort pool value asc
        Collections.sort(quantileEntity.getPoolValues());

        // calculate percent
        double percent =  0.01 * poolCalculateDTO.getPercentile();
        // calculate Lp = (n + 1) * percent
        Number ith = (n + 1) * percent;

        // ith observation isn't an Integer
        // ith observation near 0 ---->its out of pool, quantile is nearly min pool
        if (ith.intValue() < 1 ) {
            return new PoolRes(n, quantileEntity.getPoolValues().get(0));
        }
        // ith observation > pool size  ---> its out of pool, quantile is max pool
        if (ith.intValue() >= n) {
            return new PoolRes(n, quantileEntity.getPoolValues().get(n - 1));
        }

        // calculate P
        double decimal = ith.doubleValue() % 1;
        // Check ith observation is an Integer
        if (decimal == 0) {
            return new PoolRes(n, quantileEntity.getPoolValues().get(ith.intValue() -1));
        } else {

            int position = ith.intValue() - 1;
            Number lessValue =  quantileEntity.getPoolValues().get(position);
            Number extraValue =  decimal *
                    (quantileEntity.getPoolValues().get(position + 1) - lessValue.doubleValue());
            Number quantile  = lessValue.doubleValue() + extraValue.doubleValue();
            return new PoolRes(n, quantile);
        }

    }
}
