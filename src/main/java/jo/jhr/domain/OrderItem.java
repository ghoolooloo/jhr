package jo.jhr.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * 订单项\n要对该表做分区处理。\n@author Jo
 */
@Entity
@Table(name = "order_item")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 订单编号
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "order_sn", length = 20, nullable = false, unique = true)
    private String orderSN;

    /**
     * 菜品分类名称
     */
    @NotNull
    @Size(max = 10)
    @Column(name = "food_category_name", length = 10, nullable = false)
    private String foodCategoryName;

    /**
     * 菜品分类编号
     */
    @NotNull
    @Size(max = 5)
    @Column(name = "food_category_sn", length = 5, nullable = false)
    private String foodCategorySN;

    /**
     * 菜品名称
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "food_name", length = 20, nullable = false)
    private String foodName;

    /**
     * 菜品编号
     */
    @NotNull
    @Size(max = 10)
    @Column(name = "food_sn", length = 10, nullable = false)
    private String foodSN;

    /**
     * 菜品缩略图
     */
    @Size(max = 200)
    @Column(name = "food_thumbnail", length = 200)
    private String foodThumbnail;

    /**
     * 菜品大图
     */
    @Size(max = 200)
    @Column(name = "food_picture", length = 200)
    private String foodPicture;

    /**
     * 售价（单位：分）
     */
    @NotNull
    @Min(value = 0)
    @Max(value = 99999)
    @Column(name = "price", nullable = false)
    private Integer price;

    /**
     * 原价（单位：分）。原价要么为空，要么大于等于售价。原价为空或等于售价时，是普通菜品；原价不为空且原价大于售价时，是特惠菜品
     */
    @Min(value = 0)
    @Max(value = 99999)
    @Column(name = "original_price")
    private Integer originalPrice;

    /**
     * 成本（单位：分）
     */
    @Min(value = 0)
    @Max(value = 99999)
    @Column(name = "cost")
    private Integer cost;

    /**
     * 包装费（单位：分）
     */
    @Min(value = 0)
    @Max(value = 99999)
    @Column(name = "packing_fee")
    private Integer packingFee;

    /**
     * 描述
     */
    @Size(max = 100)
    @Column(name = "jhi_desc", length = 100)
    private String desc;

    /**
     * 数量
     */
    @NotNull
    @Min(value = 1)
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    /**
     * 兑换券的code。该字段不为空，表示这是一个兑换商品
     */
    @Size(max = 32)
    @Column(name = "coupon", length = 32)
    private String coupon;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderSN() {
        return orderSN;
    }

    public OrderItem orderSN(String orderSN) {
        this.orderSN = orderSN;
        return this;
    }

    public void setOrderSN(String orderSN) {
        this.orderSN = orderSN;
    }

    public String getFoodCategoryName() {
        return foodCategoryName;
    }

    public OrderItem foodCategoryName(String foodCategoryName) {
        this.foodCategoryName = foodCategoryName;
        return this;
    }

    public void setFoodCategoryName(String foodCategoryName) {
        this.foodCategoryName = foodCategoryName;
    }

    public String getFoodCategorySN() {
        return foodCategorySN;
    }

    public OrderItem foodCategorySN(String foodCategorySN) {
        this.foodCategorySN = foodCategorySN;
        return this;
    }

    public void setFoodCategorySN(String foodCategorySN) {
        this.foodCategorySN = foodCategorySN;
    }

    public String getFoodName() {
        return foodName;
    }

    public OrderItem foodName(String foodName) {
        this.foodName = foodName;
        return this;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodSN() {
        return foodSN;
    }

    public OrderItem foodSN(String foodSN) {
        this.foodSN = foodSN;
        return this;
    }

    public void setFoodSN(String foodSN) {
        this.foodSN = foodSN;
    }

    public String getFoodThumbnail() {
        return foodThumbnail;
    }

    public OrderItem foodThumbnail(String foodThumbnail) {
        this.foodThumbnail = foodThumbnail;
        return this;
    }

    public void setFoodThumbnail(String foodThumbnail) {
        this.foodThumbnail = foodThumbnail;
    }

    public String getFoodPicture() {
        return foodPicture;
    }

    public OrderItem foodPicture(String foodPicture) {
        this.foodPicture = foodPicture;
        return this;
    }

    public void setFoodPicture(String foodPicture) {
        this.foodPicture = foodPicture;
    }

    public Integer getPrice() {
        return price;
    }

    public OrderItem price(Integer price) {
        this.price = price;
        return this;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getOriginalPrice() {
        return originalPrice;
    }

    public OrderItem originalPrice(Integer originalPrice) {
        this.originalPrice = originalPrice;
        return this;
    }

    public void setOriginalPrice(Integer originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Integer getCost() {
        return cost;
    }

    public OrderItem cost(Integer cost) {
        this.cost = cost;
        return this;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getPackingFee() {
        return packingFee;
    }

    public OrderItem packingFee(Integer packingFee) {
        this.packingFee = packingFee;
        return this;
    }

    public void setPackingFee(Integer packingFee) {
        this.packingFee = packingFee;
    }

    public String getDesc() {
        return desc;
    }

    public OrderItem desc(String desc) {
        this.desc = desc;
        return this;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OrderItem quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getCoupon() {
        return coupon;
    }

    public OrderItem coupon(String coupon) {
        this.coupon = coupon;
        return this;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderItem)) {
            return false;
        }
        return id != null && id.equals(((OrderItem) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
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
