package com.shopme.shopme.enity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authentication_token")
public class AuthenticationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @Column(name = "created_date")
    private Date createdDate;

    @OneToOne(targetEntity = AppUser.class,
            fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name = "user_id")
    private AppUser appUser;

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public AuthenticationToken(AppUser appUser)
    {
        this.appUser = appUser;
        this.createdDate = new Date();
        this.token = UUID.randomUUID().toString();
    }
}
