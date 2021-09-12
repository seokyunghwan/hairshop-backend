package com.personalproject.hairshop.domain.dto;

import java.util.Collection;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.personalproject.hairshop.domain.entity.Shop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements UserDetails{

	private Long id;
	
	@NotNull
	@Size(min=3, max=50)
	private String userId;
	
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	@JsonIgnore
	@NotNull
	@Size(min=3, max=100)
	private String password;
	
	@NotNull
	@Size(min=3, max=50)
	private String name;
	
	@NotNull
	@Size(min=3, max=100)
	private String email;
	
	@NotNull
	@Size(min=3, max=100)
	private String phoneNum;
	
	private Collection<? extends GrantedAuthority> authority;
	

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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authority;
	}

	@Override
	public String getPassword() {
		
		return password;
	}

	@Override
	public String getUsername() {
		
		return userId;
	}

	public String getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNum() {
		return phoneNum;
	}
	public Collection<? extends GrantedAuthority> getAuthority() {
		return authority;
	}

	public void setAuthority(Collection<? extends GrantedAuthority> authority) {
		this.authority = authority;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	@Builder
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class SignupDto implements UserDetails{
		
		@NotNull
		private Boolean manager;
		
		@NotNull
		@Size(min=3, max=50)
		private String userId;
		
		@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
		@JsonIgnore
		@NotNull
		@Size(min=3, max=100)
		private String password;
		
		@NotNull
		@Size(min=3, max=50)
		private String name;
		
		@NotNull
		@Size(min=3, max=100)
		private String email;
		
		@NotNull
		@Size(min=3, max=100)
		private String phoneNum;
		
		private Collection<? extends GrantedAuthority> authority;

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

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			
			return authority;
		}

		@Override
		public String getPassword() {
			
			return password;
		}

		@Override
		public String getUsername() {
			
			return userId;
		}

		public String getUserId() {
			return userId;
		}

		public String getName() {
			return name;
		}

		public String getEmail() {
			return email;
		}

		public String getPhoneNum() {
			return phoneNum;
		}
		public Collection<? extends GrantedAuthority> getAuthority() {
			return authority;
		}

		public void setAuthority(Collection<? extends GrantedAuthority> authority) {
			this.authority = authority;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public void setPhoneNum(String phoneNum) {
			this.phoneNum = phoneNum;
		}
	}
}
