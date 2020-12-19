package com.codetest.springbootapi.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Product {
    @Id
    @Column(name = "product_id")
    private Long productId;

    private String productName;

    private String description;

    private BigDecimal price;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Cascade({CascadeType.SAVE_UPDATE})
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id", columnDefinition = "bigint(20)"),
            inverseJoinColumns = @JoinColumn(name = "category_id", columnDefinition = "bigint(20)"))
    private Set<Category> categories = new HashSet<>();;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private Set<ProductOrder> productOrders = new HashSet<>();
}
