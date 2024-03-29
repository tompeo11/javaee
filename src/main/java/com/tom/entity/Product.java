package com.tom.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Base64;

@Entity
@Table(name="product")
@NamedQueries({
        @NamedQuery(name = "Product.HQL.getByName", query = "SELECT u FROM Product u WHERE :name = u.name"),
        @NamedQuery(name = "Product.HQL.getByCategory",
                query = "SELECT u FROM Product u " +
        "               JOIN Category c On u.category.categoryId = c.categoryId " +
        "               AND c.categoryId = :categoryId"),
})
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Product {
    @Id
    @Column(name="product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="image")
    private byte[] image;

    @Column(name="price")
    private BigDecimal price;

    @ManyToOne()
    @JoinColumn(name="category_id")
    private Category category;

    @Transient
    private String base64Image;

    public Product(String name,  String description, byte[] image, BigDecimal price, Category category_id) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.category = category_id;
    }

    public String getBase64Image() {
        return Base64.getEncoder().encodeToString(this.image);
    }
}
