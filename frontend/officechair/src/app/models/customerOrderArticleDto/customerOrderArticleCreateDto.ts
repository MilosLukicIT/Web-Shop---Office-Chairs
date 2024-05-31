import { ArticleViewDto } from "../articleDto/articleViewDto";
import { CustomerOrderViewDto } from "../customerOrderDto/customerOrderViewDto";

export class CustomerOrderArticleCreateDto {

	amountOfArticle!: number;
	
	customerOrderArticle!: CustomerOrderViewDto;
	
	article!: ArticleViewDto;
}