import { ArticleTypeViewDto } from "../artcleTypeDto/articleTypeViewDto";
import { ArticleBrandViewDto } from "../articleBrandDto/articleBrandViewDto";

export class ArticleViewDto {
    articleId: String;
	nameOfArticle!: String;
	priceOfArticle: number;
    manufacturerOfArticle!: String;
	carryingCapacity!: Number;
	colorOfArticle!: String;
	availableAmountOfArticle!: Number;
	warrantyLength!: String;
	heightOfArticle!: Number;
	widthOfArticle!: Number;
	lengthOfArticle!: Number;
	descriptionOfArticle!: String;
	discount!: Number;
	imageUrl!: String;
	
	articleBrand!: ArticleBrandViewDto;
	articleType!: ArticleTypeViewDto;

	constructor() {
        this.articleId = "";
		this.priceOfArticle= 0;
        
    }
}