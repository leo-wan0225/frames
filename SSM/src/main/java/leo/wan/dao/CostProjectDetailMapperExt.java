package leo.wan.dao;

import leo.wan.model.CostProjectDetail;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface CostProjectDetailMapperExt extends CostProjectDetailMapper {
    List<CostProjectDetail> getList(Map params);
}
