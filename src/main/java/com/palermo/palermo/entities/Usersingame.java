/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Los_e
 */
@Entity
@Table(name = "usersingame")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usersingame.findAll", query = "SELECT u FROM Usersingame u")
    , @NamedQuery(name = "Usersingame.findByUserid", query = "SELECT u FROM Usersingame u WHERE u.usersingamePK.userid = :userid")
    , @NamedQuery(name = "Usersingame.findByGameid", query = "SELECT u FROM Usersingame u WHERE u.usersingamePK.gameid = :gameid")
    , @NamedQuery(name = "Usersingame.findByWon", query = "SELECT u FROM Usersingame u WHERE u.won = :won")})
public class Usersingame implements Serializable {

    @Column(name = "won")
    private Integer won;
    @Size(max = 45)
    @Column(name = "ingamerole")
    private String ingamerole;

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsersingamePK usersingamePK;
    @JoinColumn(name = "gameid", referencedColumnName = "gameid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Game game;
    @JoinColumn(name = "userid", referencedColumnName = "userid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public Usersingame() {
    }

    public Usersingame(UsersingamePK usersingamePK) {
        this.usersingamePK = usersingamePK;
    }

    public Usersingame(int userid, String gameid) {
        this.usersingamePK = new UsersingamePK(userid, gameid);
    }

    public UsersingamePK getUsersingamePK() {
        return usersingamePK;
    }

    public void setUsersingamePK(UsersingamePK usersingamePK) {
        this.usersingamePK = usersingamePK;
    }


    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
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
        hash += (usersingamePK != null ? usersingamePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usersingame)) {
            return false;
        }
        Usersingame other = (Usersingame) object;
        if ((this.usersingamePK == null && other.usersingamePK != null) || (this.usersingamePK != null && !this.usersingamePK.equals(other.usersingamePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.palermo.palermo.entities.Usersingame[ usersingamePK=" + usersingamePK + " ]";
    }

    public Integer getWon() {
        return won;
    }

    public void setWon(Integer won) {
        this.won = won;
    }

    public String getIngamerole() {
        return ingamerole;
    }

    public void setIngamerole(String ingamerole) {
        this.ingamerole = ingamerole;
    }
    
}
