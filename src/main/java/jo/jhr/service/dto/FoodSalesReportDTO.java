package jo.jhr.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link jo.jhr.domain.FoodSalesReport} entity.
 */
@ApiModel(description = "菜品销售报表\n@author Jo")
public class FoodSalesReportDTO implements Serializable {
    
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
     * 成本（单位：分）
     */
    @NotNull
    @ApiModelProperty(value = "成本（单位：分）", required = true)
    private Integer cost;

    /**
     * 售价（单位：分）
     */
    @NotNull
    @ApiModelProperty(value = "售价（单位：分）", required = true)
    private Integer price;

    /**
     * 原价（单位：分）
     */
    @ApiModelProperty(value = "原价（单位：分）")
    private Integer originalPrice;

    /**
     * 销售数量
     */
    @ApiModelProperty(value = "销售数量")
    private Integer salesQuantity;

    /**
     * 销售金额（单位：分）= 售价 * 销售数量
     */
    @ApiModelProperty(value = "销售金额（单位：分）= 售价 * 销售数量")
    private Integer salesAmount;

    /**
     * 退款单数
     */
    @ApiModelProperty(value = "退款单数")
    private Integer refundOrders;

    /**
     * 退款金额
     */
    @ApiModelProperty(value = "退款金额")
    private Integer refundAmount;

    /**
     * 毛利（单位：分）= (售价 - 成本) * 销售数量
     */
    @ApiModelProperty(value = "毛利（单位：分）= (售价 - 成本) * 销售数量")
    private Integer grossProfit;

    /**
     * 初始库存
     */
    @ApiModelProperty(value = "初始库存")
    private Integer initQuantity;

    /**
     * 剩余库存
     */
    @ApiModelProperty(value = "剩余库存")
    private Integer remainder;

    
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

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
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

    public Integer getSalesQuantity() {
        return salesQuantity;
    }

    public void setSalesQuantity(Integer salesQuantity) {
        this.salesQuantity = salesQuantity;
    }

    public Integer getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(Integer salesAmount) {
        this.salesAmount = salesAmount;
    }

    public Integer getRefundOrders() {
        return refundOrders;
    }

    public void setRefundOrders(Integer refundOrders) {
        this.refundOrders = refundOrders;
    }

    public Integer getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Integer refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Integer getGrossProfit() {
        return grossProfit;
    }

    public void setGrossProfit(Integer grossProfit) {
        this.grossProfit = grossProfit;
    }

    public Integer getInitQuantity() {
        return initQuantity;
    }

    public void setInitQuantity(Integer initQuantity) {
        this.initQuantity = initQuantity;
    }

    public Integer getRemainder() {
        return remainder;
    }

    public void setRemainder(Integer remainder) {
        this.remainder = remainder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FoodSalesReportDTO foodSalesReportDTO = (FoodSalesReportDTO) o;
        if (foodSalesReportDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), foodSalesReportDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FoodSalesReportDTO{" +
            "id=" + getId() +
            ", day='" + getDay() + "'" +
            ", shopSN='" + getShopSN() + "'" +
            ", shopName='" + getShopName() + "'" +
            ", categorySN='" + getCategorySN() + "'" +
            ", categoryName='" + getCategoryName() + "'" +
            ", foodSN='" + getFoodSN() + "'" +
            ", foodName='" + getFoodName() + "'" +
            ", cost=" + getCost() +
            ", price=" + getPrice() +
            ", originalPrice=" + getOriginalPrice() +
            ", salesQuantity=" + getSalesQuantity() +
            ", salesAmount=" + getSalesAmount() +
            ", refundOrders=" + getRefundOrders() +
            ", refundAmount=" + getRefundAmount() +
            ", grossProfit=" + getGrossProfit() +
            ", initQuantity=" + getInitQuantity() +
            ", remainder=" + getRemainder() +
            "}";
    }
}
