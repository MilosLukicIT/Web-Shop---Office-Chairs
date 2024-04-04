package com.webshop.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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
public class ArticleType {

	
	private String typeId;
	private String nameOfType;
	
	@JsonIgnore
	@OneToMany(mappedBy = "articleType", cascade = CascadeType.REMOVE)
	List<Article> articles;

}
