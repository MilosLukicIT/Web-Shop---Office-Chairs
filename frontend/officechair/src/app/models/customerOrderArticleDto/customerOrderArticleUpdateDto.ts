import { ArticleViewDto } from "../articleDto/articleViewDto";
import { CustomerOrderViewDto } from "../customerOrderDto/customerOrderViewDto";

export class CustomerOrderArticleUpdateDto {

    orderArticleId!: String;
	amountOfArticle!: number;
	
	customerOrderArticle!: CustomerOrderViewDto;
	
	article!: ArticleViewDto;

}