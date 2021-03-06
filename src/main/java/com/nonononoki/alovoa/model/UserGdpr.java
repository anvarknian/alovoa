package com.nonononoki.alovoa.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Convert;

import com.nonononoki.alovoa.component.TextEncryptorConverter;
import com.nonononoki.alovoa.entity.Gender;
import com.nonononoki.alovoa.entity.Location;
import com.nonononoki.alovoa.entity.Message;
import com.nonononoki.alovoa.entity.User;
import com.nonononoki.alovoa.entity.UserDates;
import com.nonononoki.alovoa.entity.UserDonation;
import com.nonononoki.alovoa.entity.UserImage;
import com.nonononoki.alovoa.entity.UserIntention;
import com.nonononoki.alovoa.entity.UserInterest;
import com.nonononoki.alovoa.entity.UserWebPush;

import lombok.Data;

@Data
public class UserGdpr {
	 
	private boolean confirmed;
	 
	private boolean disabled;

	private int preferedMinAge;

	private int preferedMaxAge;
	
	private double totalDonations;
	
	private int theme;
	
	@Convert(converter = TextEncryptorConverter.class)
	private String email;

	private String firstName;

	private String description;

	private String profilePicture;
	
	private String audio;
	
	/*
	 * Custom classes
	 */

	private Location lastLocation;

	private Gender gender;
	
	private UserIntention intention;
	
	private List<UserInterest> interests;

	private Set<Gender> preferedGenders;

	private List<UserImage> images;
	 
	private List<UserDonation> donations;
	 
//	private List<UserLike> likes;
	 
	private List<Message> messageSent;
//	 
//	private List<UserNotification> notifications;
//	 
//	private List<UserHide> hiddenUsers;
//	
//	private List<UserBlock> blockedUsers;
//	
//	private List<UserReport> reported;
	
	private List<UserWebPush> webPush;

	private UserDates dates;
	
	public static UserGdpr userToUserGdpr(User user) {
		UserGdpr u = new UserGdpr();
		u.setConfirmed(user.isConfirmed());
		u.setDisabled(user.isDisabled());
		u.setPreferedMinAge(user.getPreferedMinAge());
		u.setPreferedMaxAge(user.getPreferedMaxAge());
		u.setTotalDonations(user.getTotalDonations());
		
		u.setTheme(user.getTheme());
		
		u.setEmail(user.getEmail());
		u.setFirstName(user.getFirstName());
		u.setDescription(user.getDescription());
		u.setAudio(user.getAudio());
		
		u.setProfilePicture(user.getProfilePicture());
		
		u.setLastLocation(user.getLastLocation());
		u.setGender(user.getGender());
		u.setIntention(user.getIntention());
		u.setInterests(user.getInterests());
		u.setPreferedGenders(user.getPreferedGenders());
		u.setImages(user.getImages());
		u.setDonations(user.getDonations());
		u.setMessageSent(user.getMessageSent());
		u.setWebPush(user.getWebPush());
		u.setDates(user.getDates());

		return u;
	}
}
