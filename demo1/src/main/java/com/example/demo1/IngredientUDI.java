package com.example.demo1;
import com.example.demo1.Ingridents.Type;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@UserDefinedType("ingredient")
public class IngredientUDI {
    private  String name;
    private  Type type;
}
