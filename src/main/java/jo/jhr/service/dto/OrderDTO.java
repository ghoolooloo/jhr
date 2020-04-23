package jo.jhr.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import jo.jhr.domain.enumeration.OrderType;
import jo.jhr.domain.enumeration.DistributionPlatform;
import jo.jhr.domain.enumeration.Sex;
import jo.jhr.domain.enumeration.OffsetType;
import jo.jhr.domain.enumeration.PaymentMethod;
import jo.jhr.domain.enumeration.OrderStatus;

/**
 * A DTO for the {@link jo.jhr.domain.Order} entity.
 */
@ApiModel(description = "订单\n订单编号取至生成时的时间，格式：一个大写字母的订单类型标志+四位年数字+两位月份数字+两位日数字+两位小时数（24小时制）+四位顺序号。该顺序号就是取餐号。\n订单类型标志：T（外卖）、P（打包）、F（堂食）。\n这里的外送费只是商户要向顾客收取的外送费（可以查询某家配送平台的费用为基准），并不一定是商户最终支付给配送方的费用。因为，配送方是要到接单后才确定的，而且商户也有可能自己配送。\n已支付的订单将在向配送平台发送配送请求后，变成配送订单。\n@author Jo")
public class OrderDTO implements Serializable {
    
    private Long id;

    /**
     * 订单编号
     */
    @NotNull
    @Size(max = 20)
    @ApiModelProperty(value = "订单编号", required = true)
    private String sn;

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
     * 订单类型
     */
    @NotNull
    @ApiModelProperty(value = "订单类型", required = true)
    private OrderType orderType;

    /**
     * 门店编号
     */
    @NotNull
    @Size(max = 30)
    @ApiModelProperty(value = "门店编号", required = true)
    private String shopSN;

    /**
     * 门店名称
     */
    @NotNull
    @Size(max = 20)
    @ApiModelProperty(value = "门店名称", required = true)
    private String shopName;

    /**
     * 菜品总金额（折扣前），单位：分
     */
    @NotNull
    @Min(value = 0)
    @ApiModelProperty(value = "菜品总金额（折扣前），单位：分", required = true)
    private Integer priceTotal;

    /**
     * 代金券或折扣券减免或折扣的金额，单位：分
     */
    @ApiModelProperty(value = "代金券或折扣券减免或折扣的金额，单位：分")
    private Integer cardReduce;

    /**
     * 该订单使用的卡券code列表，code之间用逗号分隔
     */
    @Size(max = 300)
    @ApiModelProperty(value = "该订单使用的卡券code列表，code之间用逗号分隔")
    private String cards;

    /**
     * 积分抵扣金额，单位：分
     */
    @ApiModelProperty(value = "积分抵扣金额，单位：分")
    private Integer rewardPointsReduce;

    /**
     * 支付总金额（包含打折后的实际金额和各种费用），单位：分
     */
    @NotNull
    @Min(value = 0)
    @ApiModelProperty(value = "支付总金额（包含打折后的实际金额和各种费用），单位：分", required = true)
    private Integer paymentTotal;

    /**
     * 配送平台
     */
    @ApiModelProperty(value = "配送平台")
    private DistributionPlatform distributionPlatform;

    /**
     * 配送单号
     */
    @Size(max = 32)
    @ApiModelProperty(value = "配送单号")
    private String deliveryNo;

    /**
     * 外送费
     */
    @ApiModelProperty(value = "外送费")
    private Integer deliveryFee;

    /**
     * 联系人
     */
    @Size(max = 20)
    @ApiModelProperty(value = "联系人")
    private String contact;

    /**
     * 联系人性别
     */
    @ApiModelProperty(value = "联系人性别")
    private Sex sex;

    /**
     * 联系人手机，也是自助中的预留手机
     */
    @Size(max = 11)
    @ApiModelProperty(value = "联系人手机，也是自助中的预留手机")
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
     * 地区
     */
    @Size(max = 20)
    @ApiModelProperty(value = "地区")
    private String district;

    /**
     * 坐标类型
     */
    @ApiModelProperty(value = "坐标类型")
    private OffsetType offsetType;

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    private Double longitude;

    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度")
    private Double latitude;

    /**
     * 详细地址（不含省市信息）
     */
    @Size(max = 50)
    @ApiModelProperty(value = "详细地址（不含省市信息）")
    private String address;

