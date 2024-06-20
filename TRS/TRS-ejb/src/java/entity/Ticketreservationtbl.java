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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ticketreservationtbl")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ticketreservationtbl.findAll", query = "SELECT t FROM Ticketreservationtbl t ORDER BY t.reservationId DESC"),
    @NamedQuery(name = "Ticketreservationtbl.findByReservationId", query = "SELECT t FROM Ticketreservationtbl t WHERE t.reservationId = :reservationId"),
    @NamedQuery(name = "Ticketreservationtbl.findByFirstName", query = "SELECT t FROM Ticketreservationtbl t WHERE t.firstName = :firstName"),
    @NamedQuery(name = "Ticketreservationtbl.findByFatherName", query = "SELECT t FROM Ticketreservationtbl t WHERE t.fatherName = :fatherName"),
        @NamedQuery(name = "Ticketreservationtbl.findByTckNo", query = "SELECT t FROM Ticketreservationtbl t WHERE t.ticketNo = :ticketNo"),
    @NamedQuery(name = "Ticketreservationtbl.findByAllDataByUserName", query = "SELECT t FROM Ticketreservationtbl t WHERE t.userId.userName = :userName"),
     @NamedQuery(name = "Ticketreservationtbl.updateStatus", query = "UPDATE Ticketreservationtbl t SET t.status = 'Expired' WHERE t.scheduleId.scheduleDate < :currentDateString"),
    @NamedQuery(name = "Ticketreservationtbl.findByPhoneNo", query = "SELECT t FROM Ticketreservationtbl t WHERE t.phoneNo = :phoneNo"),
        @NamedQuery(name = "Ticketreservationtbl.findByBankSlipNo", query = "SELECT t FROM Ticketreservationtbl t WHERE t.dipositSlipNo = :dipositSlipNo"),
//          @NamedQuery(name = "Ticketreservationtbl.findAllSeatNoByBusa", query = "SELECT t FROM Ticketreservationtbl t INNER JOIN t.busSeatNoId AS seatNo WHERE Not IN (t.busSeatNoId.busSeatNoId = :busSeatNoId) AND b.busId.busNumber = :busNumber  ORDER BY b.seatNo"),
    
    
    @NamedQuery(name = "Ticketreservationtbl.totalCustomerNoNo", query = "SELECT COUNT(t) AS totalBusNo FROM Ticketreservationtbl t "),
    @NamedQuery(name = "Ticketreservationtbl.findLastTickNo", query = "SELECT t.ticketNo FROM Ticketreservationtbl t ORDER BY t.reservationId DESC"),
    @NamedQuery(name = "Ticketreservationtbl.totalPrice", query = "SELECT SUM(t.scheduleId.price) AS totalPrice FROM Ticketreservationtbl t "),
    @NamedQuery(name = "Ticketreservationtbl.findByStatus", query = "SELECT t FROM Ticketreservationtbl t WHERE t.status = :status"),
    @NamedQuery(name = "Ticketreservationtbl.findByReservDate", query = "SELECT t FROM Ticketreservationtbl t WHERE t.reservDate = :reservDate")})
public class Ticketreservationtbl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "reservationId")
    private Integer reservationId;
    @Size(max = 45)
    @Column(name = "firstName")
    private String firstName;
    @Size(max = 45)
    @Column(name = "fatherName")
    private String fatherName;
    @Size(max = 45)
    @Column(name = "phoneNo")
    private String phoneNo;
    @Size(max = 45)
    @Column(name = "status")
    private String status;
    @Size(max = 45)
    @Column(name = "ReservDate")
    private String reservDate;
    @Column(name = "dipositSlipNo")
    private String dipositSlipNo;
    @Column(name = "ticketNo")
    private String ticketNo;
    @JoinColumn(name = "scheduleId", referencedColumnName = "scheduleId")
    @ManyToOne(optional = false)
    private Scheduletbl scheduleId;
    @JoinColumn(name = "busSeatNoId", referencedColumnName = "detailId")
    @ManyToOne
    private BustblDetal busSeatNoId;
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    @ManyToOne
    private Usertbl userId;

    public Ticketreservationtbl() {
    }

    public Ticketreservationtbl(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReservDate() {
        return reservDate;
    }

    public void setReservDate(String reservDate) {
        this.reservDate = reservDate;
    }

    public Scheduletbl getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Scheduletbl scheduleId) {
        this.scheduleId = scheduleId;
    }

    public BustblDetal getBusSeatNoId() {
        return busSeatNoId;
    }

    public void setBusSeatNoId(BustblDetal busSeatNoId) {
        this.busSeatNoId = busSeatNoId;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reservationId != null ? reservationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ticketreservationtbl)) {
            return false;
        }
        Ticketreservationtbl other = (Ticketreservationtbl) object;
        if ((this.reservationId == null && other.reservationId != null) || (this.reservationId != null && !this.reservationId.equals(other.reservationId))) {
            return false;
        }
        return true;
    }

    public String getDipositSlipNo() {
        return dipositSlipNo;
    }

    public void setDipositSlipNo(String dipositSlipNo) {
        this.dipositSlipNo = dipositSlipNo;
    }

    @Override
    public String toString() {
        return "entity.Ticketreservationtbl[ reservationId=" + reservationId + " ]";
    }

    public Usertbl getUserId() {
        return userId;
    }

    public void setUserId(Usertbl userId) {
        this.userId = userId;
    }

}
