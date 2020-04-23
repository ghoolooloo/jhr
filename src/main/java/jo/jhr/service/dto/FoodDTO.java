package jo.jhr.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import jo.jhr.domain.enumeration.FeedingMode;

/**
 * A DTO for the {@link jo.jhr.domain.Food} entity.
 */
@ApiModel(description = "菜品\n@author Jo")
public class FoodDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 20)
    private String name;

    /**
     * 编号
     */
    @NotNull
    @Size(max = 10)
    @ApiModelProperty(value = "编号", required = true)
    private String sn;

    /**
     * 缩略图
     */
    @Size(max = 200)
    @ApiModelProperty(value = "缩略图")
    private String thumbnail;

    /**
     * 大图
     */
    @Size(max = 200)
    @ApiModelProperty(value = "大图")
    private String picture;

    /**
     * 售价（单位：分）
     */
    @NotNull
    @Min(value = 0)
    @Max(value = 99999)
    @ApiModelProperty(value = "售价（单位：分）", required = true)
    private Integer price;

    /**
     * 原价（单位：分）。原价要么为空，要么大于售价。原价为空时，是普通菜品；原价不为空且原价大于售价时，是特惠菜品
     */
    @Min(value = 0)
    @Max(value = 99999)
    @ApiModelProperty(value = "原价（单位：分）。原价要么为空，要么大于售价。原价为空时，是普通菜品；原价不为空且原价大于售价时，是特惠菜品")
    private Integer originalPrice;

    /**
     * 成本（单位：分）
     */
    @Min(value = 0)
    @Max(value = 99999)
    @ApiModelProperty(value = "成本（单位：分）")
    private Integer cost;

    /**
     * 包装费（单位：分）
     */
    @Min(value = 0)
    @Max(value = 99999)
    @ApiModelProperty(value = "包装费（单位：分）")
    private Integer packingFee;

    /**
     * 描述
     */
    @Size(max = 100)
    @ApiModelProperty(value = "描述")
    private String desc;

    /**
     * 序号
     */
    @NotNull
    @Min(value = 0)
    @Max(value = 999999999)
    @ApiModelProperty(value = "序号", required = true)
    private Integer sort;

    /**
     * 供餐方式
     */
    @NotNull
    @ApiModelProperty(value = "供餐方式", required = true)
    private FeedingMode feedingMode;

    /**
     * 是否禁用。已禁用的菜品要从一周菜单中删除
     */
    @ApiModelProperty(value = "是否禁用。已禁用的菜品要从一周菜单中删除")
    private Boolean disabled;

    @NotNull
    @Size(max = 20)
    private String createdBy;

    @NotNull
    private Instant createdDate;

    private Instant lastModifiedDate;

    @Size(max = 20)
    private String lastModifiedBy;


    private Long categoryId;
    
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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Integer originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getPackingFee() {
        return packingFee;
    }

    public void setPackingFee(Integer packingFee) {
        this.packingFee = packingFee;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public FeedingMode getFeedingMode() {
        return feedingMode;
    }

    public void setFeedingMode(FeedingMode feedingMode) {
        this.feedingMode = feedingMode;
    }

    public Boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long foodCategoryId) {
        this.categoryId = foodCategoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FoodDTO foodDTO = (FoodDTO) o;
        if (foodDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), foodDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FoodDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", sn='" + getSn() + "'" +
            ", thumbnail='" + getThumbnail() + "'" +
            ", picture='" + getPicture() + "'" +
            ", price=" + getPrice() +
            ", originalPrice=" + getOriginalPrice() +
            ", cost=" + getCost() +
            ", packingFee=" + getPackingFee() +
            ", desc='" + getDesc() + "'" +
            ", sort=" + getSort() +
            ", feedingMode='" + getFeedingMode() + "'" +
            ", disabled='" + isDisabled() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", categoryId=" + getCategoryId() +
            "}";
    }
}
