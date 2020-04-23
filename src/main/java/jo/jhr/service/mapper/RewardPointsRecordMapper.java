package jo.jhr.service.mapper;


import jo.jhr.domain.*;
import jo.jhr.service.dto.RewardPointsRecordDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link RewardPointsRecord} and its DTO {@link RewardPointsRecordDTO}.
 */
@Mapper(componentModel = "spring", uses = {RewardPointsMapper.class})
public interface RewardPointsRecordMapper extends EntityMapper<RewardPointsRecordDTO, RewardPointsRecord> {

    @Mapping(source = "rewardPoints.id", target = "rewardPointsId")
    RewardPointsRecordDTO toDto(RewardPointsRecord rewardPointsRecord);

    @Mapping(source = "rewardPointsId", target = "rewardPoints")
    RewardPointsRecord toEntity(RewardPointsRecordDTO rewardPointsRecordDTO);

    default RewardPointsRecord fromId(Long id) {
        if (id == null) {
            return null;
        }
        RewardPointsRecord rewardPointsRecord = new RewardPointsRecord();
        rewardPointsRecord.setId(id);
        return rewardPointsRecord;
    }
}
