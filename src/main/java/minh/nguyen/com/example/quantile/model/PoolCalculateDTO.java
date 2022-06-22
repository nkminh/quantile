package minh.nguyen.com.example.quantile.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PoolCalculateDTO {
    Long poolId;
    double percentile;
}
