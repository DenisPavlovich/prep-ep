package epam.pre.romanenko.entities;

import epam.pre.romanenko.store.annotations.FieldName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public class Being implements Serializable {

    private final UUID id = UUID.randomUUID();
    @FieldName("price")
    protected BigDecimal price;
    @FieldName("name")
    protected String name;

    public Being() {
    }

    public Being(BigDecimal price, String name) {
        this.price = price;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Being)) return false;

        Being being = ((Being) obj);
        return being.id.equals(id);
    }

    @Override
    public String toString() {
        return String.format("id:%s, price:%s, made in:%s", id, price, name);
    }

}
