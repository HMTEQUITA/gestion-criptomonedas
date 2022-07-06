package co.com.bancolombia.jpa.user.entity;

import co.com.bancolombia.jpa.customer.entity.CustomerEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users", schema = "management")
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false )
    private Long id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="users_roles", schema = "management",  joinColumns= @JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="role_id"))
    private Set<RoleEntity> roles;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="customer_id", referencedColumnName = "id")
    private CustomerEntity customer;
}