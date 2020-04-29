package jo.jhr.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import jo.jhr.domain.enumeration.CardType;

/**
 * 卡包，即已被用户领取的卡券\n卡券核销（已消费或过期）后，将转变为WriteOffCard中。\n订单取消，如果卡券没有失效，则相应已核销的卡券应该重新恢复未使用状态，即从WriteOffCard表移回本表，并且是未占用状态。\n部分退款的订单，不退还卡券；全额退款的订单，要退还卡券。\n卡券被订单选用，即使该订单尚未支付，则该卡券处于占用状态，不能被其他订单使用，除非它恢复可用状态。\n@author Jo
 */
@Entity
@Table(name = "card_holder")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CardHolder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 优惠码，在领取时自动生成
     */
    @NotNull
    @Size(max = 40)
    @Column(name = "code", length = 40, nullable = false, unique = true)
    private String code;

    /**
     * 卡券编号
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "card_sn", length = 20, nullable = false)
    private String cardSN;

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
     * 兑换的菜品（兑换券专用）
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
     * 领取者
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "received_by", length = 20, nullable = false)
    private String receivedBy;

    /**
     * 领取时间
     */
    @NotNull
    @Column(name = "received_date", nullable = false)
    private Instant receivedDate;

    /**
     * 表示是否被占用，非空时是占用它的订单编号。该字段只有是空时才能被设置新值，否则只能执行清空操作。
     */
    @Size(max = 20)
    @Column(name = "held", length = 20)
    private String held;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public CardHolder code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCardSN() {
        return cardSN;
    }

    public CardHolder cardSN(String cardSN) {
        this.cardSN = cardSN;
        return this;
    }

    public void setCardSN(String cardSN) {
        this.cardSN = cardSN;
    }

    public CardType getCardType() {
        return cardType;
    }

    public CardHolder cardType(CardType cardType) {
        this.cardType = cardType;
        return this;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public String getTitle() {
        return title;
    }

    public CardHolder title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public CardHolder thumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDetails() {
        return details;
    }

    public CardHolder details(String details) {
        this.details = details;
        return this;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Instant getValidPeriodBeginAt() {
        return validPeriodBeginAt;
    }

    public CardHolder validPeriodBeginAt(Instant validPeriodBeginAt) {
        this.validPeriodBeginAt = validPeriodBeginAt;
        return this;
    }

    public void setValidPeriodBeginAt(Instant validPeriodBeginAt) {
        this.validPeriodBeginAt = validPeriodBeginAt;
    }

    public Instant getValidPeriodEndAt() {
        return validPeriodEndAt;
    }

    public CardHolder validPeriodEndAt(Instant validPeriodEndAt) {
        this.validPeriodEndAt = validPeriodEndAt;
        return this;
    }

    public void setValidPeriodEndAt(Instant validPeriodEndAt) {
        this.validPeriodEndAt = validPeriodEndAt;
    }

    public Boolean isCanUseWithOtherCard() {
        return canUseWithOtherCard;
    }

    public CardHolder canUseWithOtherCard(Boolean canUseWithOtherCard) {
        this.canUseWithOtherCard = canUseWithOtherCard;
        return this;
    }

    public void setCanUseWithOtherCard(Boolean canUseWithOtherCard) {
        this.canUseWithOtherCard = canUseWithOtherCard;
    }

    public String getAcceptCategories() {
        return acceptCategories;
    }

    public CardHolder acceptCategories(String acceptCategories) {
        this.acceptCategories = acceptCategories;
        return this;
    }

    public void setAcceptCategories(String acceptCategories) {
        this.acceptCategories = acceptCategories;
    }

    public String getAcceptShops() {
        return acceptShops;
    }

    public CardHolder acceptShops(String acceptShops) {
        this.acceptShops = acceptShops;
        return this;
    }

    public void setAcceptShops(String acceptShops) {
        this.acceptShops = acceptShops;
    }

    public Integer getLeastCost() {
        return leastCost;
    }

    public CardHolder leastCost(Integer leastCost) {
        this.leastCost = leastCost;
        return this;
    }

    public void setLeastCost(Integer leastCost) {
        this.leastCost = leastCost;
    }

    public Integer getReduceCost() {
        return reduceCost;
    }

    public CardHolder reduceCost(Integer reduceCost) {
        this.reduceCost = reduceCost;
        return this;
    }

    public void setReduceCost(Integer reduceCost) {
        this.reduceCost = reduceCost;
    }

    public Integer getDiscount() {
        return discount;
    }

    public CardHolder discount(Integer discount) {
        this.discount = discount;
        return this;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getGift() {
        return gift;
    }

    public CardHolder gift(String gift) {
        this.gift = gift;
        return this;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }

    public Integer getGiftQuantity() {
        return giftQuantity;
    }

    public CardHolder giftQuantity(Integer giftQuantity) {
        this.giftQuantity = giftQuantity;
        return this;
    }

    public void setGiftQuantity(Integer giftQuantity) {
        this.giftQuantity = giftQuantity;
    }

    public String getReceivedBy() {
        return receivedBy;
    }

    public CardHolder receivedBy(String receivedBy) {
        this.receivedBy = receivedBy;
        return this;
    }

    public void setReceivedBy(String receivedBy) {
        this.receivedBy = receivedBy;
    }

    public Instant getReceivedDate() {
        return receivedDate;
    }

    public CardHolder receivedDate(Instant receivedDate) {
        this.receivedDate = receivedDate;
        return this;
    }

    public void setReceivedDate(Instant receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getHeld() {
        return held;
    }

    public CardHolder held(String held) {
        this.held = held;
        return this;
    }

    public void setHeld(String held) {
        this.held = held;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CardHolder)) {
            return false;
        }
        return id != null && id.equals(((CardHolder) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CardHolder{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", cardSN='" + getCardSN() + "'" +
            ", cardType='" + getCardType() + "'" +
            ", title='" + getTitle() + "'" +
            ", thumbnail='" + getThumbnail() + "'" +
            ", details='" + getDetails() + "'" +
            ", validPeriodBeginAt='" + getValidPeriodBeginAt() + "'" +
            ", validPeriodEndAt='" + getValidPeriodEndAt() + "'" +
            ", canUseWithOtherCard='" + isCanUseWithOtherCard() + "'" +
            ", acceptCategories='" + getAcceptCategories() + "'" +
            ", acceptShops='" + getAcceptShops() + "'" +
            ", leastCost=" + getLeastCost() +
            ", reduceCost=" + getReduceCost() +
            ", discount=" + getDiscount() +
            ", gift='" + getGift() + "'" +
            ", giftQuantity=" + getGiftQuantity() +
            ", receivedBy='" + getReceivedBy() + "'" +
            ", receivedDate='" + getReceivedDate() + "'" +
            ", held='" + getHeld() + "'" +
            "}";
    }
}
