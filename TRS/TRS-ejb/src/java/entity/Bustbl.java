/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author db
 */
@Entity
@Table(name = "bustbl")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bustbl.findAll", query = "SELECT b FROM Bustbl b"),
    @NamedQuery(name = "Bustbl.findById", query = "SELECT b FROM Bustbl b WHERE b.id = :id"),
    @NamedQuery(name = "Bustbl.findByBusName", query = "SELECT b FROM Bustbl b WHERE b.busName = :busName"),
    @NamedQuery(name = "Bustbl.findByBusNumber", query = "SELECT b FROM Bustbl b WHERE b.busNumber = :busNumber"),
    @NamedQuery(name = "Bustbl.totalBusNo", query = "SELECT COUNT(b) AS totalBusNo FROM Bustbl b "),
    @NamedQuery(name = "Bustbl.findLikeBusNumber", query = "SELECT b FROM Bustbl b WHERE UPPER(b.busNumber) like :busNumber  ORDER BY b.busNumber"),

    @NamedQuery(name = "Bustbl.findBySeatNumber", query = "SELECT b FROM Bustbl b WHERE b.seatNumber = :seatNumber")})
public class Bustbl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "busName")
    private String busName;
    @Size(max = 255)
    @Column(name = "busNumber")
    private String busNumber;
    @Column(name = "seatNumber")
    private Integer seatNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "busId")
    private List<BustblDetal> bustblDetalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "busId")
    private List<Scheduletbl> scheduletblList;

    public Bustbl() {
    }

    public Bustbl(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
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
        if (!(object instanceof Bustbl)) {
            return false;
        }
        Bustbl other = (Bustbl) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return busNumber;
    }

    @XmlTransient
    public List<Scheduletbl> getScheduletblList() {
        return scheduletblList;
    }

    public void setScheduletblList(List<Scheduletbl> scheduletblList) {
        this.scheduletblList = scheduletblList;
    }

    @XmlTransient
    public List<BustblDetal> getBustblDetalList() {
        if (bustblDetalList == null) {
            bustblDetalList = new ArrayList<>();
        }
        return bustblDetalList;
    }

    public void setBustblDetalList(List<BustblDetal> bustblDetalList) {
        this.bustblDetalList = bustblDetalList;
    }

    public void addDetaill(BustblDetal bustblDetal) {
        if (bustblDetal.getBusId() != this) {
            this.getBustblDetalList().add(bustblDetal);
            bustblDetal.setBusId(this);
        }
    }

}
