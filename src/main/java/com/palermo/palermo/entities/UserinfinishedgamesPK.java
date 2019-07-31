/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Los_e
 */
@Embeddable
public class UserinfinishedgamesPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "userid")
    private int userid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "gameid")
    private String gameid;

    public UserinfinishedgamesPK() {
    }

    public UserinfinishedgamesPK(int userid, String gameid) {
        this.userid = userid;
        this.gameid = gameid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getGameid() {
        return gameid;
    }

    public void setGameid(String gameid) {
        this.gameid = gameid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userid;
        hash += (gameid != null ? gameid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserinfinishedgamesPK)) {
            return false;
        }
        UserinfinishedgamesPK other = (UserinfinishedgamesPK) object;
        if (this.userid != other.userid) {
            return false;
        }
        if ((this.gameid == null && other.gameid != null) || (this.gameid != null && !this.gameid.equals(other.gameid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.palermo.palermo.entities.UsersinfinishedgamesPK[ userid=" + userid + ", gameid=" + gameid + " ]";
    }
    
}
