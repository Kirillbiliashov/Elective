package com.example.elective.models;



import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import javax.swing.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Model representation of account table
 * @author Kirill Biliashov
 */

@Entity
@Table(name = "account")
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Size(min = 8, max = 50, message = "Ussername should be 8 to 50 characters long")
  private String username;

  @Email(message = "Please specify valid email")
  @Size(max = 50, message = "Email should not be longer than 50 characters")
  private String email;

  @Size(min = 8, message = "Password should be at least 8 characters long")
  private String password;

  @Size(min = 2, max = 20, message = "first name should be 2 to 20 characters long")
  @Column(name = "first_name")
  private String firstName;

  @Size(min = 2, max = 20, message = "last name should be 2 to 20 characters long")
  @Column(name = "last_name")
  private String lastName;

  @Enumerated(EnumType.STRING)
  private Role role;

  @OneToOne(mappedBy = "student")
  private Blocklist block;

  public int getId() {
    return id;
  }

  public Account setId(int id) {
    this.id = id;
    return this;
  }

  public String getUsername() {
    return username;
  }

  public Account setUsername(String username) {
    this.username = username;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public Account setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public Account setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public Account setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public Account setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public String getFullName() {
    return  this.firstName + " " + this.lastName;
  }

  public Blocklist getBlock() {
    return block;
  }

  public Account setBlock(Blocklist block) {
    this.block = block;
    return this;
  }

  public Role getRole() {
    return role;
  }

  public Account setRole(Role role) {
    this.role = role;
    return this;
  }

}
