/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Los_e
 */
@Entity
@Table(name = "game")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Game.findAll", query = "SELECT g FROM Game g")
    , @NamedQuery(name = "Game.findByGameid", query = "SELECT g FROM Game g WHERE g.gameid = :gameid")
    , @NamedQuery(name = "Game.findByStartdatetime", query = "SELECT g FROM Game g WHERE g.startdatetime = :startdatetime")
    , @NamedQuery(name = "Game.findByEnddatetime", query = "SELECT g FROM Game g WHERE g.enddatetime = :enddatetime")})
public class Game implements Serializable {

    @Size(max = 45)
    @Column(name = "startdatetime")
    private String startdatetime;
    @Size(max = 45)
    @Column(name = "enddatetime")
    private String enddatetime;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "gameid")
    private String gameid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "game")
    private Collection<Usersingame> usersingameCollection;

    public Game() {
    }

    public Game(String gameid) {
        this.gameid = gameid;
    }

    public String getGameid() {
        return gameid;
    }

    public void setGameid(String gameid) {
        this.gameid = gameid;
    }


    @XmlTransient
    public Collection<Usersingame> getUsersingameCollection() {
        return usersingameCollection;
    }

    public void setUsersingameCollection(Collection<Usersingame> usersingameCollection) {
        this.usersingameCollection = usersingameCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gameid != null ? gameid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Game)) {
            return false;
        }
        Game other = (Game) object;
        if ((this.gameid == null && other.gameid != null) || (this.gameid != null && !this.gameid.equals(other.gameid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.palermo.palermo.entities.Game[ gameid=" + gameid + " ]";
    }

    public String getStartdatetime() {
        return startdatetime;
    }

    public void setStartdatetime(String startdatetime) {
        this.startdatetime = startdatetime;
    }

    public String getEnddatetime() {
        return enddatetime;
    }

    public void setEnddatetime(String enddatetime) {
        this.enddatetime = enddatetime;
    }
    
}
