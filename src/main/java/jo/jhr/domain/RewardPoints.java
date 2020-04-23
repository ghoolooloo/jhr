package jo.jhr.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * 积分\n@author Jo
 */
@Entity
@Table(name = "reward_points")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RewardPoints implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 会员名称
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "member_name", length = 20, nullable = false)
    private String memberName;

    /**
     * 会员编号
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "member_sn", length = 20, nullable = false)
    private String memberSN;

    /**
     * 积分余额
     */
    @NotNull
    @Min(value = 0)
    @Column(name = "balance", nullable = false)
    private Integer balance;

    /**
     * 最后统计时间
     */
    @NotNull
    @Column(name = "last_modified_date", nullable = false)
    private Instant lastModifiedDate;

    @OneToMany(mappedBy = "rewardPoints")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RewardPointsRecord> records = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemberName() {
        return memberName;
    }

    public RewardPoints memberName(String memberName) {
        this.memberName = memberName;
        return this;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberSN() {
        return memberSN;
    }

    public RewardPoints memberSN(String memberSN) {
        this.memberSN = memberSN;
        return this;
    }

    public void setMemberSN(String memberSN) {
        this.memberSN = memberSN;
    }

    public Integer getBalance() {
        return balance;
    }

    public RewardPoints balance(Integer balance) {
        this.balance = balance;
        return this;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public RewardPoints lastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Set<RewardPointsRecord> getRecords() {
        return records;
    }

    public RewardPoints records(Set<RewardPointsRecord> rewardPointsRecords) {
        this.records = rewardPointsRecords;
        return this;
    }

    public RewardPoints addRecords(RewardPointsRecord rewardPointsRecord) {
        this.records.add(rewardPointsRecord);
        rewardPointsRecord.setRewardPoints(this);
        return this;
    }

    public RewardPoints removeRecords(RewardPointsRecord rewardPointsRecord) {
        this.records.remove(rewardPointsRecord);
        rewardPointsRecord.setRewardPoints(null);
        return this;
    }

    public void setRecords(Set<RewardPointsRecord> rewardPointsRecords) {
        this.records = rewardPointsRecords;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RewardPoints)) {
            return false;
        }
        return id != null && id.equals(((RewardPoints) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "RewardPoints{" +
            "id=" + getId() +
            ", memberName='" + getMemberName() + "'" +
            ", memberSN='" + getMemberSN() + "'" +
            ", balance=" + getBalance() +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
