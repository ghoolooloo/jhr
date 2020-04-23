package jo.jhr.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link jo.jhr.domain.RewardPoints} entity.
 */
@ApiModel(description = "积分\n@author Jo")
public class RewardPointsDTO implements Serializable {
    
    private Long id;

    /**
     * 会员名称
     */
    @NotNull
    @Size(max = 20)
    @ApiModelProperty(value = "会员名称", required = true)
    private String memberName;

    /**
     * 会员编号
     */
    @NotNull
    @Size(max = 20)
    @ApiModelProperty(value = "会员编号", required = true)
    private String memberSN;

    /**
     * 积分余额
     */
    @NotNull
    @Min(value = 0)
    @ApiModelProperty(value = "积分余额", required = true)
    private Integer balance;

    /**
     * 最后统计时间
     */
    @NotNull
    @ApiModelProperty(value = "最后统计时间", required = true)
    private Instant lastModifiedDate;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberSN() {
        return memberSN;
    }

    public void setMemberSN(String memberSN) {
        this.memberSN = memberSN;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RewardPointsDTO rewardPointsDTO = (RewardPointsDTO) o;
        if (rewardPointsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rewardPointsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RewardPointsDTO{" +
            "id=" + getId() +
            ", memberName='" + getMemberName() + "'" +
            ", memberSN='" + getMemberSN() + "'" +
            ", balance=" + getBalance() +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
