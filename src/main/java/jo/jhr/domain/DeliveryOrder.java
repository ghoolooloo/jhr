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
 * 配送订单\n配送状态：待接单＝1 待取货＝2 配送中＝3 已完成＝4 配送员取消＝51 商户取消=52 配送平台系统或客服取消=53 其他取消=50 已过期＝7 指派单=8 妥投异常之物品返回中=9 妥投异常之物品返回完成=10 骑士到店=100 系统故障订单发布失败=1000\n配送完成的订单要么由调度器定时转变为已完结订单，要么通过申请退款转变为退款订单。\n@author Jo
 */
@Entity
@Table(name = "delivery_order")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DeliveryOrder implements Serializable {

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
     * 配送状态（由配送平台提供，以达达为例）：1-4、8
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
     * 配送违约金，单位：分。达达规定：接单后1－15分钟内取消订单，运费退回。同时扣除2元作为给配送员的违约金
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
     * 订单状态：DELIVERING（配送中）、COMPLETED（配送完成或自助取餐完成）
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
     * 配送完成时间
     */
    @Column(name = "completed_date")
    private Instant completedDate;

    /**
     * 最近修改时间
     */
    @Column(name = "last_modified_date")
    private Instant lastModifiedDate;

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

    public DeliveryOrder sn(String sn) {
        this.sn = sn;
        return this;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getMemberName() {
        return memberName;
    }

    public DeliveryOrder memberName(String memberName) {
        this.memberName = memberName;
        return this;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberSN() {
        return memberSN;
    }

    public DeliveryOrder memberSN(String memberSN) {
        this.memberSN = memberSN;
        return this;
    }

    public void setMemberSN(String memberSN) {
        this.memberSN = memberSN;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public DeliveryOrder orderType(OrderType orderType) {
        this.orderType = orderType;
        return this;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public String getShopSN() {
        return shopSN;
    }

    public DeliveryOrder shopSN(String shopSN) {
        this.shopSN = shopSN;
        return this;
    }

    public void setShopSN(String shopSN) {
        this.shopSN = shopSN;
    }

    public String getShopName() {
        return shopName;
    }

    public DeliveryOrder shopName(String shopName) {
        this.shopName = shopName;
        return this;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getPriceTotal() {
        return priceTotal;
    }

    public DeliveryOrder priceTotal(Integer priceTotal) {
        this.priceTotal = priceTotal;
        return this;
    }

    public void setPriceTotal(Integer priceTotal) {
        this.priceTotal = priceTotal;
    }

    public Integer getCardReduce() {
        return cardReduce;
    }

    public DeliveryOrder cardReduce(Integer cardReduce) {
        this.cardReduce = cardReduce;
        return this;
    }

    public void setCardReduce(Integer cardReduce) {
        this.cardReduce = cardReduce;
    }

    public String getCards() {
        return cards;
    }

    public DeliveryOrder cards(String cards) {
        this.cards = cards;
        return this;
    }

    public void setCards(String cards) {
        this.cards = cards;
    }

    public Integer getRewardPointsReduce() {
        return rewardPointsReduce;
    }

    public DeliveryOrder rewardPointsReduce(Integer rewardPointsReduce) {
        this.rewardPointsReduce = rewardPointsReduce;
        return this;
    }

    public void setRewardPointsReduce(Integer rewardPointsReduce) {
        this.rewardPointsReduce = rewardPointsReduce;
    }

    public Integer getPaymentTotal() {
        return paymentTotal;
    }

    public DeliveryOrder paymentTotal(Integer paymentTotal) {
        this.paymentTotal = paymentTotal;
        return this;
    }

    public void setPaymentTotal(Integer paymentTotal) {
        this.paymentTotal = paymentTotal;
    }

    public DistributionPlatform getDistributionPlatform() {
        return distributionPlatform;
    }

    public DeliveryOrder distributionPlatform(DistributionPlatform distributionPlatform) {
        this.distributionPlatform = distributionPlatform;
        return this;
    }

    public void setDistributionPlatform(DistributionPlatform distributionPlatform) {
        this.distributionPlatform = distributionPlatform;
    }

    public String getDeliveryNo() {
        return deliveryNo;
    }

    public DeliveryOrder deliveryNo(String deliveryNo) {
        this.deliveryNo = deliveryNo;
        return this;
    }

    public void setDeliveryNo(String deliveryNo) {
        this.deliveryNo = deliveryNo;
    }

    public Integer getDeliveryStatus() {
        return deliveryStatus;
    }

    public DeliveryOrder deliveryStatus(Integer deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
        return this;
    }

    public void setDeliveryStatus(Integer deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getDeliveryDesc() {
        return deliveryDesc;
    }

    public DeliveryOrder deliveryDesc(String deliveryDesc) {
        this.deliveryDesc = deliveryDesc;
        return this;
    }

    public void setDeliveryDesc(String deliveryDesc) {
        this.deliveryDesc = deliveryDesc;
    }

    public String getDeliverier() {
        return deliverier;
    }

    public DeliveryOrder deliverier(String deliverier) {
        this.deliverier = deliverier;
        return this;
    }

    public void setDeliverier(String deliverier) {
        this.deliverier = deliverier;
    }

    public String getDeliverierMobile() {
        return deliverierMobile;
    }

    public DeliveryOrder deliverierMobile(String deliverierMobile) {
        this.deliverierMobile = deliverierMobile;
        return this;
    }

    public void setDeliverierMobile(String deliverierMobile) {
        this.deliverierMobile = deliverierMobile;
    }

    public Integer getDeliveryDeductFee() {
        return deliveryDeductFee;
    }

    public DeliveryOrder deliveryDeductFee(Integer deliveryDeductFee) {
        this.deliveryDeductFee = deliveryDeductFee;
        return this;
    }

    public void setDeliveryDeductFee(Integer deliveryDeductFee) {
        this.deliveryDeductFee = deliveryDeductFee;
    }

    public Integer getDeliveryFee() {
        return deliveryFee;
    }

    public DeliveryOrder deliveryFee(Integer deliveryFee) {
        this.deliveryFee = deliveryFee;
        return this;
    }

    public void setDeliveryFee(Integer deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getContact() {
        return contact;
    }

    public DeliveryOrder contact(String contact) {
        this.contact = contact;
        return this;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Sex getSex() {
        return sex;
    }

    public DeliveryOrder sex(Sex sex) {
        this.sex = sex;
        return this;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public DeliveryOrder mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCountry() {
        return country;
    }

    public DeliveryOrder country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public DeliveryOrder province(String province) {
        this.province = province;
        return this;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public DeliveryOrder city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public DeliveryOrder district(String district) {
        this.district = district;
        return this;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public OffsetType getOffsetType() {
        return offsetType;
    }

    public DeliveryOrder offsetType(OffsetType offsetType) {
        this.offsetType = offsetType;
        return this;
    }

    public void setOffsetType(OffsetType offsetType) {
        this.offsetType = offsetType;
    }

    public Double getLongitude() {
        return longitude;
    }

    public DeliveryOrder longitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public DeliveryOrder latitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public DeliveryOrder address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPackingFee() {
        return packingFee;
    }

    public DeliveryOrder packingFee(Integer packingFee) {
        this.packingFee = packingFee;
        return this;
    }

    public void setPackingFee(Integer packingFee) {
        this.packingFee = packingFee;
    }

    public PaymentMethod getPaymentMode() {
        return paymentMode;
    }

    public DeliveryOrder paymentMode(PaymentMethod paymentMode) {
        this.paymentMode = paymentMode;
        return this;
    }

    public void setPaymentMode(PaymentMethod paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Instant getDiningDate() {
        return diningDate;
    }

    public DeliveryOrder diningDate(Instant diningDate) {
        this.diningDate = diningDate;
        return this;
    }

    public void setDiningDate(Instant diningDate) {
        this.diningDate = diningDate;
    }

    public String getRemark() {
        return remark;
    }

    public DeliveryOrder remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public DeliveryOrder status(OrderStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public DeliveryOrder createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getPaidDate() {
        return paidDate;
    }

    public DeliveryOrder paidDate(Instant paidDate) {
        this.paidDate = paidDate;
        return this;
    }

    public void setPaidDate(Instant paidDate) {
        this.paidDate = paidDate;
    }

    public Instant getCompletedDate() {
        return completedDate;
    }

    public DeliveryOrder completedDate(Instant completedDate) {
        this.completedDate = completedDate;
        return this;
    }

    public void setCompletedDate(Instant completedDate) {
        this.completedDate = completedDate;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public DeliveryOrder lastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeliveryOrder)) {
            return false;
        }
        return id != null && id.equals(((DeliveryOrder) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DeliveryOrder{" +
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
            ", completedDate='" + getCompletedDate() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
