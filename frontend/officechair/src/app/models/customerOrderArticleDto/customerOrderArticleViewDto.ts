import { ArticleViewDto } from "../articleDto/articleViewDto";
import { CustomerOrderViewDto } from "../customerOrderDto/customerOrderViewDto";

export class CustomerOrderArticleViewDto {

    orderArticleId: String;
	amountOfArticle: number;
	
	customerOrderArticle: CustomerOrderViewDto;
	
	article: ArticleViewDto;

	constructor() {
        this.orderArticleId = "";
        this.amountOfArticle = 0;
        this.customerOrderArticle = new CustomerOrderViewDto;
        this.article = new ArticleViewDto;
        
    }

}