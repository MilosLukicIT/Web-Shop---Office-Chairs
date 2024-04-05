package com.webshop.model;

import java.util.List;

import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ArticleBrand {


	@Id
	@UuidGenerator
	private String brandId;
	@Column(length = 20, nullable = false, unique = false)
	private String nameOfBrand;
	@Column(length = 20, nullable = false, unique = false)
	private String countryOfBrand;
	
	@JsonIgnore
	@OneToMany(mappedBy = "articleBrand", cascade = CascadeType.REMOVE)
	List<Article> articles;

}
