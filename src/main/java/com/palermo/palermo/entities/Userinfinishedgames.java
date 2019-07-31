/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.palermo.palermo.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "userinfinishedgames")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userinfinishedgames.findAll", query = "SELECT u FROM Userinfinishedgames u")
    , @NamedQuery(name = "Userinfinishedgames.findByUserid", query = "SELECT u FROM Userinfinishedgames u WHERE u.userid = :userid")
    , @NamedQuery(name = "Userinfinishedgames.findByGameid", query = "SELECT u FROM Userinfinishedgames u WHERE u.gameid = :gameid")
    , @NamedQuery(name = "Userinfinishedgames.findByStartdatetime", query = "SELECT u FROM Userinfinishedgames u WHERE u.startdatetime = :startdatetime")
    , @NamedQuery(name = "Userinfinishedgames.findByEnddatetime", query = "SELECT u FROM Userinfinishedgames u WHERE u.enddatetime = :enddatetime")
    , @NamedQuery(name = "Userinfinishedgames.findByWon", query = "SELECT u FROM Userinfinishedgames u WHERE u.won = :won")
    , @NamedQuery(name = "Userinfinishedgames.findByIngamerole", query = "SELECT u FROM Userinfinishedgames u WHERE u.ingamerole = :ingamerole")})
public class Userinfinishedgames implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected UserinfinishedgamesPK userinfinishedgamesPK;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "userid",  insertable = false, updatable = false)
    private int userid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "gameid",  insertable = false, updatable = false)
    private String gameid;
    @Size(max = 45)
    @Column(name = "startdatetime")
    private String startdatetime;
    @Size(max = 45)
    @Column(name = "enddatetime")
    private String enddatetime;
    @Column(name = "won")
    private Integer won;
    @Size(max = 45)
    @Column(name = "ingamerole")
    private String ingamerole;

    
        public Userinfinishedgames() {
    }

    public Userinfinishedgames(UserinfinishedgamesPK userinfinishedgamesPK) {
        this.userinfinishedgamesPK = userinfinishedgamesPK;
    }

    public Userinfinishedgames(int userid, String gameid) {
        this.userinfinishedgamesPK = new UserinfinishedgamesPK(userid, gameid);
    }

    public UserinfinishedgamesPK getUserinfinishedgamesPK() {
        return userinfinishedgamesPK;
    }

    public void setUserinfinishedgamesPK(UserinfinishedgamesPK userinfinishedgamesPK) {
        this.userinfinishedgamesPK = userinfinishedgamesPK;
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
