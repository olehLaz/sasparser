package ua.sasparser.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "offer")
public class Offer {

    @Id
    private Long id_offer;

    private boolean available;
    private  String group_id;
    private String url;
    private double price;
    private String vendorCode;
    private String currencyId;


    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private  Category category = new Category();


    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_id")
    private  Supplier supplier = new Supplier();


    private String picture;
    private boolean delivery;
    private String name;
    private String description;
    private String vendor;
    private long code;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "offer_param", joinColumns = @JoinColumn(name = "offer_id"))
    private List<String> param = new ArrayList<>();


    public Offer() {

    }

    public Long getId_offer() {
        return id_offer;
    }

    public void setId_offer(Long id_offer) {
        this.id_offer = id_offer;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }



    public String getCategory() {
        return category.getNameCategory();
    }

    public Long getCatByID() {
        return category.getId();
    }

    public void setCategory(Long number) {
        if(number != null) {
            this.category.setId(number);
        } else  {
            this.category.setId(68261641L);
        }
    }

    public String getSupplier() {
        return supplier.getName();
    }

    public void setSupplier(Long number) {
        if(number > 0) {
            this.supplier.setId(number);
        } else  {
            this.supplier.setId(-9999L);
        }
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        String str = "";
        if(description.length() > 200) {
            str = description.substring(0, 200);
        }
        this.description = str;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public List<String> getParam() {
        return param;
    }

    public void setParam(String str) {
        this.param.add(str);
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id_offer=" + id_offer +
                ", available=" + available +
                ", group_id='" + group_id + '\'' +
                ", url='" + url + '\'' +
                ", price=" + price +
                ", vendorCode='" + vendorCode + '\'' +
                ", currencyId='" + currencyId + '\'' +
                ", category=" + category +
                ", supplier=" + supplier +
                ", picture='" + picture + '\'' +
                ", delivery=" + delivery +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", vendor='" + vendor + '\'' +
                ", code=" + code +
                ", param=" + param +
                '}';
    }
}
