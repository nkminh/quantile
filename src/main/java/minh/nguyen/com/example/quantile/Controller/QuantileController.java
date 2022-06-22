package minh.nguyen.com.example.quantile.Controller;

import minh.nguyen.com.example.quantile.Entity.QuantileEntity;
import minh.nguyen.com.example.quantile.model.PoolCalculateDTO;
import minh.nguyen.com.example.quantile.model.PoolDTO;
import minh.nguyen.com.example.quantile.model.PoolRes;
import minh.nguyen.com.example.quantile.service.QuantileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quantile")
@CrossOrigin("*")
public class QuantileController {


    QuantileService quantileService;

    public QuantileController (QuantileService quantileSr) {
        this.quantileService = quantileSr;
    }

    @PostMapping("")
    @ResponseBody
    public String insertOrUpdate(@RequestBody PoolDTO poolDTO) {

      return quantileService.insertOrUpdate(poolDTO);

    }
    @GetMapping("")
    @ResponseBody
    public List<QuantileEntity> getAll() {

        return quantileService.getAll();

    }

    @PostMapping("/calculate")
    @ResponseBody
    public PoolRes calculateQuantile(@RequestBody PoolCalculateDTO poolCalculateDTO) {

        return quantileService.calculateQuantile(poolCalculateDTO);

    }

}
