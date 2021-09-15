package com.org.tomtom.e_commerce.util.unique_sequence_generator;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(AbstractRandomLongIdEntity.class)
public class AbstractRandomLongIdEntity_ {

	public static volatile SingularAttribute<AbstractRandomLongIdEntity, Long> id;

	public static final String ID = "id";
}
