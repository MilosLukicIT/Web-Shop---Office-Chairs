import { ArticleTypeViewDto } from "../artcleTypeDto/articleTypeViewDto";
import { ArticleBrandViewDto } from "../articleBrandDto/articleBrandViewDto";

export class ArticleViewDto {
    articleId: string;
	nameOfArticle!: string;
	priceOfArticle: number;
    manufacturerOfArticle!: string;
	carryingCapacity!: number;
	colorOfArticle!: string;
	availableAmountOfArticle!: number;
	warrantyLength!: string;
	heightOfArticle!: number;
	widthOfArticle!: number;
	lengthOfArticle!: number;
	descriptionOfArticle!: string;
	discount!: number;
	imageUrl!: string;
	
	articleBrand!: ArticleBrandViewDto;
	articleType!: ArticleTypeViewDto;

	constructor() {
        this.articleId = "";
		this.priceOfArticle= 0;
        
    }
}