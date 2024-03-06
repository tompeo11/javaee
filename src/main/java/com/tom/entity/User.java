package com.tom.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="users")
@NamedQueries({
        @NamedQuery(name = "User.HQL.getAll", query = "SELECT u FROM User u ORDER BY u.fullName"),
        @NamedQuery(name = "User.HQL.countAll", query = "SELECT count(*) FROM User"),
        @NamedQuery(name = "User.HQL.getByEmail", query = "SELECT u FROM User u WHERE :email=u.email")
})
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class User {
    @Column(name="user_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name="email")
    private String email;

    @Column(name="full_name")
    private String fullName;

    @Column(name="password")
    private String passWord;

    public User(String email, String fullName, String passWord) {
        this.email = email;
        this.fullName = fullName;
        this.passWord = passWord;
    }
}
