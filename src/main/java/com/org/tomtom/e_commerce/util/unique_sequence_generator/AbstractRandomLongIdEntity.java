package com.org.tomtom.e_commerce.util.unique_sequence_generator;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

@MappedSuperclass
public class AbstractRandomLongIdEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The randomized ID should not exceed this value.
	 *
	 * @see <a href=
	 *      "https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Number/MAX_SAFE_INTEGER">MAX_SAFE_INTEGER</a>
	 */
	private static final Long MAX_SAFE_NUMBER = (long) Math.pow(2, 53) - 1;

	@Id
	@Column(name = "id", columnDefinition = "BIGINT", nullable = false, updatable = false)
	protected Long id;

	@PrePersist
	protected void onPrePersist() {
		this.id = ThreadLocalRandom.current().nextLong(this.MAX_SAFE_NUMBER);
	}

	public Long getId() {
		return id;
	}
}
