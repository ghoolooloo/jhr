package jo.jhr.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import jo.jhr.domain.enumeration.CardType;

/**
 * A DTO for the {@link jo.jhr.domain.CardHolder} entity.
 */
@ApiModel(description = "卡包，即已被用户领取的卡券\n卡券核销（已消费或过期）后，将转变为WriteOffCard中。\n订单取消，如果卡券没有失效，则相应已核销的卡券应该重新恢复未使用状态，即从WriteOffCard表移回本表，并且是未占用状态。\n部分退款的订单，不退还卡券；全额退款的订单，要退还卡券。\n卡券被订单选用，即使该订单尚未支付，则该卡券处于占用状态，不能被其他订单使用，除非它恢复可用状态。\n@author Jo")
public class CardHolderDTO implements Serializable {
    
    private Long id;

    /**
     * 优惠码，在领取时自动生成
     */
    @NotNull
    @Size(max = 40)
    @ApiModelProperty(value = "优惠码，在领取时自动生成", required = true)
    private String code;

    /**
     * 卡券编号
     */
    @NotNull
    @Size(max = 20)
    @ApiModelProperty(value = "卡券编号", required = true)
    private String cardSN;

    /**
     * 卡券类型
     */
    @NotNull
    @ApiModelProperty(value = "卡券类型", required = true)
    private CardType cardType;

    /**
     * 标题
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "标题", required = true)
    private String title;

    /**
     * 缩略图
     */
    @Size(max = 200)
    @ApiModelProperty(value = "缩略图")
    private String thumbnail;

    /**
     * 优惠说明（兑换券的兑换内容也是写在这里）
     */
    @Size(max = 300)
    @ApiModelProperty(value = "优惠说明（兑换券的兑换内容也是写在这里）")
    private String details;

    /**
     * 有效期开始时间
     */
    @ApiModelProperty(value = "有效期开始时间")
    private Instant validPeriodBeginAt;

    /**
     * 有效期截止时间
     */
    @ApiModelProperty(value = "有效期截止时间")
    private Instant validPeriodEndAt;

    /**
     * 能否与其他卡券同时使用
     */
    @NotNull
    @ApiModelProperty(value = "能否与其他卡券同时使用", required = true)
    private Boolean canUseWithOtherCard;

    /**
     * 适用的菜品分类。分类编号之间由逗号分隔，值为空时表示不限定分类
     */
    @Size(max = 500)
    @ApiModelProperty(value = "适用的菜品分类。分类编号之间由逗号分隔，值为空时表示不限定分类")
    private String acceptCategories;

    /**
     * 适用的门店。门店编号之间由逗号分隔，值为空时表示不限定门店
     */
    @Size(max = 500)
    @ApiModelProperty(value = "适用的门店。门店编号之间由逗号分隔，值为空时表示不限定门店")
    private String acceptShops;

    /**
     * 起用金额，单位为分。如果无起用门槛则填0
     */
    @Min(value = 0)
    @Max(value = 99999)
    @ApiModelProperty(value = "起用金额，单位为分。如果无起用门槛则填0")
    private Integer leastCost;

    /**
     * 减免金额，单位为分（代金券专用）
     */
    @Min(value = 0)
    @Max(value = 99999)
    @ApiModelProperty(value = "减免金额，单位为分（代金券专用）")
    private Integer reduceCost;

    /**
     * 打折额度（百分比，例如：30就是七折）（折扣券专用）
     */
    @Min(value = 0)
    @Max(value = 100)
    @ApiModelProperty(value = "打折额度（百分比，例如：30就是七折）（折扣券专用）")
    private Integer discount;

    /**
     * 兑换的菜品（兑换券专用）
     */
    @Size(max = 10)
    @ApiModelProperty(value = "兑换的菜品（兑换券专用）")
    private String gift;

    /**
     * 兑换菜品数量（兑换券专用）
     */
    @Min(value = 0)
    @Max(value = 99999)
    @ApiModelProperty(value = "兑换菜品数量（兑换券专用）")
    private Integer giftQuantity;

    /**
     * 领取者
     */
    @NotNull
    @Size(max = 20)
    @ApiModelProperty(value = "领取者", required = true)
    private String receivedBy;

    /**
     * 领取时间
     */
    @NotNull
    @ApiModelProperty(value = "领取时间", required = true)
    private Instant receivedDate;

    /**
     * 表示是否被占用，非空时是占用它的订单编号。该字段只有是空时才能被设置新值，否则只能执行清空操作。
     */
    @Size(max = 20)
    @ApiModelProperty(value = "表示是否被占用，非空时是占用它的订单编号。该字段只有是空时才能被设置新值，否则只能执行清空操作。")
    private String held;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCardSN() {
        return cardSN;
    }

    public void setCardSN(String cardSN) {
        this.cardSN = cardSN;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Instant getValidPeriodBeginAt() {
        return validPeriodBeginAt;
    }

    public void setValidPeriodBeginAt(Instant validPeriodBeginAt) {
        this.validPeriodBeginAt = validPeriodBeginAt;
    }

    public Instant getValidPeriodEndAt() {
        return validPeriodEndAt;
    }

    public void setValidPeriodEndAt(Instant validPeriodEndAt) {
        this.validPeriodEndAt = validPeriodEndAt;
    }

    public Boolean isCanUseWithOtherCard() {
        return canUseWithOtherCard;
    }

    public void setCanUseWithOtherCard(Boolean canUseWithOtherCard) {
        this.canUseWithOtherCard = canUseWithOtherCard;
    }

    public String getAcceptCategories() {
        return acceptCategories;
    }

    public void setAcceptCategories(String acceptCategories) {
        this.acceptCategories = acceptCategories;
    }

    public String getAcceptShops() {
        return acceptShops;
    }

    public void setAcceptShops(String acceptShops) {
        this.acceptShops = acceptShops;
    }

    public Integer getLeastCost() {
        return leastCost;
    }

    public void setLeastCost(Integer leastCost) {
        this.leastCost = leastCost;
    }

    public Integer getReduceCost() {
        return reduceCost;
    }

    public void setReduceCost(Integer reduceCost) {
        this.reduceCost = reduceCost;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }

    public Integer getGiftQuantity() {
        return giftQuantity;
    }

    public void setGiftQuantity(Integer giftQuantity) {
        this.giftQuantity = giftQuantity;
    }

    public String getReceivedBy() {
        return receivedBy;
    }

    public void setReceivedBy(String receivedBy) {
        this.receivedBy = receivedBy;
    }

    public Instant getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Instant receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getHeld() {
        return held;
    }

    public void setHeld(String held) {
        this.held = held;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CardHolderDTO cardHolderDTO = (CardHolderDTO) o;
        if (cardHolderDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cardHolderDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CardHolderDTO{" +
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
