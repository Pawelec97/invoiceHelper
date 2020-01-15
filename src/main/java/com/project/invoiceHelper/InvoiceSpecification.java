package com.project.invoiceHelper;


import com.project.invoiceHelper.entities.Invoice;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InvoiceSpecification implements Specification<Invoice> {

	private List<SearchCriteria> criterias;

	public InvoiceSpecification() {
		this.criterias = new ArrayList<>();
	}

	public void add(SearchCriteria criteria) {
		this.criterias.add(criteria);
	}

	@Override
	public Predicate toPredicate(Root<Invoice> root, CriteriaQuery<?> query,
			CriteriaBuilder builder) {

		List<Predicate> predicates = new ArrayList<>();

		for (SearchCriteria criteria : criterias) {
			if (criteria.getOperation().equals(SearchOperation.GREATER_THAN)) {
				predicates.add(builder.greaterThan(
						root.get(criteria.getKey()), criteria.getValue().toString()));
			} else if (criteria.getOperation().equals(SearchOperation.LESS_THAN)) {
				predicates.add(builder.lessThan(
						root.get(criteria.getKey()), criteria.getValue().toString()));
			} else if (criteria.getOperation().equals(SearchOperation.GREATER_THAN_EQUAL)) {
				predicates.add(builder.greaterThanOrEqualTo(
						root.get(criteria.getKey()), criteria.getValue().toString()));
			} else if (criteria.getOperation().equals(SearchOperation.GREATER_THAN_EQUAL_LOCALDATE)) {
				predicates.add(builder.greaterThanOrEqualTo(
						root.get(criteria.getKey()), (LocalDate) criteria.getValue()));
			} else if (criteria.getOperation().equals(SearchOperation.LESS_THAN_EQUAL)) {
				predicates.add(builder.lessThanOrEqualTo(
						root.get(criteria.getKey()), criteria.getValue().toString()));
			} else if (criteria.getOperation().equals(SearchOperation.LESS_THAN_EQUAL_LOCALDATE)) {
				predicates.add(builder.lessThanOrEqualTo(
						root.get(criteria.getKey()), (LocalDate) criteria.getValue()));
			} else if (criteria.getOperation().equals(SearchOperation.NOT_EQUAL)) {
				predicates.add(builder.notEqual(
						root.get(criteria.getKey()), criteria.getValue()));
			} else if (criteria.getOperation().equals(SearchOperation.EQUAL)) {
				predicates.add(builder.equal(
						root.get(criteria.getKey()), criteria.getValue()));
			} else if (criteria.getOperation().equals(SearchOperation.BETWEEN)) {
				predicates.add(builder.between(
						root.get(criteria.getKey()), (LocalDate) ((List) criteria.getValue()).get(0),
						(LocalDate) ((List) criteria.getValue()).get(1)));
			}
		}

		return builder.and(predicates.toArray(new Predicate[0]));

	}
}
