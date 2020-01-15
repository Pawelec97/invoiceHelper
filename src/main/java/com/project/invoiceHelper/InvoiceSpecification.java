package com.project.invoiceHelper;


import com.project.invoiceHelper.entities.Invoice;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class InvoiceSpecification implements Specification<Invoice> {

	private List<SearchCriteria> criterias = new ArrayList<>();

	public InvoiceSpecification() {
	}

	public void add(SearchCriteria criteria) {
		this.criterias.add(criteria);
	}

	@Override
	public Predicate toPredicate(Root<Invoice> root, CriteriaQuery<?> query,
			CriteriaBuilder builder) {

		List<Predicate> predicates = new ArrayList<>();

		for (SearchCriteria criteria : criterias) {
			try {
				predicates.add(criteria.getOperation().getPredicate(root, builder, criteria));
			}catch(NullPointerException e){
				System.out.println("bad Search Operation");
			}
		}

		return builder.and(predicates.toArray(new Predicate[0])); //https://stackoverflow.com/questions/48842145/how-to-add-a-list-of-predicates-to-criteriabuilder-or

	}
}
