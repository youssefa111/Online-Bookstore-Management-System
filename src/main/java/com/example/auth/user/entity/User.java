package com.example.auth.user.entity;


import com.example.auth.role.entity.Role;
import com.example.auth.token.entity.Token;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@DynamicInsert
@DynamicUpdate
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name",length = 30, nullable = false)
    private String firstName;
    @Column(name = "last_name",length = 30 , nullable = false)
    private String lastName;
    @Column(length = 50 , nullable = false)
    private String address;
    @Column(length = 11 , nullable = false)
    private String phone;
    @Column(length = 30, nullable = false)
    private String username;
    @Column(name = "civil_id",length = 12, nullable = false)
    private String civilId;
    @Email
    @Column(length = 50, nullable = false)
    private String email;
    @Column( nullable = false)
    private String password;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
    @OneToMany(mappedBy = "user")
    private List<Token> tokens;
    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(role != null)
        return List.of(new SimpleGrantedAuthority(role.getRoleType().name()));
        else
            return null;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
