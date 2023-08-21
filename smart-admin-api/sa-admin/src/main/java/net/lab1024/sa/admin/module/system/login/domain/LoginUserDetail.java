package net.lab1024.sa.admin.module.system.login.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.lab1024.sa.admin.module.system.menu.domain.vo.MenuVO;
import net.lab1024.sa.common.common.domain.RequestUser;
import net.lab1024.sa.common.common.enumeration.UserTypeEnum;
import net.lab1024.sa.common.common.swagger.ApiModelPropertyEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * User Login
 */
@Data
public class LoginUserDetail implements UserDetails, RequestUser {

    @ApiModelProperty("token")
    private String token;

    @ApiModelProperty("user Id")
    private Long userId;

    @ApiModelPropertyEnum(UserTypeEnum.class)
    private UserTypeEnum userType;

    @ApiModelProperty("Account")
    private String loginName;

    @ApiModelProperty("User name")
    private String actualName;

    @ApiModelProperty("Phone number")
    private String phone;


    @ApiModelProperty("Admin flag")
    private Boolean administratorFlag;

    @ApiModelProperty("Menu list")
    private List<MenuVO> menuList;

    @JsonIgnore
    private String loginPassword;

    @ApiModelProperty("Last login id")
    private String lastLoginIp;

    @ApiModelProperty("Last login user-agent")
    private String lastLoginUserAgent;

    @ApiModelProperty("Last login time")
    private String lastLoginTime;

    @ApiModelProperty("ip")
    private String ip;

    @ApiModelProperty("user-agent")
    private String userAgent;

    /**
     * security authorities
     */
    private Set<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return this.loginPassword;
    }

    @Override
    public String getUsername() {
        return this.getLoginName();
    }

    /**
     * Check if the account is expired
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Check if the account is locked
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Check if the credential is expired
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public String getUserName() {
        return actualName;
    }

    @Override
    public UserTypeEnum getUserType() {
        return userType;
    }

    @Override
    public String getIp() {
        return this.ip;
    }

    @Override
    public String getUserAgent() {
        return this.userAgent;
    }
}
