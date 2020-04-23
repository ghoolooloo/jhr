package jo.jhr.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link jo.jhr.domain.RewardPointsRecord} entity.
 */
@ApiModel(description = "积分明细\n@author Jo")
public class RewardPointsRecordDTO implements Serializable {
    
    private Long id;

    /**
     * 积分数额
     */
    @NotNull
    @ApiModelProperty(value = "积分数额", required = true)
    private Integer amount;

    /**
     * 备注。要记录所对应的订单号
     */
    @Size(max = 100)
    @ApiModelProperty(value = "备注。要记录所对应的订单号")
    private String remark;

    /**
     * 发生时间
     */
    @NotNull
    @ApiModelProperty(value = "发生时间", required = true)
    private Instant createdDate;


    private Long rewardPointsId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Long getRewardPointsId() {
        return rewardPointsId;
    }

    public void setRewardPointsId(Long rewardPointsId) {
        this.rewardPointsId = rewardPointsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RewardPointsRecordDTO rewardPointsRecordDTO = (RewardPointsRecordDTO) o;
        if (rewardPointsRecordDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rewardPointsRecordDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RewardPointsRecordDTO{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", remark='" + getRemark() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", rewardPointsId=" + getRewardPointsId() +
            "}";
    }
}
