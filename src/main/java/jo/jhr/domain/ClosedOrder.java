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
 * 已完结订单\n@author Jo
 */
@Entity
@Table(name = "closed_order")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ClosedOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 订单编号
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "sn", length = 20, nullable = false, unique = true)
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
     * 配送状态（由配送平台提供，以达达为例）：4、5、7、10、1000
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
     * 订单状态：EXPIRED（已失效未支付）、REFUNDED（完成退款批准）、CLOSED（正常完结）
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
     * 失效时间
     */
    @Column(name = "expired_date")
    private Instant expiredDate;

    /**
     * 配送完成时间
     */
    @Column(name = "completed_date")
    private Instant completedDate;

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

    /**
     * 申请人
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "applicant", length = 20, nullable = false)
    private String applicant;

    /**
     * 申请时间
     */
    @NotNull
    @Column(name = "applied_date", nullable = false)
    private Instant appliedDate;

    /**
     * 退款原因描述
     */
    @Size(max = 100)
    @Column(name = "refund_desc", length = 100)
    private String refundDesc;

    /**
     * 批准人
     */
    @Size(max = 20)
    @Column(name = "refunded_by", length = 20)
    private String refundedBy;

    /**
     * 批准时间
     */
    @Column(name = "refunded_date")
    private Instant refundedDate;

    /**
     * 退款金额，单位：分
     */
    @Min(value = 0)
    @Max(value = 99999)
    @Column(name = "refund_amount")
    private Integer refundAmount;

    /**
     * 退款答复
     */
    @Size(max = 100)
    @Column(name = "reply", length = 100)
    private String reply;

    /**
     * 是否退款。true：同意退款，false：拒绝退款，空：申请中
     */
    @Column(name = "passed")
    private Boolean passed;

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

    public ClosedOrder sn(String sn) {
        this.sn = sn;
        return this;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getMemberName() {
        return memberName;
    }

    public ClosedOrder memberName(String memberName) {
        this.memberName = memberName;
        return this;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberSN() {
        return memberSN;
    }

    public ClosedOrder memberSN(String memberSN) {
        this.memberSN = memberSN;
        return this;
    }

    public void setMemberSN(String memberSN) {
        this.memberSN = memberSN;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public ClosedOrder orderType(OrderType orderType) {
        this.orderType = orderType;
        return this;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public String getShopSN() {
        return shopSN;
    }

    public ClosedOrder shopSN(String shopSN) {
        this.shopSN = shopSN;
        return this;
    }

    public void setShopSN(String shopSN) {
        this.shopSN = shopSN;
    }

    public String getShopName() {
        return shopName;
    }

    public ClosedOrder shopName(String shopName) {
        this.shopName = shopName;
        return this;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getPriceTotal() {
        return priceTotal;
    }

    public ClosedOrder priceTotal(Integer priceTotal) {
        this.priceTotal = priceTotal;
        return this;
    }

    public void setPriceTotal(Integer priceTotal) {
        this.priceTotal = priceTotal;
    }

    public Integer getCardReduce() {
        return cardReduce;
    }

    public ClosedOrder cardReduce(Integer cardReduce) {
        this.cardReduce = cardReduce;
        return this;
    }

    public void setCardReduce(Integer cardReduce) {
        this.cardReduce = cardReduce;
    }

    public String getCards() {
        return cards;
    }

    public ClosedOrder cards(String cards) {
        this.cards = cards;
        return this;
    }

    public void setCards(String cards) {
        this.cards = cards;
    }

    public Integer getRewardPointsReduce() {
        return rewardPointsReduce;
    }

    public ClosedOrder rewardPointsReduce(Integer rewardPointsReduce) {
        this.rewardPointsReduce = rewardPointsReduce;
        return this;
    }

    public void setRewardPointsReduce(Integer rewardPointsReduce) {
        this.rewardPointsReduce = rewardPointsReduce;
    }

    public Integer getPaymentTotal() {
        return paymentTotal;
    }

    public ClosedOrder paymentTotal(Integer paymentTotal) {
        this.paymentTotal = paymentTotal;
        return this;
    }

    public void setPaymentTotal(Integer paymentTotal) {
        this.paymentTotal = paymentTotal;
    }

    public DistributionPlatform getDistributionPlatform() {
        return distributionPlatform;
    }

    public ClosedOrder distributionPlatform(DistributionPlatform distributionPlatform) {
        this.distributionPlatform = distributionPlatform;
        return this;
    }

    public void setDistributionPlatform(DistributionPlatform distributionPlatform) {
        this.distributionPlatform = distributionPlatform;
    }

    public String getDeliveryNo() {
        return deliveryNo;
    }

    public ClosedOrder deliveryNo(String deliveryNo) {
        this.deliveryNo = deliveryNo;
        return this;
    }

    public void setDeliveryNo(String deliveryNo) {
        this.deliveryNo = deliveryNo;
    }

    public Integer getDeliveryStatus() {
        return deliveryStatus;
    }

    public ClosedOrder deliveryStatus(Integer deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
        return this;
    }

    public void setDeliveryStatus(Integer deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getDeliveryDesc() {
        return deliveryDesc;
    }

    public ClosedOrder deliveryDesc(String deliveryDesc) {
        this.deliveryDesc = deliveryDesc;
        return this;
    }

    public void setDeliveryDesc(String deliveryDesc) {
        this.deliveryDesc = deliveryDesc;
    }

    public String getDeliverier() {
        return deliverier;
    }

    public ClosedOrder deliverier(String deliverier) {
        this.deliverier = deliverier;
        return this;
    }

    public void setDeliverier(String deliverier) {
        this.deliverier = deliverier;
    }

    public String getDeliverierMobile() {
        return deliverierMobile;
    }

    public ClosedOrder deliverierMobile(String deliverierMobile) {
        this.deliverierMobile = deliverierMobile;
        return this;
    }

    public void setDeliverierMobile(String deliverierMobile) {
        this.deliverierMobile = deliverierMobile;
    }

    public Integer getDeliveryDeductFee() {
        return deliveryDeductFee;
    }

    public ClosedOrder deliveryDeductFee(Integer deliveryDeductFee) {
        this.deliveryDeductFee = deliveryDeductFee;
        return this;
    }

    public void setDeliveryDeductFee(Integer deliveryDeductFee) {
        this.deliveryDeductFee = deliveryDeductFee;
    }

    public Integer getDeliveryFee() {
        return deliveryFee;
    }

    public ClosedOrder deliveryFee(Integer deliveryFee) {
        this.deliveryFee = deliveryFee;
        return this;
    }

    public void setDeliveryFee(Integer deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getContact() {
        return contact;
    }

    public ClosedOrder contact(String contact) {
        this.contact = contact;
        return this;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Sex getSex() {
        return sex;
    }

    public ClosedOrder sex(Sex sex) {
        this.sex = sex;
        return this;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public ClosedOrder mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCountry() {
        return country;
    }

    public ClosedOrder country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public ClosedOrder province(String province) {
        this.province = province;
        return this;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public ClosedOrder city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public ClosedOrder district(String district) {
        this.district = district;
        return this;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public OffsetType getOffsetType() {
        return offsetType;
    }

    public ClosedOrder offsetType(OffsetType offsetType) {
        this.offsetType = offsetType;
        return this;
    }

    public void setOffsetType(OffsetType offsetType) {
        this.offsetType = offsetType;
    }

    public Double getLongitude() {
        return longitude;
    }

    public ClosedOrder longitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public ClosedOrder latitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public ClosedOrder address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPackingFee() {
        return packingFee;
    }

    public ClosedOrder packingFee(Integer packingFee) {
        this.packingFee = packingFee;
        return this;
    }

    public void setPackingFee(Integer packingFee) {
        this.packingFee = packingFee;
    }

    public PaymentMethod getPaymentMode() {
        return paymentMode;
    }

    public ClosedOrder paymentMode(PaymentMethod paymentMode) {
        this.paymentMode = paymentMode;
        return this;
    }

    public void setPaymentMode(PaymentMethod paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Instant getDiningDate() {
        return diningDate;
    }

    public ClosedOrder diningDate(Instant diningDate) {
        this.diningDate = diningDate;
        return this;
    }

    public void setDiningDate(Instant diningDate) {
        this.diningDate = diningDate;
    }

    public String getRemark() {
        return remark;
    }

    public ClosedOrder remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public ClosedOrder status(OrderStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public ClosedOrder createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getPaidDate() {
        return paidDate;
    }

    public ClosedOrder paidDate(Instant paidDate) {
        this.paidDate = paidDate;
        return this;
    }

    public void setPaidDate(Instant paidDate) {
        this.paidDate = paidDate;
    }

    public Instant getExpiredDate() {
        return expiredDate;
    }

    public ClosedOrder expiredDate(Instant expiredDate) {
        this.expiredDate = expiredDate;
        return this;
    }

    public void setExpiredDate(Instant expiredDate) {
        this.expiredDate = expiredDate;
    }

    public Instant getCompletedDate() {
        return completedDate;
    }

    public ClosedOrder completedDate(Instant completedDate) {
        this.completedDate = completedDate;
        return this;
    }

    public void setCompletedDate(Instant completedDate) {
        this.completedDate = completedDate;
    }

    public Instant getExceptionDate() {
        return exceptionDate;
    }

    public ClosedOrder exceptionDate(Instant exceptionDate) {
        this.exceptionDate = exceptionDate;
        return this;
    }

    public void setExceptionDate(Instant exceptionDate) {
        this.exceptionDate = exceptionDate;
    }

    public String getHandler() {
        return handler;
    }

    public ClosedOrder handler(String handler) {
        this.handler = handler;
        return this;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public Instant getHandledDate() {
        return handledDate;
    }

    public ClosedOrder handledDate(Instant handledDate) {
        this.handledDate = handledDate;
        return this;
    }

    public void setHandledDate(Instant handledDate) {
        this.handledDate = handledDate;
    }

    public String getHandledDesc() {
        return handledDesc;
    }

    public ClosedOrder handledDesc(String handledDesc) {
        this.handledDesc = handledDesc;
        return this;
    }

    public void setHandledDesc(String handledDesc) {
        this.handledDesc = handledDesc;
    }

    public String getApplicant() {
        return applicant;
    }

    public ClosedOrder applicant(String applicant) {
        this.applicant = applicant;
        return this;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public Instant getAppliedDate() {
        return appliedDate;
    }

    public ClosedOrder appliedDate(Instant appliedDate) {
        this.appliedDate = appliedDate;
        return this;
    }

    public void setAppliedDate(Instant appliedDate) {
        this.appliedDate = appliedDate;
    }

    public String getRefundDesc() {
        return refundDesc;
    }

    public ClosedOrder refundDesc(String refundDesc) {
        this.refundDesc = refundDesc;
        return this;
    }

    public void setRefundDesc(String refundDesc) {
        this.refundDesc = refundDesc;
    }

    public String getRefundedBy() {
        return refundedBy;
    }

    public ClosedOrder refundedBy(String refundedBy) {
        this.refundedBy = refundedBy;
        return this;
    }

    public void setRefundedBy(String refundedBy) {
        this.refundedBy = refundedBy;
    }

    public Instant getRefundedDate() {
        return refundedDate;
    }

    public ClosedOrder refundedDate(Instant refundedDate) {
        this.refundedDate = refundedDate;
        return this;
    }

    public void setRefundedDate(Instant refundedDate) {
        this.refundedDate = refundedDate;
    }

    public Integer getRefundAmount() {
        return refundAmount;
    }

    public ClosedOrder refundAmount(Integer refundAmount) {
        this.refundAmount = refundAmount;
        return this;
    }

    public void setRefundAmount(Integer refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getReply() {
        return reply;
    }

    public ClosedOrder reply(String reply) {
        this.reply = reply;
        return this;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public Boolean isPassed() {
        return passed;
    }

    public ClosedOrder passed(Boolean passed) {
        this.passed = passed;
        return this;
    }

    public void setPassed(Boolean passed) {
        this.passed = passed;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClosedOrder)) {
            return false;
        }
        return id != null && id.equals(((ClosedOrder) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ClosedOrder{" +
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
            ", expiredDate='" + getExpiredDate() + "'" +
            ", completedDate='" + getCompletedDate() + "'" +
            ", exceptionDate='" + getExceptionDate() + "'" +
            ", handler='" + getHandler() + "'" +
            ", handledDate='" + getHandledDate() + "'" +
            ", handledDesc='" + getHandledDesc() + "'" +
            ", applicant='" + getApplicant() + "'" +
            ", appliedDate='" + getAppliedDate() + "'" +
            ", refundDesc='" + getRefundDesc() + "'" +
            ", refundedBy='" + getRefundedBy() + "'" +
            ", refundedDate='" + getRefundedDate() + "'" +
            ", refundAmount=" + getRefundAmount() +
            ", reply='" + getReply() + "'" +
            ", passed='" + isPassed() + "'" +
            "}";
    }
}
