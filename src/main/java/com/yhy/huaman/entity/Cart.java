package com.yhy.huaman.entity;

/**购物车数据的实体类*/
public class Cart extends BaseEntity {
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Long price;
    private String color;
    private String size;
    private String madeof;
    private String safety;
    private Integer num;

    @Override
    public String toString() {
        return "Cart{" +
                "cid=" + cid +
                ", uid=" + uid +
                ", pid=" + pid +
                ", price=" + price +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", madeof='" + madeof + '\'' +
                ", safety='" + safety + '\'' +
                ", num=" + num +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart)) return false;
        if (!super.equals(o)) return false;

        Cart cart = (Cart) o;

        if (getCid() != null ? !getCid().equals(cart.getCid()) : cart.getCid() != null) return false;
        if (getUid() != null ? !getUid().equals(cart.getUid()) : cart.getUid() != null) return false;
        if (getPid() != null ? !getPid().equals(cart.getPid()) : cart.getPid() != null) return false;
        if (getPrice() != null ? !getPrice().equals(cart.getPrice()) : cart.getPrice() != null) return false;
        if (getColor() != null ? !getColor().equals(cart.getColor()) : cart.getColor() != null) return false;
        if (getSize() != null ? !getSize().equals(cart.getSize()) : cart.getSize() != null) return false;
        if (getMadeof() != null ? !getMadeof().equals(cart.getMadeof()) : cart.getMadeof() != null) return false;
        if (getSafety() != null ? !getSafety().equals(cart.getSafety()) : cart.getSafety() != null) return false;
        return getNum() != null ? getNum().equals(cart.getNum()) : cart.getNum() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getCid() != null ? getCid().hashCode() : 0);
        result = 31 * result + (getUid() != null ? getUid().hashCode() : 0);
        result = 31 * result + (getPid() != null ? getPid().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        result = 31 * result + (getColor() != null ? getColor().hashCode() : 0);
        result = 31 * result + (getSize() != null ? getSize().hashCode() : 0);
        result = 31 * result + (getMadeof() != null ? getMadeof().hashCode() : 0);
        result = 31 * result + (getSafety() != null ? getSafety().hashCode() : 0);
        result = 31 * result + (getNum() != null ? getNum().hashCode() : 0);
        return result;
    }

    /**
 * get,set
 * equals和hashCode
 * toString
 */
    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMadeof() {
        return madeof;
    }

    public void setMadeof(String madeof) {
        this.madeof = madeof;
    }

    public String getSafety() {
        return safety;
    }

    public void setSafety(String safety) {
        this.safety = safety;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
