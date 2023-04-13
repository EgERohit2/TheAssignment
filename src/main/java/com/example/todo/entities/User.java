package com.example.todo.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString
@Where(clause = "is_active")
@SQLDelete(sql = "UPDATE User set is_active=false where id=?")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "user_name")
	@NotNull(message = "username cannot be null")
	private String username;
	@Column(name = "email")
	@NotNull(message = "email cannot be null")
	private String email;
	@Column(name = "password")
	@NotEmpty(message = "password cannot be null")
	private String password;
	@Column(name = "mobile_number")
	@NotEmpty(message = "mobile number cannot be empty")
	private String mob;
	private Boolean isActive = true;
	@CreationTimestamp
	private Date createdAt;
	@UpdateTimestamp
	private Date updatedAt;
//	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<UserTask> usertask;
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name = "User_Role", joinColumns = @JoinColumn(name = "uid"), inverseJoinColumns = @JoinColumn(name = "rid"))
	private List<Role> role = new ArrayList<>();

}