    /**
     * 包装费
     */
    @ApiModelProperty(value = "包装费")
    private Integer packingFee;

    /**
     * 支付方式
     */
    @NotNull
    @ApiModelProperty(value = "支付方式", required = true)
    private PaymentMethod paymentMode;

    /**
     * 预计送达时间或自取时间
     */
    @NotNull
    @ApiModelProperty(value = "预计送达时间或自取时间", required = true)
    private Instant diningDate;

    /**
     * 备注
     */
    @Size(max = 30)
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 订单状态：NEW（待支付）、EXPIRED（已失效未支付）、PAID（已支付）
     */
    @NotNull
    @ApiModelProperty(value = "订单状态：NEW（待支付）、EXPIRED（已失效未支付）、PAID（已支付）", required = true)
    private OrderStatus status;

    /**
     * 订单创建时间
     */
    @NotNull
    @ApiModelProperty(value = "订单创建时间", required = true)
    private Instant createdDate;

    /**
     * 支付时间
     */
    @ApiModelProperty(value = "支付时间")
    private Instant paidDate;

    /**
     * 失效时间
     */
    @ApiModelProperty(value = "失效时间")
    private Instant expiredDate;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
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

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public String getShopSN() {
        return shopSN;
    }

    public void setShopSN(String shopSN) {
        this.shopSN = shopSN;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(Integer priceTotal) {
        this.priceTotal = priceTotal;
    }

    public Integer getCardReduce() {
        return cardReduce;
    }

    public void setCardReduce(Integer cardReduce) {
        this.cardReduce = cardReduce;
    }

    public String getCards() {
        return cards;
    }

    public void setCards(String cards) {
        this.cards = cards;
    }

    public Integer getRewardPointsReduce() {
        return rewardPointsReduce;
    }

    public void setRewardPointsReduce(Integer rewardPointsReduce) {
        this.rewardPointsReduce = rewardPointsReduce;
    }

    public Integer getPaymentTotal() {
        return paymentTotal;
    }

    public void setPaymentTotal(Integer paymentTotal) {
        this.paymentTotal = paymentTotal;
    }

    public DistributionPlatform getDistributionPlatform() {
        return distributionPlatform;
    }

    public void setDistributionPlatform(DistributionPlatform distributionPlatform) {
        this.distributionPlatform = distributionPlatform;
    }

    public String getDeliveryNo() {
        return deliveryNo;
    }

    public void setDeliveryNo(String deliveryNo) {
        this.deliveryNo = deliveryNo;
    }

    public Integer getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(Integer deliveryFee) {
        this.deliveryFee = deliveryFee;
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

    public Integer getPackingFee() {
        return packingFee;
    }

    public void setPackingFee(Integer packingFee) {
        this.packingFee = packingFee;
    }

    public PaymentMethod getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMethod paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Instant getDiningDate() {
        return diningDate;
    }

    public void setDiningDate(Instant diningDate) {
        this.diningDate = diningDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Instant paidDate) {
        this.paidDate = paidDate;
    }

    public Instant getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Instant expiredDate) {
        this.expiredDate = expiredDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrderDTO orderDTO = (OrderDTO) o;
        if (orderDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
            "id=" + getId() +
            ", sn='" + getSn() + "'" +
            ", memberName='" + getMemberName() + "'" +
            ", memberSN='" + getMemberSN() + "'" +
            ", orderType='" + getOrderType() + "'" +
            ", shopSN='" + getShopSN() + "'" +
            ", shopName='" + getShopName() + "'" +
            ", priceTotal=" + getPriceTotal() +
            ", cardReduce=" + getCardReduce() +
            ", cards='" + getCards() + "'" +
            ", rewardPointsReduce=" + getRewardPointsReduce() +
            ", paymentTotal=" + getPaymentTotal() +
            ", distributionPlatform='" + getDistributionPlatform() + "'" +
            ", deliveryNo='" + getDeliveryNo() + "'" +
            ", deliveryFee=" + getDeliveryFee() +
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
            ", packingFee=" + getPackingFee() +
            ", paymentMode='" + getPaymentMode() + "'" +
            ", diningDate='" + getDiningDate() + "'" +
            ", remark='" + getRemark() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", paidDate='" + getPaidDate() + "'" +
            ", expiredDate='" + getExpiredDate() + "'" +
            "}";
    }
}
