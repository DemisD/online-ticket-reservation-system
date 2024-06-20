/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author db
 */
@Entity
@Table(name = "usertbl")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usertbl.findAll", query = "SELECT u FROM Usertbl u"),
    @NamedQuery(name = "Usertbl.findByUserId", query = "SELECT u FROM Usertbl u WHERE u.userId = :userId"),
    @NamedQuery(name = "Usertbl.findByFirstName", query = "SELECT u FROM Usertbl u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "Usertbl.findByLastName", query = "SELECT u FROM Usertbl u WHERE u.lastName = :lastName"),
    @NamedQuery(name = "Usertbl.findByMobileNo", query = "SELECT u FROM Usertbl u WHERE u.mobileNo = :mobileNo"),
    @NamedQuery(name = "Usertbl.findByPassword", query = "SELECT u FROM Usertbl u WHERE u.password = :password"),
    @NamedQuery(name = "Usertbl.totalUserNo", query = "SELECT COUNT(u) AS totalBusNo FROM Usertbl u "),
    @NamedQuery(name = "Usertbl.findByUserName", query = "SELECT u FROM Usertbl u WHERE u.userName = :userName"),
    @NamedQuery(name = "Usertbl.findByAddress", query = "SELECT u FROM Usertbl u WHERE u.address = :address"),
    @NamedQuery(name = "Usertbl.findByRole", query = "SELECT u FROM Usertbl u WHERE u.role = :role")})
public class Usertbl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "userId")
    private Integer userId;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;
    @Column(name = "mobileNo")
    private Integer mobileNo;

    @Column(name = "password")
    private String password;
    @Size(max = 255)
    @Column(name = "userName")
    private String userName;

    @Column(name = "Address")
    private String address;

    @Column(name = "role")
    private String role;
    
     @Column(name = "plainTaxtPassword")
    private String plainTaxtPassword;
    
    @OneToMany(mappedBy = "userId")
    private List<Ticketreservationtbl> ticketreservationtblList;

    public Usertbl() {
    }

    public Usertbl(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Integer getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(Integer mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPlainTaxtPassword() {
        return plainTaxtPassword;
    }

    public void setPlainTaxtPassword(String plainTaxtPassword) {
        this.plainTaxtPassword = plainTaxtPassword;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usertbl)) {
            return false;
        }
        Usertbl other = (Usertbl) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Usertbl[ userId=" + userId + " ]";
    }

    @XmlTransient
    public List<Ticketreservationtbl> getTicketreservationtblList() {
        return ticketreservationtblList;
    }

    public void setTicketreservationtblList(List<Ticketreservationtbl> ticketreservationtblList) {
        this.ticketreservationtblList = ticketreservationtblList;
    }

}
