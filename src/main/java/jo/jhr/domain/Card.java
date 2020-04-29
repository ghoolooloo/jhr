package jo.jhr.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import jo.jhr.domain.enumeration.CardType;

import jo.jhr.domain.enumeration.CardStatus;

/**
 * 卡券\n卡券编号取自创建时间，格式：一个大写字母的卡券类型标志+四位年数字+两位月份数字+两位日数字+两位小时数（24小时制）+两位分钟数+两位秒数+三位毫秒数。\n卡券类型标志：C（代金券）、D（折扣券）、G（通用兑换券）、B（买一送一兑换券）。\n@author Jo
 */
@Entity
@Table(name = "card")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Card implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 卡券编号
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "sn", length = 20, nullable = false, unique = true)
    private String sn;

    /**
     * 卡券类型
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "card_type", nullable = false)
    private CardType cardType;

    /**
     * 标题
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "title", length = 100, nullable = false)
    private String title;

    /**
     * 缩略图
     */
    @Size(max = 200)
    @Column(name = "thumbnail", length = 200)
    private String thumbnail;

    /**
     * 优惠说明（兑换券的兑换内容也是写在这里）
     */
    @Size(max = 300)
    @Column(name = "details", length = 300)
    private String details;

    /**
     * 有效期开始时间
     */
    @Column(name = "valid_period_begin_at")
    private Instant validPeriodBeginAt;

    /**
     * 有效期截止时间
     */
    @Column(name = "valid_period_end_at")
    private Instant validPeriodEndAt;

    /**
     * 投放数量
     */
    @Min(value = 0)
    @Max(value = 999999999)
    @Column(name = "quantity")
    private Integer quantity;

    /**
     * 已领取数量，需采用乐观锁更新
     */
    @Min(value = 0)
    @Max(value = 999999999)
    @Column(name = "received_quantity")
    private Integer receivedQuantity;

    /**
     * 能否与其他卡券同时使用
     */
    @NotNull
    @Column(name = "can_use_with_other_card", nullable = false)
    private Boolean canUseWithOtherCard;

    /**
     * 适用的菜品分类。分类编号之间由逗号分隔，值为空时表示不限定分类
     */
    @Size(max = 500)
    @Column(name = "accept_categories", length = 500)
    private String acceptCategories;

    /**
     * 适用的门店。门店编号之间由逗号分隔，值为空时表示不限定门店
     */
    @Size(max = 500)
    @Column(name = "accept_shops", length = 500)
    private String acceptShops;

    /**
     * 起用金额，单位为分。如果无起用门槛则填0
     */
    @Min(value = 0)
    @Max(value = 99999)
    @Column(name = "least_cost")
    private Integer leastCost;

    /**
     * 减免金额，单位为分（代金券专用）
     */
    @Min(value = 0)
    @Max(value = 99999)
    @Column(name = "reduce_cost")
    private Integer reduceCost;

    /**
     * 打折额度（百分比，例如：30就是七折）（折扣券专用）
     */
    @Min(value = 0)
    @Max(value = 100)
    @Column(name = "discount")
    private Integer discount;

    /**
     * 兑换的菜品编号（兑换券专用）
     */
    @Size(max = 10)
    @Column(name = "gift", length = 10)
    private String gift;

    /**
     * 兑换菜品数量（兑换券专用）
     */
    @Min(value = 0)
    @Max(value = 99999)
    @Column(name = "gift_quantity")
    private Integer giftQuantity;

    /**
     * 卡券状态。这里只可能取 NEW 和 PUT 两种值
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CardStatus status;

    @NotNull
    @Size(max = 20)
    @Column(name = "created_by", length = 20, nullable = false)
    private String createdBy;

    @NotNull
    @Column(name = "created_date", nullable = false)
    private Instant createdDate;

    /**
     * 投放人
     */
    @Size(max = 20)
    @Column(name = "deliveried_by", length = 20)
    private String deliveriedBy;

    /**
     * 投放时间
     */
    @Column(name = "deliveried_date")
    private Instant deliveriedDate;

    @Column(name = "last_modified_date")
    private Instant lastModifiedDate;

    @Size(max = 20)
    @Column(name = "last_modified_by", length = 20)
    private String lastModifiedBy;

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

    public Card sn(String sn) {
        this.sn = sn;
        return this;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public CardType getCardType() {
        return cardType;
    }

    public Card cardType(CardType cardType) {
        this.cardType = cardType;
        return this;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public String getTitle() {
        return title;
    }

    public Card title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public Card thumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDetails() {
        return details;
    }

    public Card details(String details) {
        this.details = details;
        return this;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Instant getValidPeriodBeginAt() {
        return validPeriodBeginAt;
    }

    public Card validPeriodBeginAt(Instant validPeriodBeginAt) {
        this.validPeriodBeginAt = validPeriodBeginAt;
        return this;
    }

    public void setValidPeriodBeginAt(Instant validPeriodBeginAt) {
        this.validPeriodBeginAt = validPeriodBeginAt;
    }

    public Instant getValidPeriodEndAt() {
        return validPeriodEndAt;
    }

    public Card validPeriodEndAt(Instant validPeriodEndAt) {
        this.validPeriodEndAt = validPeriodEndAt;
        return this;
    }

    public void setValidPeriodEndAt(Instant validPeriodEndAt) {
        this.validPeriodEndAt = validPeriodEndAt;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Card quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getReceivedQuantity() {
        return receivedQuantity;
    }

    public Card receivedQuantity(Integer receivedQuantity) {
        this.receivedQuantity = receivedQuantity;
        return this;
    }

    public void setReceivedQuantity(Integer receivedQuantity) {
        this.receivedQuantity = receivedQuantity;
    }

    public Boolean isCanUseWithOtherCard() {
        return canUseWithOtherCard;
    }

    public Card canUseWithOtherCard(Boolean canUseWithOtherCard) {
        this.canUseWithOtherCard = canUseWithOtherCard;
        return this;
    }

    public void setCanUseWithOtherCard(Boolean canUseWithOtherCard) {
        this.canUseWithOtherCard = canUseWithOtherCard;
    }

    public String getAcceptCategories() {
        return acceptCategories;
    }

    public Card acceptCategories(String acceptCategories) {
        this.acceptCategories = acceptCategories;
        return this;
    }

    public void setAcceptCategories(String acceptCategories) {
        this.acceptCategories = acceptCategories;
    }

    public String getAcceptShops() {
        return acceptShops;
    }

    public Card acceptShops(String acceptShops) {
        this.acceptShops = acceptShops;
        return this;
    }

    public void setAcceptShops(String acceptShops) {
        this.acceptShops = acceptShops;
    }

    public Integer getLeastCost() {
        return leastCost;
    }

    public Card leastCost(Integer leastCost) {
        this.leastCost = leastCost;
        return this;
    }

    public void setLeastCost(Integer leastCost) {
        this.leastCost = leastCost;
    }

    public Integer getReduceCost() {
        return reduceCost;
    }

    public Card reduceCost(Integer reduceCost) {
        this.reduceCost = reduceCost;
        return this;
    }

    public void setReduceCost(Integer reduceCost) {
        this.reduceCost = reduceCost;
    }

    public Integer getDiscount() {
        return discount;
    }

    public Card discount(Integer discount) {
        this.discount = discount;
        return this;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getGift() {
        return gift;
    }

    public Card gift(String gift) {
        this.gift = gift;
        return this;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }

    public Integer getGiftQuantity() {
        return giftQuantity;
    }

    public Card giftQuantity(Integer giftQuantity) {
        this.giftQuantity = giftQuantity;
        return this;
    }

    public void setGiftQuantity(Integer giftQuantity) {
        this.giftQuantity = giftQuantity;
    }

    public CardStatus getStatus() {
        return status;
    }

    public Card status(CardStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(CardStatus status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Card createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public Card createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getDeliveriedBy() {
        return deliveriedBy;
    }

    public Card deliveriedBy(String deliveriedBy) {
        this.deliveriedBy = deliveriedBy;
        return this;
    }

    public void setDeliveriedBy(String deliveriedBy) {
        this.deliveriedBy = deliveriedBy;
    }

    public Instant getDeliveriedDate() {
        return deliveriedDate;
    }

    public Card deliveriedDate(Instant deliveriedDate) {
        this.deliveriedDate = deliveriedDate;
        return this;
    }

    public void setDeliveriedDate(Instant deliveriedDate) {
        this.deliveriedDate = deliveriedDate;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public Card lastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public Card lastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Card)) {
            return false;
        }
        return id != null && id.equals(((Card) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Card{" +
            "id=" + getId() +
            ", sn='" + getSn() + "'" +
            ", cardType='" + getCardType() + "'" +
            ", title='" + getTitle() + "'" +
            ", thumbnail='" + getThumbnail() + "'" +
            ", details='" + getDetails() + "'" +
            ", validPeriodBeginAt='" + getValidPeriodBeginAt() + "'" +
            ", validPeriodEndAt='" + getValidPeriodEndAt() + "'" +
            ", quantity=" + getQuantity() +
            ", receivedQuantity=" + getReceivedQuantity() +
            ", canUseWithOtherCard='" + isCanUseWithOtherCard() + "'" +
            ", acceptCategories='" + getAcceptCategories() + "'" +
            ", acceptShops='" + getAcceptShops() + "'" +
            ", leastCost=" + getLeastCost() +
            ", reduceCost=" + getReduceCost() +
            ", discount=" + getDiscount() +
            ", gift='" + getGift() + "'" +
            ", giftQuantity=" + getGiftQuantity() +
            ", status='" + getStatus() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", deliveriedBy='" + getDeliveriedBy() + "'" +
            ", deliveriedDate='" + getDeliveriedDate() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            "}";
    }
}
