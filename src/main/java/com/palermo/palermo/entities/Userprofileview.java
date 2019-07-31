/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Los_e
 */
@Entity
@Table(name = "userprofileview")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userprofileview.findAll", query = "SELECT u FROM Userprofileview u")
    , @NamedQuery(name = "Userprofileview.findByUserid", query = "SELECT u FROM Userprofileview u WHERE u.userid = :userid")
    , @NamedQuery(name = "Userprofileview.findByUsername", query = "SELECT u FROM Userprofileview u WHERE u.username = :username")
    , @NamedQuery(name = "Userprofileview.findByFirstname", query = "SELECT u FROM Userprofileview u WHERE u.firstname = :firstname")
    , @NamedQuery(name = "Userprofileview.findByLastname", query = "SELECT u FROM Userprofileview u WHERE u.lastname = :lastname")})
public class Userprofileview implements Serializable {
    
    @Id
    @Basic(optional = false)
    @NotNull()
    @Column(name = "userid")
    private int userid;
    @Basic(optional = false)
    @NotNull()
    @Size(min = 1, max = 45)
    @Column(name = "username")
    private String username;
    @Size(max = 100)
    @Column(name = "firstname")
    private String firstname;
    @Size(max = 100)
    @Column(name = "lastname")
    private String lastname;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull()
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull()
    @Size(min = 1, max = 45)
    @Column(name = "role")
    private String role;
    @Column(name = "active")
    private Integer active;
    @Lob
    @Size(max = 16777215)
    @Column(name = "profileimagebase64")
    private String profileimagebase64;
    private static final long serialVersionUID = 1L;

    public Userprofileview() {
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getProfileimagebase64() {
        return profileimagebase64;
    }

    public void setProfileimagebase64(String profileimagebase64) {
        this.profileimagebase64 = profileimagebase64;
    }
    
}
