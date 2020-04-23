package jo.jhr.service.mapper;


import jo.jhr.domain.*;
import jo.jhr.service.dto.RewardPointsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link RewardPoints} and its DTO {@link RewardPointsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RewardPointsMapper extends EntityMapper<RewardPointsDTO, RewardPoints> {


    @Mapping(target = "records", ignore = true)
    @Mapping(target = "removeRecords", ignore = true)
    RewardPoints toEntity(RewardPointsDTO rewardPointsDTO);

    default RewardPoints fromId(Long id) {
        if (id == null) {
            return null;
        }
        RewardPoints rewardPoints = new RewardPoints();
        rewardPoints.setId(id);
        return rewardPoints;
    }
}
