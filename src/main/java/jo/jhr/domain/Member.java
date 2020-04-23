package jo.jhr.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.Instant;

import jo.jhr.domain.enumeration.Sex;

/**
 * 会员\n@author Jo
 */
@Entity
@Table(name = "member")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 会员名称
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "name", length = 20, nullable = false)
    private String name;

    /**
     * 会员编号。会员编号取自入会时间，生成格式：四位年数字+两位月份数字+两位日数字+两位小时数（24小时制）+两位分钟数+两位秒数+三位毫秒数
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "sn", length = 20, nullable = false, unique = true)
    private String sn;

    /**
     * 微信会员Openid
     */
    @Size(max = 32)
    @Column(name = "open_id", length = 32)
    private String openID;

    /**
     * 微信会员Unionid
     */
    @Size(max = 32)
    @Column(name = "union_id", length = 32)
    private String unionID;

    /**
     * 头像
     */
    @Size(max = 200)
    @Column(name = "profile_picture", length = 200)
    private String profilePicture;

    /**
     * 会员性别
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    private Sex sex;

    /**
     * 国家
     */
    @Size(max = 20)
    @Column(name = "country", length = 20)
    private String country;

    /**
     * 省、直辖市、自治区
     */
    @Size(max = 20)
    @Column(name = "province", length = 20)
    private String province;

    /**
     * 城市
     */
    @Size(max = 20)
    @Column(name = "city", length = 20)
    private String city;

    /**
     * 入会时间
     */
    @NotNull
    @Column(name = "join_date", nullable = false)
    private Instant joinDate;

    /**
     * 最近登入时间
     */
    @Column(name = "last_login_date")
    private Instant lastLoginDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Member name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSn() {
        return sn;
    }

    public Member sn(String sn) {
        this.sn = sn;
        return this;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getOpenID() {
        return openID;
    }

    public Member openID(String openID) {
        this.openID = openID;
        return this;
    }

    public void setOpenID(String openID) {
        this.openID = openID;
    }

    public String getUnionID() {
        return unionID;
    }

    public Member unionID(String unionID) {
        this.unionID = unionID;
        return this;
    }

    public void setUnionID(String unionID) {
        this.unionID = unionID;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public Member profilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
        return this;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Sex getSex() {
        return sex;
    }

    public Member sex(Sex sex) {
        this.sex = sex;
        return this;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public Member country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public Member province(String province) {
        this.province = province;
        return this;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public Member city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Instant getJoinDate() {
        return joinDate;
    }

    public Member joinDate(Instant joinDate) {
        this.joinDate = joinDate;
        return this;
    }

    public void setJoinDate(Instant joinDate) {
        this.joinDate = joinDate;
    }

    public Instant getLastLoginDate() {
        return lastLoginDate;
    }

    public Member lastLoginDate(Instant lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
        return this;
    }

    public void setLastLoginDate(Instant lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Member)) {
            return false;
        }
        return id != null && id.equals(((Member) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Member{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", sn='" + getSn() + "'" +
            ", openID='" + getOpenID() + "'" +
            ", unionID='" + getUnionID() + "'" +
            ", profilePicture='" + getProfilePicture() + "'" +
            ", sex='" + getSex() + "'" +
            ", country='" + getCountry() + "'" +
            ", province='" + getProvince() + "'" +
            ", city='" + getCity() + "'" +
            ", joinDate='" + getJoinDate() + "'" +
            ", lastLoginDate='" + getLastLoginDate() + "'" +
            "}";
    }
}
