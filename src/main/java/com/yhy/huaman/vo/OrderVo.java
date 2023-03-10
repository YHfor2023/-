package com.yhy.huaman.vo;



import java.io.Serializable;
import java.util.Date;


public class OrderVo implements Serializable {
    private Integer oid;
    private Integer aid;
    private String recvName;
    private String zip;
    private String phone;
    private String provinceName;
    private String cityName;
    private String areaName;
    private String address;
    private Long totalPrice;
    private Integer status;
    private Date orderTime;
    private Date payTime;
    private String image;
    private String title;
    private Long price;
    private Integer num;

    @Override
    public String toString() {
        return "OrderVo{" +
                "oid=" + oid +
                ", aid=" + aid +
                ", recvName='" + recvName + '\'' +
                ", zip='" + zip + '\'' +
                ", phone='" + phone + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", areaName='" + areaName + '\'' +
                ", address='" + address + '\'' +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                ", orderTime=" + orderTime +
                ", payTime=" + payTime +
                ", image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", num=" + num +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderVo)) return false;

        OrderVo orderVo = (OrderVo) o;

        if (getOid() != null ? !getOid().equals(orderVo.getOid()) : orderVo.getOid() != null) return false;
        if (getAid() != null ? !getAid().equals(orderVo.getAid()) : orderVo.getAid() != null) return false;
        if (getRecvName() != null ? !getRecvName().equals(orderVo.getRecvName()) : orderVo.getRecvName() != null)
            return false;
        if (getZip() != null ? !getZip().equals(orderVo.getZip()) : orderVo.getZip() != null) return false;
        if (getPhone() != null ? !getPhone().equals(orderVo.getPhone()) : orderVo.getPhone() != null) return false;
        if (getProvinceName() != null ? !getProvinceName().equals(orderVo.getProvinceName()) :
                orderVo.getProvinceName() != null)
            return false;
        if (getCityName() != null ? !getCityName().equals(orderVo.getCityName()) : orderVo.getCityName() != null)
            return false;
        if (getAreaName() != null ? !getAreaName().equals(orderVo.getAreaName()) : orderVo.getAreaName() != null)
            return false;
        if (getAddress() != null ? !getAddress().equals(orderVo.getAddress()) : orderVo.getAddress() != null)
            return false;
        if (getTotalPrice() != null ? !getTotalPrice().equals(orderVo.getTotalPrice()) :
                orderVo.getTotalPrice() != null)
            return false;
        if (getStatus() != null ? !getStatus().equals(orderVo.getStatus()) : orderVo.getStatus() != null) return false;
        if (getOrderTime() != null ? !getOrderTime().equals(orderVo.getOrderTime()) : orderVo.getOrderTime() != null)
            return false;
        if (getPayTime() != null ? !getPayTime().equals(orderVo.getPayTime()) : orderVo.getPayTime() != null)
            return false;
        if (getImage() != null ? !getImage().equals(orderVo.getImage()) : orderVo.getImage() != null) return false;
        if (getTitle() != null ? !getTitle().equals(orderVo.getTitle()) : orderVo.getTitle() != null) return false;
        if (getPrice() != null ? !getPrice().equals(orderVo.getPrice()) : orderVo.getPrice() != null) return false;
        return getNum() != null ? getNum().equals(orderVo.getNum()) : orderVo.getNum() == null;
    }

    @Override
    public int hashCode() {
        int result = getOid() != null ? getOid().hashCode() : 0;
        result = 31 * result + (getAid() != null ? getAid().hashCode() : 0);
        result = 31 * result + (getRecvName() != null ? getRecvName().hashCode() : 0);
        result = 31 * result + (getZip() != null ? getZip().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        result = 31 * result + (getProvinceName() != null ? getProvinceName().hashCode() : 0);
        result = 31 * result + (getCityName() != null ? getCityName().hashCode() : 0);
        result = 31 * result + (getAreaName() != null ? getAreaName().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getTotalPrice() != null ? getTotalPrice().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getOrderTime() != null ? getOrderTime().hashCode() : 0);
        result = 31 * result + (getPayTime() != null ? getPayTime().hashCode() : 0);
        result = 31 * result + (getImage() != null ? getImage().hashCode() : 0);
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        result = 31 * result + (getNum() != null ? getNum().hashCode() : 0);
        return result;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getRecvName() {
        return recvName;
    }

    public void setRecvName(String recvName) {
        this.recvName = recvName;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
