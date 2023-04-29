package com.yhy.huaman.entity;

/** 订单中的商品数据 */
public class OrderItem extends BaseEntity {
    private Integer id;
    private Integer oid;
    private Integer pid;
    private String title;
    private String image;
    private Long price;
    private Integer num;
    private String color;
    private String size;
    private String madeof;
    private String safety;

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", oid=" + oid +
                ", pid=" + pid +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", madeof='" + madeof + '\'' +
                ", safety='" + safety + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem)) return false;
        if (!super.equals(o)) return false;

        OrderItem orderItem = (OrderItem) o;

        if (getId() != null ? !getId().equals(orderItem.getId()) : orderItem.getId() != null) return false;
        if (getOid() != null ? !getOid().equals(orderItem.getOid()) : orderItem.getOid() != null) return false;
        if (getPid() != null ? !getPid().equals(orderItem.getPid()) : orderItem.getPid() != null) return false;
        if (getTitle() != null ? !getTitle().equals(orderItem.getTitle()) : orderItem.getTitle() != null) return false;
        if (getImage() != null ? !getImage().equals(orderItem.getImage()) : orderItem.getImage() != null) return false;
        if (getPrice() != null ? !getPrice().equals(orderItem.getPrice()) : orderItem.getPrice() != null) return false;
        if (getNum() != null ? !getNum().equals(orderItem.getNum()) : orderItem.getNum() != null) return false;
        if (getColor() != null ? !getColor().equals(orderItem.getColor()) : orderItem.getColor() != null) return false;
        if (getSize() != null ? !getSize().equals(orderItem.getSize()) : orderItem.getSize() != null) return false;
        if (getMadeof() != null ? !getMadeof().equals(orderItem.getMadeof()) : orderItem.getMadeof() != null)
            return false;
        return getSafety() != null ? getSafety().equals(orderItem.getSafety()) : orderItem.getSafety() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        result = 31 * result + (getOid() != null ? getOid().hashCode() : 0);
        result = 31 * result + (getPid() != null ? getPid().hashCode() : 0);
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getImage() != null ? getImage().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        result = 31 * result + (getNum() != null ? getNum().hashCode() : 0);
        result = 31 * result + (getColor() != null ? getColor().hashCode() : 0);
        result = 31 * result + (getSize() != null ? getSize().hashCode() : 0);
        result = 31 * result + (getMadeof() != null ? getMadeof().hashCode() : 0);
        result = 31 * result + (getSafety() != null ? getSafety().hashCode() : 0);
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
}
