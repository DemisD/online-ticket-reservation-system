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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author db
 */
@Entity
@Table(name = "citytbl")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Citytbl.findAll", query = "SELECT c FROM Citytbl c"),
    @NamedQuery(name = "Citytbl.findByCityId", query = "SELECT c FROM Citytbl c WHERE c.cityId = :cityId"),
    @NamedQuery(name = "Citytbl.findByCityName", query = "SELECT c FROM Citytbl c WHERE c.cityName = :cityName"),
    @NamedQuery(name = "Citytbl.findLikeCityName", query = "SELECT c FROM Citytbl c WHERE UPPER(c.cityName) like :cityName  ORDER BY c.cityName"),
    @NamedQuery(name = "Citytbl.findByCityDescription", query = "SELECT c FROM Citytbl c WHERE c.cityDescription = :cityDescription")})
public class Citytbl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cityId")
    private Integer cityId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "cityName")
    private String cityName;
    @Size(max = 45)
    @Column(name = "cityDescription")
    private String cityDescription;
    @OneToMany(mappedBy = "destinationCityId")
    private List<Routetbl> routetblList;
    @OneToMany(mappedBy = "depatureCityId")
    private List<Routetbl> routetblList1;

    public Citytbl() {
    }

    public Citytbl(Integer cityId) {
        this.cityId = cityId;
    }

    public Citytbl(Integer cityId, String cityName) {
        this.cityId = cityId;
        this.cityName = cityName;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityDescription() {
        return cityDescription;
    }

    public void setCityDescription(String cityDescription) {
        this.cityDescription = cityDescription;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cityId != null ? cityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Citytbl)) {
            return false;
        }
        Citytbl other = (Citytbl) object;
        if ((this.cityId == null && other.cityId != null) || (this.cityId != null && !this.cityId.equals(other.cityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return cityName;
    }

    @XmlTransient
    public List<Routetbl> getRoutetblList() {
        return routetblList;
    }

    public void setRoutetblList(List<Routetbl> routetblList) {
        this.routetblList = routetblList;
    }

    @XmlTransient
    public List<Routetbl> getRoutetblList1() {
        return routetblList1;
    }

    public void setRoutetblList1(List<Routetbl> routetblList1) {
        this.routetblList1 = routetblList1;
    }

}
