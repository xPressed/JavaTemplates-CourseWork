package ru.xpressed.javatemplatescoursework.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Entity
@Table(name = "user")
@Getter
@Setter
public class Customer implements UserDetails {
    @Id
    @NotEmpty(message = "Username can not be empty!")
    private String username;

    @NotEmpty(message = "Password can not be empty!")
    private String password;

    @Column(updatable = false, insertable = false)
    @NotEmpty(message = "Repeated password can not be empty!")
    private String repeated;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
