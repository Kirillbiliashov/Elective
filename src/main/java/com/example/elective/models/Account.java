package com.example.elective.models;



import javax.persistence.*;
import javax.swing.*;
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
  private String username;
  private String email;
  private String password;
  @Column(name = "first_name")
  private String firstName;
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
