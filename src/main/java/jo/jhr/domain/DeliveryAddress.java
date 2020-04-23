package jo.jhr.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

import jo.jhr.domain.enumeration.Sex;

import jo.jhr.domain.enumeration.OffsetType;

/**
 * 送餐地址\n@author Jo
 */
@Entity
@Table(name = "delivery_address")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DeliveryAddress implements Serializable {

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
     * 联系人
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "contact", length = 20, nullable = false)
    private String contact;

    /**
     * 性别
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    private Sex sex;

    /**
     * 手机
     */
    @Size(max = 11)
    @Column(name = "mobile", length = 11)
    private String mobile;

    /**
     * 国家
     */
    @Size(max = 20)
    @Column(name = "country", length = 20)
    private String country;

    /**
     * 省、直辖市、自治区
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "province", length = 20, nullable = false)
    private String province;

    /**
     * 城市
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "city", length = 20, nullable = false)
    private String city;

    /**
     * 地区
     */
    @Size(max = 20)
    @Column(name = "district", length = 20)
    private String district;

    /**
     * 坐标类型
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "offset_type", nullable = false)
    private OffsetType offsetType;

    /**
     * 经度
     */
    @NotNull
    @Column(name = "longitude", nullable = false)
    private Double longitude;

    /**
     * 纬度
     */
    @NotNull
    @Column(name = "latitude", nullable = false)
    private Double latitude;

    /**
     * 详细地址（不含省市信息）
     */
    @Size(max = 50)
    @Column(name = "address", length = 50)
    private String address;

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

    public DeliveryAddress memberName(String memberName) {
        this.memberName = memberName;
        return this;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberSN() {
        return memberSN;
    }

    public DeliveryAddress memberSN(String memberSN) {
        this.memberSN = memberSN;
        return this;
    }

    public void setMemberSN(String memberSN) {
        this.memberSN = memberSN;
    }

    public String getContact() {
        return contact;
    }

    public DeliveryAddress contact(String contact) {
        this.contact = contact;
        return this;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Sex getSex() {
        return sex;
    }

    public DeliveryAddress sex(Sex sex) {
        this.sex = sex;
        return this;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public DeliveryAddress mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCountry() {
        return country;
    }

    public DeliveryAddress country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public DeliveryAddress province(String province) {
        this.province = province;
        return this;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public DeliveryAddress city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public DeliveryAddress district(String district) {
        this.district = district;
        return this;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public OffsetType getOffsetType() {
        return offsetType;
    }

    public DeliveryAddress offsetType(OffsetType offsetType) {
        this.offsetType = offsetType;
        return this;
    }

    public void setOffsetType(OffsetType offsetType) {
        this.offsetType = offsetType;
    }

    public Double getLongitude() {
        return longitude;
    }

    public DeliveryAddress longitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public DeliveryAddress latitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public DeliveryAddress address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeliveryAddress)) {
            return false;
        }
        return id != null && id.equals(((DeliveryAddress) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DeliveryAddress{" +
            "id=" + getId() +
            ", memberName='" + getMemberName() + "'" +
            ", memberSN='" + getMemberSN() + "'" +
            ", contact='" + getContact() + "'" +
            ", sex='" + getSex() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", country='" + getCountry() + "'" +
            ", province='" + getProvince() + "'" +
            ", city='" + getCity() + "'" +
            ", district='" + getDistrict() + "'" +
            ", offsetType='" + getOffsetType() + "'" +
            ", longitude=" + getLongitude() +
            ", latitude=" + getLatitude() +
            ", address='" + getAddress() + "'" +
            "}";
    }
}
