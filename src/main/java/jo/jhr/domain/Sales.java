package jo.jhr.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.Instant;

/**
 * 菜品销量\n@author Jo
 */
@Entity
@Table(name = "sales")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Sales implements Serializable {

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
     * 菜品原价（单位：分）
     */
    @Min(value = 0)
    @Max(value = 99999)
    @Column(name = "food_original_price")
    private Integer foodOriginalPrice;

    /**
     * 初始库存
     */
    @NotNull
    @Min(value = 0)
    @Max(value = 99999)
    @Column(name = "init_quantity", nullable = false)
    private Integer initQuantity;

    /**
     * 菜品销售数量
     */
    @NotNull
    @Min(value = 0)
    @Max(value = 99999)
    @Column(name = "sales_quantity", nullable = false)
    private Integer salesQuantity;

    /**
     * 最后统计时间
     */
    @NotNull
    @Column(name = "last_modified_date", nullable = false)
    private Instant lastModifiedDate;

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

    public Sales day(Instant day) {
        this.day = day;
        return this;
    }

    public void setDay(Instant day) {
        this.day = day;
    }

    public String getShopSN() {
        return shopSN;
    }

    public Sales shopSN(String shopSN) {
        this.shopSN = shopSN;
        return this;
    }

    public void setShopSN(String shopSN) {
        this.shopSN = shopSN;
    }

    public String getShopName() {
        return shopName;
    }

    public Sales shopName(String shopName) {
        this.shopName = shopName;
        return this;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getCategorySN() {
        return categorySN;
    }

    public Sales categorySN(String categorySN) {
        this.categorySN = categorySN;
        return this;
    }

    public void setCategorySN(String categorySN) {
        this.categorySN = categorySN;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Sales categoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getFoodSN() {
        return foodSN;
    }

    public Sales foodSN(String foodSN) {
        this.foodSN = foodSN;
        return this;
    }

    public void setFoodSN(String foodSN) {
        this.foodSN = foodSN;
    }

    public String getFoodName() {
        return foodName;
    }

    public Sales foodName(String foodName) {
        this.foodName = foodName;
        return this;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Integer getFoodOriginalPrice() {
        return foodOriginalPrice;
    }

    public Sales foodOriginalPrice(Integer foodOriginalPrice) {
        this.foodOriginalPrice = foodOriginalPrice;
        return this;
    }

    public void setFoodOriginalPrice(Integer foodOriginalPrice) {
        this.foodOriginalPrice = foodOriginalPrice;
    }

    public Integer getInitQuantity() {
        return initQuantity;
    }

    public Sales initQuantity(Integer initQuantity) {
        this.initQuantity = initQuantity;
        return this;
    }

    public void setInitQuantity(Integer initQuantity) {
        this.initQuantity = initQuantity;
    }

    public Integer getSalesQuantity() {
        return salesQuantity;
    }

    public Sales salesQuantity(Integer salesQuantity) {
        this.salesQuantity = salesQuantity;
        return this;
    }

    public void setSalesQuantity(Integer salesQuantity) {
        this.salesQuantity = salesQuantity;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public Sales lastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Sales)) {
            return false;
        }
        return id != null && id.equals(((Sales) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Sales{" +
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
