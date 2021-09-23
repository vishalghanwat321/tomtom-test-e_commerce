package com.org.tomtom.e_commerce.service.model.user;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.org.tomtom.e_commerce.service.model.cart.Cart;
import com.org.tomtom.e_commerce.service.model.order.Order;
import com.org.tomtom.e_commerce.util.unique_sequence_generator.AbstractRandomLongIdEntity;

@Entity
@Table(name = "user")
@Cacheable(false)
public class User extends AbstractRandomLongIdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
	@Column(name = "title", nullable = false, length = 10)
	private Title title;

	@Column(name = "first_name", nullable = false, length = 45)
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 45)
	private String lastName;

	@Enumerated(EnumType.STRING)
	@Column(name = "gender", nullable = false, length = 10)
	private UserGender gender;

	@Enumerated(EnumType.STRING)
	@Column(name = "user_type", nullable = false, length = 25)
	private UserType userType;

	@Enumerated(EnumType.STRING)
	@Column(name = "user_status", nullable = false, length = 25)
	private UserStatus userStatus;

	@Column(name = "email", unique = true, nullable = false, length = 50)
	private String email;

	@Column(name = "dob", nullable = false, columnDefinition = "DATE")
	private LocalDate dob;

	@Column(name = "contact_number", unique = true, nullable = false, columnDefinition = "BIGINT(10)")
	private Long contactNumber;

	@Column(name = "passwrd", nullable = false)
	private String passwrd;

	@Column(name = "passwrd_expiry_date_time", columnDefinition = "TIMESTAMP")
	private LocalDateTime passwrdExpiryDateTime;

	@Column(name = "created_date_time", columnDefinition = "TIMESTAMP", updatable = false)
	private LocalDateTime createdDateTime;

	@Column(name = "created_by_id", updatable = false, length = 20)
	private Long createdBy;

	@Column(name = "modified_date_time", insertable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime modifiedDateTime;

	@Column(name = "modified_by_id", insertable = false, length = 20)
	private Long modifiedBy;

	@Column(name = "contact_code", nullable = false, columnDefinition = "BIGINT")
	private Long countryCode;

	@ElementCollection(fetch = FetchType.EAGER, targetClass = Address.class)
	@CollectionTable(name = "user_address", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id") }, uniqueConstraints = @UniqueConstraint(columnNames = {
					"user_id" }))
	@Column(name = "address_id", nullable = false)
	private Set<Address> address;

	@ElementCollection(fetch = FetchType.LAZY, targetClass = Order.class)
	@CollectionTable(name = "user_order_item_details", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), uniqueConstraints = @UniqueConstraint(columnNames = {
			"user_id" }))
	@Column(name = "order_item_id", nullable = false)
	private Set<Order> order;

	@OneToOne
	@JoinColumn(name = "cart_id", unique = true)
	private Cart cart;

	@PrePersist
	protected void onPrePersist() {
		super.onPrePersist();
		this.userStatus = UserStatus.ACTIVE;
		this.createdDateTime = LocalDateTime.now().atOffset(ZoneOffset.UTC).toLocalDateTime();
		this.passwrdExpiryDateTime = LocalDateTime.now().atOffset(ZoneOffset.UTC).plusDays(60).toLocalDateTime();
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public LocalDateTime getModifiedDateTime() {
		return modifiedDateTime;
	}

	public void setModifiedDateTime(LocalDateTime modifiedDateTime) {
		this.modifiedDateTime = modifiedDateTime;
	}

	public Set<Address> getAddress() {
		return address;
	}

	public void setAddress(Set<Address> address) {
		this.address = address;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public UserGender getGender() {
		return gender;
	}

	public void setGender(UserGender gender) {
		this.gender = gender;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public Long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getPasswrd() {
		return passwrd;
	}

	public void setPasswrd(String passwrd) {
		this.passwrd = passwrd;
	}

	public LocalDateTime getPasswrdExpiryDateTime() {
		return passwrdExpiryDateTime;
	}

	public void setPasswrdExpiryDateTime(LocalDateTime passwrdExpiryDateTime) {
		this.passwrdExpiryDateTime = passwrdExpiryDateTime;
	}

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Long getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(Long countryCode) {
		this.countryCode = countryCode;
	}

	public Set<Order> getOrder() {
		return order;
	}

	public void setOrder(Set<Order> order) {
		this.order = order;
	}

}
