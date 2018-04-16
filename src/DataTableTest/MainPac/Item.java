package DataTableTest.MainPac;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "item")
class Item {
	@Id
	@GeneratedValue
	@Column (name = "id")
    private int id;
    @Column (name = "name", nullable = false, length = 20)
    private String name;
    @Column (name = "amount", nullable = false)
    private int amount;
    @Column (name = "price", nullable = false)
    private BigDecimal price;

    public Item() {
    }

    public Item(String name, int amount, BigDecimal price) {
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

}
