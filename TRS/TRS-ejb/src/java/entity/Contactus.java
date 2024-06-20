/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author demis
 */
@Entity
@Table(name = "contactus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contactus.findAll", query = "SELECT c FROM Contactus c"),
    @NamedQuery(name = "Contactus.findById", query = "SELECT c FROM Contactus c WHERE c.id = :id"),
    @NamedQuery(name = "Contactus.findByName", query = "SELECT c FROM Contactus c WHERE c.name = :name"),
    @NamedQuery(name = "Contactus.findByEmaile", query = "SELECT c FROM Contactus c WHERE c.emaile = :emaile"),
    @NamedQuery(name = "Contactus.findByPhone", query = "SELECT c FROM Contactus c WHERE c.phone = :phone"),
    @NamedQuery(name = "Contactus.findByMessage", query = "SELECT c FROM Contactus c WHERE c.message = :message")})
public class Contactus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 45)
    @Column(name = "emaile")
    private String emaile;
    @Column(name = "phone")
    private String phone;
    @Size(max = 145)
    @Column(name = "message")
    private String message;

    public Contactus() {
    }

    public Contactus(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmaile() {
        return emaile;
    }

    public void setEmaile(String emaile) {
        this.emaile = emaile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contactus)) {
            return false;
        }
        Contactus other = (Contactus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Contactus[ id=" + id + " ]";
    }
    
}
