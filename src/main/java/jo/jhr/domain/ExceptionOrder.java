package jo.jhr.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import jo.jhr.domain.enumeration.OrderType;

import jo.jhr.domain.enumeration.DistributionPlatform;

import jo.jhr.domain.enumeration.Sex;

import jo.jhr.domain.enumeration.OffsetType;

import jo.jhr.domain.enumeration.PaymentMethod;

import jo.jhr.domain.enumeration.OrderStatus;

/**
 * 异常订单\n配送状态：待接单＝1 待取货＝2 配送中＝3 已完成＝4 配送员取消＝51 商户取消=52 配送平台系统或客服取消=53 其他取消=50 已过期＝7 指派单=8 妥投异常之物品返回中=9 妥投异常之物品返回完成=10 骑士到店=100 系统故障订单发布失败=1000\n@author Jo
 */
@Entity
@Table(name = "exception_order")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ExceptionOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 订单编号
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "sn", length = 20, nullable = false)
    private String sn;

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
     * 订单类型
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "order_type", nullable = false)
    private OrderType orderType;

    /**
     * 门店编号
     */
    @NotNull
    @Size(max = 30)
    @Column(name = "shop_sn", length = 30, nullable = false)
    private String shopSN;

    /**
     * 门店名称
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "shop_name", length = 20, nullable = false)
    private String shopName;

    /**
     * 菜品总金额（折扣前），单位：分
     */
    @NotNull
    @Min(value = 0)
    @Column(name = "price_total", nullable = false)
    private Integer priceTotal;

    /**
     * 代金券或折扣券减免或折扣的金额，单位：分
     */
    @Column(name = "card_reduce")
    private Integer cardReduce;

    /**
     * 该订单使用的卡券code列表，code之间用逗号分隔
     */
    @Size(max = 300)
    @Column(name = "cards", length = 300)
    private String cards;

    /**
     * 积分抵扣金额，单位：分
     */
    @Column(name = "reward_points_reduce")
    private Integer rewardPointsReduce;

    /**
     * 支付总金额（包含打折后的实际金额和各种费用），单位：分
     */
    @NotNull
    @Min(value = 0)
    @Column(name = "payment_total", nullable = false)
    private Integer paymentTotal;

    /**
     * 配送平台
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "distribution_platform")
    private DistributionPlatform distributionPlatform;

    /**
     * 配送单号
     */
    @Size(max = 32)
    @Column(name = "delivery_no", length = 32)
    private String deliveryNo;

    /**
     * 配送状态（由配送平台提供，以达达为例）：5、7、9、10、1000
     */
    @Column(name = "delivery_status")
    private Integer deliveryStatus;

    /**
     * 配送说明
     */
    @Size(max = 50)
    @Column(name = "delivery_desc", length = 50)
    private String deliveryDesc;

    /**
     * 配送员
     */
    @Size(max = 20)
    @Column(name = "deliverier", length = 20)
    private String deliverier;

    /**
     * 配送员手机
     */
    @Size(max = 11)
    @Column(name = "deliverier_mobile", length = 11)
    private String deliverierMobile;

    /**
     * 配送违约金，单位：分
     */
    @Column(name = "delivery_deduct_fee")
    private Integer deliveryDeductFee;

    /**
     * 外送费
     */
    @Column(name = "delivery_fee")
    private Integer deliveryFee;

    /**
     * 联系人
     */
    @Size(max = 20)
    @Column(name = "contact", length = 20)
    private String contact;

    /**
     * 联系人性别
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    private Sex sex;

    /**
     * 联系人手机，也是自助中的预留手机
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
     * 地区
     */
    @Size(max = 20)
    @Column(name = "district", length = 20)
    private String district;

    /**
     * 坐标类型
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "offset_type")
    private OffsetType offsetType;

    /**
     * 经度
     */
    @Column(name = "longitude")
    private Double longitude;

    /**
     * 纬度
     */
    @Column(name = "latitude")
    private Double latitude;

    /**
     * 详细地址（不含省市信息）
     */
    @Size(max = 50)
    @Column(name = "address", length = 50)
    private String address;

    /**
     * 包装费
     */
    @Column(name = "packing_fee")
    private Integer packingFee;

    /**
     * 支付方式
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_mode", nullable = false)
    private PaymentMethod paymentMode;

    /**
     * 预计送达时间或自取时间
     */
    @NotNull
    @Column(name = "dining_date", nullable = false)
    private Instant diningDate;

    /**
     * 备注
     */
    @Size(max = 30)
    @Column(name = "remark", length = 30)
    private String remark;

    /**
     * 订单状态：DELIVERING（配送中）
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;

    /**
     * 订单创建时间
     */
    @NotNull
    @Column(name = "created_date", nullable = false)
    private Instant createdDate;

    /**
     * 支付时间
     */
    @Column(name = "paid_date")
    private Instant paidDate;

    /**
     * 异常时间
     */
    @Column(name = "exception_date")
    private Instant exceptionDate;

    /**
     * 处理人
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "handler", length = 20, nullable = false)
    private String handler;

    /**
     * 处理时间
     */
    @Column(name = "handled_date")
    private Instant handledDate;

    /**
     * 处理描述
     */
    @Size(max = 100)
    @Column(name = "handled_desc", length = 100)
    private String handledDesc;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public ExceptionOrder sn(String sn) {
        this.sn = sn;
        return this;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getMemberName() {
        return memberName;
    }

    public ExceptionOrder memberName(String memberName) {
        this.memberName = memberName;
        return this;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberSN() {
        return memberSN;
    }

    public ExceptionOrder memberSN(String memberSN) {
        this.memberSN = memberSN;
        return this;
    }

    public void setMemberSN(String memberSN) {
        this.memberSN = memberSN;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public ExceptionOrder orderType(OrderType orderType) {
        this.orderType = orderType;
        return this;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public String getShopSN() {
        return shopSN;
    }

    public ExceptionOrder shopSN(String shopSN) {
        this.shopSN = shopSN;
        return this;
    }

    public void setShopSN(String shopSN) {
        this.shopSN = shopSN;
    }

    public String getShopName() {
        return shopName;
    }

    public ExceptionOrder shopName(String shopName) {
        this.shopName = shopName;
        return this;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getPriceTotal() {
        return priceTotal;
    }

    public ExceptionOrder priceTotal(Integer priceTotal) {
        this.priceTotal = priceTotal;
        return this;
    }

    public void setPriceTotal(Integer priceTotal) {
        this.priceTotal = priceTotal;
    }

    public Integer getCardReduce() {
        return cardReduce;
    }

    public ExceptionOrder cardReduce(Integer cardReduce) {
        this.cardReduce = cardReduce;
        return this;
    }

    public void setCardReduce(Integer cardReduce) {
        this.cardReduce = cardReduce;
    }

    public String getCards() {
        return cards;
    }

    public ExceptionOrder cards(String cards) {
        this.cards = cards;
        return this;
    }

    public void setCards(String cards) {
        this.cards = cards;
    }

    public Integer getRewardPointsReduce() {
        return rewardPointsReduce;
    }

    public ExceptionOrder rewardPointsReduce(Integer rewardPointsReduce) {
        this.rewardPointsReduce = rewardPointsReduce;
        return this;
    }

    public void setRewardPointsReduce(Integer rewardPointsReduce) {
        this.rewardPointsReduce = rewardPointsReduce;
    }

    public Integer getPaymentTotal() {
        return paymentTotal;
    }

    public ExceptionOrder paymentTotal(Integer paymentTotal) {
        this.paymentTotal = paymentTotal;
        return this;
    }

    public void setPaymentTotal(Integer paymentTotal) {
        this.paymentTotal = paymentTotal;
    }

    public DistributionPlatform getDistributionPlatform() {
        return distributionPlatform;
    }

    public ExceptionOrder distributionPlatform(DistributionPlatform distributionPlatform) {
        this.distributionPlatform = distributionPlatform;
        return this;
    }

    public void setDistributionPlatform(DistributionPlatform distributionPlatform) {
        this.distributionPlatform = distributionPlatform;
    }

    public String getDeliveryNo() {
        return deliveryNo;
    }

    public ExceptionOrder deliveryNo(String deliveryNo) {
        this.deliveryNo = deliveryNo;
        return this;
    }

    public void setDeliveryNo(String deliveryNo) {
        this.deliveryNo = deliveryNo;
    }

    public Integer getDeliveryStatus() {
        return deliveryStatus;
    }

    public ExceptionOrder deliveryStatus(Integer deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
        return this;
    }

    public void setDeliveryStatus(Integer deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getDeliveryDesc() {
        return deliveryDesc;
    }

    public ExceptionOrder deliveryDesc(String deliveryDesc) {
        this.deliveryDesc = deliveryDesc;
        return this;
    }

    public void setDeliveryDesc(String deliveryDesc) {
        this.deliveryDesc = deliveryDesc;
    }

    public String getDeliverier() {
        return deliverier;
    }

    public ExceptionOrder deliverier(String deliverier) {
        this.deliverier = deliverier;
        return this;
    }

    public void setDeliverier(String deliverier) {
        this.deliverier = deliverier;
    }

    public String getDeliverierMobile() {
        return deliverierMobile;
    }

    public ExceptionOrder deliverierMobile(String deliverierMobile) {
        this.deliverierMobile = deliverierMobile;
        return this;
    }

    public void setDeliverierMobile(String deliverierMobile) {
        this.deliverierMobile = deliverierMobile;
    }

    public Integer getDeliveryDeductFee() {
        return deliveryDeductFee;
    }

    public ExceptionOrder deliveryDeductFee(Integer deliveryDeductFee) {
        this.deliveryDeductFee = deliveryDeductFee;
        return this;
    }

    public void setDeliveryDeductFee(Integer deliveryDeductFee) {
        this.deliveryDeductFee = deliveryDeductFee;
    }

    public Integer getDeliveryFee() {
        return deliveryFee;
    }

    public ExceptionOrder deliveryFee(Integer deliveryFee) {
        this.deliveryFee = deliveryFee;
        return this;
    }

    public void setDeliveryFee(Integer deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getContact() {
        return contact;
    }

    public ExceptionOrder contact(String contact) {
        this.contact = contact;
        return this;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Sex getSex() {
        return sex;
    }

    public ExceptionOrder sex(Sex sex) {
        this.sex = sex;
        return this;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public ExceptionOrder mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCountry() {
        return country;
    }

    public ExceptionOrder country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public ExceptionOrder province(String province) {
        this.province = province;
        return this;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public ExceptionOrder city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public ExceptionOrder district(String district) {
        this.district = district;
        return this;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public OffsetType getOffsetType() {
        return offsetType;
    }

    public ExceptionOrder offsetType(OffsetType offsetType) {
        this.offsetType = offsetType;
        return this;
    }

    public void setOffsetType(OffsetType offsetType) {
        this.offsetType = offsetType;
    }

    public Double getLongitude() {
        return longitude;
    }

    public ExceptionOrder longitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public ExceptionOrder latitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public ExceptionOrder address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPackingFee() {
        return packingFee;
    }

    public ExceptionOrder packingFee(Integer packingFee) {
        this.packingFee = packingFee;
        return this;
    }

    public void setPackingFee(Integer packingFee) {
        this.packingFee = packingFee;
    }

    public PaymentMethod getPaymentMode() {
        return paymentMode;
    }

    public ExceptionOrder paymentMode(PaymentMethod paymentMode) {
        this.paymentMode = paymentMode;
        return this;
    }

    public void setPaymentMode(PaymentMethod paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Instant getDiningDate() {
        return diningDate;
    }

    public ExceptionOrder diningDate(Instant diningDate) {
        this.diningDate = diningDate;
        return this;
    }

    public void setDiningDate(Instant diningDate) {
        this.diningDate = diningDate;
    }

    public String getRemark() {
        return remark;
    }

    public ExceptionOrder remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public ExceptionOrder status(OrderStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public ExceptionOrder createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getPaidDate() {
        return paidDate;
    }

    public ExceptionOrder paidDate(Instant paidDate) {
        this.paidDate = paidDate;
        return this;
    }

    public void setPaidDate(Instant paidDate) {
        this.paidDate = paidDate;
    }

    public Instant getExceptionDate() {
        return exceptionDate;
    }

    public ExceptionOrder exceptionDate(Instant exceptionDate) {
        this.exceptionDate = exceptionDate;
        return this;
    }

    public void setExceptionDate(Instant exceptionDate) {
        this.exceptionDate = exceptionDate;
    }

    public String getHandler() {
        return handler;
    }

    public ExceptionOrder handler(String handler) {
        this.handler = handler;
        return this;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public Instant getHandledDate() {
        return handledDate;
    }

    public ExceptionOrder handledDate(Instant handledDate) {
        this.handledDate = handledDate;
        return this;
    }

    public void setHandledDate(Instant handledDate) {
        this.handledDate = handledDate;
    }

    public String getHandledDesc() {
        return handledDesc;
    }

    public ExceptionOrder handledDesc(String handledDesc) {
        this.handledDesc = handledDesc;
        return this;
    }

    public void setHandledDesc(String handledDesc) {
        this.handledDesc = handledDesc;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExceptionOrder)) {
            return false;
        }
        return id != null && id.equals(((ExceptionOrder) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ExceptionOrder{" +
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
            ", deliveryStatus=" + getDeliveryStatus() +
            ", deliveryDesc='" + getDeliveryDesc() + "'" +
            ", deliverier='" + getDeliverier() + "'" +
            ", deliverierMobile='" + getDeliverierMobile() + "'" +
            ", deliveryDeductFee=" + getDeliveryDeductFee() +
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
            ", exceptionDate='" + getExceptionDate() + "'" +
            ", handler='" + getHandler() + "'" +
            ", handledDate='" + getHandledDate() + "'" +
            ", handledDesc='" + getHandledDesc() + "'" +
            "}";
    }
}
