package com.nonononoki.alovoa.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.nonononoki.alovoa.component.TextEncryptorConverter;
import com.nonononoki.alovoa.config.SecurityConfig;

import lombok.Data;

@Component
@Data
@Entity
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, unique = true)
	@Convert(converter = TextEncryptorConverter.class)
	private String email;
	private String password;

	// private String oauthProvider;

	@Convert(converter = TextEncryptorConverter.class)
	private String firstName;

	private String description;

	@Column(columnDefinition = "mediumtext")
	@Convert(converter = TextEncryptorConverter.class)
	private String audio;

	private int preferedMinAge;

	private int preferedMaxAge;

	// private int age;

	@Column(columnDefinition = "mediumtext")
	private String profilePicture;

	private double totalDonations;

	private int theme;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn
	private Location lastLocation;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn
	private UserRegisterToken registerToken;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn
	private UserPasswordToken passwordToken;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn
	private UserDeleteToken deleteToken;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn
	private UserDates dates;

	@ManyToOne
	private Gender gender;

	@ManyToMany
	@JoinTable(name = "user2genders")
	private Set<Gender> preferedGenders;

	@ManyToOne
	private UserIntention intention;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
	private List<UserInterest> interests;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
	private List<UserImage> images;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
	private List<UserDonation> donations;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "userFrom")
	private List<UserLike> likes;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "userTo")
	private List<UserLike> likedBy;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "userFrom")
	private List<Conversation> conversations;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "userTo")
	private List<Conversation> conversationsBy;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "userFrom")
	private List<Message> messageSent;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "userTo")
	private List<Message> messageReceived;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "userFrom")
	private List<UserNotification> notificationsFrom;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "userTo")
	private List<UserNotification> notifications;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "userFrom")
	private List<UserHide> hiddenUsers;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "userTo")
	private List<UserHide> hiddenByUsers;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "userFrom")
	private List<UserBlock> blockedUsers;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "userTo")
	private List<UserBlock> blockedByUsers;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "userFrom")
	private List<UserReport> reported;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "userTo")
	private List<UserReport> reportedByUsers;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
	private List<UserWebPush> webPush;

	private boolean admin;

	private boolean confirmed;

	private boolean disabled;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		final String ROLE_PREFIX = "ROLE_";
		String role;
		if (admin) {
			role = ROLE_PREFIX + SecurityConfig.ROLE_ADMIN;
		} else {
			role = ROLE_PREFIX + SecurityConfig.ROLE_USER;
		}
		authorities.add(new SimpleGrantedAuthority(role));

		return authorities;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !disabled;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !disabled;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !disabled;
	}

	@Override
	public boolean isEnabled() {
		return !disabled;
	}
}
