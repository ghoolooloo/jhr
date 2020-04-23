package jo.jhr.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import jo.jhr.domain.enumeration.Sex;
import jo.jhr.domain.enumeration.OffsetType;

/**
 * A DTO for the {@link jo.jhr.domain.DeliveryAddress} entity.
 */
@ApiModel(description = "送餐地址\n@author Jo")
public class DeliveryAddressDTO implements Serializable {
    
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
     * 联系人
     */
    @NotNull
    @Size(max = 20)
    @ApiModelProperty(value = "联系人", required = true)
    private String contact;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private Sex sex;

    /**
     * 手机
     */
    @Size(max = 11)
    @ApiModelProperty(value = "手机")
    private String mobile;

    /**
     * 国家
     */
    @Size(max = 20)
    @ApiModelProperty(value = "国家")
    private String country;

    /**
     * 省、直辖市、自治区
     */
    @NotNull
    @Size(max = 20)
    @ApiModelProperty(value = "省、直辖市、自治区", required = true)
    private String province;

    /**
     * 城市
     */
    @NotNull
    @Size(max = 20)
    @ApiModelProperty(value = "城市", required = true)
    private String city;

    /**
     * 地区
     */
    @Size(max = 20)
    @ApiModelProperty(value = "地区")
    private String district;

    /**
     * 坐标类型
     */
    @NotNull
    @ApiModelProperty(value = "坐标类型", required = true)
    private OffsetType offsetType;

    /**
     * 经度
     */
    @NotNull
    @ApiModelProperty(value = "经度", required = true)
    private Double longitude;

    /**
     * 纬度
     */
    @NotNull
    @ApiModelProperty(value = "纬度", required = true)
    private Double latitude;

    /**
     * 详细地址（不含省市信息）
     */
    @Size(max = 50)
    @ApiModelProperty(value = "详细地址（不含省市信息）")
    private String address;

    
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public OffsetType getOffsetType() {
        return offsetType;
    }

    public void setOffsetType(OffsetType offsetType) {
        this.offsetType = offsetType;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DeliveryAddressDTO deliveryAddressDTO = (DeliveryAddressDTO) o;
        if (deliveryAddressDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), deliveryAddressDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DeliveryAddressDTO{" +
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
