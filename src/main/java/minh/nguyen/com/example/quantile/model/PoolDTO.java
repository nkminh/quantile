package minh.nguyen.com.example.quantile.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PoolDTO {
    Long poolId;
    List<Double> poolValues;
}
