package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {
    @Id @GeneratedValue
    @Column(name = "PARENT_ID")
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child;
}
