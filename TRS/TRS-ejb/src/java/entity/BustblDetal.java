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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author demis
 */
@Entity
@Table(name = "bustbl_detal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BustblDetal.findAll", query = "SELECT b FROM BustblDetal b ORDER BY b.seatNo"),
    @NamedQuery(name = "BustblDetal.findAllSeatNoByBus", query = "SELECT b FROM BustblDetal b  WHERE b.busId.busNumber = :busNumber  ORDER BY b.seatNo"),
    
       @NamedQuery(name = "BustblDetal.findAllSeatNoByBus1", query = "SELECT b FROM BustblDetal b   WHERE b.busId.busNumber = :busNumber AND b NOT IN (SELECT t.busSeatNoId  FROM Ticketreservationtbl t )"),
       
    @NamedQuery(name = "BustblDetal.findByDetailId", query = "SELECT b FROM BustblDetal b WHERE b.detailId = :detailId"),
    @NamedQuery(name = "BustblDetal.findByBusId", query = "SELECT b FROM BustblDetal b WHERE b.busId.id = :id ORDER BY b.seatNo"),
    @NamedQuery(name = "BustblDetal.findByBusIdAndSeatNo", query = "SELECT b FROM BustblDetal b WHERE b.busId.id = :id AND b.seatNo = :seatNo"),
    @NamedQuery(name = "BustblDetal.findBySeatNo", query = "SELECT b FROM BustblDetal b WHERE b.seatNo = :seatNo AND b.busId.busNumber = :busNumber")})
public class BustblDetal implements Serializable {
    @OneToMany(mappedBy = "busSeatNoId")
    private List<Ticketreservationtbl> ticketreservationtblList;
   
  
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "detailId")
    private Integer detailId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "seatNo")
    private String seatNo;
    @JoinColumn(name = "busId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Bustbl busId;

  

    public BustblDetal() {
    }

    public BustblDetal(Integer detailId) {
        this.detailId = detailId;
    }

    public BustblDetal(Integer detailId, String seatNo) {
        this.detailId = detailId;
        this.seatNo = seatNo;
    }

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public Bustbl getBusId() {
        if (busId == null) {
            busId = new Bustbl();
        }
        return busId;
    }

    public void setBusId(Bustbl busId) {
        this.busId = busId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detailId != null ? detailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BustblDetal)) {
            return false;
        }
        BustblDetal other = (BustblDetal) object;
        if ((this.detailId == null && other.detailId != null) || (this.detailId != null && !this.detailId.equals(other.detailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return seatNo;
    }

    @XmlTransient
    public List<Ticketreservationtbl> getTicketreservationtblList() {
        return ticketreservationtblList;
    }

    public void setTicketreservationtblList(List<Ticketreservationtbl> ticketreservationtblList) {
        this.ticketreservationtblList = ticketreservationtblList;
    }

  

  
}
