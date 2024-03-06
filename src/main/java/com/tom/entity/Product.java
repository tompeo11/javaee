package com.tom.entity;

import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="product")
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

    @Column(name="author")
    private String author;

    @Column(name="description")
    private String description;

    @Column(name="isbn")
    private String isbn;

    @Column(name="image")
    private byte[] image;

    @Column(name="price")
    private BigDecimal price;

    @Column(name="publish_date")
    private Date publishDate;

    @UpdateTimestamp
    @Column(name="last_update_time")
    private Date lastUpdateTime;

    @ManyToOne()
    @JoinColumn(name="category_id")
    private Category category_id;

    public Product(String name, String author, String description, String isbn, byte[] image, BigDecimal price, Date publishDate, Date lastUpdateTime, Category category_id) {
        this.name = name;
        this.author = author;
        this.description = description;
        this.isbn = isbn;
        this.image = image;
        this.price = price;
        this.publishDate = publishDate;
        this.lastUpdateTime = lastUpdateTime;
        this.category_id = category_id;
    }
}
