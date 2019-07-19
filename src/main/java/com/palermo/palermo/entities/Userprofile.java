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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author djbil
 */
@Entity
@Table(name = "userprofile")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userprofile.findAll", query = "SELECT u FROM Userprofile u")
    , @NamedQuery(name = "Userprofile.findByUserprofileid", query = "SELECT u FROM Userprofile u WHERE u.userprofileid = :userprofileid")
    , @NamedQuery(name = "Userprofile.findByFirstname", query = "SELECT u FROM Userprofile u WHERE u.firstname = :firstname")
    , @NamedQuery(name = "Userprofile.findByLastname", query = "SELECT u FROM Userprofile u WHERE u.lastname = :lastname")
    , @NamedQuery(name = "Userprofile.findByDateofbirthday", query = "SELECT u FROM Userprofile u WHERE u.dateofbirthday = :dateofbirthday")
    , @NamedQuery(name = "Userprofile.findByProfileimage", query = "SELECT u FROM Userprofile u WHERE u.profileimage = :profileimage")
    , @NamedQuery(name = "Userprofile.findByNumberofwins", query = "SELECT u FROM Userprofile u WHERE u.numberofwins = :numberofwins")})
public class Userprofile implements Serializable {

    @Size(max = 100)
    @Column(name = "firstname")
    private String firstname;
    @Size(max = 100)
    @Column(name = "lastname")
    private String lastname;
    @Size(max = 100)
    @Column(name = "dateofbirthday")
    private String dateofbirthday;
    @Lob()
    @Column(name = "profileimage")
    private byte[] profileimage;
    @Lob
    @Size(max = 16777215)
    @Column(name = "profileimagebase64")
    private String profileimagebase64;
    @Size(max = 100)
    @Column(name = "profileimageoriginalfilename")
    private String profileimageoriginalfilename;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "userprofileid")
    private Integer userprofileid;
    @Column(name = "numberofwins")
    private Integer numberofwins;
    @JoinColumn(name = "userprofileid", referencedColumnName = "userid", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private User user;

    public Userprofile() {
    }

    public Userprofile(Integer userprofileid) {
        this.userprofileid = userprofileid;
    }

    public Integer getUserprofileid() {
        return userprofileid;
    }

    public void setUserprofileid(Integer userprofileid) {
        this.userprofileid = userprofileid;
    }


    public Integer getNumberofwins() {
        return numberofwins;
    }

    public void setNumberofwins(Integer numberofwins) {
        this.numberofwins = numberofwins;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userprofileid != null ? userprofileid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userprofile)) {
            return false;
        }
        Userprofile other = (Userprofile) object;
        if ((this.userprofileid == null && other.userprofileid != null) || (this.userprofileid != null && !this.userprofileid.equals(other.userprofileid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.palermo.palermo.entities.Userprofile[ userprofileid=" + userprofileid + " ]";
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

    public String getDateofbirthday() {
        return dateofbirthday;
    }

    public void setDateofbirthday(String dateofbirthday) {
        this.dateofbirthday = dateofbirthday;
    }

    public byte[] getProfileimage() {
        return profileimage;
    }

    public void setProfileimage(byte[] profileimage) {
        this.profileimage = profileimage;
    }

    public String getProfileimagebase64() {
        return profileimagebase64;
    }

    public void setProfileimagebase64(String profileimagebase64) {
        this.profileimagebase64 = profileimagebase64;
    }

    public String getProfileimageoriginalfilename() {
        return profileimageoriginalfilename;
    }

    public void setProfileimageoriginalfilename(String profileimageoriginalfilename) {
        this.profileimageoriginalfilename = profileimageoriginalfilename;
    }
    
}
