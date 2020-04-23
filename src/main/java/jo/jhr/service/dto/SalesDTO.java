package jo.jhr.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link jo.jhr.domain.Sales} entity.
 */
@ApiModel(description = "菜品销量\n@author Jo")
public class SalesDTO implements Serializable {
    
    private Long id;

    /**
     * 营业日（只有日期，不需要时间）
     */
    @NotNull
    @ApiModelProperty(value = "营业日（只有日期，不需要时间）", required = true)
    private Instant day;

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
     * 分类编号
     */
    @NotNull
    @Size(max = 5)
    @ApiModelProperty(value = "分类编号", required = true)
    private String categorySN;

    /**
     * 分类名称
     */
    @NotNull
    @Size(max = 10)
    @ApiModelProperty(value = "分类名称", required = true)
    private String categoryName;

    /**
     * 菜品编号
     */
    @NotNull
    @Size(max = 10)
    @ApiModelProperty(value = "菜品编号", required = true)
    private String foodSN;

    /**
     * 菜品名称
     */
    @NotNull
    @Size(max = 20)
    @ApiModelProperty(value = "菜品名称", required = true)
    private String foodName;

    /**
     * 菜品原价（单位：分）
     */
    @Min(value = 0)
    @Max(value = 99999)
    @ApiModelProperty(value = "菜品原价（单位：分）")
    private Integer foodOriginalPrice;

    /**
     * 初始库存
     */
    @NotNull
    @Min(value = 0)
    @Max(value = 99999)
    @ApiModelProperty(value = "初始库存", required = true)
    private Integer initQuantity;

    /**
     * 菜品销售数量
     */
    @NotNull
    @Min(value = 0)
    @Max(value = 99999)
    @ApiModelProperty(value = "菜品销售数量", required = true)
    private Integer salesQuantity;

    /**
     * 最后统计时间
     */
    @NotNull
    @ApiModelProperty(value = "最后统计时间", required = true)
    private Instant lastModifiedDate;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDay() {
        return day;
    }

    public void setDay(Instant day) {
        this.day = day;
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

    public String getCategorySN() {
        return categorySN;
    }

    public void setCategorySN(String categorySN) {
        this.categorySN = categorySN;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getFoodSN() {
        return foodSN;
    }

    public void setFoodSN(String foodSN) {
        this.foodSN = foodSN;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Integer getFoodOriginalPrice() {
        return foodOriginalPrice;
    }

    public void setFoodOriginalPrice(Integer foodOriginalPrice) {
        this.foodOriginalPrice = foodOriginalPrice;
    }

    public Integer getInitQuantity() {
        return initQuantity;
    }

    public void setInitQuantity(Integer initQuantity) {
        this.initQuantity = initQuantity;
    }

    public Integer getSalesQuantity() {
        return salesQuantity;
    }

    public void setSalesQuantity(Integer salesQuantity) {
        this.salesQuantity = salesQuantity;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SalesDTO salesDTO = (SalesDTO) o;
        if (salesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), salesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SalesDTO{" +
            "id=" + getId() +
            ", day='" + getDay() + "'" +
            ", shopSN='" + getShopSN() + "'" +
            ", shopName='" + getShopName() + "'" +
            ", categorySN='" + getCategorySN() + "'" +
            ", categoryName='" + getCategoryName() + "'" +
            ", foodSN='" + getFoodSN() + "'" +
            ", foodName='" + getFoodName() + "'" +
            ", foodOriginalPrice=" + getFoodOriginalPrice() +
            ", initQuantity=" + getInitQuantity() +
            ", salesQuantity=" + getSalesQuantity() +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
