package me.zhulin.onlineshopping.entity;

import lombok.Data;
import me.zhulin.onlineshopping.enums.ProductStatusEnum;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created By Zhu Lin on 3/14/2018.
 */
@Entity
@Data

public class ProductInOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderMain orderMain;

    @NotEmpty
    private String productId;

    /** 名字. */
    @NotEmpty
    private String productName;

    /** 描述. */
    @NotNull
    private String productDescription;

    /** 小图. */
    private String productIcon;

    /** 类目编号. */
    @NotNull
    private Integer categoryType;

    /** 单价. */
    @NotNull
    private BigDecimal productPrice;

    @Min(1)
    private Integer productQuantity;

    public ProductInOrder() {
    }

    public ProductInOrder(ProductInfo productInfo, Integer quantity) {
        this.productId = productInfo.getProductId();
        this.productName = productInfo.getProductName();
        this.productDescription = productInfo.getProductDescription();
        this.productIcon = productInfo.getProductIcon();
        this.categoryType = productInfo.getCategoryType();
        this.productPrice = productInfo.getProductPrice();
        this.productQuantity = quantity;
    }

    // fix bi-direction toString() recursion problem
    @Override
    public String toString() {
        return "ProductInOrder{" +
                "id=" + id +
                ", productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productIcon='" + productIcon + '\'' +
                ", categoryType=" + categoryType +
                ", productPrice=" + productPrice +
                ", productQuantity=" + productQuantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProductInOrder that = (ProductInOrder) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(productId, that.productId) &&
                Objects.equals(productName, that.productName) &&
                Objects.equals(productDescription, that.productDescription) &&
                Objects.equals(productIcon, that.productIcon) &&
                Objects.equals(categoryType, that.categoryType) &&
                Objects.equals(productPrice, that.productPrice) &&
                Objects.equals(productQuantity, that.productQuantity);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), id, productId, productName, productDescription, productIcon, categoryType, productPrice, productQuantity);
    }
}
