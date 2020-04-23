package jo.jhr.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.Instant;

/**
 * 菜品销售报表\n@author Jo
 */
@Entity
@Table(name = "food_sales_report")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FoodSalesReport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 营业日（只有日期，不需要时间）
     */
    @NotNull
    @Column(name = "day", nullable = false)
    private Instant day;

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
     * 分类编号
     */
    @NotNull
    @Size(max = 5)
    @Column(name = "category_sn", length = 5, nullable = false)
    private String categorySN;

    /**
     * 分类名称
     */
    @NotNull
    @Size(max = 10)
    @Column(name = "category_name", length = 10, nullable = false)
    private String categoryName;

    /**
     * 菜品编号
     */
    @NotNull
    @Size(max = 10)
    @Column(name = "food_sn", length = 10, nullable = false)
    private String foodSN;

    /**
     * 菜品名称
     */
    @NotNull
    @Size(max = 20)
    @Column(name = "food_name", length = 20, nullable = false)
    private String foodName;

    /**
     * 成本（单位：分）
     */
    @NotNull
    @Column(name = "cost", nullable = false)
    private Integer cost;

    /**
     * 售价（单位：分）
     */
    @NotNull
    @Column(name = "price", nullable = false)
    private Integer price;

    /**
     * 原价（单位：分）
     */
    @Column(name = "original_price")
    private Integer originalPrice;

    /**
     * 销售数量
     */
    @Column(name = "sales_quantity")
    private Integer salesQuantity;

    /**
     * 销售金额（单位：分）= 售价 * 销售数量
     */
    @Column(name = "sales_amount")
    private Integer salesAmount;

    /**
     * 退款单数
     */
    @Column(name = "refund_orders")
    private Integer refundOrders;

    /**
     * 退款金额
     */
    @Column(name = "refund_amount")
    private Integer refundAmount;

    /**
     * 毛利（单位：分）= (售价 - 成本) * 销售数量
     */
    @Column(name = "gross_profit")
    private Integer grossProfit;

    /**
     * 初始库存
     */
    @Column(name = "init_quantity")
    private Integer initQuantity;

    /**
     * 剩余库存
     */
    @Column(name = "remainder")
    private Integer remainder;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDay() {
        return day;
    }

    public FoodSalesReport day(Instant day) {
        this.day = day;
        return this;
    }

    public void setDay(Instant day) {
        this.day = day;
    }

    public String getShopSN() {
        return shopSN;
    }

    public FoodSalesReport shopSN(String shopSN) {
        this.shopSN = shopSN;
        return this;
    }

    public void setShopSN(String shopSN) {
        this.shopSN = shopSN;
    }

    public String getShopName() {
        return shopName;
    }

    public FoodSalesReport shopName(String shopName) {
        this.shopName = shopName;
        return this;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getCategorySN() {
        return categorySN;
    }

    public FoodSalesReport categorySN(String categorySN) {
        this.categorySN = categorySN;
        return this;
    }

    public void setCategorySN(String categorySN) {
        this.categorySN = categorySN;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public FoodSalesReport categoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getFoodSN() {
        return foodSN;
    }

    public FoodSalesReport foodSN(String foodSN) {
        this.foodSN = foodSN;
        return this;
    }

    public void setFoodSN(String foodSN) {
        this.foodSN = foodSN;
    }

    public String getFoodName() {
        return foodName;
    }

    public FoodSalesReport foodName(String foodName) {
        this.foodName = foodName;
        return this;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Integer getCost() {
        return cost;
    }

    public FoodSalesReport cost(Integer cost) {
        this.cost = cost;
        return this;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getPrice() {
        return price;
    }

    public FoodSalesReport price(Integer price) {
        this.price = price;
        return this;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getOriginalPrice() {
        return originalPrice;
    }

    public FoodSalesReport originalPrice(Integer originalPrice) {
        this.originalPrice = originalPrice;
        return this;
    }

    public void setOriginalPrice(Integer originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Integer getSalesQuantity() {
        return salesQuantity;
    }

    public FoodSalesReport salesQuantity(Integer salesQuantity) {
        this.salesQuantity = salesQuantity;
        return this;
    }

    public void setSalesQuantity(Integer salesQuantity) {
        this.salesQuantity = salesQuantity;
    }

    public Integer getSalesAmount() {
        return salesAmount;
    }

    public FoodSalesReport salesAmount(Integer salesAmount) {
        this.salesAmount = salesAmount;
        return this;
    }

    public void setSalesAmount(Integer salesAmount) {
        this.salesAmount = salesAmount;
    }

    public Integer getRefundOrders() {
        return refundOrders;
    }

    public FoodSalesReport refundOrders(Integer refundOrders) {
        this.refundOrders = refundOrders;
        return this;
    }

    public void setRefundOrders(Integer refundOrders) {
        this.refundOrders = refundOrders;
    }

    public Integer getRefundAmount() {
        return refundAmount;
    }

    public FoodSalesReport refundAmount(Integer refundAmount) {
        this.refundAmount = refundAmount;
        return this;
    }

    public void setRefundAmount(Integer refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Integer getGrossProfit() {
        return grossProfit;
    }

    public FoodSalesReport grossProfit(Integer grossProfit) {
        this.grossProfit = grossProfit;
        return this;
    }

    public void setGrossProfit(Integer grossProfit) {
        this.grossProfit = grossProfit;
    }

    public Integer getInitQuantity() {
        return initQuantity;
    }

    public FoodSalesReport initQuantity(Integer initQuantity) {
        this.initQuantity = initQuantity;
        return this;
    }

    public void setInitQuantity(Integer initQuantity) {
        this.initQuantity = initQuantity;
    }

    public Integer getRemainder() {
        return remainder;
    }

    public FoodSalesReport remainder(Integer remainder) {
        this.remainder = remainder;
        return this;
    }

    public void setRemainder(Integer remainder) {
        this.remainder = remainder;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FoodSalesReport)) {
            return false;
        }
        return id != null && id.equals(((FoodSalesReport) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "FoodSalesReport{" +
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
