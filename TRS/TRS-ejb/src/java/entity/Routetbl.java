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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author db
 */
@Entity
@Table(name = "routetbl")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Routetbl.findAll", query = "SELECT r FROM Routetbl r"),
    @NamedQuery(name = "Routetbl.findByRouterRouteName", query = "SELECT r FROM Routetbl r WHERE r.routeName = :routeName"),
    @NamedQuery(name = "Routetbl.findAllLikeRouteName", query = "SELECT r FROM Routetbl r WHERE r.routeName LIKE :routeName"),
    @NamedQuery(name = "Routetbl.totalRoutNo", query = "SELECT COUNT(r) AS totalRoutNo FROM Routetbl r "),
    @NamedQuery(name = "Routetbl.findByRouterId", query = "SELECT r FROM Routetbl r WHERE r.routerId = :routerId")})
public class Routetbl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "routerId")
    private Integer routerId;
    @Size(max = 45)
    @Column(name = "routeName")
    private String routeName;
    @JoinColumn(name = "depatureCityId", referencedColumnName = "cityId")
    @ManyToOne
    private Citytbl depatureCityId;
    @JoinColumn(name = "destinationCityId", referencedColumnName = "cityId")
    @ManyToOne
    private Citytbl destinationCityId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "routeId")
    private List<Scheduletbl> scheduletblList;

    public Routetbl() {
    }

    public Routetbl(Integer routerId) {
        this.routerId = routerId;
    }

    public Integer getRouterId() {
        return routerId;
    }

    public void setRouterId(Integer routerId) {
        this.routerId = routerId;
    }

    public Citytbl getDepatureCityId() {
        return depatureCityId;
    }

    public void setDepatureCityId(Citytbl depatureCityId) {
        this.depatureCityId = depatureCityId;
    }

    public Citytbl getDestinationCityId() {
        return destinationCityId;
    }

    public void setDestinationCityId(Citytbl destinationCityId) {
        this.destinationCityId = destinationCityId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (routerId != null ? routerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Routetbl)) {
            return false;
        }
        Routetbl other = (Routetbl) object;
        if ((this.routerId == null && other.routerId != null) || (this.routerId != null && !this.routerId.equals(other.routerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return routeName;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    @XmlTransient
    public List<Scheduletbl> getScheduletblList() {
        return scheduletblList;
    }

    public void setScheduletblList(List<Scheduletbl> scheduletblList) {
        this.scheduletblList = scheduletblList;
    }

}
