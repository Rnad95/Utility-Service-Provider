package com.example.utilityserviceprovider.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class Category {

    @Setter(value= AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id ;

    public Category(String title) {
        this.title = title;
    }

    @Column(unique = true)
    String title;

    //one category can have many users
    @OneToMany (mappedBy = "category")
    List<MyUser> usersList;

    //category and sub category
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE)
    private List<Category> children = new ArrayList<Category>();

    @Override
    public String toString() {
        return "Category{" +
                ", title='" + title + '\'' +
                ", children=" + children +
                '}';
    }
}
