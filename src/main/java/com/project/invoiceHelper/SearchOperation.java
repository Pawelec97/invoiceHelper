package com.project.invoiceHelper;

import com.project.invoiceHelper.entities.Invoice;
import java.time.LocalDate;
import javax.management.BadAttributeValueExpException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public enum SearchOperation {
	GREATER_THAN,
	LESS_THAN,
	GREATER_THAN_EQUAL,
	GREATER_THAN_EQUAL_LOCALDATE,
	LESS_THAN_EQUAL,
	LESS_THAN_EQUAL_LOCALDATE,
	NOT_EQUAL,
	EQUAL;

	public Predicate getPredicate(Root<Invoice> root, CriteriaBuilder builder,
			SearchCriteria criteria) {

		switch (this) {
			case GREATER_THAN: {
				return builder.greaterThan(
						root.get(criteria.getKey()), criteria.getValue().toString());
			}
			case LESS_THAN: {
				return builder.lessThan(
						root.get(criteria.getKey()), criteria.getValue().toString());
			}
			case GREATER_THAN_EQUAL: {
				return builder.greaterThanOrEqualTo(
						root.get(criteria.getKey()), criteria.getValue().toString());
			}
			case LESS_THAN_EQUAL: {
				return builder.lessThanOrEqualTo(
						root.get(criteria.getKey()), criteria.getValue().toString());
			}
			case EQUAL: {
				return builder.equal(
						root.get(criteria.getKey()), criteria.getValue());
			}
			case NOT_EQUAL: {
				return builder.notEqual(
						root.get(criteria.getKey()), criteria.getValue());
			}
			case LESS_THAN_EQUAL_LOCALDATE: {
				return builder.lessThanOrEqualTo(
						root.get(criteria.getKey()), (LocalDate) criteria.getValue());
			}
			case GREATER_THAN_EQUAL_LOCALDATE: {
				return builder.greaterThanOrEqualTo(
						root.get(criteria.getKey()), (LocalDate) criteria.getValue());
			}
			default: {
				return null;
			}
		}

	}
}
