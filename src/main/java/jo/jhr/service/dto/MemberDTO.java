package jo.jhr.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import jo.jhr.domain.enumeration.Sex;

/**
 * A DTO for the {@link jo.jhr.domain.Member} entity.
 */
@ApiModel(description = "会员\n@author Jo")
public class MemberDTO implements Serializable {
    
    private Long id;

    /**
     * 会员名称
     */
    @NotNull
    @Size(max = 20)
    @ApiModelProperty(value = "会员名称", required = true)
    private String name;

    /**
     * 会员编号。会员编号取自入会时间，生成格式：四位年数字+两位月份数字+两位日数字+两位小时数（24小时制）+两位分钟数+两位秒数+三位毫秒数
     */
    @NotNull
    @Size(max = 20)
    @ApiModelProperty(value = "会员编号。会员编号取自入会时间，生成格式：四位年数字+两位月份数字+两位日数字+两位小时数（24小时制）+两位分钟数+两位秒数+三位毫秒数", required = true)
    private String sn;

    /**
     * 微信会员Openid
     */
    @Size(max = 32)
    @ApiModelProperty(value = "微信会员Openid")
    private String openID;

    /**
     * 微信会员Unionid
     */
    @Size(max = 32)
    @ApiModelProperty(value = "微信会员Unionid")
    private String unionID;

    /**
     * 头像
     */
    @Size(max = 200)
    @ApiModelProperty(value = "头像")
    private String profilePicture;

    /**
     * 会员性别
     */
    @ApiModelProperty(value = "会员性别")
    private Sex sex;

    /**
     * 国家
     */
    @Size(max = 20)
    @ApiModelProperty(value = "国家")
    private String country;

    /**
     * 省、直辖市、自治区
     */
    @Size(max = 20)
    @ApiModelProperty(value = "省、直辖市、自治区")
    private String province;

    /**
     * 城市
     */
    @Size(max = 20)
    @ApiModelProperty(value = "城市")
    private String city;

    /**
     * 入会时间
     */
    @NotNull
    @ApiModelProperty(value = "入会时间", required = true)
    private Instant joinDate;

    /**
     * 最近登入时间
     */
    @ApiModelProperty(value = "最近登入时间")
    private Instant lastLoginDate;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getOpenID() {
        return openID;
    }

    public void setOpenID(String openID) {
        this.openID = openID;
    }

    public String getUnionID() {
        return unionID;
    }

    public void setUnionID(String unionID) {
        this.unionID = unionID;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
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

    public Instant getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Instant joinDate) {
        this.joinDate = joinDate;
    }

    public Instant getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Instant lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MemberDTO memberDTO = (MemberDTO) o;
        if (memberDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), memberDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
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
