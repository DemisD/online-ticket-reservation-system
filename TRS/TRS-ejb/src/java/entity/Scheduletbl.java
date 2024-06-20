/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author demis
 */
@Entity
@Table(name = "scheduletbl")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Scheduletbl.findAll", query = "SELECT s FROM Scheduletbl s ORDER BY S.scheduleId DESC"),
     @NamedQuery(name = "Scheduletbl.findAllByStatus", query = "SELECT s FROM Scheduletbl s WHERE s.status = 'Active' ORDER BY S.scheduleId DESC"),
    @NamedQuery(name = "Scheduletbl.findByScheduleId", query = "SELECT s FROM Scheduletbl s WHERE s.scheduleId = :scheduleId"),
    @NamedQuery(name = "Scheduletbl.findByScheduleDate", query = "SELECT s FROM Scheduletbl s WHERE s.scheduleDate = :scheduleDate"),
    @NamedQuery(name = "Scheduletbl.findByDespatureTime", query = "SELECT s FROM Scheduletbl s WHERE s.despatureTime = :despatureTime"),
    @NamedQuery(name = "Scheduletbl.findByDestinationTime", query = "SELECT s FROM Scheduletbl s WHERE s.destinationTime = :destinationTime"),
    @NamedQuery(name = "Scheduletbl.findByPrice", query = "SELECT s FROM Scheduletbl s WHERE s.price = :price"),
    @NamedQuery(name = "Scheduletbl.findByStatus", query = "SELECT s FROM Scheduletbl s WHERE s.status = :status")})
public class Scheduletbl implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "scheduleId")
    private List<Ticketreservationtbl> ticketreservationtblList;
 

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "scheduleId")
    private Integer scheduleId;
  
    @Column(name = "scheduleDate")
    private String scheduleDate;

    @Column(name = "despatureTime")
    private String despatureTime;

    @Column(name = "destinationTime")
    private String destinationTime;
  
    @Column(name = "price")
    private double price;
  
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "busId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Bustbl busId;
    @JoinColumn(name = "routeId", referencedColumnName = "routerId")
    @ManyToOne(optional = false)
    private Routetbl routeId;

    public Scheduletbl() {
    }

    public Scheduletbl(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Scheduletbl(Integer scheduleId, String scheduleDate, String despatureTime, String destinationTime, double price, String status) {
        this.scheduleId = scheduleId;
        this.scheduleDate = scheduleDate;
        this.despatureTime = despatureTime;
        this.destinationTime = destinationTime;
        this.price = price;
        this.status = status;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getDespatureTime() {
        return despatureTime;
    }

    public void setDespatureTime(String despatureTime) {
        this.despatureTime = despatureTime;
    }

    public String getDestinationTime() {
        return destinationTime;
    }

    public void setDestinationTime(String destinationTime) {
        this.destinationTime = destinationTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Bustbl getBusId() {
        return busId;
    }

    public void setBusId(Bustbl busId) {
        this.busId = busId;
    }

    public Routetbl getRouteId() {
        return routeId;
    }

    public void setRouteId(Routetbl routeId) {
        this.routeId = routeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (scheduleId != null ? scheduleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Scheduletbl)) {
            return false;
        }
        Scheduletbl other = (Scheduletbl) object;
        if ((this.scheduleId == null && other.scheduleId != null) || (this.scheduleId != null && !this.scheduleId.equals(other.scheduleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Scheduletbl[ scheduleId=" + scheduleId + " ]";
    }

    @XmlTransient
    public List<Ticketreservationtbl> getTicketreservationtblList() {
        return ticketreservationtblList;
    }

    public void setTicketreservationtblList(List<Ticketreservationtbl> ticketreservationtblList) {
        this.ticketreservationtblList = ticketreservationtblList;
    }

  

    
    
}
