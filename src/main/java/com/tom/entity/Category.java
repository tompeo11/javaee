package com.tom.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="category")
@NamedQueries({
        @NamedQuery(name = "Category.HQL.getByName", query = "SELECT u FROM Category u WHERE :name = u.name"),
})
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Category {
    @Column(name="category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer categoryId;

    @Column(name="name")
    private String name;

    public Category(String name) {
        this.name = name;
    }
}
