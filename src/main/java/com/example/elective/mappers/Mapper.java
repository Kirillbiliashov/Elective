package com.example.elective.mappers;

import com.example.elective.exceptions.MappingException;

/**
 * Interface that describes mapping operation between two classes
 * @param <U> Input class type parameter
 * @param <V> Output class type parameter
 * @author Kirill Biliashov
 */

public interface Mapper<U, V> {

  V map(U inpType) throws MappingException;

}
