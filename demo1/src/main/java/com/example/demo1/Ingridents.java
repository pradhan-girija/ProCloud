package com.example.demo1;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@Table("ingridents")
@RequiredArgsConstructor
public class Ingridents {
    @PrimaryKey
    public  String id;
    public  String name;
    public  Type type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Ingridents(String id,String name,Type type)
    {
        this.id=id;
        this.name=name;
        this.type=type;
    }
    public static enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
