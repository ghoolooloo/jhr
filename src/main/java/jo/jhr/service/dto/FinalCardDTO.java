package jo.jhr.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import jo.jhr.domain.enumeration.CardType;
import jo.jhr.domain.enumeration.CardStatus;

/**
 * A DTO for the {@link jo.jhr.domain.FinalCard} entity.
 */
@ApiModel(description = "已完结卡券\n已经过期或中止的卡券将被移到这里。\n@author Jo")
public class FinalCardDTO implements Serializable {
    
    private Long id;

    /**
     * 卡券编号
     */
    @NotNull
    @Size(max = 20)
    @ApiModelProperty(value = "卡券编号", required = true)
    private String sn;

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
     * 投放数量
     */
    @Min(value = 0)
    @Max(value = 999999999)
    @ApiModelProperty(value = "投放数量")
    private Integer quantity;

    /**
     * 已领取数量
     */
    @Min(value = 0)
    @Max(value = 999999999)
    @ApiModelProperty(value = "已领取数量")
    private Integer receivedQuantity;

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
     * 卡券状态。这里只可能取 DISCONTINUE 和 EXPIRED 两种值
     */
    @NotNull
    @ApiModelProperty(value = "卡券状态。这里只可能取 DISCONTINUE 和 EXPIRED 两种值", required = true)
    private CardStatus status;

    @NotNull
    @Size(max = 20)
    private String createdBy;

    @NotNull
    private Instant createdDate;

    /**
     * 投放人
     */
    @Size(max = 20)
    @ApiModelProperty(value = "投放人")
    private String deliveriedBy;

    /**
     * 投放时间
     */
    @ApiModelProperty(value = "投放时间")
    private Instant deliveriedDate;

    /**
     * 中止人
     */
    @Size(max = 20)
    @ApiModelProperty(value = "中止人")
    private String discontinueBy;

    /**
     * 中止时间
     */
    @ApiModelProperty(value = "中止时间")
    private Instant discontinueDate;

    private Instant lastModifiedDate;

    @Size(max = 20)
    private String lastModifiedBy;

    
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getReceivedQuantity() {
        return receivedQuantity;
    }

    public void setReceivedQuantity(Integer receivedQuantity) {
        this.receivedQuantity = receivedQuantity;
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

    public CardStatus getStatus() {
        return status;
    }

    public void setStatus(CardStatus status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getDeliveriedBy() {
        return deliveriedBy;
    }

    public void setDeliveriedBy(String deliveriedBy) {
        this.deliveriedBy = deliveriedBy;
    }

    public Instant getDeliveriedDate() {
        return deliveriedDate;
    }

    public void setDeliveriedDate(Instant deliveriedDate) {
        this.deliveriedDate = deliveriedDate;
    }

    public String getDiscontinueBy() {
        return discontinueBy;
    }

    public void setDiscontinueBy(String discontinueBy) {
        this.discontinueBy = discontinueBy;
    }

    public Instant getDiscontinueDate() {
        return discontinueDate;
    }

    public void setDiscontinueDate(Instant discontinueDate) {
        this.discontinueDate = discontinueDate;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FinalCardDTO finalCardDTO = (FinalCardDTO) o;
        if (finalCardDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), finalCardDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FinalCardDTO{" +
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
            ", discontinueBy='" + getDiscontinueBy() + "'" +
            ", discontinueDate='" + getDiscontinueDate() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            "}";
    }
}
