package jo.jhr.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link jo.jhr.domain.OrderItem} entity.
 */
@ApiModel(description = "订单项\n要对该表做分区处理。\n@author Jo")
public class OrderItemDTO implements Serializable {
    
    private Long id;

    /**
     * 订单编号
     */
    @NotNull
    @Size(max = 20)
    @ApiModelProperty(value = "订单编号", required = true)
    private String orderSN;

    /**
     * 菜品分类名称
     */
    @NotNull
    @Size(max = 10)
    @ApiModelProperty(value = "菜品分类名称", required = true)
    private String foodCategoryName;

    /**
     * 菜品分类编号
     */
    @NotNull
    @Size(max = 5)
    @ApiModelProperty(value = "菜品分类编号", required = true)
    private String foodCategorySN;

    /**
     * 菜品名称
     */
    @NotNull
    @Size(max = 20)
    @ApiModelProperty(value = "菜品名称", required = true)
    private String foodName;

    /**
     * 菜品编号
     */
    @NotNull
    @Size(max = 10)
    @ApiModelProperty(value = "菜品编号", required = true)
    private String foodSN;

    /**
     * 菜品缩略图
     */
    @Size(max = 200)
    @ApiModelProperty(value = "菜品缩略图")
    private String foodThumbnail;

    /**
     * 菜品大图
     */
    @Size(max = 200)
    @ApiModelProperty(value = "菜品大图")
    private String foodPicture;

    /**
     * 售价（单位：分）
     */
    @NotNull
    @Min(value = 0)
    @Max(value = 99999)
    @ApiModelProperty(value = "售价（单位：分）", required = true)
    private Integer price;

    /**
     * 原价（单位：分）。原价要么为空，要么大于等于售价。原价为空或等于售价时，是普通菜品；原价不为空且原价大于售价时，是特惠菜品
     */
    @Min(value = 0)
    @Max(value = 99999)
    @ApiModelProperty(value = "原价（单位：分）。原价要么为空，要么大于等于售价。原价为空或等于售价时，是普通菜品；原价不为空且原价大于售价时，是特惠菜品")
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
     * 数量
     */
    @NotNull
    @Min(value = 1)
    @ApiModelProperty(value = "数量", required = true)
    private Integer quantity;

    /**
     * 兑换券的code。该字段不为空，表示这是一个兑换商品
     */
    @Size(max = 32)
    @ApiModelProperty(value = "兑换券的code。该字段不为空，表示这是一个兑换商品")
    private String coupon;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderSN() {
        return orderSN;
    }

    public void setOrderSN(String orderSN) {
        this.orderSN = orderSN;
    }

    public String getFoodCategoryName() {
        return foodCategoryName;
    }

    public void setFoodCategoryName(String foodCategoryName) {
        this.foodCategoryName = foodCategoryName;
    }

    public String getFoodCategorySN() {
        return foodCategorySN;
    }

    public void setFoodCategorySN(String foodCategorySN) {
        this.foodCategorySN = foodCategorySN;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodSN() {
        return foodSN;
    }

    public void setFoodSN(String foodSN) {
        this.foodSN = foodSN;
    }

    public String getFoodThumbnail() {
        return foodThumbnail;
    }

    public void setFoodThumbnail(String foodThumbnail) {
        this.foodThumbnail = foodThumbnail;
    }

    public String getFoodPicture() {
        return foodPicture;
    }

    public void setFoodPicture(String foodPicture) {
        this.foodPicture = foodPicture;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrderItemDTO orderItemDTO = (OrderItemDTO) o;
        if (orderItemDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderItemDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderItemDTO{" +
            "id=" + getId() +
            ", orderSN='" + getOrderSN() + "'" +
            ", foodCategoryName='" + getFoodCategoryName() + "'" +
            ", foodCategorySN='" + getFoodCategorySN() + "'" +
            ", foodName='" + getFoodName() + "'" +
            ", foodSN='" + getFoodSN() + "'" +
            ", foodThumbnail='" + getFoodThumbnail() + "'" +
            ", foodPicture='" + getFoodPicture() + "'" +
            ", price=" + getPrice() +
            ", originalPrice=" + getOriginalPrice() +
            ", cost=" + getCost() +
            ", packingFee=" + getPackingFee() +
            ", desc='" + getDesc() + "'" +
            ", quantity=" + getQuantity() +
            ", coupon='" + getCoupon() + "'" +
            "}";
    }
}
