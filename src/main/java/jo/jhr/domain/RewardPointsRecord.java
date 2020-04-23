package jo.jhr.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.Instant;

/**
 * 积分明细\n@author Jo
 */
@Entity
@Table(name = "reward_points_record")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RewardPointsRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 积分数额
     */
    @NotNull
    @Column(name = "amount", nullable = false)
    private Integer amount;

    /**
     * 备注。要记录所对应的订单号
     */
    @Size(max = 100)
    @Column(name = "remark", length = 100)
    private String remark;

    /**
     * 发生时间
     */
    @NotNull
    @Column(name = "created_date", nullable = false)
    private Instant createdDate;

    @ManyToOne
    @JsonIgnoreProperties("records")
    private RewardPoints rewardPoints;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public RewardPointsRecord amount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public RewardPointsRecord remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public RewardPointsRecord createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public RewardPoints getRewardPoints() {
        return rewardPoints;
    }

    public RewardPointsRecord rewardPoints(RewardPoints rewardPoints) {
        this.rewardPoints = rewardPoints;
        return this;
    }

    public void setRewardPoints(RewardPoints rewardPoints) {
        this.rewardPoints = rewardPoints;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RewardPointsRecord)) {
            return false;
        }
        return id != null && id.equals(((RewardPointsRecord) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "RewardPointsRecord{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", remark='" + getRemark() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}
